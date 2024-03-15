package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.feature.vi.model.QViFrndReqHist;
import twentyoz.viven.webapi.client.vi.form.FrndReqHistForm.Input.GetAll;

public class FrndReqHistFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViFrndReqHist qViFrndReqHist = QViFrndReqHist.viFrndReqHist;

    // 식별번호
    if (in.getId() != null) {
      builder.and(qViFrndReqHist.frndReqHistId.eq(in.getId()));
    }

    return builder;
  }
}
