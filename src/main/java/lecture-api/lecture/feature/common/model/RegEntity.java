package twentyoz.viven.feature.common.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class RegEntity implements Reg {

  /** 등록자식별번호 */
  @CreatedBy
  @Column(name = "reg_id", updatable = false)
  private UUID regId;

  /** 등록일시 */
  @CreatedDate
  @Column(name = "reg_dt", nullable = false, updatable = false)
  private DateTime regDt;

  /** 엔티티 기본 값 설정 */
  public void defaultValue() {}
}
