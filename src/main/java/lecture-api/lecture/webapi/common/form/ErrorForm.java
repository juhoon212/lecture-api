package twentyoz.viven.webapi.common.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

public class ErrorForm {

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Simple {

      @ApiModelProperty(value = "응답코드")
      private String retcode;

      @ApiModelProperty(value = "응답메시지")
      private String retmsg;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Field {

      @ApiModelProperty(value = "응답코드")
      private String retcode;

      @ApiModelProperty(value = "응답메시지")
      private String retmsg;

      @ApiModelProperty(value = "에러상세")
      @Accessors
      private List<Detail> errors;

      @Data
      @Builder
      @ToString
      @NoArgsConstructor
      @AllArgsConstructor
      public static class Detail {

        @ApiModelProperty(value = "필드명")
        private String field;

        @ApiModelProperty(value = "입력된 값")
        private String value;

        @ApiModelProperty(value = "입력값 오류 코드")
        private String code;

        @ApiModelProperty(value = "입력값 오류 사유")
        private String reason;
      }
    }
  }
}
