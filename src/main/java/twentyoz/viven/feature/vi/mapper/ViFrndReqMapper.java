package twentyoz.viven.feature.vi.mapper;

import twentyoz.viven.feature.vi.model.ViFrndReq;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViFrndReqMapper {

  public ViFrndReq modify(ViFrndReq in, ViFrndReq out) {
    if (in == null) {
      return null;
    }
    // 요청상태코드
    if (StringUtils.isNotEmpty(in.getReqStatusCode())) {
      out.setReqStatusCode(in.getReqStatusCode());
    }

    return out;
  }
}
