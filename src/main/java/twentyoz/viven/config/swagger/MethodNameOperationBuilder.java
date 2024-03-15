package twentyoz.viven.config.swagger;

import com.google.common.base.Optional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.core.annotation.Order;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

/** Swagger UI OperationId 생성빌더 */
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
public class MethodNameOperationBuilder implements OperationBuilderPlugin {

  @Override
  public void apply(OperationContext context) {
    String operationId = findName(context) + findGroupName(context);

    context.operationBuilder().uniqueId(operationId);
    context.operationBuilder().codegenMethodNameStem(operationId);
  }

  @Override
  public boolean supports(DocumentationType delimiter) {
    return SwaggerPluginSupport.pluginDoesApply(delimiter);
  }

  private String findGroupName(OperationContext context) {
    // Controller @Api
    // noinspection Guava
    Optional<Api> optional = context.findControllerAnnotation(Api.class);
    if (optional.isPresent()) {
      Api annotation = optional.get();
      if (StringUtils.isNotBlank(annotation.value())) {
        return annotation.value();
      }
    }

    // Or PascalCase GroupName
    String out = StringUtils.replace(context.getGroupName(), "-", " ");
    out = WordUtils.capitalizeFully(out);
    out = StringUtils.replace(out, " ", "");

    return out;
  }

  private String findName(OperationContext context) {
    // Method @ApiOperation
    // noinspection Guava
    Optional<ApiOperation> optional = context.findAnnotation(ApiOperation.class);
    if (optional.isPresent()) {
      ApiOperation annotation = optional.get();
      if (StringUtils.isNotBlank(annotation.nickname())) {
        return annotation.nickname();
      }
    }

    return context.getName();
  }
}
