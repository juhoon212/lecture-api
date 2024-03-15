package twentyoz.viven.feature.mb.mapper;

import twentyoz.viven.feature.mb.model.MbMbrEmailAuth;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class MbMbrEmailAuthMapper {

  public MbMbrEmailAuth modify(MbMbrEmailAuth in, MbMbrEmailAuth out) {
    if (in == null) {
      return null;
    }

    // 회원식별번호
    if (in.getMbrId() != null) {
      out.setMbrId(in.getMbrId());
    }

    // 인증값
    if (StringUtils.isNotEmpty(in.getAuthVal())) {
      out.setAuthVal(in.getAuthVal());
    }

    // 인증종료일시
    if (in.getAuthEndDt() != null) {
      out.setAuthEndDt(in.getAuthEndDt());
    }

    // 인증완료여부
    if (StringUtils.isNotEmpty(in.getAuthComplYn())) {
      out.setAuthComplYn(in.getAuthComplYn());
    }

    return out;
  }
}
