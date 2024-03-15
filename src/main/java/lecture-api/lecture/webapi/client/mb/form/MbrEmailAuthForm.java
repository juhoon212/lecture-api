package twentyoz.viven.webapi.client.mb.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

public class MbrEmailAuthForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "식별번호")
      private UUID id;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "인증값")
      private String authVal;

      @ApiModelProperty(value = "인증종료일시")
      private DateTime authEndDt;

      @ApiModelProperty(value = "인증완료여부")
      private String authComplYn;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "회원이메일인증식별번호", required = true)
      private UUID mbrEmailAuthId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "인증값")
      private String authVal;

      @ApiModelProperty(value = "인증종료일시")
      private DateTime authEndDt;

      @ApiModelProperty(value = "인증완료여부")
      private String authComplYn;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Remove {

      @ApiModelProperty(value = "식별번호 목록")
      private List<UUID> ids;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindId {

      @ApiModelProperty(value = "회원이름", required = true)
      private String mbrName;

      @ApiModelProperty(value = "이메일 - 길이 250", required = true)
      @Pattern(
          regexp =
              "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
          message = "메일의 양식을 지켜주세요.")
      @Length(max = 250, message = "이메일 의 입력길이를 확인하시기 바랍니다.")
      private String email;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindPw {

      @ApiModelProperty(value = "회원이메일인증식별번호", required = true)
      private UUID mbrEmailAuthId;

      @ApiModelProperty(value = "인증값", required = true)
      private String authVal;

      @ApiModelProperty(value = "회원이름", required = true)
      private String mbrName;

      @ApiModelProperty(value = "로그인아이디", required = true)
      private String loginId;

      @ApiModelProperty(value = "이메일 - 길이 250", required = true)
      @Pattern(
          regexp =
              "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
          message = "메일의 양식을 지켜주세요.")
      @Length(max = 250, message = "이메일 의 입력길이를 확인하시기 바랍니다.")
      private String email;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindPwAuthEmail {

      @ApiModelProperty(value = "로그인아이디", required = true)
      private String loginId;

      @ApiModelProperty(value = "회원이름", required = true)
      private String mbrName;

      @ApiModelProperty(value = "이메일 - 길이 250", required = true)
      @Pattern(
          regexp =
              "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
          message = "메일의 양식을 지켜주세요.")
      @Length(max = 250, message = "이메일 의 입력길이를 확인하시기 바랍니다.")
      private String email;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimeExtension {

      @ApiModelProperty(value = "성공여부")
      private boolean success;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindId {

      @ApiModelProperty(value = "성공여부")
      private boolean success;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendTempPw {

      @ApiModelProperty(value = "성공여부")
      private boolean success;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "회원이메일인증식별번호")
      private UUID mbrEmailAuthId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "인증값")
      private String authVal;

      @ApiModelProperty(value = "인증종료일시")
      private DateTime authEndDt;

      @ApiModelProperty(value = "인증완료여부")
      private String authComplYn;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "회원이메일인증식별번호")
      private UUID mbrEmailAuthId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "인증값")
      private String authVal;

      @ApiModelProperty(value = "인증종료일시")
      private DateTime authEndDt;

      @ApiModelProperty(value = "인증완료여부")
      private String authComplYn;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;
    }
  }
}
