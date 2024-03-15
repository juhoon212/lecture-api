package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViRoomAuth;
import org.apache.commons.lang3.StringUtils;

@Mapper
public abstract class ViRoomAuthMapper {

  public ViRoomAuth modify(ViRoomAuth in, ViRoomAuth out) {
    if (in == null) {
      return null;
    }
    

    // 방식별번호
    if (in.getRoomId() != null) {
      out.setRoomId(in.getRoomId());
    }

    // 방권한명
    if (StringUtils.isNotEmpty(in.getRoomAuthName())) {
      out.setRoomAuthName(in.getRoomAuthName());
    }

    return out;
  }
}
