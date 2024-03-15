package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.util.StringUtils;
import twentyoz.viven.webapi.client.vi.form.LectureRefForm;
import twentyoz.viven.feature.vi.model.QViLectureRef;

public class LectureRefFormPredicate {

  public static Predicate search(LectureRefForm.Input.GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViLectureRef qViLectureRef = QViLectureRef.viLectureRef;

    builder.and(qViLectureRef.delYn.eq("N"));

    if(in.getLectureId() != null) {
      builder.and(qViLectureRef.lectureId.eq(in.getLectureId()));
    }

    if(StringUtils.hasText(in.getRefName())) {
      builder.and(qViLectureRef.refName.containsIgnoreCase(in.getRefName()));
    }

    if(StringUtils.hasText(in.getDescCont())) {
      builder.and(qViLectureRef.descCont.containsIgnoreCase(in.getDescCont()));
    }

    return builder;
  }

}
