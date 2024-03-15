package twentyoz.viven.feature.mb.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegEntity;
import twentyoz.viven.util.RequestUtils;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;

/** 회원로그인 이력 */
@Entity
@Table(schema = "mb", name = "mb_mbr_login_hist")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MbMbrLoginHist extends RegEntity implements Identifiable<UUID> {

  /** 회원로그인이력식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "mbr_login_hist_id", nullable = false)
  private UUID mbrLoginHistId;

  /** 회원식별번호 */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /** 로그인 유형 코드 Code Group: MB_004 */
  @Column(name = "login_type_code")
  private String loginTypeCode;

  /** 아이피주소 */
  @Column(name = "ip_addr")
  private String ipAddr;

  /** 사용자에이전트값 */
  @Column(name = "ua_val")
  private String uaVal;

  /** 로그인일시 */
  @Column(name = "login_dt")
  private DateTime loginDt;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return mbrLoginHistId;
  }

  public void setId(UUID mbrLoginHistId) {
    this.mbrLoginHistId = mbrLoginHistId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  /** 엔티티 기본 값 설정 */
  @Override
  public void defaultValue() {
    super.defaultValue();

    ipAddr =
        ObjectUtils.defaultIfNull(ipAddr, RequestUtils.getCurrentIP(RequestUtils.getRequest()));
    uaVal = ObjectUtils.defaultIfNull(uaVal, RequestUtils.getUserAgent(RequestUtils.getRequest()));
  }
}
