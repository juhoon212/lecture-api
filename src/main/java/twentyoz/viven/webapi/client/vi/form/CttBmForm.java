package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class CttBmForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "콘텐츠전시명")
      private String cttDpName;

      @ApiModelProperty(value = "식별번호")
      private UUID id;

      @ApiModelProperty(value = "콘텐츠유형코드")
      private String cttTypeCode;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "콘텐츠즐겨찾기식별번호", required = true)
      private UUID cttBmId;

      @ApiModelProperty(value = "콘텐츠바이너리식별번호")
      private UUID cttBinId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;
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

      @ApiModelProperty(value = "콘텐츠즐겨찾기식별번호", required = true)
      private UUID cttBmId;

      @ApiModelProperty(value = "콘텐츠바이너리식별번호")
      private UUID cttBinId;

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "콘텐츠즐겨찾기식별번호", required = true)
      private UUID cttBmId;

      @ApiModelProperty(value = "콘텐츠바이너리식별번호")
      private UUID cttBinId;

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;
    }
  }
}
