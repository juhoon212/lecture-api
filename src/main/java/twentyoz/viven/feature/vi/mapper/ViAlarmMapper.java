package twentyoz.viven.feature.vi.mapper;

import twentyoz.viven.feature.vi.model.ViAlarm;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViAlarmMapper {

  public ViAlarm modify(ViAlarm in, ViAlarm out) {
    if (in == null) {
      return null;
    }

    // 조회여부
    if (StringUtils.isNotEmpty(in.getReadYn())) {
      out.setReadYn(in.getReadYn());
    }

    return out;
  }
}
