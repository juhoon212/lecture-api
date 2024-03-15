package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViLectureExam;
import org.apache.commons.lang3.StringUtils;

@Mapper
public abstract class ViLectureExamMapper {

  public ViLectureExam modify(ViLectureExam in, ViLectureExam out) {
    if (in == null) {
      return null;
    }
    

    // 강의식별번호
    if (in.getLectureId() != null) {
      out.setLectureId(in.getLectureId());
    }

    // 시험지식별번호
    if (in.getExamId() != null) {
      out.setExamId(in.getExamId());
    }

    // 시험지이름
    if (StringUtils.isNotEmpty(in.getExamName())) {
      out.setExamName(in.getExamName());
    }

    // 시험지유형코드
    if (StringUtils.isNotEmpty(in.getExamTypeCode())) {
      out.setExamTypeCode(in.getExamTypeCode());
    }

    // 설명내용
    if (StringUtils.isNotEmpty(in.getDescCont())) {
      out.setDescCont(in.getDescCont());
    }

    // 시험시작시간
    if (in.getExamStartDt() != null) {
      out.setExamStartDt(in.getExamStartDt());
    }

    // 시험종료시간
    if (in.getExamEndDt() != null) {
      out.setExamEndDt(in.getExamEndDt());
    }

    // 시험소요시간(분)
    if (in.getExamPeriod() != null) {
      out.setExamPeriod(in.getExamPeriod());
    }

    return out;
  }
}
