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

public class RoomPropForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "식별번호")
      private UUID id;

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "방식별번호", required = true)
      private UUID roomId;

      @ApiModelProperty(value = "속성내용", required = true)
      private String propCont;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "속성내용", required = true)
      private String propCont;
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

      @ApiModelProperty(value = "방속성식별번호")
      private UUID roomPropId;

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "속성내용")
      private String propCont;

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

      @ApiModelProperty(value = "방속성식별번호")
      private UUID roomPropId;

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "속성내용")
      private String propCont;

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
