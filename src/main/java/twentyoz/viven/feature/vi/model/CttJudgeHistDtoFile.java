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
public class CttJudgeHistDtoFile {

  /** 콘텐츠심사요청 */
  private ViCttJudgeHist cttJudgeHist;

  /** 콘텐츠심사요청 > 콘텐츠식별번호 */
  private UUID cttId;

  /** 콘텐츠심사요청 > 콘텐츠버전식별번호 */
  private UUID cttBinId;

  private String binDescCont;

  private String binFileName;
  private String binImgPath;

  public CttJudgeHistDtoFile(
      UUID cttJudgeHistId,
      UUID cttJudgeReqId,
      String cttTypeCode,
      String cttName,
      String cttDpName,
      String descCont,
      String binVal,
      String judgeTypeCode,
      String judgeReqCont,
      String remarkCont,
      String judgeStatusCode,
      String rejectTypeCode,
      String rejectReason,
      UUID binImgGroupId,
      UUID binFileGroupId,
      UUID reqUserId,
      DateTime reqDt,
      UUID procUserId,
      DateTime procDt,
      UUID regId,
      DateTime regDt,
      UUID attachFileGroupId,
      UUID cttId,
      UUID cttBinId,
      String binDescCont,
      String binFileName,
      String binImgPath) {
    ViCttJudgeHist viCttJudgeHist = new ViCttJudgeHist();
    viCttJudgeHist.setCttJudgeHistId(cttJudgeHistId);
    viCttJudgeHist.setCttJudgeReqId(cttJudgeReqId);
    viCttJudgeHist.setCttTypeCode(cttTypeCode);
    viCttJudgeHist.setCttName(cttName);
    viCttJudgeHist.setCttDpName(cttDpName);
    viCttJudgeHist.setDescCont(descCont);
    viCttJudgeHist.setBinVal(binVal);
    viCttJudgeHist.setJudgeTypeCode(judgeTypeCode);
    viCttJudgeHist.setJudgeReqCont(judgeReqCont);
    viCttJudgeHist.setRemarkCont(remarkCont);
    viCttJudgeHist.setJudgeStatusCode(judgeStatusCode);
    viCttJudgeHist.setRejectTypeCode(rejectTypeCode);
    viCttJudgeHist.setRejectReason(rejectReason);
    viCttJudgeHist.setBinImgGroupId(binImgGroupId);
    viCttJudgeHist.setBinFileGroupId(binFileGroupId);
    viCttJudgeHist.setReqUserId(reqUserId);
    viCttJudgeHist.setReqDt(reqDt);
    viCttJudgeHist.setProcUserId(procUserId);
    viCttJudgeHist.setProcDt(procDt);
    viCttJudgeHist.setRegId(regId);
    viCttJudgeHist.setRegDt(regDt);
    viCttJudgeHist.setAttachFileGroupId(attachFileGroupId);
    this.cttJudgeHist = viCttJudgeHist;

    this.cttId = cttId;
    this.cttBinId = cttBinId;
    this.binDescCont = binDescCont;

    this.binFileName = binFileName;
    this.binImgPath = binImgPath;
  }
}
