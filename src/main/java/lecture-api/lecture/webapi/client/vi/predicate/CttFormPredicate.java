package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.Locale;
import twentyoz.viven.feature.vi.model.QViCtt;
import twentyoz.viven.feature.vi.model.QViCttBm;
import twentyoz.viven.webapi.client.vi.form.CttForm.Input.GetAll;
import twentyoz.viven.webapi.client.vi.form.CttForm.Input.GetAllAvt;
import org.apache.commons.lang3.StringUtils;

public class CttFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViCtt qViCtt = QViCtt.viCtt;
    QViCttBm qViCttBm = QViCttBm.viCttBm;

    // 콘텐츠명
    if (StringUtils.isNotEmpty(in.getCttName())) {
      builder.and(qViCtt.cttName.lower().contains(in.getCttName().toLowerCase(Locale.ROOT)));
    }

    // 콘텐츠전시명
    if (StringUtils.isNotEmpty(in.getCttDpName())) {
      builder.and(qViCtt.cttDpName.lower().contains(in.getCttDpName().toLowerCase(Locale.ROOT)));
    }

    // 콘텐츠유형코드
    if (StringUtils.isNotEmpty(in.getCttTypeCode())) {
      builder.and(qViCtt.cttTypeCode.eq(in.getCttTypeCode()));
    }

    // 콘텐츠맵유형코드
    if (in.getCttMapTypes() != null) {
      BooleanBuilder sb = new BooleanBuilder();
      for (String cttMapType : in.getCttMapTypes()) {
        if (StringUtils.isBlank(cttMapType)) {
          continue;
        }
        sb.or(qViCtt.cttMapType.lower().eq(cttMapType.toLowerCase(Locale.ROOT)));
      }
      builder.and(sb);
    }

    // 전시여부
    if (StringUtils.isNotEmpty(in.getDpYn())) {
      builder.and(qViCtt.dpYn.eq(in.getDpYn()));
    }

    return builder;
  }

  public static Predicate searchAvt(GetAllAvt in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViCtt qViCtt = QViCtt.viCtt;
    QViCttBm qViCttBm = QViCttBm.viCttBm;

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
