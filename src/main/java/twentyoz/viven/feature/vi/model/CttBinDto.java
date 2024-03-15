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
public class CttBinDto {

  /** 콘텐츠 */
  private ViCttBin cttBin;

  /** 첨부파일 > 첨부 이미지 파일 경로 */
  private String attachImgPath;

  /** 첨부파일 > 첨부 파일명 */
  private String attachFileName;

  /** 첨부파일 > 첨부 파일 경로 */
  private String attachFilePath;

  public CttBinDto(
      UUID cttBinId,
      UUID cttId,
      String binVal,
      String cttPropCont,
      UUID attachImgGroupId,
      UUID attachFileGroupId,
      String descCont,
      String activeYn,
      UUID regId,
      DateTime regDt,
      UUID modId,
      DateTime modDt,
      String attachImgPath,
      String attachFileName,
      String attachFilePath) {
    ViCttBin viCttBin = new ViCttBin();
    viCttBin.setCttBinId(cttBinId);
    viCttBin.setCttId(cttId);
    viCttBin.setBinVal(binVal);
    viCttBin.setCttPropCont(cttPropCont);
    viCttBin.setAttachImgGroupId(attachImgGroupId);
    viCttBin.setAttachFileGroupId(attachFileGroupId);
    viCttBin.setDescCont(descCont);
    viCttBin.setActiveYn(activeYn);
    viCttBin.setRegId(regId);
    viCttBin.setRegDt(regDt);
    viCttBin.setModId(modId);
    viCttBin.setModDt(modDt);
    this.cttBin = viCttBin;

    this.attachImgPath = attachImgPath;
    this.attachFileName = attachFileName;
    this.attachFilePath = attachFilePath;
  }
}
