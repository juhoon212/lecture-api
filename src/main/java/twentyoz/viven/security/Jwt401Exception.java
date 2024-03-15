package twentyoz.viven.security;

/** 401 오류 */
public class Jwt401Exception extends JwtException {
  public Jwt401Exception() {
    super();
  }

  public Jwt401Exception(String message) {
    super(message);
  }

  public Jwt401Exception(String message, Throwable cause) {
    super(message, cause);
  }

  public Jwt401Exception(Throwable cause) {
    super(cause);
  }

  @Override
  public int getHttpStatus() {
    return 401;
  }
}
