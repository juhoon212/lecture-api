package twentyoz.viven.feature.vi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViRoomConnHistMapper {

  public ViRoomConnHist modify(ViRoomConnHist in, ViRoomConnHist out) {
    if (in == null) {
      return null;
    }

    // 방식별번호
    if (in.getRoomId() != null) {
      out.setRoomId(in.getRoomId());
    }

    // 방번호
    if (StringUtils.isNotEmpty(in.getRoomNo())) {
      out.setRoomNo(in.getRoomNo());
    }

    // 방이름
    if (StringUtils.isNotEmpty(in.getRoomName())) {
      out.setRoomName(in.getRoomName());
    }

    // 회원식별번호
    if (in.getMbrId() != null) {
      out.setMbrId(in.getMbrId());
    }

    // 로그인아이디
    if (StringUtils.isNotEmpty(in.getLoginId())) {
      out.setLoginId(in.getLoginId());
    }

    // 회원명
    if (StringUtils.isNotEmpty(in.getMbrName())) {
      out.setMbrName(in.getMbrName());
    }

    // 닉네임
    if (StringUtils.isNotEmpty(in.getNickname())) {
      out.setNickname(in.getNickname());
    }

    // 아이피주소
    if (StringUtils.isNotEmpty(in.getIpAddr())) {
      out.setIpAddr(in.getIpAddr());
    }

    // 방접속유형코드
    if (StringUtils.isNotEmpty(in.getRoomConnTypeCode())) {
      out.setRoomConnTypeCode(in.getRoomConnTypeCode());
    }

    // 접속일시
    if (in.getConnDt() != null) {
      out.setConnDt(in.getConnDt());
    }

    // 접속해제일시
    if (in.getDcnDt() != null) {
      out.setDcnDt(in.getDcnDt());
    }

    return out;
  }
}
