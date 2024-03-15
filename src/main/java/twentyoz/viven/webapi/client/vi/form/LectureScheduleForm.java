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

public class LectureScheduleForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "강의수업식별번호")
      private UUID id;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "강의식별번호", required = true)
      private UUID lectureId;

      @ApiModelProperty(value = "강의수업이름", required = true)
      private String lectureScheduleName;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "시작시간", required = true)
      private String startDt;

      @ApiModelProperty(value = "종료시간", required = true)
      private String endDt;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "강의식별번호", required = true)
      private UUID lectureId;

      @ApiModelProperty(value = "강의수업이름", required = true)
      private String lectureScheduleName;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "시작시간", required = true)
      private String startDt;

      @ApiModelProperty(value = "종료시간", required = true)
      private String endDt;



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
      
      @ApiModelProperty(value = "강의수업식별번호")
      private UUID lectureScheduleId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의수업이름")
      private String lectureScheduleName;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "시작시간")
      private DateTime startDt;

      @ApiModelProperty(value = "종료시간")
      private DateTime endDt;

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
      
      @ApiModelProperty(value = "강의수업식별번호")
      private UUID lectureScheduleId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "강의수업이름")
      private String lectureScheduleName;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "시작시간")
      private DateTime startDt;

      @ApiModelProperty(value = "종료시간")
      private DateTime endDt;

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
