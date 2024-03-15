package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExamQuestForm {

  public static class Input {

    @Data
    @ToString
    @NoArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "시험문제 유형 코드, Exam_001_001 : 객관식 단일," +
              " Exam_001_002 : 객관식 복수," +
              " Exam_001_003 : OX," +
              " Exam_001_004 : 단답형" +
              " Exam_001_005 : 서술형"
      )
      private String questTypeCode;

      @ApiModelProperty(value = "시험문제 이름")
      private String questName;

      @ApiModelProperty(value = "키워드")
      private String keyword;

      @ApiModelProperty(value = "골든벨 가능 여부")
      private String goldenbellYn;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "시험문제 이름", required = true)
      private String questName;

      @ApiModelProperty(value = "시험문제 유형 코드, Exam_001_001 : 객관식 단일," +
              " Exam_001_002 : 객관식 복수," +
              " Exam_001_003 : OX," +
              " Exam_001_004 : 단답형" +
              " Exam_001_005 : 서술형"
              , required = true)
      private String questTypeCode;

      @ApiModelProperty(value = "시험문제 내용", required = true)
      private String questCont;

      @ApiModelProperty(value = "객관식문항설정정보", required = true)
      private String choiceCont;

      @ApiModelProperty(value = "배점", required = true)
      private Integer score;

      @ApiModelProperty(value = "정답", required = true)
      private List<String> answer;

      @ApiModelProperty(value = "골든벨 가능여부")
      private String goldenbellYn;

      @ApiModelProperty(value = "시험문제 첨부파일 식별번호")
      private UUID questFileGroupId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "시험문제 이름")
      private String questName;

      @ApiModelProperty(value = "시험문제 유형 코드, Exam_001_001 : 객관식 단일," +
              " Exam_001_002 : 객관식 복수," +
              " Exam_001_003 : OX," +
              " Exam_001_004 : 단답형" +
              " Exam_001_005 : 서술형"
      )
      private String questTypeCode;

      @ApiModelProperty(value = "시험문제 내용")
      private String questCont;

      @ApiModelProperty(value = "객관식문항설정정보")
      private String choiceCont;

      @ApiModelProperty(value = "배점")
      private Integer score;

      @ApiModelProperty(value = "정답")
      private List<String> answer = new ArrayList<>();

      @ApiModelProperty(value = "골든벨 가능여부")
      private String goldenbellYn;

      @ApiModelProperty(value = "시험문제 첨부파일 식별번호")
      private UUID questFileGroupId;
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
      
      @ApiModelProperty(value = "시험문제식별번호")
      private UUID examQuestId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "시험문제 이름")
      private String questName;

      @ApiModelProperty(value = "시험문제 유형 코드, Exam_001_001 : 객관식 단일," +
              " Exam_001_002 : 객관식 복수," +
              " Exam_001_003 : OX," +
              " Exam_001_004 : 단답형" +
              " Exam_001_005 : 서술형"
      )
      private String questTypeCode;

      @ApiModelProperty(value = "시험문제 내용")
      private String questCont;

      @ApiModelProperty(value = "객관식문항설정정보")
      private String choiceCont;

      @ApiModelProperty(value = "배점")
      private Integer score;

      @ApiModelProperty(value = "정답")
      private List<String> answer;

      @ApiModelProperty(value = "골든벨가능여부")
      private String goldenbellYn;

      @ApiModelProperty(value = "시험문제 첨부파일 식별번호")
      private UUID questFileGroupId;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @Data
      @Builder
      @ToString
      @NoArgsConstructor
      @AllArgsConstructor
      public static class ChoiceCont {

        private UUID id;
        private String title;
        private Integer sortOrd;
        private String value;

      }
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
      
      @ApiModelProperty(value = "시험문제식별번호")
      private UUID examQuestId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "시험문제 이름")
      private String questName;

      @ApiModelProperty(value = "시험문제 유형 코드, Exam_001_001 : 객관식 단일," +
              " Exam_001_002 : 객관식 복수," +
              " Exam_001_003 : OX," +
              " Exam_001_004 : 단답형" +
              " Exam_001_005 : 서술형"
      )
      private String questTypeCode;

      @ApiModelProperty(value = "시험문제 내용")
      private String questCont;

      @ApiModelProperty(value = "객관식문항설정정보")
      private String choiceCont;

      @ApiModelProperty(value = "배점")
      private Integer score;

      @ApiModelProperty(value = "정답")
      private List<String> answer;

      @ApiModelProperty(value = "골든벨가능여부")
      private String goldenbellYn;

      @ApiModelProperty(value = "시험문제 첨부파일 식별번호")
      private UUID questFileGroupId;

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
