package twentyoz.viven.webapi.client.mb.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

public class LoginForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Login {

      @ApiModelProperty(value = "로그안아이디")
      private String loginId;

      @ApiModelProperty(value = "비밀번호")
      private String pw;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Login {

      @ApiModelProperty(value = "토큰")
      private String token;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Logout {

      @ApiModelProperty(value = "성공여부")
      private boolean success;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "사용자로그인아이디")
      private String loginId;

      @ApiModelProperty(value = "회원명")
      private String mbrName;

      @ApiModelProperty(value = "닉네임")
      private String nickname;

      @ApiModelProperty(value = "회원상태 코드")
      private String mbrStatusCode;

      @ApiModelProperty(value = "프로필 배경색상")
      private String profileBgColor;

      @ApiModelProperty(value = "프로필 파일 경로_1(전신)")
      private String avtBodyFilePath;

      @ApiModelProperty(value = "프로필 파일 경로_2(얼굴)")
      private String avtProfileFilePath;

      @ApiModelProperty(value = "임시비밀번호 여부")
      private String tempPwYn;

      @ApiModelProperty(value = "비밀번호 유효일시")
      private DateTime pwValidDt;
    }
  }
}
