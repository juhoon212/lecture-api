package twentyoz.viven.feature.vi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViRoomSvMapper {

  public ViRoomSv modify(ViRoomSv in, ViRoomSv out) {
    if (in == null) {
      return null;
    }

    // 방서버번호
    if (StringUtils.isNotEmpty(in.getRoomSvNo())) {
      out.setRoomSvNo(in.getRoomSvNo());
    }

    // 방서버명
    if (StringUtils.isNotEmpty(in.getRoomSvName())) {
      out.setRoomSvName(in.getRoomSvName());
    }

    // 액세스토큰
    if (StringUtils.isNotEmpty(in.getRoomSvStatusCode())) {
      out.setRoomSvStatusCode(in.getRoomSvStatusCode());
    }

    // 액세스토큰
    if (StringUtils.isNotEmpty(in.getAccessToken())) {
      out.setAccessToken(in.getAccessToken());
    }

    // 아이피주소
    if (StringUtils.isNotEmpty(in.getIpAddr())) {
      out.setIpAddr(in.getIpAddr());
    }

    // 설명내용
    if (StringUtils.isNotEmpty(in.getDescCont())) {
      out.setDescCont(in.getDescCont());
    }

    // 사용여부
    if (StringUtils.isNotEmpty(in.getUseYn())) {
      out.setUseYn(in.getUseYn());
    }

    // 삭제여부
    if (StringUtils.isNotEmpty(in.getDelYn())) {
      out.setDelYn(in.getDelYn());
    }

    return out;
  }
}
