package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;
import twentyoz.viven.feature.vi.model.ViRoomProp;
import twentyoz.viven.webapi.client.vi.form.RoomPropForm.Input.Add;
import twentyoz.viven.webapi.client.vi.form.RoomPropForm.Input.Modify;
import twentyoz.viven.webapi.client.vi.form.RoomPropForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.RoomPropForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "RoomPropFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class RoomPropFormMapper {

  public abstract ViRoomProp toViRoomProp(Add in);

  public abstract ViRoomProp toViRoomProp(Modify in);

  public abstract Get toGet(ViRoomProp in);

  public abstract GetAll toGetAll(ViRoomProp in);

  public abstract List<GetAll> toGetAllList(List<ViRoomProp> in);
}
