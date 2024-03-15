package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.Locale;
import twentyoz.viven.feature.vi.model.QViRoomConnHist;
import twentyoz.viven.util.DateTimeUtils;
import twentyoz.viven.webapi.client.vi.form.RoomConnHistForm.Input.GetAll;
import org.joda.time.DateTime;

public class RoomConnHistFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViRoomConnHist qViRoomConnHist = QViRoomConnHist.viRoomConnHist;

    // 식별번호
    if (in.getRoomConnHistId() != null) {
      builder.and(qViRoomConnHist.roomConnHistId.eq(in.getRoomConnHistId()));
    }

    if (in.getRoomId() != null) {
      builder.and(qViRoomConnHist.roomId.eq(in.getRoomId()));
    }

    if (in.getMbrId() != null) {
      builder.and(qViRoomConnHist.mbrId.eq(in.getMbrId()));
    }

    if (in.getRoomNo() != null) {
      builder.and(qViRoomConnHist.roomNo.lower().contains(in.getRoomNo().toLowerCase(Locale.ROOT)));
    }

    if (in.getRoomName() != null) {
      builder.and(
          qViRoomConnHist.roomName.lower().contains(in.getRoomName().toLowerCase(Locale.ROOT)));
    }

    if (in.getLoginId() != null) {
      builder.and(
          qViRoomConnHist.loginId.lower().contains(in.getLoginId().toLowerCase(Locale.ROOT)));
    }

    if (in.getMbrName() != null) {
      builder.and(
          qViRoomConnHist.mbrName.lower().contains(in.getMbrName().toLowerCase(Locale.ROOT)));
    }

    if (in.getNickname() != null) {
      builder.and(
          qViRoomConnHist.nickname.lower().contains(in.getNickname().toLowerCase(Locale.ROOT)));
    }

    if (in.getRoomConnTypeCode() != null) {
      builder.and(qViRoomConnHist.roomConnTypeCode.eq(in.getRoomConnTypeCode()));
    }

    if (in.getIpAddr() != null) {
      builder.and(qViRoomConnHist.ipAddr.lower().contains(in.getIpAddr().toLowerCase(Locale.ROOT)));
    }

    // 검색기간
    if (in.getConnStartDt() != null && in.getConnEndDt() != null) {
      DateTime startDate = DateTimeUtils.getFirstTimeOfTheDay(in.getConnStartDt());
      DateTime endDate = DateTimeUtils.getLastTimeOfTheDay(in.getConnEndDt());

      BooleanBuilder dateBuilder = new BooleanBuilder();
      dateBuilder.or(qViRoomConnHist.connDt.between(startDate, endDate));

      BooleanBuilder dateBuilder1 = new BooleanBuilder();
      dateBuilder1.and(qViRoomConnHist.connDt.loe(startDate));
      dateBuilder1.and(qViRoomConnHist.connDt.goe(endDate));

      dateBuilder.or(dateBuilder1);
      builder.and(dateBuilder);
    }

    return builder;
  }
}
