package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import twentyoz.viven.webapi.client.vi.form.LectureExamForm;
import twentyoz.viven.feature.vi.model.QViLectureExam;
//import twentyoz.viven.webapi.dts.vi.form.LectureQuestFormD;

public class LectureExamFormPredicate {

  public static Predicate search(LectureExamForm.Input.GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViLectureExam qViLectureExam = QViLectureExam.viLectureExam;

    // 강의 식별번호
    if (in.getLectureId() != null) {
      builder.and(qViLectureExam.lectureId.eq(in.getLectureId()));
    }

    // 시험 이름
    if(StringUtils.isNotEmpty(in.getExamName())) {
      builder.and(qViLectureExam.examName.containsIgnoreCase(in.getExamName()));
    }

    // 설명 내용
    if(StringUtils.isNotEmpty(in.getDescCont())) {
      builder.and(qViLectureExam.descCont.containsIgnoreCase(in.getDescCont()));
    }

    // 시험지 유형 코드
    if(StringUtils.isNotEmpty(in.getExamTypeCode())) {
      builder.and(qViLectureExam.examTypeCode.eq(in.getExamTypeCode()));
    }

    return builder;
  }

  public static Predicate search(LectureExamForm.Input.DtsAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViLectureExam qViLectureExam = QViLectureExam.viLectureExam;

    if(in.getLectureExamId() != null) {
      builder.and(qViLectureExam.lectureExamId.eq(in.getLectureExamId()));
    }
    return builder;
  }

}
