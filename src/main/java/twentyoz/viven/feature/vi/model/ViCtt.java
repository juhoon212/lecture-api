package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.common.model.UseYnEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;

/** 콘텐츠 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(schema = "vi", name = "vi_ctt")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViCtt extends UseYnEntity implements Identifiable<UUID> {

  public static final String PREFIX_CTT_NO = "CTT";

  /** 콘텐츠식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ctt_id", nullable = false)
  private UUID cttId;

  /** 콘텐츠번호 */
  @Column(name = "ctt_no")
  private String cttNo;

  /** 콘텐츠유형코드 Code Group: CT_001 */
  @Column(name = "ctt_type_code")
  private String cttTypeCode;

  /** 콘텐츠명 */
  @Column(name = "ctt_name")
  private String cttName;

  /** 콘텐츠전시명 */
  @Column(name = "ctt_dp_name")
  private String cttDpName;

  /** 설명내용 */
  @Column(name = "desc_cont")
  private String descCont;

  /** Code Group: CT_002 */
  @Column(name = "sell_status_code")
  private String sellStatusCode;

  /** 전시여부 */
  @Column(name = "dp_yn")
  private String dpYn;

  /** 전시시작일시 */
  @Column(name = "dp_start_dt")
  private DateTime dpStartDt;

  /** 전시종료일시 */
  @Column(name = "dp_end_dt")
  private DateTime dpEndDt;

  /** 첨부파일그룹식별번호 */
  @Column(name = "attach_file_group_id")
  private UUID attachFileGroupId;

  /** 사용여부 */
  @Column(name = "use_yn")
  private String useYn;

  /** 삭제여부 */
  @Column(name = "del_yn")
  private String delYn;

  /** 회원식별번호 */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /** 아바타이미지그룹식별번호 */
  @Column(name = "avt_img_group_id")
  private UUID avtImgGroupId;

  /** 콘텐츠데이터유형코드 */
  @Column(name = "ctt_data_type_code")
  private String cttDataTypeCode;

  /** 콘텐츠맵유형 */
  @Column(name = "ctt_map_type")
  private String cttMapType;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return cttId;
  }

  public void setId(UUID cttId) {
    this.cttId = cttId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  @Override
  public void defaultValue() {
    super.defaultValue();
    useYn = ObjectUtils.defaultIfNull(useYn, "Y");
    delYn = ObjectUtils.defaultIfNull(delYn, "N");
    dpYn = ObjectUtils.defaultIfNull(dpYn, "N");
    sellStatusCode = ObjectUtils.defaultIfNull(sellStatusCode, Code.CT_002_001.getCode());
  }

  /** 사용여부 : Y 처리 */
  public void useTrue() {
    useYn = "Y";
  }

  /** 사용여부 : N 처리 */
  public void useFalse() {
    useYn = "N";
  }

  /** 삭제여부 : Y 처리 */
  public void delTrue() {
    delYn = "Y";
  }

  /** 삭제여부 : N 처리 */
  public void delFalse() {
    delYn = "N";
  }
}
