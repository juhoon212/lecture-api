package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;

@Mapper
public abstract class ViFrndReqHistMapper {

  public ViFrndReqHist modify(ViFrndReqHist in, ViFrndReqHist out) {
    if (in == null) {
      return null;
    }

    return out;
  }
}
