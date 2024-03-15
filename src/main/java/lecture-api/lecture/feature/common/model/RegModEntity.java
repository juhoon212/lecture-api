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
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class RegModEntity implements Reg, Mod {

  /** 등록자식별번호 */
  @CreatedBy
  @Column(name = "reg_id", updatable = false)
  private UUID regId;

  /** 등록일시 */
  @CreatedDate
  @Column(name = "reg_dt", nullable = false, updatable = false)
  private DateTime regDt;

  /** 수정자식별번호 */
  @LastModifiedBy
  @Column(name = "mod_id")
  private UUID modId;

  /** 수정일시 */
  @LastModifiedDate
  @Column(name = "mod_dt")
  private DateTime modDt;

  /** 엔티티 기본 값 설정 */
  public void defaultValue() {}
}
