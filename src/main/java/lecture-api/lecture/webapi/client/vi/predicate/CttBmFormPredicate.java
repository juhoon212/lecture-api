package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.Locale;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.QViCtt;
import twentyoz.viven.feature.vi.model.QViCttBm;
import twentyoz.viven.webapi.client.vi.form.CttBmForm.Input.GetAll;
import org.apache.commons.lang3.StringUtils;

public class CttBmFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViCttBm qViCttBm = QViCttBm.viCttBm;
    QViCtt qViCtt = QViCtt.viCtt;

    // 회원식별번호
    builder.and(qViCttBm.mbrId.eq(UserInfo.getUserId()));

    // 식별번호
    if (in.getId() != null) {
      builder.and(qViCttBm.cttBmId.eq(in.getId()));
    }

    // 콘텐츠전시명
    if (StringUtils.isNotEmpty(in.getCttDpName())) {
      builder.and(qViCtt.cttDpName.lower().contains(in.getCttDpName().toLowerCase(Locale.ROOT)));
    }

    // 콘텐츠유형코드
    if (StringUtils.isNotEmpty(in.getCttTypeCode())) {
      builder.and(qViCtt.cttTypeCode.eq(in.getCttTypeCode()));
    }

    return builder;
  }
}
