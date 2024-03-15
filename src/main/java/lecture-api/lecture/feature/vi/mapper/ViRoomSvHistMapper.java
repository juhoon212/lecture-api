package twentyoz.viven.feature.vi.mapper;

import twentyoz.viven.feature.vi.model.ViRoomSvHist;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViRoomSvHistMapper {

  public ViRoomSvHist modify(ViRoomSvHist in, ViRoomSvHist out) {
    if (in == null) {
      return null;
    }

    // 방서버식별번호
    if (in.getRoomSvId() != null) {
      out.setRoomSvId(in.getRoomSvId());
    }

    // 방서버번호
    if (StringUtils.isNotEmpty(in.getRoomSvNo())) {
      out.setRoomSvNo(in.getRoomSvNo());
    }

    // 방서버명
    if (StringUtils.isNotEmpty(in.getRoomSvName())) {
      out.setRoomSvName(in.getRoomSvName());
    }

    // 서버이력유형코드
    if (StringUtils.isNotEmpty(in.getSvHistTypeCode())) {
      out.setSvHistTypeCode(in.getSvHistTypeCode());
    }

    // 로그유형코드
    if (StringUtils.isNotEmpty(in.getLogTypeCode())) {
      out.setLogTypeCode(in.getLogTypeCode());
    }

    // 메시지내용
    if (StringUtils.isNotEmpty(in.getMsgCont())) {
      out.setMsgCont(in.getMsgCont());
    }

    // 아이피주소
    if (StringUtils.isNotEmpty(in.getIpAddr())) {
      out.setIpAddr(in.getIpAddr());
    }

    // 발생일시
    if (in.getOccurDt() != null) {
      out.setOccurDt(in.getOccurDt());
    }

    return out;
  }
}
