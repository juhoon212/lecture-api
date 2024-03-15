package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import twentyoz.viven.webapi.client.vi.form.ExamForm;
import twentyoz.viven.feature.vi.model.QViExam;

import java.util.UUID;

public class ExamFormPredicate {

  public static Predicate search(ExamForm.Input.GetAll in, UUID mbrId) {
    BooleanBuilder builder = new BooleanBuilder();
    QViExam qViExam = QViExam.viExam;

    builder.and(qViExam.mbrId.eq(mbrId));
    builder.and(qViExam.delYn.eq("N"));

    // 유형
    if(in.getExamTypeCode() != null) {
      builder.and(qViExam.examTypeCode.eq(in.getExamTypeCode()));
    }

    // 시험지이름
    if(in.getExamName() != null) {
      builder.and(qViExam.examName.containsIgnoreCase(in.getExamName()));
    }

    // 키워드
    if(in.getKeyword() != null) {
      builder.and(qViExam.keyword.containsIgnoreCase(in.getKeyword()));
    }

    // 사용여부
    if(in.getUseYn() != null) {
      builder.and(qViExam.useYn.eq(in.getUseYn()));
    }



    return builder;
  }
}
