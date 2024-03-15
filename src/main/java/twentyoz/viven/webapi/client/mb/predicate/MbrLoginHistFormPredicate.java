package twentyoz.viven.webapi.client.mb.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.feature.mb.model.QMbMbrLoginHist;
import twentyoz.viven.webapi.client.mb.form.MbrLoginHistForm.Input.GetAll;

public class MbrLoginHistFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QMbMbrLoginHist qMbMbrLoginHist = QMbMbrLoginHist.mbMbrLoginHist;

    // 식별번호
    if (in.getId() != null) {
      builder.and(qMbMbrLoginHist.mbrId.eq(in.getId()));
    }

    return builder;
  }
}
