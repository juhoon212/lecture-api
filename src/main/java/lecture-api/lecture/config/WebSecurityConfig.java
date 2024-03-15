package twentyoz.viven.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import twentyoz.viven.feature.common.security.UserInfoInterceptor;
import twentyoz.viven.feature.mb.service.MbrService;
import twentyoz.viven.security.JwtVerifyInterceptor;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {

  private final ObjectMapper mapper;

  private final MbrService mbrService;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    final String[] excludePathPatterns = {
      // webjars
      "/webjars/**",
      // Swagger UI
      "/swagger-ui.html",
      "/swagger-resources/**",
      // 로그인 API
      "/login/get-token",
      // 아이디/비밀번호 찾기 API
      "/mb/mbr-email-auth/**",
      // 이메일 중복검사 API
      "/mb/mbr/get-email/**",
      // 닉네임 중복검사 API
      "/mb/mbr/get-nickname/**",
      // 로그인아이디 중복검사 API
      "/mb/mbr/get-loginId/**",
      // 회원등록 API
      "/mb/mbr/addMember",
    };

    registry
        .addInterceptor(jwtVerifyInterceptor())
        .order(Ordered.HIGHEST_PRECEDENCE)
        .excludePathPatterns(excludePathPatterns);

    registry
        .addInterceptor(userInfoInterceptor())
        .order(Ordered.HIGHEST_PRECEDENCE + 1)
        .excludePathPatterns(excludePathPatterns);
  }

  @Bean
  public UserInfoInterceptor userInfoInterceptor() {
    return new UserInfoInterceptor(mbrService);
  }

  @Bean
  public JwtVerifyInterceptor jwtVerifyInterceptor() {
    return new JwtVerifyInterceptor(mapper);
  }
}
