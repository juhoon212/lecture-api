package twentyoz.viven.feature.common.model;

import java.io.Serializable;

/** DB COLUMNS : USE_YN */
public interface UseYn extends Serializable {

  /** 사용여부 */
  String getUseYn();

  /** 사용여부 : 사용 설정 */
  void useTrue();

  /** 사용여부 : 미사용 설정 */
  void useFalse();
}
