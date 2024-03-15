package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

public class RoomSvForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "방서버번호")
      private String roomSvNo;

      @ApiModelProperty(value = "방서버명")
      private String roomSvName;

      @ApiModelProperty(value = "방서버식별번호")
      private UUID roomSvId;

      @ApiModelProperty(value = "방서버상태코드")
      private String roomSvStatusCode;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

      @ApiModelProperty(value = "삭제여부")
      private String delYn;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "방서버번호")
      private String roomSvNo;

      @ApiModelProperty(value = "방서버명")
      private String roomSvName;

      //      @ApiModelProperty(value = "액세스토큰")
      //      private String accessToken;
      //
      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "사용여부")
      private String useYn;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "방서버식별번호", required = true)
      private UUID roomSvId;

      @ApiModelProperty(value = "방서버번호")
      private String roomSvNo;

      @ApiModelProperty(value = "방서버명")
      private String roomSvName;

      @ApiModelProperty(value = "방서버상태코드")
      private String roomSvStatusCode;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "사용여부")
      private String useYn;
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

      @ApiModelProperty(value = "방서버식별번호")
      private UUID roomSvId;

      @ApiModelProperty(value = "방서버번호")
      private String roomSvNo;

      @ApiModelProperty(value = "방서버명")
      private String roomSvName;

      @ApiModelProperty(value = "방서버상태코드")
      private String roomSvStatusCode;

      @ApiModelProperty(value = "액세스토큰")
      private String accessToken;

      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

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

      @ApiModelProperty(value = "방서버식별번호")
      private UUID roomSvId;

      @ApiModelProperty(value = "방서버번호")
      private String roomSvNo;

      @ApiModelProperty(value = "방서버명")
      private String roomSvName;

      @ApiModelProperty(value = "방서버상태코드")
      private String roomSvStatusCode;

      @ApiModelProperty(value = "액세스토큰")
      private String accessToken;

      @ApiModelProperty(value = "아이피주소")
      private String ipAddr;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

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
