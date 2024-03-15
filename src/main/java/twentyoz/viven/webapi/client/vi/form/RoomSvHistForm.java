package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

public class RoomSvHistForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {
      @ApiModelProperty(value = "식별번호")
      private UUID id;

      @ApiModelProperty(value = "방서버식별번호")
      private UUID roomSvId;

      @ApiModelProperty(value = "방서버번호")
      private String roomSvNo;

      @ApiModelProperty(value = "방서버명")
      private String roomSvName;

      @ApiModelProperty(value = "서버이력유형코드")
      private String svHistTypeCode;

      @ApiModelProperty(value = "로그유형코드")
      private String logTypeCode;

      @ApiModelProperty(value = "메시지내용")
      private String msgCont;

      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "발생시작일시")
      private DateTime occurStartDt;

      @ApiModelProperty(value = "발생종료일시")
      private DateTime occurEndDt;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "방서버이력식별번호")
      private UUID roomSvHistId;

      @ApiModelProperty(value = "방서버식별번호")
      private UUID roomSvId;

      @ApiModelProperty(value = "방서버번호")
      private String roomSvNo;

      @ApiModelProperty(value = "방서버명 ")
      private String roomSvName;

      @ApiModelProperty(value = "서버이력유형코드")
      private String svHistTypeCode;

      @ApiModelProperty(value = "로그유형코드")
      private String logTypeCode;

      @ApiModelProperty(value = "메시지내용")
      private String msgCont;

      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "발생일시")
      private DateTime occurDt;

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

      @ApiModelProperty(value = "방서버이력식별번호")
      private UUID roomSvHistId;

      @ApiModelProperty(value = "방서버식별번호")
      private UUID roomSvId;

      @ApiModelProperty(value = "방서버번호")
      private String roomSvNo;

      @ApiModelProperty(value = "방서버명 ")
      private String roomSvName;

      @ApiModelProperty(value = "서버이력유형코드")
      private String svHistTypeCode;

      @ApiModelProperty(value = "로그유형코드")
      private String logTypeCode;

      @ApiModelProperty(value = "메시지내용")
      private String msgCont;

      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "발생일시")
      private DateTime occurDt;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;
    }
  }
}
