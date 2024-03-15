package twentyoz.viven.webapi.client.mb.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.Locale;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.webapi.client.mb.form.MbrForm.Input.GetAll;
import twentyoz.viven.webapi.client.mb.form.MbrForm.Input.GetFriendAll;

public class MbrFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    // 닉네임
    if (in.getNickname() != null) {
      builder.and(qMbMbr.nickname.lower().contains(in.getNickname().toLowerCase(Locale.ROOT)));
    }

    // 로그인 아이디
    if (in.getLoginId() != null) {
      builder.and(qMbMbr.nickname.lower().contains(in.getLoginId().toLowerCase(Locale.ROOT)));
    }

    return builder;
  }

  public static Predicate searchFriend(GetFriendAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    // 식별번호
    if (in.getId() != null) {
      builder.and(qMbMbr.mbrId.eq(in.getId()));
    }

    // 닉네임
    if (in.getNickname() != null) {
      builder.and(qMbMbr.nickname.lower().contains(in.getNickname().toLowerCase(Locale.ROOT)));
    }

    // 로그인 아이디
    if (in.getLoginId() != null) {
      builder.and(qMbMbr.nickname.lower().contains(in.getLoginId().toLowerCase(Locale.ROOT)));
    }

    return builder;
  }
}
