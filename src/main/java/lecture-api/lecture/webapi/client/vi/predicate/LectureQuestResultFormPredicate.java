package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.webapi.client.vi.form.LectureQuestResultForm;
import twentyoz.viven.feature.vi.model.QViLectureQuestResult;

public class LectureQuestResultFormPredicate {

  public static Predicate search(LectureQuestResultForm.Input.GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViLectureQuestResult qViLectureQuestResult = QViLectureQuestResult.viLectureQuestResult;

    // 강의 식별 번호
    if (in.getLectureId() != null) {
      builder.and(qViLectureQuestResult.lectureId.eq(in.getLectureId()));
    }

    // 강의 시험 문제 식별번호
    if(in.getLectureQuestId() != null) {
      builder.and(qViLectureQuestResult.lectureQuestId.eq(in.getLectureQuestId()));
    }

    if(in.getLectureExamResultId() != null) {
      builder.and(qViLectureQuestResult.lectureExamResultId.eq(in.getLectureExamResultId()));
    }

    return builder;
  }

}
