package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.webapi.client.vi.form.LectureForm;
import twentyoz.viven.feature.vi.model.QViLecture;

public class LectureFormPredicate {

  public static Predicate search(LectureForm.Input.GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViLecture qViLecture = QViLecture.viLecture;

    // 식별번호
    if (in.getRoomId() != null) {
      builder.and(qViLecture.roomId.eq(in.getRoomId()));
    }

    return builder;
  }

}
