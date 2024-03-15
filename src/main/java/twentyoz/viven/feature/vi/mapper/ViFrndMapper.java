package twentyoz.viven.feature.vi.mapper;

import twentyoz.viven.feature.vi.model.ViFrnd;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViFrndMapper {

  public ViFrnd modify(ViFrnd in, ViFrnd out) {
    if (in == null) {
      return null;
    }

    // 회원식별번호
    if (in.getMbrId() != null) {
      out.setMbrId(in.getMbrId());
    }

    // 친구회원식별번호
    if (in.getFrndMbrId() != null) {
      out.setFrndMbrId(in.getFrndMbrId());
    }

    return out;
  }
}
