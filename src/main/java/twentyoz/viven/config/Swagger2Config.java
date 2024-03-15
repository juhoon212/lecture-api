package twentyoz.viven.config;

import static java.util.Collections.singletonList;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import twentyoz.viven.config.swagger.DefaultTypeNameProviderFixer;
import twentyoz.viven.config.swagger.MethodNameOperationBuilder;
import twentyoz.viven.config.swagger.OperationPageableParameterReader;

@EnableSwagger2
@Configuration
@RequiredArgsConstructor
public class Swagger2Config {

  private final Environment environment;

  /** 앱 정보 생성 */
  private ApiInfo createApiInfo() {
    String appRev = environment.getProperty("git.build.version", "unknown");
    String gitRev = environment.getProperty("git.commit.id.abbrev", "unknown");
    String version = String.format("%s (%s)", appRev, gitRev);

    return new ApiInfoBuilder()
        .title(environment.getProperty("app.name"))
        .description(environment.getProperty("app.description"))
        .version(version)
        .build();
  }

  @Bean
  public Docket appDocket() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2);
    docket.useDefaultResponseMessages(false);
    docket.apiInfo(createApiInfo());

    // API 그룹 정보
    docket.groupName("0-APP");

    // API TAG 정보
    int tagOrd = 0;
    docket.tags(
            // SY
            new Tag("Login", "로그인 API", ++tagOrd),
            new Tag("Mbr", "회원 API", ++tagOrd),
            new Tag("MbrLoginHist", "회원로그인이력API", ++tagOrd),
            new Tag("MbrEmailAuth", "회원이메일인증API", ++tagOrd),
            new Tag("Ctt", "콘텐츠 API", ++tagOrd),
            new Tag("Ref", "자료", ++tagOrd),
            new Tag("ExamQuest", "시험문제", ++tagOrd),
            new Tag("ExamItem", "시험지문제", ++tagOrd),
            new Tag("Exam", "시험지", ++tagOrd),
            new Tag("LectureSchedule", "강의수업", ++tagOrd),
            new Tag("Lecture", "강의", ++tagOrd),
            new Tag("LectureExam", "강의시험", ++tagOrd),
            new Tag("LectureQuest", "강의시험문제", ++tagOrd),
            new Tag("LectureQuestResult", "강의시험문제결과", ++tagOrd),
            new Tag("LectureExamResult", "강의시험지결과", ++tagOrd),
            new Tag("LectureRef", "강의자료실", ++tagOrd),
            new Tag("DtsQuest", "더미데이터", ++tagOrd)
    );



    // API 범위
    docket.select()
        .apis(RequestHandlerSelectors.basePackage("twentyoz.viven.webapi.client")) // 수정 필요
        .build();

    // API 인증 정보
    // - 인증 적용 범위
    ApiKey jwtSecurity = new ApiKey("JWT-Bearer", "Authorization", "header");
    docket.securitySchemes(Arrays.asList(jwtSecurity));

    SecurityContext oauthSecurityContext =
        SecurityContext.builder()
            .securityReferences(
                Arrays.asList(
                    // jwtSecurityScheme
                    SecurityReference.builder()
                        .reference(jwtSecurity.getName())
                        .scopes(
                            new AuthorizationScope[] {
                              new AuthorizationScope("global", "accessEverything")
                            })
                        .build()))
            .forPaths(Predicates.not(PathSelectors.ant("/login/get-token")))
            .build();

    docket.securityContexts(singletonList(oauthSecurityContext));

    return docket;
  }

  @Bean
  public Docket dtsDocket() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2);
    docket.useDefaultResponseMessages(false);
    docket.apiInfo(createApiInfo());

    // API 그룹 정보
    docket.groupName("1-DTS");

    // API TAG 정보
    int tagOrd = 0;
    docket.tags(
        // SY
        new Tag("DtsRoomProp", "방 속성 API", ++tagOrd)
//        new Tag("DtsQuest", "더미데이터", ++tagOrd)
    );

    // API 범위
    docket.select()
        .apis(RequestHandlerSelectors.basePackage("twentyoz.viven.webapi.dts")) // 수정 필요
        .build();

    return docket;
  }

  /** Swagger UI 설정 */
  @Bean
  public UiConfiguration uiConfiguration() {
    return UiConfigurationBuilder.builder()
        .deepLinking(false)
        .displayOperationId(false)
        .defaultModelsExpandDepth(0)
        .defaultModelExpandDepth(5)
        .defaultModelRendering(ModelRendering.MODEL)
        .displayRequestDuration(false)
        .docExpansion(DocExpansion.LIST)
        .filter(false)
        .maxDisplayedTags(null)
        .operationsSorter(OperationsSorter.ALPHA)
        .showExtensions(false)
        .tagsSorter(TagsSorter.ALPHA)
        .validatorUrl(null)
        .build();
  }

  @Bean
  public DefaultTypeNameProviderFixer defaultTypeNameProviderFixer() {
    return new DefaultTypeNameProviderFixer();
  }

  @Bean
  public MethodNameOperationBuilder methodNameOperationBuilder() {
    return new MethodNameOperationBuilder();
  }

  @Bean
  public OperationPageableParameterReader operationPageableParameterReader(
      TypeNameExtractor nameExtractor, TypeResolver resolver) {
    return new OperationPageableParameterReader(nameExtractor, resolver);
  }
}
