package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;

@Mapper
public abstract class ViRoomPropMapper {

  public ViRoomProp modify(ViRoomProp in, ViRoomProp out) {
    if (in == null) {
      return null;
    }

    // 방식별번호
    if (in.getRoomId() != null) {
      out.setRoomId(in.getRoomId());
    }

    // 방속성내용
    if (in.getPropCont() != null) {
      out.setPropCont(in.getPropCont());
    }

    return out;
  }
}
