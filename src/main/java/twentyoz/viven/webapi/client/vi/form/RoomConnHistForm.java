package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

public class RoomConnHistForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "방접속이력식별번호")
      private UUID roomConnHistId;

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "방번호")
      private String roomNo;

      @ApiModelProperty(value = "방이름")
      private String roomName;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "로그인아이디")
      private String loginId;

      @ApiModelProperty(value = "회원명")
      private String mbrName;

      @ApiModelProperty(value = "닉네임")
      private String nickname;

      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "방접속유형코드")
      private String roomConnTypeCode;

      @ApiModelProperty(value = "접속시작일시")
      private DateTime connStartDt;

      @ApiModelProperty(value = "접속종료일시")
      private DateTime connEndDt;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "방접속이력식별번호")
      private UUID roomConnHistId;

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "방번호")
      private String roomNo;

      @ApiModelProperty(value = "방이름")
      private String roomName;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "로그인아이디")
      private String loginId;

      @ApiModelProperty(value = "회원명")
      private String mbrName;

      @ApiModelProperty(value = "닉네임")
      private String nickname;

      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "방접속유형코드")
      private String roomConnTypeCode;

      @ApiModelProperty(value = "접속일시")
      private DateTime connDt;

      @ApiModelProperty(value = "접속해제일시")
      private DateTime dcnDt;

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

      @ApiModelProperty(value = "방접속이력식별번호")
      private UUID roomConnHistId;

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "방번호")
      private String roomNo;

      @ApiModelProperty(value = "방이름")
      private String roomName;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "로그인아이디")
      private String loginId;

      @ApiModelProperty(value = "회원명")
      private String mbrName;

      @ApiModelProperty(value = "닉네임")
      private String nickname;

      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "방접속유형코드")
      private String roomConnTypeCode;

      @ApiModelProperty(value = "접속일시")
      private DateTime connDt;

      @ApiModelProperty(value = "접속해제일시")
      private DateTime dcnDt;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;
    }
  }
}
