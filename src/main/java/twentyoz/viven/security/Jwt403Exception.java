package twentyoz.viven.security;

/** 403 오류 */
public class Jwt403Exception extends JwtException {
  public Jwt403Exception() {
    super();
  }

  public Jwt403Exception(String message) {
    super(message);
  }

  public Jwt403Exception(String message, Throwable cause) {
    super(message, cause);
  }

  public Jwt403Exception(Throwable cause) {
    super(cause);
  }

  @Override
  public int getHttpStatus() {
    return 403;
  }
}
