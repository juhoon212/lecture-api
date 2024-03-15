package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.feature.vi.model.QViCttBin;
import twentyoz.viven.webapi.client.vi.form.CttBinForm.Input.GetAll;

public class CttBinFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViCttBin qViCttBin = QViCttBin.viCttBin;

    // 콘텐츠식별번호
    if (in.getCttId() != null) {
      builder.and(qViCttBin.cttId.eq(in.getCttId()));
    }

    return builder;
  }
}
