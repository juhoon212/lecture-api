package twentyoz.viven.feature.vi.mapper;

import twentyoz.viven.feature.vi.model.ViCttBin;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViCttBinMapper {

  public ViCttBin modify(ViCttBin in, ViCttBin out) {
    if (in == null) {
      return null;
    }

    // 첨부파일그룹식별번호
    if (in.getAttachFileGroupId() != null) {
      out.setAttachFileGroupId(in.getAttachFileGroupId());
    }

    // 설명내용
    if (StringUtils.isNotEmpty(in.getDescCont())) {
      out.setDescCont(in.getDescCont());
    }

    // 버전값
    if (StringUtils.isNotEmpty(in.getBinVal())) {
      out.setBinVal(in.getBinVal());
    }

    return out;
  }
}
