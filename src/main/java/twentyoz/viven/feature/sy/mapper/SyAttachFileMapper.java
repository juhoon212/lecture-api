package twentyoz.viven.feature.sy.mapper;

import twentyoz.viven.feature.sy.model.SyAttachFile;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class SyAttachFileMapper {

  public SyAttachFile modify(SyAttachFile in, SyAttachFile out) {
    if (in == null) {
      return null;
    }

    // 정렬순서
    if (in.getSortOrd() != null) {
      out.setSortOrd(in.getSortOrd());
    }

    // 첨부구분값
    if (StringUtils.isNotEmpty(in.getAttachDivVal())) {
      out.setAttachDivVal(in.getAttachDivVal());
    }

    // 비고
    if (StringUtils.isNotEmpty(in.getRemarkCont())) {
      out.setRemarkCont(in.getRemarkCont());
    }

    return out;
  }
}
