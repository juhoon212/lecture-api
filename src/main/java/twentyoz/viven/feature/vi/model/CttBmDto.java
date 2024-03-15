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
public class CttBmDto {

  /** 콘텐츠 */
  private ViCttBm cttBm;

  /** 프로필 첨부파일 경로 */
  private String avtProfileFilePath;

  private String cttTypeCode;
  private String cttDpName;
  private String descCont;
  private String avtBodyFilePath;
  private String mainFilePath;
  private UUID cttMbrId;

  public CttBmDto(
      UUID cttBmId,
      UUID cttId,
      UUID cttBinId,
      UUID mbrId,
      UUID regId,
      DateTime regDt,
      String avtProfileFilePath,
      String cttTypeCode,
      String cttDpName,
      String descCont,
      String avtBodyFilePath,
      String mainFilePath,
      UUID cttMbrId) {
    ViCttBm viCttBm = new ViCttBm();
    viCttBm.setCttBmId(cttBmId);
    viCttBm.setCttId(cttId);
    viCttBm.setCttBinId(cttBinId);
    viCttBm.setMbrId(mbrId);
    viCttBm.setRegId(regId);
    viCttBm.setRegDt(regDt);
    this.cttBm = viCttBm;

    this.avtProfileFilePath = avtProfileFilePath;
    this.cttTypeCode = cttTypeCode;
    this.cttDpName = cttDpName;
    this.descCont = descCont;
    this.avtBodyFilePath = avtBodyFilePath;
    this.mainFilePath = mainFilePath;
    this.cttMbrId = cttMbrId;
  }
}
