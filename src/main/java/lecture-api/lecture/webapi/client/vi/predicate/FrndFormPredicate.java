package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.feature.vi.model.QViFrnd;
import twentyoz.viven.webapi.client.vi.form.FrndForm.Input.GetAll;

public class FrndFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViFrnd qViFrnd = QViFrnd.viFrnd;

    // 식별번호
    if (in.getId() != null) {
      builder.and(qViFrnd.frndId.eq(in.getId()));
    }

    return builder;
  }
}
