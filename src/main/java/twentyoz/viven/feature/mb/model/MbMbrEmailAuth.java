package twentyoz.viven.feature.mb.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegEntity;
import lombok.*;
import org.joda.time.DateTime;

/** 이메일 인증 */
@Entity
@Table(schema = "mb", name = "mb_mbr_email_auth")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MbMbrEmailAuth extends RegEntity implements Identifiable<UUID> {

  /** 회원이메일인증식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "mbr_email_auth_id", nullable = false)
  private UUID mbrEmailAuthId;

  /** 회원식별번호 */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /** 인증값 */
  @Column(name = "auth_val")
  private String authVal;

  /** 인증종료일시 */
  @Column(name = "auth_end_dt")
  private DateTime authEndDt;

  /** 인증완료여부 */
  @Column(name = "auth_compl_yn")
  private String authComplYn;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return mbrEmailAuthId;
  }

  public static MbMbrEmailAuth initSave(UUID mbrId, String authVal) {
    return new MbMbrEmailAuth()
        .builder()
        .mbrId(mbrId)
        .authVal(authVal)
        .authComplYn("N")
        .authEndDt(new DateTime().plusMinutes(10))
        .build();
  }
}
