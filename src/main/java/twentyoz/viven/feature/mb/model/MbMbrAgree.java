package twentyoz.viven.feature.mb.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegModEntity;
import lombok.*;

/** 회원동의 */
@Entity
@Table(schema = "mb", name = "mb_mbr_agree")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MbMbrAgree extends RegModEntity implements Identifiable<UUID> {

  /** 회원동의식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "mbr_agree_id", nullable = false)
  private UUID mbrAgreeId;

  /** 회원식별번호 */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /** Code Group: MB_003 */
  @Column(name = "mbr_agree_type_code")
  private String mbrAgreeTypeCode;

  /** 동의여부 */
  @Column(name = "agree_yn")
  private String agreeYn;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return mbrAgreeId;
  }

  /**
   * 회원 등록 시 회원약관동의 저장
   *
   * @param mbrId
   * @param mbrAgreeTypeCode
   * @return
   */
  public static MbMbrAgree of(UUID mbrId, String mbrAgreeTypeCode) {
    return new MbMbrAgree()
        .builder()
        .mbrId(mbrId)
        .mbrAgreeTypeCode(mbrAgreeTypeCode)
        // todo 해당부분에서 agreeYn은 추후 화면에서 cheackbox cheack시 데이터 저장하는 것으로 수정 필요
        .agreeYn("Y")
        .build();
  }
}
