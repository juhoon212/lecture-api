package twentyoz.viven.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtHelper {

  private JwtHelper() {}

  /** 암호화키 */
  private static final String SECRET = "potc7bmlk3ko7aaz3xmzwso73g13fq407xomwl7d";

  /** 알고리듬 */
  private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

  public static String sign(JwtPayload payload) {
    return JWT.create().withPayload(payload.asMap()).sign(ALGORITHM);
  }

  public static JwtPayload verify(String token) {
    if (token == null) {
      throw new Jwt401Exception("Token required");
    }

    try {
      DecodedJWT jwt = JWT.decode(token);
      //      if (jwt.getExpiresAt().before(new Date())) {
      //        throw new Jwt401Exception("Token is expired");
      //      }

      return new JwtPayload(jwt.getClaims());
    } catch (JwtException e) {
      throw e;
    } catch (Exception e) {
      log.debug("JWT 검증실패 (Token: {})", token, e);

      throw new Jwt401Exception("Invalid Token");
    }
  }
}
