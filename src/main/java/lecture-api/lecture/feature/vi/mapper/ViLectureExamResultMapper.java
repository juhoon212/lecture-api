package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViLectureExamResult;


@Mapper
public abstract class ViLectureExamResultMapper {

  public ViLectureExamResult modify(ViLectureExamResult in, ViLectureExamResult out) {
    if (in == null) {
      return null;
    }
    

    // 강의식별번호
    if (in.getLectureId() != null) {
      out.setLectureId(in.getLectureId());
    }

    // 강의시험지식별번호
    if (in.getLectureExamId() != null) {
      out.setLectureExamId(in.getLectureExamId());
    }

    // 회원식별번호
    if (in.getMbrId() != null) {
      out.setMbrId(in.getMbrId());
    }

    // 시험시작시간
    if (in.getExamStartDt() != null) {
      out.setExamStartDt(in.getExamStartDt());
    }

    // 시험종료시간
    if (in.getExamEndDt() != null) {
      out.setExamEndDt(in.getExamEndDt());
    }

    return out;
  }
}
