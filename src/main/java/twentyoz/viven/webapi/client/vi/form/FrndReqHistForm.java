package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

public class FrndReqHistForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "식별번호")
      private UUID id;

      @ApiModelProperty(value = "요청회원식별번호")
      private UUID reqMbrId;

      @ApiModelProperty(value = "응답회원식별번호")
      private UUID resMbrId;

      @ApiModelProperty(value = "요청상태코드")
      private String reqStatusCode;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "요청회원식별번호")
      private UUID reqMbrId;

      @ApiModelProperty(value = "응답회원식별번호")
      private UUID resMbrId;

      @ApiModelProperty(value = "요청상태코드")
      private String reqStatusCode;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "친구요청이력식별번호")
      private UUID frndReqHistId;

      @ApiModelProperty(value = "요청회원식별번호")
      private UUID reqMbrId;

      @ApiModelProperty(value = "응답회원식별번호")
      private UUID resMbrId;

      @ApiModelProperty(value = "요청상태코드")
      private String reqStatusCode;

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

      @ApiModelProperty(value = "친구요청이력식별번호")
      private UUID frndReqHistId;

      @ApiModelProperty(value = "요청회원식별번호")
      private UUID reqMbrId;

      @ApiModelProperty(value = "응답회원식별번호")
      private UUID resMbrId;

      @ApiModelProperty(value = "요청상태코드")
      private String reqStatusCode;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;
    }
  }
}
