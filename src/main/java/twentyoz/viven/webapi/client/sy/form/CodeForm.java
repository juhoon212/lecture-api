package twentyoz.viven.webapi.client.sy.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class CodeForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "")
      private String codeGroup;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "")
      private String code;

      @ApiModelProperty(value = "")
      private String refCode;

      @ApiModelProperty(value = "")
      private String codeGroup;

      @ApiModelProperty(value = "")
      private String codeName;

      @ApiModelProperty(value = "")
      private String codeAddVal1;

      @ApiModelProperty(value = "")
      private String codeAddVal2;

      @ApiModelProperty(value = "")
      private String codeAddVal3;

      @ApiModelProperty(value = "")
      private String descCont;

      @ApiModelProperty(value = "")
      private String sysCodeYn;

      @ApiModelProperty(value = "")
      private Integer sortOrd;

      @ApiModelProperty(value = "")
      private String useYn;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "")
      private String refCode;

      @ApiModelProperty(value = "")
      private String codeGroup;

      @ApiModelProperty(value = "")
      private String codeName;

      @ApiModelProperty(value = "")
      private String codeAddVal1;

      @ApiModelProperty(value = "")
      private String codeAddVal2;

      @ApiModelProperty(value = "")
      private String codeAddVal3;

      @ApiModelProperty(value = "")
      private String descCont;

      @ApiModelProperty(value = "")
      private String sysCodeYn;

      @ApiModelProperty(value = "")
      private Integer sortOrd;

      @ApiModelProperty(value = "")
      private String useYn;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "")
      private String code;

      @ApiModelProperty(value = "")
      private String refCode;

      @ApiModelProperty(value = "")
      private String codeId;

      @ApiModelProperty(value = "")
      private String codeGroup;

      @ApiModelProperty(value = "")
      private String codeName;

      @ApiModelProperty(value = "")
      private String codeAddVal1;

      @ApiModelProperty(value = "")
      private String codeAddVal2;

      @ApiModelProperty(value = "")
      private String codeAddVal3;

      @ApiModelProperty(value = "")
      private String descCont;

      @ApiModelProperty(value = "")
      private String sysCodeYn;

      @ApiModelProperty(value = "")
      private Integer sortOrd;

      @ApiModelProperty(value = "")
      private String useYn;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "")
      private String code;

      @ApiModelProperty(value = "")
      private String refCode;

      @ApiModelProperty(value = "")
      private String codeId;

      @ApiModelProperty(value = "")
      private String codeGroup;

      @ApiModelProperty(value = "")
      private String codeName;

      @ApiModelProperty(value = "")
      private String codeAddVal1;

      @ApiModelProperty(value = "")
      private String codeAddVal2;

      @ApiModelProperty(value = "")
      private String codeAddVal3;

      @ApiModelProperty(value = "")
      private String descCont;

      @ApiModelProperty(value = "")
      private String sysCodeYn;

      @ApiModelProperty(value = "")
      private Integer sortOrd;

      @ApiModelProperty(value = "")
      private String useYn;
    }
  }
}
