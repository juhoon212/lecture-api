package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CttJudgeReqDto {

  /** 콘텐츠심사요청 */
  private ViCttJudgeReq cttJudgeReq;

  /** 콘텐츠 > 콘텐츠유형코드 */
  private String cttTypeCode;

  /** 콘텐츠버전 > 버전값 */
  private String binVal;

  private String binDescCont;

  /** 콘텐츠버전 > 첨부이미지그룹식별번호 */
  private UUID binAttachImgGroupId;

  /** 콘텐츠버전 > 첨부파일그룹식별번호 */
  private UUID binAttachFileGroupId;

  private String mainAttachFilePath;

  public CttJudgeReqDto(
      UUID cttJudgeReqId,
      UUID cttId,
      String cttTypeCode,
      UUID cttBinId,
      String judgeTypeCode,
      String judgeReqCont,
      String cttName,
      String cttDpName,
      String remarkCont,
      String judgeStatusCode,
      String rejectTypeCode,
      String rejectReason,
      UUID reqUserId,
      DateTime reqDt,
      UUID procUserId,
      DateTime procDt,
      UUID regId,
      DateTime regDt,
      UUID modId,
      DateTime modDt,
      String descCont,
      UUID attachFileGroupId,
      UUID avtImgGroupId,
      String binVal,
      String binDescCont,
      UUID binAttachImgGroupId,
      UUID binAttachFileGroupId,
      String mainAttachFilePath) {
    ViCttJudgeReq viCttJudgeReq = new ViCttJudgeReq();
    viCttJudgeReq.setCttJudgeReqId(cttJudgeReqId);
    viCttJudgeReq.setCttId(cttId);
    viCttJudgeReq.setCttBinId(cttBinId);
    viCttJudgeReq.setJudgeTypeCode(judgeTypeCode);
    viCttJudgeReq.setJudgeReqCont(judgeReqCont);
    viCttJudgeReq.setCttName(cttName);
    viCttJudgeReq.setCttDpName(cttDpName);
    viCttJudgeReq.setRemarkCont(remarkCont);
    viCttJudgeReq.setJudgeStatusCode(judgeStatusCode);
    viCttJudgeReq.setRejectTypeCode(rejectTypeCode);
    viCttJudgeReq.setRejectReason(rejectReason);
    viCttJudgeReq.setReqUserId(reqUserId);
    viCttJudgeReq.setReqDt(reqDt);
    viCttJudgeReq.setProcUserId(procUserId);
    viCttJudgeReq.setProcDt(procDt);
    viCttJudgeReq.setRegId(regId);
    viCttJudgeReq.setRegDt(regDt);
    viCttJudgeReq.setModId(modId);
    viCttJudgeReq.setModDt(modDt);
    viCttJudgeReq.setDescCont(descCont);
    viCttJudgeReq.setAttachFileGroupId(attachFileGroupId);
    viCttJudgeReq.setAvtImgGroupId(avtImgGroupId);
    this.cttJudgeReq = viCttJudgeReq;

    this.cttTypeCode = cttTypeCode;

    this.binVal = binVal;
    this.binDescCont = binDescCont;
    this.binAttachImgGroupId = binAttachImgGroupId;
    this.binAttachFileGroupId = binAttachFileGroupId;

    this.mainAttachFilePath = mainAttachFilePath;
  }
}
