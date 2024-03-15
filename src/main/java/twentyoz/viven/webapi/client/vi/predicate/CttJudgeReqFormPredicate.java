package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.Locale;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.QViCttJudgeReq;
import twentyoz.viven.webapi.client.vi.form.CttJudgeReqForm.Input.GetAll;
import org.apache.commons.lang3.StringUtils;

public class CttJudgeReqFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViCttJudgeReq qViCttJudgeReq = QViCttJudgeReq.viCttJudgeReq;

    // 회원식별번호
    builder.and(qViCttJudgeReq.reqUserId.eq(UserInfo.getUserId()));

    // 콘텐츠명
    if (StringUtils.isNotEmpty(in.getCttName())) {
      builder.and(
          qViCttJudgeReq.cttName.lower().contains(in.getCttName().toLowerCase(Locale.ROOT)));
    }

    // 콘텐츠전시명
    if (StringUtils.isNotEmpty(in.getCttDpName())) {
      builder.and(
          qViCttJudgeReq.cttDpName.lower().contains(in.getCttDpName().toLowerCase(Locale.ROOT)));
    }

    // 콘텐츠유형코드
    if (StringUtils.isNotEmpty(in.getCttTypeCode())) {
      builder.and(qViCttJudgeReq.cttTypeCode.eq(in.getCttTypeCode()));
    }

    // 심사상태코드
    if (StringUtils.isNotEmpty(in.getJudgeStatusCode())) {
      builder.and(qViCttJudgeReq.judgeStatusCode.eq(in.getJudgeStatusCode()));
    }

    // 심사상태코드
    if (StringUtils.isNotEmpty(in.getJudgeTypeCode())) {
      builder.and(qViCttJudgeReq.judgeTypeCode.eq(in.getJudgeTypeCode()));
    }

    // 콘텐츠식별번호
    if (in.getCttId() != null) {
      builder.and(qViCttJudgeReq.cttId.eq(in.getCttId()));
    }

    // 콘텐츠바이너리식별번호
    if (in.getCttBinId() != null) {
      builder.and(qViCttJudgeReq.cttBinId.eq(in.getCttBinId()));
    }

    return builder;
  }
}
