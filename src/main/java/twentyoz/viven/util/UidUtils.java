package twentyoz.viven.util;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class UidUtils {

  /** ID PREFIX */
  public static final String ID_PREFIX = "_";

  /** PREFIX 0 개수 */
  public static final int ID_PREFIX_ZEROFILL = 4;

  /**
   * PK로 식별번호 생성
   *
   * @param prefix
   * @param zerofill
   * @param pk
   * @return
   */
  public static String getUniqueId(String prefix, int zerofill, String pk) {
    return StringUtils.join(
        prefix, ID_PREFIX, StringUtils.leftPad(String.valueOf(pk), zerofill, "0"));
  }

  /**
   * 범용 고유 식별자 uuid v4
   *
   * @return String
   */
  public static UUID getUuid() {

    try {
      return UUID.randomUUID();
    } catch (Exception e) {
      throw new IllegalStateException("범용 고유 식별자 생성중 문제가 발생하였습니다.");
    }
  }
}
