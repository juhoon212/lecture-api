package twentyoz.viven.feature.mb.mapper;

import twentyoz.viven.feature.mb.model.MbMbrLoginHist;
import org.mapstruct.Mapper;

@Mapper
public abstract class MbMbrLoginHistMapper {

  public MbMbrLoginHist modify(MbMbrLoginHist in, MbMbrLoginHist out) {
    if (in == null) {
      return null;
    }

    //
    //    // 회원식별번호
    //    if (in.getMbrId() != null) {
    //      out.setMbrId(in.getMbrId());
    //    }
    //
    //    // Code Group: MB_004
    //    if (StringUtils.isNotEmpty(in.getLoginTypeCode())) {
    //      out.setLoginTypeCode(in.getLoginTypeCode());
    //    }
    //
    //    // 아이피주소
    //    if (StringUtils.isNotEmpty(in.getIpAddr())) {
    //      out.setIpAddr(in.getIpAddr());
    //    }
    //
    //    // 사용자에이전트값
    //    if (StringUtils.isNotEmpty(in.getUaVal())) {
    //      out.setUaVal(in.getUaVal());
    //    }
    //
    //    // 로그인일시
    //    if (in.getLoginDt() != null) {
    //      out.setLoginDt(in.getLoginDt());
    //    }

    return out;
  }
}
