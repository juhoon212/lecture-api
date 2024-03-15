package twentyoz.viven.feature.mb.mapper;

import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.mb.model.MbMbr;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class MbMbrMapper {

  public MbMbr modify(MbMbr in, MbMbr out) {
    if (in == null) {
      return null;
    }

    // Tmpl: MBR_{mbr_id first 8 char}
    if (StringUtils.isNotEmpty(in.getMbrNo())) {
      out.setMbrNo(in.getMbrNo());
    }

    // Code Group: MB_001
    if (StringUtils.isNotEmpty(in.getMbrTypeCode())) {
      out.setMbrTypeCode(in.getMbrTypeCode());
    }

    // Code Group: MB_002
    if (StringUtils.isNotEmpty(in.getMbrStatusCode())) {
      out.setMbrStatusCode(in.getMbrStatusCode());
    }

    // 로그인아이디
    if (StringUtils.isNotEmpty(in.getLoginId())) {
      out.setLoginId(in.getLoginId());
    }

    // 비밀번호
    if (StringUtils.isNotEmpty(in.getPw())) {
      out.setPw(in.getPw());
    }

    // 임시 비밀번호 여부
    if (StringUtils.isNotEmpty(in.getTempPwYn())) {
      out.setTempPwYn(in.getTempPwYn());
    }

    // 회원명
    if (StringUtils.isNotEmpty(in.getMbrName())) {
      out.setMbrName(in.getMbrName());
    }

    // 닉네임
    if (StringUtils.isNotEmpty(in.getNickname())) {
      out.setNickname(in.getNickname());
    }

    // 이메일
    if (StringUtils.isNotEmpty(in.getEmail())) {
      out.setEmail(in.getEmail());
    }

    // 휴대폰번호
    if (StringUtils.isNotEmpty(in.getMphoneNo())) {
      out.setMphoneNo(in.getMphoneNo());
    }

    // 가입일시
    if (in.getJoinDt() != null) {
      out.setJoinDt(in.getJoinDt());
    }

    // 최근로그인일시
    if (in.getLastLoginDt() != null) {
      out.setLastLoginDt(in.getLastLoginDt());
    }

    // 사용여부
    if (StringUtils.isNotEmpty(in.getUseYn())) {
      out.setUseYn(in.getUseYn());
    }

    // 삭제여부
    if (StringUtils.isNotEmpty(in.getDelYn())) {
      out.setDelYn(in.getDelYn());
    }

    // 생년월일
    if (StringUtils.isNotEmpty(in.getBirthday())) {
      out.setBirthday(in.getBirthday());
    }

    // 온라인 여부
    if (StringUtils.isNotEmpty(in.getOnlineYn())) {
      out.setOnlineYn(in.getOnlineYn());
    }

    // 콘텐츠식별번호
    if (in.getCttId() != null) {
      out.setCttId(in.getCttId());
    }

    // 프로필 파일 경로_1(전신)
    if (StringUtils.isNotEmpty(in.getAvtBodyFilePath())) {
      out.setAvtBodyFilePath(in.getAvtBodyFilePath());
    }

    // 프로필 파일 경로_1(얼굴)
    if (StringUtils.isNotEmpty(in.getAvtProfileFilePath())) {
      out.setAvtProfileFilePath(in.getAvtProfileFilePath());
    }

    // 프로필 배경 색상
    if (StringUtils.isNotEmpty(in.getProfileBgColor())) {
      out.setProfileBgColor(in.getProfileBgColor());
    }

    // 임시비밀번호 여부
    if (StringUtils.isNotEmpty(in.getTempPwYn())) {
      out.setTempPwYn(in.getTempPwYn());
    }

    // 비밀번호유효일시
    if (in.getPwValidDt() != null) {
      out.setPwValidDt(in.getPwValidDt());
    }

    return out;
  }

  public MbMbr secession(MbMbr in, MbMbr out) {
    if (in == null) {
      return null;
    }

    out.setMbrStatusCode(Code.MB_002_004.getCode());
    out.setMbrName("-");
    out.setPw("-");
    out.setNickname("-");
    out.setEmail("-");
    out.setMphoneNo(null);
    out.setUseYn("N");
    out.setBirthday("0000-00-00");
    out.setCttId(null);
    out.setOnlineYn(null);
    out.setProfileBgColor(null);
    out.setPwValidDt(null);
    out.setAvtBodyFilePath(null);
    out.setAvtProfileFilePath(null);
    return out;
  }
}
