package twentyoz.viven.feature.vi.mapper;

import twentyoz.viven.feature.vi.model.ViCttBm;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViCttBmMapper {

  public ViCttBm modify(ViCttBm in, ViCttBm out) {
    if (in == null) {
      return null;
    }

    // 콘텐츠바이너리식별번호
    if (in.getCttBinId() != null) {
      out.setCttBinId(in.getCttBinId());
    }

    // 회원식별번호
    if (in.getMbrId() != null) {
      out.setMbrId(in.getMbrId());
    }

    return out;
  }
}
