package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.webapi.client.vi.form.RoomAuthForm;
import twentyoz.viven.feature.vi.model.QViRoomAuth;

public class RoomAuthFormPredicate {

  public static Predicate search(RoomAuthForm.Input.GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViRoomAuth qViRoomAuth = QViRoomAuth.viRoomAuth;

    // 식별번호
    if (in.getId() != null) {
      builder.and(qViRoomAuth.roomAuthId.eq(in.getId()));
    }

    return builder;
  }

}
