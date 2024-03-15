package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViLectureQuest;
import org.apache.commons.lang3.StringUtils;

@Mapper
public abstract class ViLectureQuestMapper {

  public ViLectureQuest modify(ViLectureQuest in, ViLectureQuest out) {
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

    // 시험지문제식별번호
    if (in.getExamItemId() != null) {
      out.setExamItemId(in.getExamItemId());
    }

    // 시험문제식별번호
    if (in.getExamQuestId() != null) {
      out.setExamQuestId(in.getExamQuestId());
    }

    // 시험문제 이름
    if (StringUtils.isNotEmpty(in.getQuestName())) {
      out.setQuestName(in.getQuestName());
    }

    // 시험문제 유형 코드
    if (StringUtils.isNotEmpty(in.getQuestTypeCode())) {
      out.setQuestTypeCode(in.getQuestTypeCode());
    }

    // 시험문제 내용
    if (StringUtils.isNotEmpty(in.getQuestCont())) {
      out.setQuestCont(in.getQuestCont());
    }

    // 객관식문항설정정보
    if (StringUtils.isNotEmpty(in.getChoiceCont())) {
      out.setChoiceCont(in.getChoiceCont());
    }

    // 배점
    if (in.getScore() != null) {
      out.setScore(in.getScore());
    }

    // 정답
    if (!in.getAnswer().isEmpty()) {
      out.setAnswer(in.getAnswer());
    }

    // 골든벨가능여부
    if (StringUtils.isNotEmpty(in.getGoldenbellYn())) {
      out.setGoldenbellYn(in.getGoldenbellYn());
    }

    // 정렬순서
    if (in.getSortOrd() != null) {
      out.setSortOrd(in.getSortOrd());
    }

    return out;
  }
}
