package twentyoz.viven.feature.mb.model;

import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

/** 친구요청 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class MbrDtoDetail {

  /** "회원식별번호" */
  private UUID mbrId;

  /** "닉네임" */
  private String nickname;

  /** "최근로그인일시" */
  private DateTime lastLoginDt;

  /** "온라인여부" */
  private String onlineYn;

  /** "프로필 배경색상" */
  private String profileBgColor;

  /** "프로필 파일 경로_1(전신)" */
  private String avtBodyFilePath;

  /** "프로필 파일 경로_2(얼굴)" */
  private String avtProfileFilePath;

  private UUID frndId;

  public MbrDtoDetail(
      UUID mbrId,
      String nickname,
      DateTime lastLoginDt,
      String onlineYn,
      String profileBgColor,
      String avtBodyFilePath,
      String avtProfileFilePath,
      UUID frndId) {
    this.mbrId = mbrId;
    this.nickname = nickname;
    this.onlineYn = onlineYn;
    this.lastLoginDt = lastLoginDt;
    this.profileBgColor = profileBgColor;
    this.avtBodyFilePath = avtBodyFilePath;
    this.avtProfileFilePath = avtProfileFilePath;

    this.frndId = frndId;
  }
}
