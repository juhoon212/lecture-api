package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.feature.vi.model.QViRoomProp;
import twentyoz.viven.webapi.client.vi.form.RoomPropForm.Input.GetAll;

public class RoomPropFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViRoomProp qViRoomProp = QViRoomProp.viRoomProp;

    // 식별번호
    if (in.getId() != null) {
      builder.and(qViRoomProp.roomPropId.eq(in.getId()));
    }
    // 방식별번호
    if (in.getRoomId() != null) {
      builder.and(qViRoomProp.roomId.eq(in.getRoomId()));
    }

    return builder;
  }
}
