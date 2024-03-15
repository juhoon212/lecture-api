package twentyoz.viven.feature.sy.mapper;

import twentyoz.viven.feature.sy.model.SyAttachFileGroupRefObj;
import org.mapstruct.Mapper;

@Mapper
public abstract class SyAttachFileGroupRefObjMapper {

  public SyAttachFileGroupRefObj modify(SyAttachFileGroupRefObj in, SyAttachFileGroupRefObj out) {
    if (in == null) {
      return null;
    }

    return out;
  }
}
