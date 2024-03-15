package twentyoz.viven.webapi.client.sy.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class AttachFileGroupRefObjForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "첨부파일그룹참조객체식별번호")
      private UUID attachFileGroupRefObjId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "참조객체명")
      private String refObjName;

      @ApiModelProperty(value = "참조객체식별번호")
      private UUID refObjId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "참조객체명")
      private String refObjName;

      @ApiModelProperty(value = "참조객체식별번호")
      private UUID refObjId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "첨부파일그룹참조객체식별번호")
      private UUID attachFileGroupRefObjId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "참조객체명")
      private String refObjName;

      @ApiModelProperty(value = "참조객체식별번호")
      private UUID refObjId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Remove {

      @ApiModelProperty(value = "첨부파일그룹참조식별번호")
      private UUID attachFileGroupRefObjId;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "첨부파일그룹참조객체식별번호")
      private UUID attachFileGroupRefObjId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "참조객체명")
      private String refObjName;

      @ApiModelProperty(value = "참조객체식별번호")
      private UUID refObjId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "첨부파일그룹참조객체식별번호")
      private UUID attachFileGroupRefObjId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "참조객체명")
      private String refObjName;

      @ApiModelProperty(value = "참조객체식별번호")
      private UUID refObjId;
    }
  }
}
