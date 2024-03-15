package twentyoz.viven.feature.mb.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegModEntity;
import lombok.*;
import org.joda.time.DateTime;

/** 회원상태정보 */
@Entity
@Table(schema = "mb", name = "mb_mbr_status_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MbMbrStatusInfo extends RegModEntity implements Identifiable<UUID> {

  /** 회원상태정보식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "mbr_status_info_id", nullable = false)
  private UUID mbrStatusInfoId;

  /** 회원식별번호 */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /** Code Group: MB_005 */
  @Column(name = "pause_reason_type_code")
  private String pauseReasonTypeCode;

  /** Code Group: MB_007 */
  @Column(name = "secession_reason_type_code")
  private String secessionReasonTypeCode;

  /** 상태변경사유 */
  @Column(name = "status_chg_reason")
  private String statusChgReason;

  /** 상태변경시작일시 */
  @Column(name = "status_chg_start_dt")
  private DateTime statusChgStartDt;

  /** 상태변경종료일시 */
  @Column(name = "status_chg_end_dt")
  private DateTime statusChgEndDt;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return mbrStatusInfoId;
  }

  public static MbMbrStatusInfo of(
      UUID mbrId, String secessionReasonTypeCode, String statusChgReason, DateTime statusChgEndDt) {
    return new MbMbrStatusInfo()
        .builder()
        .mbrId(mbrId)
        .secessionReasonTypeCode(secessionReasonTypeCode)
        .statusChgReason(statusChgReason)
        .statusChgStartDt(new DateTime())
        .statusChgEndDt(statusChgEndDt)
        .build();
  }
}
