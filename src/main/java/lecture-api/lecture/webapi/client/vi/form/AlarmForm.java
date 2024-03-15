package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

public class AlarmForm {

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

      @ApiModelProperty(value = "알람유형코드")
      private String alarmTypeCode;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "조회여부")
      private String readYn;

      @ApiModelProperty(value = "알람속성내용")
      private String alarmPropCont;

      @ApiModelProperty(value = "알람명")
      private String alarmName;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "알람식별번호")
      private UUID alarmId;

      @ApiModelProperty(value = "알람유형코드")
      private String alarmTypeCode;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "조회여부")
      private String readYn;

      @ApiModelProperty(value = "알람속성내용 ")
      private String alarmPropCont;

      @ApiModelProperty(value = "알람명")
      private String alarmName;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "알람식별번호")
      private UUID alarmId;

      @ApiModelProperty(value = "알람유형코드")
      private String alarmTypeCode;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "조회여부")
      private String readYn;

      @ApiModelProperty(value = "알람속성내용 ")
      private String alarmPropCont;

      @ApiModelProperty(value = "알람명")
      private String alarmName;
    }
  }
}
