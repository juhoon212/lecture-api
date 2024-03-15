package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegEntity;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

/** 알람 */
@Entity
@Table(schema = "vi", name = "vi_alarm")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViAlarm extends RegEntity implements Identifiable<UUID> {

  /** 알람식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "alarm_id", nullable = false)
  private UUID alarmId;

  /** 알람유형코드 */
  @Column(name = "alarm_type_code")
  private String alarmTypeCode;

  /** 회원식별번호 */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /** 조회여부 */
  @Column(name = "read_yn")
  private String readYn;

  /** 알람속성내용 */
  @Column(name = "alarm_prop_cont")
  private String alarmPropCont;

  /** 알람명 */
  @Column(name = "alarm_name")
  private String alarmName;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return alarmId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  @Override
  public void defaultValue() {
    super.defaultValue();
    readYn = ObjectUtils.defaultIfNull(readYn, "N");
  }
}
