package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViLectureQuestResult;
import org.apache.commons.lang3.StringUtils;

@Mapper
public abstract class ViLectureQuestResultMapper {

  public ViLectureQuestResult modify(ViLectureQuestResult in, ViLectureQuestResult out) {
    if (in == null) {
      return null;
    }
    

    // 강의식별번호
    if (in.getLectureId() != null) {
      out.setLectureId(in.getLectureId());
    }

    // 강의시험결과식별번호
    if (in.getLectureExamResultId() != null) {
      out.setLectureExamResultId(in.getLectureExamResultId());
    }

    // 강의시험문제식별번호
    if (in.getLectureQuestId() != null) {
      out.setLectureQuestId(in.getLectureQuestId());
    }

    // 회원식별번호
    if (in.getMbrId() != null) {
      out.setMbrId(in.getMbrId());
    }

    // 사용자 답안
    if (!in.getMbrAnswer().isEmpty()) {
      out.setMbrAnswer(in.getMbrAnswer());
    }

    // 사용자 점수
    if (in.getMbrScore() != null) {
      out.setMbrScore(in.getMbrScore());
    }

    return out;
  }
}
