package twentyoz.viven.security;

public class JwtPayloadHolder {

  private static final ThreadLocal<JwtPayload> payload = new ThreadLocal<>();

  private JwtPayloadHolder() {}

  public static JwtPayload get() {
    return payload.get();
  }

  public static void set(JwtPayload o) {
    payload.set(o);
  }

  public static void clear() {
    payload.remove();
  }
}
