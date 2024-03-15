package twentyoz.viven.webapi.client.sy.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class AttachFileForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "첨부파일그룹식별번호", required = true)
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileId;

      @ApiModelProperty(value = "첨부구분값")
      private String attachDivVal;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "첨부구분값")
      private String attachDivVal;

      @ApiModelProperty(value = "정렬순서")
      private Integer sortOrd;

      @ApiModelProperty(value = "비고내용")
      private String remarkCont;

      @ApiModelProperty(
          value = "root 경로 (아바타: /ctt/avt, 맵: /ctt/map, 오브젝트: /ctt/obj",
          required = true)
      private String rootDir;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "첨부구분값")
      private String attachDivVal;

      @ApiModelProperty(value = "정렬순서")
      private Integer sortOrd;

      @ApiModelProperty(value = "비고내용")
      private String remarkCont;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Remove {

      @ApiModelProperty(value = "첨부파일식별번호 목록")
      private List<UUID> attachFileIds;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "첨부파일식별번호")
      private UUID attachFileId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "첨부구분값")
      private String attachDivVal;

      @ApiModelProperty(value = "파일명")
      private String fileName;

      @ApiModelProperty(value = "파일전체경로")
      private String fileFullPath;

      @ApiModelProperty(value = "파일확장자")
      private String fileExt;

      @ApiModelProperty(value = "파일사이즈")
      private String fileSize;

      @ApiModelProperty(value = "파일MIME유형값")
      private String fileMimeTypeVal;

      @ApiModelProperty(value = "원본파일명")
      private String oriFileName;

      @ApiModelProperty(value = "원본파일용량")
      private String oriFileCapa;

      @ApiModelProperty(value = "정렬순서")
      private Integer sortOrd;

      @ApiModelProperty(value = "비고내용")
      private String remarkCont;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "첨부파일식별번호")
      private UUID attachFileId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "첨부구분값")
      private String attachDivVal;

      @ApiModelProperty(value = "파일명")
      private String fileName;

      @ApiModelProperty(value = "파일전체경로")
      private String fileFullPath;

      @ApiModelProperty(value = "파일확장자")
      private String fileExt;

      @ApiModelProperty(value = "파일사이즈")
      private String fileSize;

      @ApiModelProperty(value = "파일MIME유형값")
      private String fileMimeTypeVal;

      @ApiModelProperty(value = "원본파일명")
      private String oriFileName;

      @ApiModelProperty(value = "원본파일용량")
      private String oriFileCapa;

      @ApiModelProperty(value = "정렬순서")
      private Integer sortOrd;

      @ApiModelProperty(value = "비고내용")
      private String remarkCont;
    }
  }
}
