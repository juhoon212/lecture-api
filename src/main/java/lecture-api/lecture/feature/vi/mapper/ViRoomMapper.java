package twentyoz.viven.feature.vi.mapper;

import twentyoz.viven.feature.vi.model.ViRoom;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViRoomMapper {

  public ViRoom modify(ViRoom in, ViRoom out) {
    if (in == null) {
      return null;
    }

    // 방번호
    if (StringUtils.isNotEmpty(in.getRoomNo())) {
      out.setRoomNo(in.getRoomNo());
    }

    // 방이름
    if (StringUtils.isNotEmpty(in.getRoomName())) {
      out.setRoomName(in.getRoomName());
    }

    // 방서버식별번호
    if (in.getRoomSvId() != null) {
      out.setRoomSvId(in.getRoomSvId());
    }

    // 콘텐츠식별번호
    if (in.getCttId() != null) {
      out.setCttId(in.getCttId());
    }

    // 회원식별번호
    if (in.getMbrId() != null) {
      out.setMbrId(in.getMbrId());
    }

    // 공개여부
    if (StringUtils.isNotEmpty(in.getPublicYn())) {
      out.setPublicYn(in.getPublicYn());
    }

    // 방비밀번호
    if (StringUtils.isNotEmpty(in.getRoomPw())) {
      out.setRoomPw(in.getRoomPw());
    }

    // 전시여부
    if (StringUtils.isNotEmpty(in.getDpYn())) {
      out.setDpYn(in.getDpYn());
    }

    // 예약여부
    if (StringUtils.isNotEmpty(in.getReservYn())) {
      out.setReservYn(in.getReservYn());
    }

    // 시작예약시간
    //    if (in.getStartReservTime() != null) {
    out.setStartReservTime(in.getStartReservTime());
    //    }

    // 종료예약시간
    //    if (in.getEndReservTime() != null) {
    out.setEndReservTime(in.getEndReservTime());
    //    }

    // 인원제한
    if (in.getPerLimit() != null) {
      out.setPerLimit(in.getPerLimit());
    }

    // 초대링크
    if (StringUtils.isNotEmpty(in.getInvLink())) {
      out.setInvLink(in.getInvLink());
    }

    // 설명내용
    if (StringUtils.isNotEmpty(in.getDescCont())) {
      out.setDescCont(in.getDescCont());
    }

    // 태그
    if (StringUtils.isNotEmpty(in.getKeyword())) {
      out.setKeyword(in.getKeyword());
    }

    // 사용여부
    if (StringUtils.isNotEmpty(in.getUseYn())) {
      out.setUseYn(in.getUseYn());
    }

    // 온라인여부
    if (StringUtils.isNotEmpty(in.getOnlineYn())) {
      out.setOnlineYn(in.getOnlineYn());
    }

    // 삭제여부
    if (StringUtils.isNotEmpty(in.getDelYn())) {
      out.setDelYn(in.getDelYn());
    }
    // 방 유형 코드
    if(StringUtils.isNotEmpty(in.getRoomTypeCode())) {
      out.setRoomTypeCode(in.getRoomTypeCode());
    }

    // 수정자 식별번호
    if (in.getMbrId() != null) {
      out.setModId(in.getMbrId());
    }

    return out;
  }
}
