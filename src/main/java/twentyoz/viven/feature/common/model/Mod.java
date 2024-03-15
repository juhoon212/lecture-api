package twentyoz.viven.feature.common.model;

import java.io.Serializable;
import java.util.UUID;
import org.joda.time.DateTime;

/** DB COLUMNS : MOD_ID, MOD_DT */
public interface Mod extends Serializable {

  /** 수정자식별번호 */
  UUID getModId();

  /** 수정일시 */
  DateTime getModDt();
}
