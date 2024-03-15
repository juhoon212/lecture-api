package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import twentyoz.viven.feature.vi.model.QViExamQuest;
import twentyoz.viven.feature.vi.model.QViLectureQuest;
import twentyoz.viven.webapi.client.vi.form.ExamQuestForm;

import java.util.UUID;

public class ExamQuestFormPredicate {

  public static Predicate search(UUID mbrId, ExamQuestForm.Input.GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViExamQuest qViExamQuest = QViExamQuest.viExamQuest;

    builder.and(qViExamQuest.mbrId.eq(mbrId));

    if(in.getQuestName() != null) {
      builder.and(qViExamQuest.questName.containsIgnoreCase(in.getQuestName()));
    }

    if(StringUtils.isNotEmpty(in.getKeyword())) {
      builder.and(qViExamQuest.keyword.containsIgnoreCase(in.getKeyword()));
    }

    if(StringUtils.isNotEmpty(in.getQuestTypeCode())) {

      builder.and(qViExamQuest.questTypeCode.eq(in.getQuestTypeCode()));
  }

    if(StringUtils.isNotEmpty(in.getGoldenbellYn())) {
      builder.and(qViExamQuest.goldenbellYn.eq(in.getGoldenbellYn()));
    }

    return builder;

  }
}
