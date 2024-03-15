package twentyoz.viven.feature.mb.model;

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
public class MbMbrEmailAuthDto {

  /** 이메일 인증 테이블 */
  private MbMbrEmailAuth mbrEmailAuth;

  /** 회원식별번호 */
  private UUID mbrId;

  private String mbrTypeCode;

  private String mbrStatusCode;

  private String loginId;

  private String pw;

  private String mbrName;

  private String email;

  public MbMbrEmailAuthDto(
      UUID mbrEmailAuthId,
      String authVal,
      DateTime authEndDt,
      String authComplYn,
      UUID mbrId,
      String mbrTypeCode,
      String mbrStatusCode,
      String loginId,
      String mbrName,
      String email) {
    MbMbrEmailAuth mbMbrEmailAuth = new MbMbrEmailAuth();
    mbMbrEmailAuth.setMbrEmailAuthId(mbrEmailAuthId);
    mbMbrEmailAuth.setAuthVal(authVal);
    mbMbrEmailAuth.setAuthEndDt(authEndDt);
    mbMbrEmailAuth.setAuthComplYn(authComplYn);

    this.mbrEmailAuth = mbMbrEmailAuth;

    this.mbrId = mbrId;
    this.mbrTypeCode = mbrTypeCode;
    this.mbrStatusCode = mbrStatusCode;
    this.loginId = loginId;
    this.mbrName = mbrName;
    this.email = email;
  }
}
