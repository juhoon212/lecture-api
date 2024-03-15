package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.joda.time.DateTime;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

public class ExamItemForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty("시험지 식별 번호")
      private UUID examId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor

    public static class AddAll {

      @ApiModelProperty(value = "시험지문제 목록")
      private List<Add> addList;

      @Data
      @Builder
      @ToString
      @NoArgsConstructor
      @AllArgsConstructor
      public static class Add {

        private UUID examId;

        private UUID examQuestId;

        private Integer score;

        private Integer sortOrd;
      }

    }


    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyAll {

      @ApiModelProperty("시험지문제 수정 목록")
      private List<Modify> modifyList;

      @Data
      @Builder
      @ToString
      @NoArgsConstructor
      @AllArgsConstructor
      public static class Modify {
        private UUID examItemId;

        @Positive(message = "숫자만 입력할 수 있습니다.")
        private Integer score;

        @Positive(message = "숫자만 입력할 수 있습니다.")
        private Integer sortOrd;
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
      
      @ApiModelProperty(value = "시험지문제식별번호")
      private UUID examItemId;

      @ApiModelProperty(value = "시험지식별번호")
      private UUID examId;

      @ApiModelProperty(value = "시험문제식별번호")
      private UUID examQuestId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "배점")
      private Integer score;

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
      
      @ApiModelProperty(value = "시험지문제식별번호")
      private UUID examItemId;

      @ApiModelProperty(value = "시험지식별번호")
      private UUID examId;

      @ApiModelProperty(value = "시험문제식별번호")
      private UUID examQuestId;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

      @ApiModelProperty(value = "배점")
      private Integer score;

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
