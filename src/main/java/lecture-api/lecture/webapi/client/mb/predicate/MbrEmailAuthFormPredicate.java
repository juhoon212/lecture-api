package twentyoz.viven.webapi.client.mb.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.mb.model.QMbMbrEmailAuth;
import twentyoz.viven.webapi.client.mb.form.MbrEmailAuthForm.Input.FindId;
import twentyoz.viven.webapi.client.mb.form.MbrEmailAuthForm.Input.FindPw;

public class MbrEmailAuthFormPredicate {

  public static Predicate search(FindId in) {
    BooleanBuilder builder = new BooleanBuilder();
    QMbMbrEmailAuth qMbMbrEmailAuth = QMbMbrEmailAuth.mbMbrEmailAuth;

    QMbMbr qMbMbr = QMbMbr.mbMbr;

    // 이름
    if (in.getMbrName() != null) {
      builder.and(qMbMbr.mbrName.eq(in.getMbrName()));
    }

    // 이메일
    if (in.getEmail() != null) {
      builder.and(qMbMbr.email.eq(in.getEmail()));
    }

    return builder;
  }

  public static Predicate search(FindPw in) {
    BooleanBuilder builder = new BooleanBuilder();
    QMbMbrEmailAuth qMbMbrEmailAuth = QMbMbrEmailAuth.mbMbrEmailAuth;

    QMbMbr qMbMbr = QMbMbr.mbMbr;

    // 식별번호
    if (in.getMbrEmailAuthId() != null) {
      builder.and(qMbMbrEmailAuth.mbrEmailAuthId.eq(in.getMbrEmailAuthId()));
    }

    // 인증값
    if (in.getAuthVal() != null) {
      builder.and(qMbMbrEmailAuth.authVal.eq(in.getAuthVal()));
    }

    // 이름
    if (in.getMbrName() != null) {
      builder.and(qMbMbr.mbrName.eq(in.getMbrName()));
    }

    // 이메일
    if (in.getEmail() != null) {
      builder.and(qMbMbr.email.eq(in.getEmail()));
    }

    // 로그인아이디
    if (in.getLoginId() != null) {
      builder.and(qMbMbr.loginId.eq(in.getLoginId()));
    }

    return builder;
  }
}
