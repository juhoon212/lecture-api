package twentyoz.viven.webapi.client.mb.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

public class MbrLoginHistForm {

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
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "회원로그인이력식별번호")
      private UUID mbrLoginHistId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "사용자에이전트값")
      private String uaVal;

      @ApiModelProperty(value = "로그인일시")
      private DateTime loginDt;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "회원로그인이력식별번호")
      private UUID mbrLoginHistId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "사용자에이전트값")
      private String uaVal;

      @ApiModelProperty(value = "로그인일시")
      private DateTime loginDt;
    }
  }
}
