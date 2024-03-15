package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;

import javax.validation.constraints.Positive;

public class ExamForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "시험지 유형 코드")
      private String examTypeCode;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

      @ApiModelProperty("시험지이름")
      private String examName;

      @ApiModelProperty("키워드")
      private String keyword;

      @ApiModelProperty("삭제여부")
      private String delYn;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "시험지이름", required = true)
      private String examName;

      @ApiModelProperty(value = "시험소요시간(분)")
      @Positive(message = "숫자만 입력 가능합니다")
      private Integer examPeriod;

      @ApiModelProperty(value = "시험지유형코드", required = true)
      private String examTypeCode;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "키워드")
      private String keyword;

      @ApiModelProperty(value = "사용여부", required = true)
      private String useYn;


    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
      
      @ApiModelProperty(value = "시험지식별번호", required = true)
      private UUID examId;

      @ApiModelProperty(value = "시험지이름")
      private String examName;

      @ApiModelProperty(value = "시험소요시간(분)")
      @Positive(message = "숫자만 입력 가능합니다")
      private Integer examPeriod;

      @ApiModelProperty(value = "시험지유형코드")
      private String examTypeCode;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "키워드")
      private String keyword;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

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
      
      @ApiModelProperty(value = "시험지식별번호")
      private UUID examId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "시험지이름")
      private String examName;

      @ApiModelProperty(value = "시험지유형코드")
      private String examTypeCode;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "키워드")
      private String keyword;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "시험소요시간(분)")
      private Integer examPeriod;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

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
      
      @ApiModelProperty(value = "시험지식별번호")
      private UUID examId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "시험지이름")
      private String examName;

      @ApiModelProperty(value = "시험지유형코드")
      private String examTypeCode;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "키워드")
      private String keyword;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "시험소요시간(분)")
      private Integer examPeriod;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

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
