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

public class LectureRefForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "자료 이름")
      private String refName;

      @ApiModelProperty(value = "설명 내용")
      private String descCont;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {
      
      @ApiModelProperty(value = "강의식별번호", required = true)
      private UUID lectureId;

      @ApiModelProperty(value = "자료식별번호")
      private UUID refId;

      @ApiModelProperty(value = "자료이름", required = true)
      private String refName;

      @ApiModelProperty(value = "자료유형코드", required = true)
      private String refTypeCode;

      @ApiModelProperty(value = "링크")
      private String refLink;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
      
      @ApiModelProperty(value = "강의자료식별번호", required = true)
      private UUID lectureRefId;

      @ApiModelProperty(value = "자료 식별 번호")
      private UUID refId;

      @ApiModelProperty(value = "자료이름")
      private String refName;

      @ApiModelProperty(value = "자료유형코드")
      private String refTypeCode;

      @ApiModelProperty(value = "링크")
      private String refLink;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

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
      
      @ApiModelProperty(value = "강의자료식별번호")
      private UUID lectureRefId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "자료식별번호")
      private UUID refId;

      @ApiModelProperty(value = "자료이름")
      private String refName;

      @ApiModelProperty(value = "자료유형코드")
      private String refTypeCode;

      @ApiModelProperty(value = "링크")
      private String refLink;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

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

    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
      
      @ApiModelProperty(value = "강의자료식별번호")
      private UUID lectureRefId;

      @ApiModelProperty(value = "강의식별번호")
      private UUID lectureId;

      @ApiModelProperty(value = "자료식별번호")
      private UUID refId;

      @ApiModelProperty(value = "자료이름")
      private String refName;

      @ApiModelProperty(value = "자료유형코드")
      private String refTypeCode;

      @ApiModelProperty(value = "링크")
      private String refLink;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

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

    }

  }
}
