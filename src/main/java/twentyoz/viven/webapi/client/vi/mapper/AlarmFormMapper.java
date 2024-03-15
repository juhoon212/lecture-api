package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;
import twentyoz.viven.feature.vi.model.ViAlarm;
import twentyoz.viven.webapi.client.vi.form.AlarmForm.Input.Add;
import twentyoz.viven.webapi.client.vi.form.AlarmForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.AlarmForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "AlarmFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class AlarmFormMapper {

  public abstract ViAlarm toViAlarm(Add in);

  public abstract Get toGet(ViAlarm in);

  public abstract GetAll toGetAll(ViAlarm in);

  public abstract List<GetAll> toGetAllList(List<ViAlarm> in);
}
