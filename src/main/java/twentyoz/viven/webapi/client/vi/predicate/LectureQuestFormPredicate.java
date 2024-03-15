package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.feature.vi.model.QViLectureQuest;
import twentyoz.viven.webapi.client.vi.form.LectureQuestForm;

public class LectureQuestFormPredicate {

  public static Predicate search(LectureQuestForm.Input.GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViLectureQuest qViLectureQuest = QViLectureQuest.viLectureQuest;

    // 식별번호
    if(in.getLectureExamId() != null) {
      builder.and(qViLectureQuest.lectureExamId.eq(in.getLectureExamId()));
    }

    return builder;
  }

}
