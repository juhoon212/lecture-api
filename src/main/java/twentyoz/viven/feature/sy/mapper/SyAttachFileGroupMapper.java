package twentyoz.viven.feature.sy.mapper;

import twentyoz.viven.feature.sy.model.SyAttachFileGroup;
import org.mapstruct.Mapper;

@Mapper
public abstract class SyAttachFileGroupMapper {

  public SyAttachFileGroup modify(SyAttachFileGroup in, SyAttachFileGroup out) {
    if (in == null) {
      return null;
    }

    return out;
  }
}
