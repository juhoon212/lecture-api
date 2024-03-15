package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.common.model.RegModEntity;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

/** 콘텐츠버전 */
@Entity
@Table(schema = "vi", name = "vi_ctt_bin")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViCttBin extends RegModEntity implements Identifiable<UUID> {

  /** 콘텐츠바이너리식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ctt_bin_id", nullable = false)
  private UUID cttBinId;

  /** 콘텐츠식별번호 */
  @Column(name = "ctt_id")
  private UUID cttId;

  /** 바이너리파일상태코드 */
  @Column(name = "bin_file_status_code")
  private String binFileStatusCode;

  /** 바이너리값 */
  @Column(name = "bin_val")
  private String binVal;

  /** 콘텐츠속성내용 */
  @Column(name = "ctt_prop_cont")
  private String cttPropCont;

  /** 첨부이미지그룹식별번호 */
  @Column(name = "attach_img_group_id")
  private UUID attachImgGroupId;

  /** 첨부파일그룹식별번호 */
  @Column(name = "attach_file_group_id")
  private UUID attachFileGroupId;

  /** 설명내용 */
  @Column(name = "desc_cont")
  private String descCont;

  /** 활성여부 */
  @Column(name = "active_yn")
  private String activeYn;

  /** 심사여부 */
  @Column(name = "judge_yn")
  private String judgeYn;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return cttBinId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  @Override
  public void defaultValue() {
    super.defaultValue();
    binFileStatusCode =
        ObjectUtils.defaultIfNull(binFileStatusCode, Code.CT_008_001.getCode()); // 대기
    judgeYn = ObjectUtils.defaultIfNull(judgeYn, "N");
  }
}
