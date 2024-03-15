package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViExamQuest;
import org.apache.commons.lang3.StringUtils;

@Mapper
public abstract class ViExamQuestMapper {

  public ViExamQuest modify(ViExamQuest in, ViExamQuest out) {
    if (in == null) {
      return null;
    }

    // 파일
    out.setQuestFileGroupId(in.getQuestFileGroupId());

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
    } else {
      out.setAnswer(out.getAnswer());
    }

    // 골든벨가능여부
    if (StringUtils.isNotEmpty(in.getGoldenbellYn())) {
      out.setGoldenbellYn(in.getGoldenbellYn());
    }

    return out;
  }
}
