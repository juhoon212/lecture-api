package twentyoz.viven.config.swagger;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.spi.schema.contexts.ModelContext.inputParam;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Function;
import com.google.common.collect.Sets;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.schema.ResolvedTypes;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.contexts.ModelContext;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.ParameterContext;

@Order(Ordered.LOWEST_PRECEDENCE)
public class OperationPageableParameterReader implements OperationBuilderPlugin {

  private final TypeNameExtractor nameExtractor;
  private final ResolvedType pageableType;
  private final TypeResolver resolver;

  public OperationPageableParameterReader(TypeNameExtractor nameExtractor, TypeResolver resolver) {
    this.nameExtractor = nameExtractor;
    this.resolver = resolver;
    this.pageableType = resolver.resolve(Pageable.class);
  }

  @Override
  @SneakyThrows
  public void apply(OperationContext context) {
    List<ResolvedMethodParameter> methodParameters = context.getParameters();
    List<Parameter> parameters = newArrayList();

    for (ResolvedMethodParameter methodParameter : methodParameters) {
      ResolvedType resolvedType = methodParameter.getParameterType();
      if (!pageableType.equals(resolvedType)) {
        continue;
      }

      ParameterContext parameterContext =
          new ParameterContext(
              methodParameter,
              new ParameterBuilder(),
              context.getDocumentationContext(),
              context.getGenericsNamingStrategy(),
              context);
      Function<ResolvedType, ? extends ModelReference> factory =
          createModelRefFactory(parameterContext);

      ModelReference intModel = factory.apply(resolver.resolve(Integer.TYPE));
      ModelReference stringModel = factory.apply(resolver.resolve(String.class));
      ModelReference listStringModel = factory.apply(resolver.resolve(List.class, String.class));

      parameters.add(
          new ParameterBuilder()
              .parameterType("query")
              .name("page")
              .modelRef(intModel)
              .description("페이지 인덱스 (0..N)")
              .defaultValue("0")
              .build());
      parameters.add(
          new ParameterBuilder()
              .parameterType("query")
              .name("size")
              .modelRef(intModel)
              .description("페이지당 보여질 행의 개수")
              .defaultValue("20")
              .build());
      parameters.add(
          new ParameterBuilder()
              .parameterType("query")
              .name("sort")
              .modelRef(listStringModel)
              .allowMultiple(true)
              .description("페이지 정렬 [포멧: 변수명,(asc|desc), 예): name,asc&id,desc]")
              .build());

      final OperationBuilder operationBuilder = context.operationBuilder();
      operationBuilder.parameters(parameters);

      Set<String> toDelete =
          Sets.newLinkedHashSet(
              Arrays.asList(
                  "offset",
                  "pageNumber",
                  "pageSize",
                  "paged",
                  "sort.sorted",
                  "sort.unsorted",
                  "unpaged"));
      final Field field = operationBuilder.getClass().getDeclaredField("parameters");
      field.setAccessible(true);
      @SuppressWarnings("unchecked")
      final List<Parameter> list = (List<Parameter>) field.get(operationBuilder);
      field.set(
          operationBuilder,
          list.stream().filter(p -> !toDelete.contains(p.getName())).collect(Collectors.toList()));
    }
  }

  private Function<ResolvedType, ? extends ModelReference> createModelRefFactory(
      ParameterContext context) {
    ModelContext modelContext =
        inputParam(
            context.getGroupName(),
            context.resolvedMethodParameter().getParameterType(),
            context.getDocumentationType(),
            context.getAlternateTypeProvider(),
            context.getGenericNamingStrategy(),
            context.getIgnorableParameterTypes());
    return ResolvedTypes.modelRefFactory(modelContext, nameExtractor);
  }

  @Override
  public boolean supports(DocumentationType delimiter) {
    return true;
  }
}
