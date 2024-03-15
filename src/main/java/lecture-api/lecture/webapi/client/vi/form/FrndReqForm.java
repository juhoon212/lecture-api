package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

public class FrndReqForm {
  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

      @ApiModelProperty(value = "응답회원식별번호")
      private UUID resMbrId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Respond {
      @ApiModelProperty(value = "식별번호 목록")
      private List<UUID> ids;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "친구요청식별번호", required = true)
      private UUID frndReqId;

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
    public static class Remove {

      @ApiModelProperty(value = "식별번호 목록")
      private List<UUID> ids;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "친구요청식별번호")
      private UUID frndReqId;

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

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "친구요청식별번호")
      private UUID frndReqId;

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

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;
    }
  }
}
