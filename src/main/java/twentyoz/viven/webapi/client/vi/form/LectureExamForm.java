package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.validation.constraints.Positive;

public class LectureExamForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "강의 식별번호", required = true)
      private UUID lectureId;

      @ApiModelProperty(value = "시험지 이름")
      private String examName;

      @ApiModelProperty(value = "설명 내용")
      private String descCont;

      @ApiModelProperty(value = "시험지 유형코드")
      private String examTypeCode;




    }



    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DtsAll {

      @ApiModelProperty(value = "강의 식별번호", required = true)
      private UUID lectureExamId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DtsGet {

      @ApiModelProperty(value = "강의 문제 식별번호", required = true)
      private UUID lectureQuestId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Submit {

      @ApiModelProperty(value = "강의시험 식별 번호")
      private UUID lectureExamResultId;

      @ApiModelProperty(value = "시험지문제 리스트")
      private List<SubmitList> lectureQuestIds;

      @ApiModelProperty(value = "강의 시험문제지 식별번호")
      private UUID lectureExamId;

      @Data
      @Builder
      @ToString
      @NoArgsConstructor
      @AllArgsConstructor
      public static class SubmitList {

        private UUID lectureQuestId;

        private List<String> mbrAnswer;
      }

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Reset {

      @ApiModelProperty(value = "강의시험 식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "회원 식별 번호")
      private List<UUID> mbrIdList;
    }



    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {
      
      @ApiModelProperty(value = "강의식별번호", required = true)
      private UUID lectureId;

      @ApiModelProperty(value = "시험지식별번호", required = true)
      private UUID examId;

      @ApiModelProperty(value = "시험지이름", required = true)
      private String examName;

      @ApiModelProperty(value = "시험지유형코드", required = true)
      private String examTypeCode;

      @ApiModelProperty(value = "설명내용(지침)", required = true)
      private String descCont;

      @ApiModelProperty(value = "시험시작시간")
      private String examStartDt;

      @ApiModelProperty(value = "시험종료시간")
      private String examEndDt;

      @ApiModelProperty(value = "시험소요시간(분)", required = true)
      @Positive(message = "숫자만 입력할 수 있습니다")
      private Integer examPeriod;

      @ApiModelProperty("골든벨 콘텐츠 식별번호")
      private UUID goldenbellCttId;

      @ApiModelProperty("채점 여부")
      private String markingYn;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
      
      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "시험지식별번호")
      private UUID examId;

      @ApiModelProperty(value = "시험지이름")
      private String examName;

      @ApiModelProperty(value = "시험지유형코드")
      private String examTypeCode;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "시험시작시간")
      private String examStartDt;

      @ApiModelProperty(value = "시험종료시간")
      private String examEndDt;

      @ApiModelProperty(value = "시험소요시간(분)")
      @Positive(message = "숫자(0 이상) 입력할 수 있습니다")
      private Integer examPeriod;

      @ApiModelProperty(value = "채점 완료여부")
      private String markingYn;

      @ApiModelProperty("골든벨 콘텐츠 식별번호")
      private UUID goldenbellCttId;

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
    public static class Start {

      @ApiModelProperty(value = "강의 시험 식별번호")
      private UUID lectureExamId;

    }

  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {
      
      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "시험지식별번호")
      private UUID examId;

      @ApiModelProperty(value = "시험지이름")
      private String examName;

      @ApiModelProperty(value = "시험지유형코드")
      private String examTypeCode;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "시험시작시간")
      private DateTime examStartDt;

      @ApiModelProperty(value = "시험종료시간")
      private DateTime examEndDt;

      @ApiModelProperty(value = "시험소요시간(분)")
      private Integer examPeriod;

      @ApiModelProperty(value = "골든벨 콘텐츠 식별번호")
      private UUID goldenbellCttId;

      @ApiModelProperty(value = "채점 완료여부")
      private String markingYn;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @ApiModelProperty(value = "시험 상태")
      private String examStatus;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
      
      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "시험지식별번호")
      private UUID examId;

      @ApiModelProperty(value = "시험지이름")
      private String examName;

      @ApiModelProperty(value = "시험지유형코드")
      private String examTypeCode;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "시험시작시간")
      private DateTime examStartDt;

      @ApiModelProperty(value = "시험종료시간")
      private DateTime examEndDt;

      @ApiModelProperty(value = "시험소요시간(분)")
      private Integer examPeriod;

      @ApiModelProperty(value = "골든벨 콘텐츠 식별번호")
      private UUID goldenbellCttId;

      @ApiModelProperty(value = "채점 완료여부")
      private String markingYn;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @ApiModelProperty(value = "시험 상태")
      private String examStatus;

    }

  }
}
