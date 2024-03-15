package twentyoz.viven.feature.common.security;

import java.util.UUID;
import twentyoz.viven.feature.mb.model.MbMbr;

public class UserInfo {

  private static final ThreadLocal<MbMbr> CONTEXT = new ThreadLocal<>();

  private UserInfo() {}

  /** 사용자정보 조회 */
  public static MbMbr getUser() {
    return CONTEXT.get();
  }

  /** 사용자정보 설정 */
  public static void setUser(MbMbr user) {
    CONTEXT.set(user);
  }

  /** 사용자정보 초기화 */
  public static void clearUser() {
    CONTEXT.remove();
  }

  /** 로그인 여부 */
  public static boolean isLogin() {
    return getUser() != null;
  }

  /** 사용자 식별번호 */
  public static UUID getUserId() {
    MbMbr user = getUser();

    return user == null ? null : user.getId();
  }

  /** 사용자상태코드 */
  public static String getUserStatusCode() {
    MbMbr user = getUser();

    return user == null ? null : user.getMbrStatusCode();
  }

  /** 사용자 로그인아이디 */
  public static String getLoginId() {
    MbMbr user = getUser();

    return user == null ? null : user.getLoginId();
  }

  /** 사용자명 */
  public static String getUserName() {
    MbMbr user = getUser();

    return user == null ? null : user.getMbrName();
  }

  /** 사용자유형코드 */
  public static String getUserTypeCode() {
    MbMbr user = getUser();

    return user == null ? null : user.getMbrTypeCode();
  }

  /** 사용자 이메일 */
  public static String getUserEmail() {
    MbMbr user = getUser();

    return user == null ? null : user.getEmail();
  }

  /** 사용자 닉네임 */
  public static String getNickName() {
    MbMbr user = getUser();

    return user == null ? null : user.getNickname();
  }
}
