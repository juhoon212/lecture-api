package twentyoz.viven.feature.common.model;

import java.io.Serializable;
import java.util.UUID;
import org.joda.time.DateTime;

/** DB COLUMNS : REG_ID, REG_DT */
public interface Reg extends Serializable {

  /** 등록자식별번호 */
  UUID getRegId();

  /** 등록일시 */
  DateTime getRegDt();
}
