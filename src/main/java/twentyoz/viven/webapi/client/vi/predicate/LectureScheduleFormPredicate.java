package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.webapi.client.vi.form.LectureScheduleForm;
import twentyoz.viven.feature.vi.model.QViLectureSchedule;

public class LectureScheduleFormPredicate {

  public static Predicate search(LectureScheduleForm.Input.GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViLectureSchedule qViLectureSchedule = QViLectureSchedule.viLectureSchedule;

    // 강의수업 식별번호
    if (in.getId() != null) {
      builder.and(qViLectureSchedule.lectureScheduleId.eq(in.getId()));
    }

    // 강의 식별번호
    if(in.getLectureId() != null) {
      builder.and(qViLectureSchedule.lectureId.eq(in.getLectureId()));
    }

    return builder;
  }

}
