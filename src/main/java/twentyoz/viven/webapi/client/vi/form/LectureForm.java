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

public class LectureForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "방 번호")
      private UUID roomId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {
      
      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "강의이름")
      private String lectureName;

      @ApiModelProperty("강의 계획서 첨부파일 그룹 식별번호")
      private UUID lecturePlanFileGroupId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
      
      @ApiModelProperty(value = "강의식별번호", required = true)
      private UUID lectureId;

      @ApiModelProperty(value = "강의이름")
      private String lectureName;

      @ApiModelProperty("강의 계획서 첨부파일 그룹 식별번호")
      private UUID lecturePlanFileGroupId;

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
      
      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "강의이름")
      private String lectureName;

      @ApiModelProperty("강의 계획서 첨부파일 그룹 식별번호")
      private UUID lecturePlanFileGroupId;

      @ApiModelProperty(value = "방 컨텐츠 식별번호")
      private UUID mapCttId;

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
      
      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "강의이름")
      private String lectureName;

      @ApiModelProperty("강의 계획서 첨부파일 그룹 식별번호")
      private UUID lecturePlanFileGroupId;

      @ApiModelProperty(value = "방 컨텐츠 식별번호")
      private UUID mapCttId;


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
