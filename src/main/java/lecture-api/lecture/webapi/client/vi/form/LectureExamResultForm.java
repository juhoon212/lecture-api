package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;

public class LectureExamResultForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "식별번호")
      private UUID lectureExamResultId;

      @ApiModelProperty(value = "강의 시험지 식별번호", required = true)
      private UUID lectureExamId;

      @ApiModelProperty(value = "강의 식별번호")
      private UUID lectureId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
      @ApiModelProperty(value = "강의 시험 결과 식별 번호")
      private UUID lectureExamResultId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {
      
      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "시험시작시간")
      private String examStartDt;

      @ApiModelProperty(value = "시험종료시간")
      private String examEndDt;

      @ApiModelProperty("채점 여부")
      private String markingYn;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "채점여부")
      private String markingYn;


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
      
      @ApiModelProperty(value = "강의시험결과식별번호")
      private UUID lectureExamResultId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "시험시작시간")
      private DateTime examStartDt;

      @ApiModelProperty(value = "시험종료시간")
      private DateTime examEndDt;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @ApiModelProperty(value = "시험결과 상태")
      private String examResultStatus;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
      
      @ApiModelProperty(value = "강의시험결과식별번호")
      private UUID lectureExamResultId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "시험시작시간")
      private DateTime examStartDt;

      @ApiModelProperty(value = "시험종료시간")
      private DateTime examEndDt;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @ApiModelProperty(value = "시험결과 상태")
      private String examResultStatus;

    }

  }
}
