package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

public class CttForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAllAvt {

      @ApiModelProperty(value = "콘텐츠전시명")
      private String cttDpName;

      @ApiModelProperty(value = "콘텐츠유형코드 Code Group: CT_001")
      private String cttTypeCode;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "콘텐츠명")
      private String cttName;

      @ApiModelProperty(value = "콘텐츠전시명")
      private String cttDpName;

      @ApiModelProperty(value = "콘텐츠유형코드 Code Group: CT_001")
      private String cttTypeCode;

      @ApiModelProperty(value = "전시여부")
      private String dpYn;

      @ApiModelProperty(value = "나의 콘텐츠 여부", required = true)
      private Boolean isMyCtt;

      @ApiModelProperty(value = "콘텐츠맵유형")
      private List<String> cttMapTypes;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "나의 콘텐츠 여부")
      private Boolean isMyCtt;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetId {

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "전시여부", required = true)
      @NotBlank(message = "전시여부 은/는 필수입력 항목입니다.")
      private String dpYn;

      @ApiModelProperty(value = "전시시작일시")
      private DateTime dpStartDt;

      @ApiModelProperty(value = "전시종료일시")
      private DateTime dpEndDt;
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

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "콘텐츠번호")
      private String cttNo;

      @ApiModelProperty(value = "콘텐츠유형코드 Code Group: CT_001")
      private String cttTypeCode;

      @ApiModelProperty(value = "콘텐츠맵유형")
      private String cttMapType;

      @ApiModelProperty(value = "콘텐츠명")
      private String cttName;

      @ApiModelProperty(value = "콘텐츠전시명")
      private String cttDpName;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "판매상태코드 Code Group: CT_002")
      private String sellStatusCode;

      @ApiModelProperty(value = "전시여부")
      private String dpYn;

      @ApiModelProperty(value = "전시시작일시")
      private DateTime dpStartDt;

      @ApiModelProperty(value = "전시종료일시")
      private DateTime dpEndDt;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "아바타이미지그룹식별번호")
      private UUID avtImgGroupId;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

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

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "콘텐츠번호")
      private String cttNo;

      @ApiModelProperty(value = "콘텐츠유형코드 Code Group: CT_001")
      private String cttTypeCode;

      @ApiModelProperty(value = "콘텐츠맵유형")
      private String cttMapType;

      @ApiModelProperty(value = "콘텐츠명")
      private String cttName;

      @ApiModelProperty(value = "콘텐츠전시명")
      private String cttDpName;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "판매상태코드 Code Group: CT_002")
      private String sellStatusCode;

      @ApiModelProperty(value = "전시여부")
      private String dpYn;

      @ApiModelProperty(value = "전시시작일시")
      private DateTime dpStartDt;

      @ApiModelProperty(value = "전시종료일시")
      private DateTime dpEndDt;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "아바타이미지그룹식별번호")
      private UUID avtImgGroupId;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;

      @ApiModelProperty(value = "회원식별번호")
      private UUID mbrId;

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
