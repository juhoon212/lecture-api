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

public class LectureQuestForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "강의시험지 식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "강의 식별 번호")
      private UUID lectureId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty("강의 시험 문제 목록")
      private List<AddList> lectureExamList;

      @Data
      @Builder
      @ToString
      @NoArgsConstructor
      @AllArgsConstructor
      public static class AddList {

        @ApiModelProperty(value = "강의식별번호")
        private UUID lectureId;

        @ApiModelProperty(value = "강의시험지식별번호")
        private UUID lectureExamId;

        @ApiModelProperty(value = "시험지문제식별번호")
        private UUID examItemId;

        @ApiModelProperty(value = "시험문제식별번호")
        private UUID examQuestId;

        @ApiModelProperty(value = "시험문제 이름")
        private String questName;

        @ApiModelProperty(value = "시험문제 유형 코드")
        private String questTypeCode;

        @ApiModelProperty(value = "시험문제 내용")
        private String questCont;

        @ApiModelProperty(value = "시험 문제 첨부파일 식별번호")
        private UUID questFileGroupId;

        @ApiModelProperty(value = "객관식문항설정정보")
        private String choiceCont;

        @ApiModelProperty(value = "배점")
        private Integer score;

        @ApiModelProperty(value = "정답")
        private List<String> answer;

        @ApiModelProperty(value = "골든벨가능여부")
        private String goldenbellYn;

        @ApiModelProperty(value = "정렬순서")
        private Integer sortOrd;
      }
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddOne {

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "시험지문제식별번호")
      private UUID examItemId;

      @ApiModelProperty(value = "시험문제식별번호")
      private UUID examQuestId;

      @ApiModelProperty(value = "시험문제 이름")
      private String questName;

      @ApiModelProperty(value = "시험문제 유형 코드")
      private String questTypeCode;

      @ApiModelProperty(value = "시험문제 내용")
      private String questCont;

      @ApiModelProperty(value = "시험 문제 첨부파일 식별번호")
      private UUID questFileGroupId;

      @ApiModelProperty(value = "객관식문항설정정보")
      private String choiceCont;

      @ApiModelProperty(value = "배점")
      private Integer score;

      @ApiModelProperty(value = "정답")
      private List<String> answer;

      @ApiModelProperty(value = "골든벨가능여부")
      private String goldenbellYn;

      @ApiModelProperty(value = "정렬순서")
      private Integer sortOrd;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
      
      @ApiModelProperty(value = "강의시험문제식별번호", required = true)
      private UUID lectureQuestId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "시험지문제식별번호")
      private UUID examItemId;

      @ApiModelProperty(value = "시험문제식별번호")
      private UUID examQuestId;

      @ApiModelProperty(value = "시험문제 이름")
      private String questName;

      @ApiModelProperty(value = "시험문제 유형 코드")
      private String questTypeCode;

      @ApiModelProperty(value = "시험문제 내용")
      private String questCont;

      @ApiModelProperty(value = "시험 문제 첨부파일 식별번호")
      private UUID questFileGroupId;

      @ApiModelProperty(value = "객관식문항설정정보")
      private String choiceCont;

      @ApiModelProperty(value = "배점")
      private Integer score;

      @ApiModelProperty(value = "정답")
      private List<String> answer;

      @ApiModelProperty(value = "골든벨가능여부")
      private String goldenbellYn;

      @ApiModelProperty(value = "정렬순서")
      private Integer sortOrd;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DtsGet{

      @ApiModelProperty(value = "시험 문제 식별번호")
      private UUID lectureQuestId;

      @ApiModelProperty(value = "사용자 답안")
      private List<String> answer;

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
      
      @ApiModelProperty(value = "강의시험문제식별번호")
      private UUID lectureQuestId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "시험지문제식별번호")
      private UUID examItemId;

      @ApiModelProperty(value = "시험문제식별번호")
      private UUID examQuestId;

      @ApiModelProperty(value = "시험문제 이름")
      private String questName;

      @ApiModelProperty(value = "시험문제 유형 코드")
      private String questTypeCode;

      @ApiModelProperty(value = "시험문제 내용")
      private String questCont;

      @ApiModelProperty(value = "객관식문항설정정보")
      private String choiceCont;

      @ApiModelProperty(value = "시험 문제 첨부파일 식별번호")
      private UUID questFileGroupId;

      @ApiModelProperty(value = "배점")
      private Integer score;

      @ApiModelProperty(value = "정답")
      private List<String> answer;

      @ApiModelProperty(value = "골든벨가능여부")
      private String goldenbellYn;

      @ApiModelProperty(value = "정렬순서")
      private Integer sortOrd;

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
      
      @ApiModelProperty(value = "강의시험문제식별번호")
      private UUID lectureQuestId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "시험지문제식별번호")
      private UUID examItemId;

      @ApiModelProperty(value = "시험문제식별번호")
      private UUID examQuestId;

      @ApiModelProperty(value = "시험문제 이름")
      private String questName;

      @ApiModelProperty(value = "시험문제 유형 코드")
      private String questTypeCode;

      @ApiModelProperty(value = "시험문제 내용")
      private String questCont;

      @ApiModelProperty(value = "시험 문제 첨부파일 식별번호")
      private UUID questFileGroupId;

      @ApiModelProperty(value = "객관식문항설정정보")
      private String choiceCont;

      @ApiModelProperty(value = "배점")
      private Integer score;

      @ApiModelProperty(value = "정답")
      private List<String> answer;

      @ApiModelProperty(value = "골든벨가능여부")
      private String goldenbellYn;

      @ApiModelProperty(value = "정렬순서")
      private Integer sortOrd;

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
    public static class GetNoAnswer {

      @ApiModelProperty(value = "강의시험문제식별번호")
      private UUID lectureQuestId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의시험지식별번호")
      private UUID lectureExamId;

      @ApiModelProperty(value = "시험지문제식별번호")
      private UUID examItemId;

      @ApiModelProperty(value = "시험문제식별번호")
      private UUID examQuestId;

      @ApiModelProperty(value = "시험문제 이름")
      private String questName;

      @ApiModelProperty(value = "시험문제 유형 코드")
      private String questTypeCode;

      @ApiModelProperty(value = "시험문제 내용")
      private String questCont;

      @ApiModelProperty(value = "시험 문제 첨부파일 식별번호")
      private UUID questFileGroupId;

      @ApiModelProperty(value = "객관식문항설정정보")
      private String choiceCont;

      @ApiModelProperty(value = "배점")
      private Integer score;

      @ApiModelProperty(value = "골든벨가능여부")
      private String goldenbellYn;

      @ApiModelProperty(value = "정렬순서")
      private Integer sortOrd;

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
