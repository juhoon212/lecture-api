package twentyoz.viven.security;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BcryptHelper {

  private BcryptHelper() {}

  public static String hash(String raw) {
    return BCrypt.withDefaults().hashToString(10, raw.toCharArray());
  }

  public static boolean compare(String hashed, String raw) {
    return BCrypt.verifyer().verify(raw.toCharArray(), hashed).verified;
  }
}
