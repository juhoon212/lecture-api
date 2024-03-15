package twentyoz.viven.feature.vi.mapper;

import twentyoz.viven.feature.vi.model.ViFrndReqHist;
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
