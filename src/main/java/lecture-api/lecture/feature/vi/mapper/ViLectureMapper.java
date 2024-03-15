package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViLecture;
import org.apache.commons.lang3.StringUtils;

@Mapper
public abstract class ViLectureMapper {

  public ViLecture modify(ViLecture in, ViLecture out) {
    if (in == null) {
      return null;
    }

    // 강의이름
    if (StringUtils.isNotEmpty(in.getLectureName())) {
      out.setLectureName(in.getLectureName());
    }

    if(in.getLecturePlanFileGroupId() != null) {
      out.setLecturePlanFileGroupId(in.getLecturePlanFileGroupId());
    }

    return out;
  }
}
