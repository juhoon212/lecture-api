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

public class RoomForm {

  public static class Input {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "방번호")
      private String roomNo;

      @ApiModelProperty(value = "방이름")
      private String roomName;

      @ApiModelProperty(value = "키워드")
      private String keyword;

      @ApiModelProperty(value = "회원닉네임")
      private String mbrNickname;

      @ApiModelProperty(value = "방 유형")
      private String roomTypeCode;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "방번호")
      private String roomNo;

      @ApiModelProperty(value = "방이름", required = true)
      private String roomName;

      @ApiModelProperty(value = "콘텐츠식별번호", required = true)
      private UUID cttId;

      @ApiModelProperty(value = "공개여부", required = true)
      private String publicYn;

      @ApiModelProperty(value = "방비밀번호")
      private String roomPw;

      @ApiModelProperty(value = "전시여부", required = true)
      private String dpYn;

      @ApiModelProperty(value = "예약여부", required = true)
      private String reservYn;

      @ApiModelProperty(value = "시작예약시간")
      private DateTime startReservTime;

      @ApiModelProperty(value = "종료예약시간")
      private DateTime endReservTime;

      @ApiModelProperty(value = "인원제한")
      private Integer perLimit;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "키워드")
      private String keyword;

      @ApiModelProperty(value = "방 유형 코드", required = true)
      private String roomTypeCode;
    }

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "방식별번호", required = true)
      private UUID roomId;

      @ApiModelProperty(value = "방번호")
      private String roomNo;

      @ApiModelProperty(value = "방이름", required = true)
      private String roomName;

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "공개여부", required = true)
      private String publicYn;

      @ApiModelProperty(value = "방비밀번호")
      private String roomPw;

      @ApiModelProperty(value = "전시여부", required = true)
      private String dpYn;

      @ApiModelProperty(value = "예약여부", required = true)
      private String reservYn;

      @ApiModelProperty(value = "시작예약시간")
      private DateTime startReservTime;

      @ApiModelProperty(value = "종료예약시간")
      private DateTime endReservTime;

      @ApiModelProperty(value = "인원제한")
      private Integer perLimit;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "키워드")
      private String keyword;

      @ApiModelProperty(value = "방 유형 변호")
      private String roomTypeCode;
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
    public static class CheckPassword {

      @ApiModelProperty(value = "방비밀번호")
      private String roomPw;
    }
  }

  public static class Output {

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "방번호")
      private String roomNo;

      @ApiModelProperty(value = "방이름")
      private String roomName;

      @ApiModelProperty(value = "방서버식별번호")
      private UUID roomSvId;

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "공개여부")
      private String publicYn;

      @ApiModelProperty(value = "전시여부")
      private String dpYn;

      @ApiModelProperty(value = "예약여부")
      private String reservYn;

      @ApiModelProperty(value = "온라인여부")
      private String onlineYn;

      @ApiModelProperty(value = "시작예약시간")
      private DateTime startReservTime;

      @ApiModelProperty(value = "종료예약시간")
      private DateTime endReservTime;

      @ApiModelProperty(value = "인원제한")
      private Integer perLimit;

      @ApiModelProperty(value = "접속인원")
      private Integer connPer;

      @ApiModelProperty(value = "초대링크")
      private String invLink;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "키워드")
      private String keyword;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @ApiModelProperty(value = "방 유형 코드")
      private String roomTypeCode;


    }

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Get {

      @ApiModelProperty(value = "방식별번호")
      private UUID roomId;

      @ApiModelProperty(value = "방번호")
      private String roomNo;

      @ApiModelProperty(value = "방이름")
      private String roomName;

      @ApiModelProperty(value = "방서버식별번호")
      private UUID roomSvId;

      @ApiModelProperty(value = "콘텐츠식별번호")
      private UUID cttId;

      @ApiModelProperty(value = "공개여부")
      private String publicYn;

      @ApiModelProperty(value = "온라인여부")
      private String onlineYn;

      @ApiModelProperty(value = "전시여부")
      private String dpYn;

      @ApiModelProperty(value = "예약여부")
      private String reservYn;

      @ApiModelProperty(value = "시작예약시간")
      private DateTime startReservTime;

      @ApiModelProperty(value = "종료예약시간")
      private DateTime endReservTime;

      @ApiModelProperty(value = "인원제한")
      private Integer perLimit;

      @ApiModelProperty(value = "접속인원")
      private Integer connPer;

      @ApiModelProperty(value = "초대링크")
      private String invLink;

      @ApiModelProperty(value = "설명내용")
      private String descCont;

      @ApiModelProperty(value = "키워드")
      private String keyword;

      @ApiModelProperty(value = "사용여부")
      private String useYn;

      @ApiModelProperty(value = "등록자식별번호")
      private UUID regId;

      @ApiModelProperty(value = "등록일시")
      private DateTime regDt;

      @ApiModelProperty(value = "수정자식별번호")
      private UUID modId;

      @ApiModelProperty(value = "수정일시")
      private DateTime modDt;

      @ApiModelProperty(value = "방 유형 코드")
      private String roomTypeCode;
    }
  }
}
