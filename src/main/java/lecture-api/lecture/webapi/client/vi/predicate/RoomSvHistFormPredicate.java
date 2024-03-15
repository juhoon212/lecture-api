package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.Locale;
import twentyoz.viven.feature.vi.model.QViRoomSvHist;
import twentyoz.viven.util.DateTimeUtils;
import twentyoz.viven.webapi.client.vi.form.RoomSvHistForm.Input.GetAll;
import org.joda.time.DateTime;

public class RoomSvHistFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViRoomSvHist qViRoomSvHist = QViRoomSvHist.viRoomSvHist;

    // 식별번호
    if (in.getId() != null) {
      builder.and(qViRoomSvHist.roomSvHistId.eq(in.getId()));
    }

    if (in.getRoomSvId() != null) {
      builder.and(qViRoomSvHist.roomSvId.eq(in.getRoomSvId()));
    }

    if (in.getRoomSvNo() != null) {
      builder.and(
          qViRoomSvHist.roomSvNo.lower().contains(in.getRoomSvNo().toLowerCase(Locale.ROOT)));
    }

    if (in.getRoomSvName() != null) {
      builder.and(
          qViRoomSvHist.roomSvName.lower().contains(in.getRoomSvName().toLowerCase(Locale.ROOT)));
    }

    if (in.getMsgCont() != null) {
      builder.and(qViRoomSvHist.msgCont.lower().contains(in.getMsgCont().toLowerCase(Locale.ROOT)));
    }

    if (in.getLogTypeCode() != null) {
      builder.and(qViRoomSvHist.logTypeCode.eq(in.getLogTypeCode()));
    }

    if (in.getLogTypeCode() != null) {
      builder.and(qViRoomSvHist.logTypeCode.eq(in.getLogTypeCode()));
    }

    if (in.getIpAddr() != null) {
      builder.and(qViRoomSvHist.ipAddr.lower().contains(in.getIpAddr().toLowerCase(Locale.ROOT)));
    }

    // 검색기간
    if (in.getOccurStartDt() != null && in.getOccurEndDt() != null) {
      DateTime startDate = DateTimeUtils.getFirstTimeOfTheDay(in.getOccurStartDt());
      DateTime endDate = DateTimeUtils.getLastTimeOfTheDay(in.getOccurEndDt());

      BooleanBuilder dateBuilder = new BooleanBuilder();
      dateBuilder.or(qViRoomSvHist.occurDt.between(startDate, endDate));

      BooleanBuilder dateBuilder1 = new BooleanBuilder();
      dateBuilder1.and(qViRoomSvHist.occurDt.loe(startDate));
      dateBuilder1.and(qViRoomSvHist.occurDt.goe(endDate));

      dateBuilder.or(dateBuilder1);
      builder.and(dateBuilder);
    }

    return builder;
  }
}
