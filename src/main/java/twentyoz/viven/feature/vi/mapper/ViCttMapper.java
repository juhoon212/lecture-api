package twentyoz.viven.feature.vi.mapper;

import twentyoz.viven.feature.vi.model.ViCtt;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViCttMapper {

  public ViCtt modify(ViCtt in, ViCtt out) {
    if (in == null) {
      return null;
    }

    // 콘텐츠번호
    if (StringUtils.isNotEmpty(in.getCttNo())) {
      out.setCttNo(in.getCttNo());
    }

    // Code Group: CT_001
    if (StringUtils.isNotEmpty(in.getCttTypeCode())) {
      out.setCttTypeCode(in.getCttTypeCode());
    }

    // 콘텐츠명
    if (StringUtils.isNotEmpty(in.getCttName())) {
      out.setCttName(in.getCttName());
    }

    // 콘텐츠전시명
    if (StringUtils.isNotEmpty(in.getCttDpName())) {
      out.setCttDpName(in.getCttDpName());
    }

    // 설명내용
    if (StringUtils.isNotEmpty(in.getDescCont())) {
      out.setDescCont(in.getDescCont());
    }

    // Code Group: CT_002
    if (StringUtils.isNotEmpty(in.getSellStatusCode())) {
      out.setSellStatusCode(in.getSellStatusCode());
    }

    // 전시여부
    if (StringUtils.isNotEmpty(in.getDpYn())) {
      out.setDpYn(in.getDpYn());
    }

    // 전시시작일시
    //    if (in.getDpStartDt() != null) {
    out.setDpStartDt(in.getDpStartDt());
    //    }

    // 전시종료일시
    //    if (in.getDpEndDt() != null) {
    out.setDpEndDt(in.getDpEndDt());
    //    }

    // 첨부파일그룹식별번호
    if (in.getAttachFileGroupId() != null) {
      out.setAttachFileGroupId(in.getAttachFileGroupId());
    }

    // 사용여부
    if (StringUtils.isNotEmpty(in.getUseYn())) {
      out.setUseYn(in.getUseYn());
    }

    // 삭제여부
    if (StringUtils.isNotEmpty(in.getDelYn())) {
      out.setDelYn(in.getDelYn());
    }

    // 회원식별번호
    if (in.getMbrId() != null) {
      out.setMbrId(in.getMbrId());
    }

    // 아바타이미지그룹식별번호
    if (in.getAvtImgGroupId() != null) {
      out.setAvtImgGroupId(in.getAvtImgGroupId());
    }

    return out;
  }
}
