package twentyoz.viven.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** JWT 검증 인터셉터 */
@Slf4j
@RequiredArgsConstructor
public class JwtVerifyInterceptor implements HandlerInterceptor {

  private static final List<String> KEYS = Collections.singletonList("Bearer");

  private final ObjectMapper mapper;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if ("OPTIONS".equals(request.getMethod())) {
      return true;
    }

    try {
      String token = parseToken(request);
      JwtPayload payload = JwtHelper.verify(token);

      JwtPayloadHolder.set(payload);

      return true;
    } catch (JwtException e) {
      writeError(response, e);

      return false;
    }
  }

  @Override
  public void postHandle(
      HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView m)
      throws Exception {}

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
      throws Exception {
    JwtPayloadHolder.clear();
  }

  private String parseToken(HttpServletRequest request) {
    String header = request.getHeader("authorization");
    if (header == null) {
      throw new Jwt401Exception("Token required");
    }

    String[] keyVal = header.split(" ");
    if (keyVal.length != 2) {
      throw new Jwt401Exception("Token required");
    }

    if (!KEYS.contains(keyVal[0])) {
      throw new Jwt401Exception("Token required");
    }

    return keyVal[1];
  }

  private void writeError(HttpServletResponse response, JwtException e) {
    log.debug("[AuthFailed][{}] {}", e.getClass().getSimpleName(), e.getMessage());

    final int status = e.getHttpStatus();

    response.setStatus(status);
    response.setContentType("application/json");
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    // JSON 쓰기 실패시에도 401/403 응답 내리기 위해 오류처리함
    try {
      Map<String, String> body = new LinkedHashMap<>();
      body.put("retcode", String.valueOf(status));
      body.put("retmsg", e.getMessage());

      response.getWriter().write(mapper.writeValueAsString(body));
    } catch (Exception ex) {
      log.warn("{}", ex.getMessage(), ex);
    }
  }
}
