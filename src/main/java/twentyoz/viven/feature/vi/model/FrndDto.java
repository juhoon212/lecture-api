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
public class FrndDto {

  /** 친구식별번호 */
  private UUID frndId;

  /** 회원식별번호 */
  private UUID mbrId;

  /** 친구회원식별번호 */
  private UUID frndMbrId;

  private UUID regId;
  private DateTime regDt;

  private MbMbr frndProfile;

  public FrndDto(
      UUID frndId,
      UUID mbrId,
      UUID frndMbrId,
      UUID regId,
      DateTime regDt,
      String nickname,
      String avtBodyFilePath,
      String avtProfileFilePath,
      String profileBgColor,
      String onlineYn,
      DateTime lastLoginDt) {
    MbMbr mbr = new MbMbr();
    mbr.setMbrId(frndMbrId);
    mbr.setNickname(nickname);
    mbr.setAvtBodyFilePath(avtBodyFilePath);
    mbr.setAvtProfileFilePath(avtProfileFilePath);
    mbr.setProfileBgColor(profileBgColor);
    mbr.setOnlineYn(onlineYn);
    mbr.setLastLoginDt(lastLoginDt);

    this.frndId = frndId;
    this.frndMbrId = frndMbrId;
    this.mbrId = mbrId;
    this.regId = regId;
    this.regDt = regDt;

    this.frndProfile = mbr;
  }
}
