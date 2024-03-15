package twentyoz.viven.feature.mb.model;

import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class MbrDto {

  private UUID mbrId;
  private String loginId;
  private String mbrName;
  private String nickname;
  private String mbrStatusCode;
  private String profileBgColor;
  private String avtBodyFilePath;
  private String avtProfileFilePath;
  private String tempPwYn;
  private DateTime pwValidDt;

  /** 콘텐츠버전 > 버전파일 경로 */
  private String binFilePath;

  // 아바타 콘텐츠 정보
  private UUID cttId;
  private String cttDpName;
  private String cttTypeCode;
  private String cttDataTypeCode;
  private UUID cttBinId;
  private String cttBinVal;

  public MbrDto(
      UUID mbrId,
      String loginId,
      String mbrName,
      String nickname,
      String mbrStatusCode,
      String profileBgColor,
      String avtBodyFilePath,
      String avtProfileFilePath,
      String tempPwYn,
      DateTime pwValidDt,
      String binFilePath,
      UUID cttId,
      String cttDpName,
      String cttTypeCode,
      String cttDataTypeCode,
      UUID cttBinId,
      String cttBinVal) {

    this.mbrId = mbrId;
    this.loginId = loginId;
    this.mbrName = mbrName;
    this.nickname = nickname;
    this.mbrStatusCode = mbrStatusCode;
    this.profileBgColor = profileBgColor;
    this.avtBodyFilePath = avtBodyFilePath;
    this.avtProfileFilePath = avtProfileFilePath;
    this.tempPwYn = tempPwYn;
    this.pwValidDt = pwValidDt;

    this.binFilePath = binFilePath;

    this.cttId = cttId;
    this.cttDpName = cttDpName;
    this.cttTypeCode = cttTypeCode;
    this.cttDataTypeCode = cttDataTypeCode;
    this.cttBinId = cttBinId;
    this.cttBinVal = cttBinVal;
  }
}
