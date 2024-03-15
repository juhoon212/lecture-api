package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.Locale;
import twentyoz.viven.feature.vi.model.QViRoomSv;
import twentyoz.viven.webapi.client.vi.form.RoomSvForm.Input.GetAll;

public class RoomSvFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViRoomSv qViRoomSv = QViRoomSv.viRoomSv;

    // 식별번호
    if (in.getRoomSvId() != null) {
      builder.and(qViRoomSv.roomSvId.eq(in.getRoomSvId()));
    }

    // 방서버번호
    if (in.getRoomSvNo() != null) {
      builder.and(qViRoomSv.roomSvNo.eq(in.getRoomSvNo()));
    }

    // 방서버명
    if (in.getRoomSvName() != null) {
      builder.and(
          qViRoomSv.roomSvName.lower().contains(in.getRoomSvName().toLowerCase(Locale.ROOT)));
    }

    // 방서버 상태
    if (in.getRoomSvStatusCode() != null) {
      builder.and(qViRoomSv.roomSvStatusCode.eq(in.getRoomSvStatusCode()));
    }

    if (in.getUseYn() != null) {
      builder.and(qViRoomSv.useYn.eq(in.getUseYn()));
    }

    if (in.getDelYn() != null) {
      builder.and(qViRoomSv.delYn.eq(in.getDelYn()));
    }

    return builder;
  }
}
