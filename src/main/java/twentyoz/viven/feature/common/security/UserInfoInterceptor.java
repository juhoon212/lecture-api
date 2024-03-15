package twentyoz.viven.feature.common.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twentyoz.viven.feature.mb.model.MbMbr;
import twentyoz.viven.feature.mb.service.MbrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import twentyoz.viven.security.JwtPayload;
import twentyoz.viven.security.JwtPayloadHolder;

/**
 * UserInfo 인터셉터
 *
 * <pre>
 * JWT 토큰 기반으로 기존 UserInfo 호환되도록 구현
 * </pre>
 */
@RequiredArgsConstructor
public class UserInfoInterceptor implements HandlerInterceptor {

  private final MbrService mbrService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    JwtPayload payload = JwtPayloadHolder.get();
    if (payload != null) {
      MbMbr user = mbrService.getByUserLoginId(payload.getLoginId());

      UserInfo.setUser(user);
    }

    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView m)
      throws Exception {}

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
      throws Exception {
    UserInfo.clearUser();
  }
}
