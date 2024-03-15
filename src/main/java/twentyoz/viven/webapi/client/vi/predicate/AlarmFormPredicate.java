package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.QViAlarm;
import twentyoz.viven.webapi.client.vi.form.AlarmForm.Input.GetAll;

public class AlarmFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QViAlarm qViAlarm = QViAlarm.viAlarm;

    // 회원식별번호
    builder.and(qViAlarm.mbrId.eq(UserInfo.getUserId()));

    // 식별번호
    if (in.getId() != null) {
      builder.and(qViAlarm.alarmId.eq(in.getId()));
    }

    return builder;
  }
}
