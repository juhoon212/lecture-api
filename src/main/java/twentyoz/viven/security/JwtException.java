package twentyoz.viven.security;

/** JWT Exception */
public abstract class JwtException extends RuntimeException {

  public JwtException() {}

  public JwtException(String message) {
    super(message);
  }

  public JwtException(String message, Throwable cause) {
    super(message, cause);
  }

  public JwtException(Throwable cause) {
    super(cause);
  }

  public abstract int getHttpStatus();
}
