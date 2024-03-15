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

public class CttJudgeReqForm {

  public static class Input {

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

      @ApiModelProperty(value = "심사상태코드 Code Group: CT_003")
      private String judgeStatusCode;

      @ApiModelProperty(value = "심사유형코드 Code Group: CT_004")
      private String judgeTypeCode;

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "콘텐츠바이너리식별번호")
      private UUID cttBinId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "콘텐츠유형코드 Code Group: CT_001", required = true)
      @NotBlank(message = "콘텐츠유형코드는 필수입력 항목입니다.")
      private String cttTypeCode;

      @ApiModelProperty(value = "콘텐츠명", required = true)
      @NotBlank(message = "콘텐츠명은 필수입력 항목입니다.")
      private String cttName;

      @ApiModelProperty(value = "콘텐츠전시명")
      private String cttDpName;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "아바타이미지그룹식별번호")
      private UUID avtImgGroupId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "콘텐츠버전")
      private CttBinForm.Input.Add cttBin;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "콘텐츠명")
      @NotBlank(message = "콘텐츠명은 필수입력 항목입니다.")
      private String cttName;

      @ApiModelProperty(value = "콘텐츠전시명")
      private String cttDpName;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "아바타이미지그룹식별번호")
      private UUID avtImgGroupId;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "콘텐츠버전")
      private CttBinForm.Input.Modify cttBin;
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

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApprovalRequest {

      @ApiModelProperty(value = "콘텐츠심사요청식별번호", required = true)
      private UUID cttJudgeReqId;

      @ApiModelProperty(value = "심사유형코드 Code Group: CT_004", required = true)
      @NotBlank(message = "심사유형코드는 필수입력 항목입니다.")
      private String judgeTypeCode;

      @ApiModelProperty(value = "심사요청내용")
      private String judgeReqCont;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetActiveReq {

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "콘텐츠심사요청식별번호")
      private UUID cttJudgeReqId;

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "콘텐츠바이너리식별번호")
      private UUID cttBinId;

      @ApiModelProperty(value = "심사유형코드 Code Group: CT_004")
      private String judgeTypeCode;

      @ApiModelProperty(value = "심사요청내용")
      private String judgeReqCont;

      @ApiModelProperty(value = "콘텐츠명")
      private String cttName;

      @ApiModelProperty(value = "콘텐츠전시명")
      private String cttDpName;

      @ApiModelProperty(value = "비고내용")
      private String remarkCont;

      @ApiModelProperty(value = "심사상태코드 Code Group: CT_003")
      private String judgeStatusCode;

      @ApiModelProperty(value = "반려유형코드 Code Group: CT_005")
      private String rejectTypeCode;

      @ApiModelProperty(value = "반려사유")
      private String rejectReason;

      @ApiModelProperty(value = "요청사용자식별번호")
      private UUID reqUserId;

      @ApiModelProperty(value = "요청일시")
      private DateTime reqDt;

      @ApiModelProperty(value = "처리사용자식별번호")
      private UUID procUserId;

      @ApiModelProperty(value = "처리일시")
      private DateTime procDt;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @ApiModelProperty(value = "전시유형코드 Code Group: CT_006")
      private String dpTypeCode;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "아바타이미지그룹식별번호")
      private UUID avtImgGroupId;

      @ApiModelProperty(value = "콘텐츠유형코드 Code Group: CT_001")
      private String cttTypeCode;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "콘텐츠심사요청식별번호")
      private UUID cttJudgeReqId;

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "콘텐츠바이너리식별번호")
      private UUID cttBinId;

      @ApiModelProperty(value = "심사유형코드 Code Group: CT_004")
      private String judgeTypeCode;

      @ApiModelProperty(value = "심사요청내용")
      private String judgeReqCont;

      @ApiModelProperty(value = "콘텐츠명")
      private String cttName;

      @ApiModelProperty(value = "콘텐츠전시명")
      private String cttDpName;

      @ApiModelProperty(value = "비고내용")
      private String remarkCont;

      @ApiModelProperty(value = "심사상태코드 Code Group: CT_003")
      private String judgeStatusCode;

      @ApiModelProperty(value = "반려유형코드 Code Group: CT_005")
      private String rejectTypeCode;

      @ApiModelProperty(value = "반려사유")
      private String rejectReason;

      @ApiModelProperty(value = "요청사용자식별번호")
      private UUID reqUserId;

      @ApiModelProperty(value = "요청일시")
      private DateTime reqDt;

      @ApiModelProperty(value = "처리사용자식별번호")
      private UUID procUserId;

      @ApiModelProperty(value = "처리일시")
      private DateTime procDt;

      @ApiModelProperty(value = "전시유형코드 Code Group: CT_006")
      private String dpTypeCode;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "첨부파일그룹식별번호")
      private UUID attachFileGroupId;

      @ApiModelProperty(value = "아바타이미지그룹식별번호")
      private UUID avtImgGroupId;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @ApiModelProperty(value = "콘텐츠유형코드 Code Group: CT_001")
      private String cttTypeCode;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetActive {

      @ApiModelProperty(value = "콘텐츠심사요청식별번호")
      private UUID cttJudgeReqId;

      @ApiModelProperty(value = "심사상태코드 Code Group: CT_003")
      private String judgeStatusCode;
    }
  }
}
