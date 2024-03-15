package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.Locale;

import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.vi.model.QViRoom;
import twentyoz.viven.webapi.client.vi.form.RoomForm.Input.GetAll;

public class RoomFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViRoom qViRoom = QViRoom.viRoom;
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    builder.and(qViRoom.useYn.eq("Y"));
    builder.and(qViRoom.delYn.eq("N"));
    builder.and(qViRoom.myroomYn.eq("N"));

    // 식별번호
    if (in.getRoomId() != null) {
      builder.and(qViRoom.roomId.eq(in.getRoomId()));
    }

    // 방번호
    if (in.getRoomNo() != null) {
      builder.and(qViRoom.roomNo.lower().contains(in.getRoomNo().toLowerCase(Locale.ROOT)));
    }

    // 방이름
    if (in.getRoomName() != null) {
      builder.and(qViRoom.roomName.lower().contains(in.getRoomName().toLowerCase(Locale.ROOT)));
    }

    // 키워드
    if (in.getKeyword() != null) {
      builder.and(qViRoom.keyword.lower().contains(in.getKeyword().toLowerCase(Locale.ROOT)));
    }

    // 닉네임
    if (in.getMbrNickname() != null) {
      builder.and(qMbMbr.nickname.eq(in.getMbrNickname()));
    }

    // 방타입
    if(in.getRoomTypeCode() != null) {
      builder.and(qViRoom.roomTypeCode.eq(in.getRoomTypeCode()));
    }

    return builder;
  }
}
