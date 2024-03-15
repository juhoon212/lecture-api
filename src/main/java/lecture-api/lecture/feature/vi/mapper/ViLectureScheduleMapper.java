package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViLectureSchedule;
import org.apache.commons.lang3.StringUtils;

@Mapper
public abstract class ViLectureScheduleMapper {

  public ViLectureSchedule modify(ViLectureSchedule in, ViLectureSchedule out) {
    if (in == null) {
      return null;
    }
    

    // 강의식별번호
    if (in.getLectureId() != null) {
      out.setLectureId(in.getLectureId());
    }

    // 강의계획서이름
    if (StringUtils.isNotEmpty(in.getLectureScheduleName())) {
      out.setLectureScheduleName(in.getLectureScheduleName());
    }

    // 설명내용
    if (StringUtils.isNotEmpty(in.getDescCont())) {
      out.setDescCont(in.getDescCont());
    }

    // 시작시간
    if (in.getStartDt() != null) {
      out.setStartDt(in.getStartDt());
    }

    // 종료시간
    if (in.getEndDt() != null) {
      out.setEndDt(in.getEndDt());
    }

    return out;
  }
}
