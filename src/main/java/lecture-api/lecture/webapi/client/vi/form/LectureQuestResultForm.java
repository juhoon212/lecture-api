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

public class LectureQuestResultForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "강의 식별 번호")
      private UUID lectureId;
      @ApiModelProperty(value = "강의시험 문제 식별번호")
      private UUID lectureQuestId;

      @ApiModelProperty(value = "강의시험결과 식별번호")
      private UUID lectureExamResultId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty("시험시험 문제결과 리스트")
      private List<AddList> addList;

      @Data
      @Builder
      @ToString
      @NoArgsConstructor
      @AllArgsConstructor
      public static class AddList {

        @ApiModelProperty(value = "강의식별번호")
        private UUID lectureId;

        @ApiModelProperty(value = "강의시험결과식별번호")
        private UUID lectureExamResultId;

        @ApiModelProperty(value = "강의시험문제식별번호")
        private UUID lectureQuestId;

        @ApiModelProperty(value = "회원식별번호")
        private UUID mbrId;

        @ApiModelProperty(value = "사용자 답안")
        private List<String> mbrAnswer;

        @ApiModelProperty(value = "사용자 점수")
        private Integer mbrScore;
      }

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "강의문제 목록")
      List<ModifyAll> modifyList;

      @Data
      @Builder
      @ToString
      @NoArgsConstructor
      @AllArgsConstructor
      public static class ModifyAll {

        @ApiModelProperty(value = "강의시험 문제결과 식별번호")
        private UUID lectureQuestResultId;

        @ApiModelProperty(value = "사용자 점수")
        private Integer mbrScore;

      }

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
      
      @ApiModelProperty(value = "강의시험문제결과식별번호")
      private UUID lectureQuestResultId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험결과식별번호")
      private UUID lectureExamResultId;

      @ApiModelProperty(value = "강의시험문제식별번호")
      private UUID lectureQuestId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "사용자 답안")
      private List<String> mbrAnswer;

      @ApiModelProperty(value = "사용자 점수")
      private Integer mbrScore;

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
      
      @ApiModelProperty(value = "강의시험문제결과식별번호")
      private UUID lectureQuestResultId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험결과식별번호")
      private UUID lectureExamResultId;

      @ApiModelProperty(value = "강의시험문제식별번호")
      private UUID lectureQuestId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "사용자 답안")
      private List<String> mbrAnswer;

      @ApiModelProperty(value = "사용자 점수")
      private Integer mbrScore;

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
