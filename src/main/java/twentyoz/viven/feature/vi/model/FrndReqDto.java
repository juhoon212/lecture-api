package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import twentyoz.viven.feature.mb.model.MbMbr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

/** 친구요청 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class FrndReqDto {

  /** 친구요청식별번호 */
  private UUID frndReqId;

  /** 요청회원식별번호 */
  private UUID reqMbrId;

  /** 응답회원식별번호 */
  private UUID resMbrId;

  /** 요청상태코드 */
  private String reqStatusCode;

  private UUID regId;
  private DateTime regDt;

  private UUID modId;
  private DateTime modDt;

  private MbMbr mbr;

  public FrndReqDto(
      UUID frndReqId,
      UUID reqMbrId,
      UUID resMbrId,
      String reqStatusCode,
      UUID regId,
      DateTime regDt,
      UUID modId,
      DateTime modDt,
      UUID mbrId,
      String nickname,
      String avtBodyFilePath,
      String avtProfileFilePath,
      String profileBgColor,
      String onlineYn,
      DateTime lastLoginDt) {
    MbMbr mbr = new MbMbr();
    mbr.setMbrId(mbrId);
    mbr.setNickname(nickname);
    mbr.setAvtBodyFilePath(avtBodyFilePath);
    mbr.setAvtProfileFilePath(avtProfileFilePath);
    mbr.setProfileBgColor(profileBgColor);
    mbr.setOnlineYn(onlineYn);
    mbr.setLastLoginDt(lastLoginDt);

    this.frndReqId = frndReqId;
    this.reqMbrId = reqMbrId;
    this.resMbrId = resMbrId;
    this.reqStatusCode = reqStatusCode;
    this.regId = regId;
    this.regDt = regDt;
    this.modId = modId;
    this.modDt = modDt;

    this.mbr = mbr;
  }
}
