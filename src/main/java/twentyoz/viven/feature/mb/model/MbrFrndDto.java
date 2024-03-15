package twentyoz.viven.feature.mb.model;

import java.util.UUID;
import twentyoz.viven.feature.vi.model.ViFrndReq;
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
public class MbrFrndDto {

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

  private ViFrndReq viFrndReq;

  private UUID frndId;

  public MbrFrndDto(
      UUID mbrId,
      String nickname,
      DateTime lastLoginDt,
      String onlineYn,
      String profileBgColor,
      String avtBodyFilePath,
      String avtProfileFilePath,
      UUID frndReqId,
      UUID resMbrId,
      UUID reqMbrId,
      String frndReqStatusCode,
      UUID frndId) {

    this.mbrId = mbrId;
    this.nickname = nickname;
    this.onlineYn = onlineYn;
    this.lastLoginDt = lastLoginDt;
    this.profileBgColor = profileBgColor;
    this.avtBodyFilePath = avtBodyFilePath;
    this.avtProfileFilePath = avtProfileFilePath;
    ViFrndReq viFrndReq = new ViFrndReq();

    viFrndReq.setResMbrId(resMbrId);
    viFrndReq.setReqMbrId(reqMbrId);
    viFrndReq.setFrndReqId(frndReqId);
    viFrndReq.setReqStatusCode(frndReqStatusCode);

    this.frndId = frndId;

    this.viFrndReq = viFrndReq;
  }
}
