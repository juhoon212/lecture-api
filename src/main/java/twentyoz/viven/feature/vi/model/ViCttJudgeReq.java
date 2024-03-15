package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.common.model.RegModEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;

/** 콘텐츠심사요청 */
@Entity
@Table(schema = "vi", name = "vi_ctt_judge_req")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViCttJudgeReq extends RegModEntity implements Identifiable<UUID> {

  /** 콘텐츠심사요청식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ctt_judge_req_id", nullable = false)
  private UUID cttJudgeReqId;

  /** 콘텐츠식별번호 */
  @Column(name = "ctt_id")
  private UUID cttId;

  /** 콘텐츠유형코드 Code Group: CT_001 */
  @Column(name = "ctt_type_code")
  private String cttTypeCode;

  /** 콘텐츠바이너리식별번호 */
  @Column(name = "ctt_bin_id")
  private UUID cttBinId;

  /** 심사유형코드 - Code Group: CT_004 */
  @Column(name = "judge_type_code")
  private String judgeTypeCode;

  /** 심사요청내용 */
  @Column(name = "judge_req_cont")
  private String judgeReqCont;

  /** 콘텐츠명 */
  @Column(name = "ctt_name")
  private String cttName;

  /** 콘텐츠전시명 */
  @Column(name = "ctt_dp_name")
  private String cttDpName;

  /** 비고내용 */
  @Column(name = "remark_cont")
  private String remarkCont;

  /** 심사상태코드 - Code Group: CT_003 */
  @Column(name = "judge_status_code")
  private String judgeStatusCode;

  /** 반려유형코드 - Code Group: CT_005 */
  @Column(name = "reject_type_code")
  private String rejectTypeCode;

  /** 반려사유 */
  @Column(name = "reject_reason")
  private String rejectReason;

  /** 요청사용자식별번호 */
  @Column(name = "req_user_id")
  private UUID reqUserId;

  /** 요청일시 */
  @Column(name = "req_dt")
  private DateTime reqDt;

  /** 처리사용자식별번호 */
  @Column(name = "proc_user_id")
  private UUID procUserId;

  /** 처리일시 */
  @Column(name = "proc_dt")
  private DateTime procDt;

  /** 설명내용 */
  @Column(name = "desc_cont")
  private String descCont;

  /** 첨부파일그룹식별번호 */
  @Column(name = "attach_file_group_id")
  private UUID attachFileGroupId;

  /** 아바타이미지그룹식별번호 */
  @Column(name = "avt_img_group_id")
  private UUID avtImgGroupId;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return cttJudgeReqId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  @Override
  public void defaultValue() {
    super.defaultValue();
    judgeStatusCode = ObjectUtils.defaultIfNull(judgeStatusCode, Code.CT_003_006.getCode()); // 임시저장
  }
}
