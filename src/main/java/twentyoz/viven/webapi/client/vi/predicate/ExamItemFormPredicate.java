package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.feature.vi.model.QViExamItem;
import twentyoz.viven.webapi.client.vi.form.ExamItemForm;

import java.util.UUID;

public class ExamItemFormPredicate {

  public static Predicate search(ExamItemForm.Input.GetAll in, UUID mbrId) {
    BooleanBuilder builder = new BooleanBuilder();
    QViExamItem qViExamItem = QViExamItem.viExamItem;

    builder.and(qViExamItem.mbrId.eq(mbrId));

    if(in.getExamId() != null) {
      builder.and(qViExamItem.examId.eq(in.getExamId()));
    }
    return builder;
  }

}
