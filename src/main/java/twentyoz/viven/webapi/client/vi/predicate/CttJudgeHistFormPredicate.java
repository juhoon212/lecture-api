package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.feature.vi.model.QViCttJudgeHist;
import twentyoz.viven.feature.vi.model.QViCttJudgeReq;
import twentyoz.viven.webapi.client.vi.form.CttJudgeHistForm.Input.GetAll;

public class CttJudgeHistFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViCttJudgeReq qViCttJudgeReq = QViCttJudgeReq.viCttJudgeReq;
    QViCttJudgeHist qViCttJudgeHist = QViCttJudgeHist.viCttJudgeHist;

    // 콘텐츠식별번호
    if (in.getCttId() != null) {
      builder.and(qViCttJudgeReq.cttId.eq(in.getCttId()));
    }

    // 콘텐츠버전식별번호
    if (in.getCttBinId() != null) {
      builder.and(qViCttJudgeReq.cttBinId.eq(in.getCttBinId()));
    }

    // 콘텐츠버전식별번호
    if (in.getBinVal() != null) {
      builder.and(qViCttJudgeHist.binVal.eq(in.getBinVal()));
    }

    return builder;
  }
}
