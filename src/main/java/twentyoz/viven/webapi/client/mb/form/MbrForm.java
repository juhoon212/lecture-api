package twentyoz.viven.webapi.client.mb.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import javax.validation.constraints.Pattern;
import twentyoz.viven.feature.vi.model.ViFrndReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

public class MbrForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "식별번호")
      private UUID id;

      @ApiModelProperty(value = "닉네임")
      private String nickname;

      @ApiModelProperty(value = "로그인아이디")
      private String loginId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetFriendAll {

      @ApiModelProperty(value = "식별번호")
      private UUID id;

      @ApiModelProperty(value = "닉네임")
      private String nickname;

      @ApiModelProperty(value = "로그인아이디")
      private String loginId;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "회원상태코드")
      private String mbrStatusCode;

      @ApiModelProperty(value = "회원유형코드")
      private String mbrTypeCode;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "로그인아이디 - 길이 100", required = true)
      @NotBlank(message = "로그인아이디는 필수입력 항목입니다.")
      @Length(max = 100, message = "로그인아이디의 입력길이를 확인하시기 바랍니다.")
      private String loginId;

      @ApiModelProperty(value = "비밀번호 - 길이 20", required = true)
      @NotBlank(message = "비밀번호는 필수입력 항목입니다.")
      @Pattern(
          regexp =
              "^(?=.*[a-z])(?=.*[0-9]).{6,20}$|"
                  + "^(?=.*[a-z])(?=.*[$@$!%*#_*()?%*+=&]).{6,20}$|"
                  + "^(?=.*[A-Z])(?=.*[$@$!%*#_*()?%*+=&]).{6,20}$|"
                  + "^(?=.*[0-9])(?=.*[$@$!%*#_*()?%*+=&]).{6,20}$",
          message = "비밀번호 형식을 확인해주세요.\n" + "1. 8 ~ 20 글자\n" + "2. 영대소문자, 숫자, 특수문자 중 최소 2가지 이상 조합\n")
      private String pw;

      @ApiModelProperty(value = "회원명 - 길이 100", required = true)
      @NotBlank(message = "회원명은 필수입력 항목입니다.")
      @Length(max = 100, message = "회원명의 입력길이를 확인하시기 바랍니다.")
      private String mbrName;

      @ApiModelProperty(value = "닉네임 - 길이 100", required = true)
      @NotBlank(message = "닉네임은 필수입력 항목입니다.")
      @Length(max = 100, message = "닉네임의 입력길이를 확인하시기 바랍니다.")
      private String nickname;

      @ApiModelProperty(value = "이메일 - 길이 250", required = true)
      @Pattern(
          regexp =
              "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
          message = "메일의 양식을 지켜주세요.")
      @Length(max = 250, message = "이메일 의 입력길이를 확인하시기 바랍니다.")
      private String email;

      @ApiModelProperty(value = "휴대폰번호 - 길이 30")
      @Pattern(
          regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$",
          message = "휴대폰번호 형식을 확인해주세요.")
      @Length(max = 30, message = "휴대폰번호 의 입력길이를 확인하시기 바랍니다.")
      private String mphoneNo;

      @ApiModelProperty(value = "생년월일 - 길이 25", required = true)
      @Pattern(
          regexp = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",
          message = "생년월일 형식을 확인해주세요.")
      @Length(max = 25, message = "생년월일의 입력길이를 확인하시기 바랍니다.")
      private String birthday;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "닉네임 - 길이 100")
      @Length(max = 100, message = "닉네임의 입력길이를 확인하시기 바랍니다.")
      private String nickname;

      @ApiModelProperty(value = "이메일 - 길이 250")
      @Pattern(
          regexp =
              "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
          message = "메일의 양식을 지켜주세요.")
      @Length(max = 250, message = "이메일 의 입력길이를 확인하시기 바랍니다.")
      private String email;

      @ApiModelProperty(value = "휴대폰번호 - 길이 30")
      @Pattern(
          regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$",
          message = "휴대폰번호 형식을 확인해주세요.")
      @Length(max = 30, message = "휴대폰번호 의 입력길이를 확인하시기 바랍니다.")
      private String mphoneNo;

      @ApiModelProperty(value = "생년월일 - 길이 25")
      @Pattern(
          regexp = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",
          message = "생년월일 형식을 확인해주세요.")
      @Length(max = 25, message = "생년월일의 입력길이를 확인하시기 바랍니다.")
      private String birthday;

      @ApiModelProperty(value = "프로필 배경색상")
      private String profileBgColor;

      @ApiModelProperty(value = "프로필 파일 경로_1(전신)")
      private String avtBodyFilePath;

      @ApiModelProperty(value = "프로필 파일 경로_2(얼굴)")
      private String avtProfileFilePath;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyAvt {

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyTempPw {

      @ApiModelProperty(value = "비밀번호 - 길이 20")
      @Pattern(
          regexp =
              "^(?=.*[a-z])(?=.*[0-9]).{6,20}$|"
                  + "^(?=.*[a-z])(?=.*[$@$!%*#_*()?%*+=&]).{6,20}$|"
                  + "^(?=.*[A-Z])(?=.*[$@$!%*#_*()?%*+=&]).{6,20}$|"
                  + "^(?=.*[0-9])(?=.*[$@$!%*#_*()?%*+=&]).{6,20}$",
          message = "비밀번호 형식을 확인해주세요.\n" + "1. 8 ~ 20 글자\n" + "2. 영대소문자, 숫자, 특수문자 중 최소 2가지 이상 조합\n")
      private String pw;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyPw {

      @ApiModelProperty(value = "현재 비밀번호 - 길이 20")
      private String curPw;

      @ApiModelProperty(value = "비밀번호 - 길이 20")
      @Pattern(
          regexp =
              "^(?=.*[a-z])(?=.*[0-9]).{6,20}$|"
                  + "^(?=.*[a-z])(?=.*[$@$!%*#_*()?%*+=&]).{6,20}$|"
                  + "^(?=.*[A-Z])(?=.*[$@$!%*#_*()?%*+=&]).{6,20}$|"
                  + "^(?=.*[0-9])(?=.*[$@$!%*#_*()?%*+=&]).{6,20}$",
          message = "비밀번호 형식을 확인해주세요.\n" + "1. 8 ~ 20 글자\n" + "2. 영대소문자, 숫자, 특수문자 중 최소 2가지 이상 조합\n")
      private String pw;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Secession {

      @ApiModelProperty(value = "탈퇴유형코드 - Code Group: MB_007")
      @NotBlank(message = "탈퇴유형코드 필수입력 항목입니다.")
      private String secessionReasonTypeCode;

      @ApiModelProperty(value = "상태변경사유")
      private String statusChgReason;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckPw {
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
    public static class GetAll {

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "회원번호 - Tmpl: MBR_{mbr_id first 8 char}")
      private String mbrNo;

      @ApiModelProperty(value = "회원유형코드 - Code Group: MB_001")
      private String mbrTypeCode;

      @ApiModelProperty(value = "회원상태코드 - Code Group: MB_002")
      private String mbrStatusCode;

      @ApiModelProperty(value = "로그인아이디")
      private String loginId;

      @ApiModelProperty(value = "비밀번호")
      private String pw;

      @ApiModelProperty(value = "회원명")
      private String mbrName;

      @ApiModelProperty(value = "닉네임")
      private String nickname;

      @ApiModelProperty(value = "이메일")
      private String email;

      @ApiModelProperty(value = "휴대폰번호")
      private String mphoneNo;

      @ApiModelProperty(value = "생년월일")
      private String birthday;

      @ApiModelProperty(value = "가입일시")
      private DateTime joinDt;

      @ApiModelProperty(value = "최근로그인일시")
      private DateTime lastLoginDt;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @ApiModelProperty(value = "온라인여부")
      private String onlineYn;

      @ApiModelProperty(value = "프로필 배경색상")
      private String profileBgColor;

      @ApiModelProperty(value = "프로필 파일 경로_1(전신)")
      private String avtBodyFilePath;

      @ApiModelProperty(value = "프로필 파일 경로_2(얼굴)")
      private String avtProfileFilePath;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "회원유형코드 - Code Group: MB_001")
      private String mbrTypeCode;

      @ApiModelProperty(value = "회원상태코드 - Code Group: MB_002")
      private String mbrStatusCode;

      @ApiModelProperty(value = "로그인아이디")
      private String loginId;

      @ApiModelProperty(value = "회원명")
      private String mbrName;

      @ApiModelProperty(value = "닉네임")
      private String nickname;

      @ApiModelProperty(value = "이메일")
      private String email;

      @ApiModelProperty(value = "휴대폰번호")
      private String mphoneNo;

      @ApiModelProperty(value = "생년월일")
      private String birthday;

      @ApiModelProperty(value = "가입일시")
      private DateTime joinDt;

      @ApiModelProperty(value = "최근로그인일시")
      private DateTime lastLoginDt;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "임시비밀번호여부")
      private String tempPwYn;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @ApiModelProperty(value = "온라인여부")
      private String onlineYn;

      @ApiModelProperty(value = "프로필 배경색상")
      private String profileBgColor;

      @ApiModelProperty(value = "프로필 파일 경로_1(전신)")
      private String avtBodyFilePath;

      @ApiModelProperty(value = "프로필 파일 경로_2(얼굴)")
      private String avtProfileFilePath;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetFriend {

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "닉네임")
      private String nickname;

      @ApiModelProperty(value = "최근로그인일시")
      private DateTime lastLoginDt;

      @ApiModelProperty(value = "온라인여부")
      private String onlineYn;

      @ApiModelProperty(value = "프로필 배경색상")
      private String profileBgColor;

      @ApiModelProperty(value = "프로필 파일 경로_1(전신)")
      private String avtBodyFilePath;

      @ApiModelProperty(value = "프로필 파일 경로_2(얼굴)")
      private String avtProfileFilePath;

      @ApiModelProperty(value = "친구요청 정보")
      private ViFrndReq viFrndReq;

      @ApiModelProperty(value = "친구식별번호")
      private UUID frndId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckPw {
      @ApiModelProperty(value = "성공여부")
      private boolean success;
    }
  }
}
