package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.webapi.client.vi.form.LectureExamResultForm;
import twentyoz.viven.feature.vi.model.QViLectureExamResult;

import java.util.UUID;

public class LectureExamResultFormPredicate {

  public static Predicate search(LectureExamResultForm.Input.GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViLectureExamResult qViLectureExamResult = QViLectureExamResult.viLectureExamResult;

    // 식별번호
    if (in.getLectureExamId() != null) {
      builder.and(qViLectureExamResult.lectureExamId.eq(in.getLectureExamId()));
    }

    return builder;
  }

  public static Predicate search(UUID id) {
    BooleanBuilder builder = new BooleanBuilder();
    QViLectureExamResult qViLectureExamResult = QViLectureExamResult.viLectureExamResult;

    if(id != null) {
      builder.and(qViLectureExamResult.lectureExamResultId.eq(id));
    }

    return builder;
  }



}
