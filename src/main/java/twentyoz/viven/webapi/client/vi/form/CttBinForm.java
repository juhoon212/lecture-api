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

public class CttBinForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "심사파일(첨부파일그룹식별번호)", required = true)
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "심사파일(설명내용)")
      private String descCont;

      @ApiModelProperty(value = "버전값")
      private String binVal;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "심사파일(첨부파일그룹식별번호)")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "심사파일(설명내용)")
      private String descCont;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "버전값")
      private String binVal;
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
    public static class GetId {

      @ApiModelProperty(value = "콘텐츠식별번호", required = true)
      private UUID cttId;

      @ApiModelProperty(value = "바이너리값", required = true)
      private String binVal;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetBinId {

      @ApiModelProperty(value = "콘텐츠버전식별번호", required = true)
      private UUID cttBinId;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "콘텐츠바이너리식별번호")
      private UUID cttBinId;

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "바이너리값")
      private String binVal;

      @ApiModelProperty(value = "콘텐츠속성내용")
      private String cttPropCont;

      @ApiModelProperty(value = "첨부이미지그룹식별번호")
      private UUID attachImgGroupId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "활성여부")
      private String activeYn;

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

      @ApiModelProperty(value = "콘텐츠바이너리식별번호")
      private UUID cttBinId;

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "바이너리값")
      private String binVal;

      @ApiModelProperty(value = "콘텐츠속성내용")
      private String cttPropCont;

      @ApiModelProperty(value = "첨부이미지그룹식별번호")
      private UUID attachImgGroupId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "활성여부")
      private String activeYn;

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
