package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.vi.form.RoomSvForm.Input.Add;
import twentyoz.viven.webapi.client.vi.form.RoomSvForm.Input.Modify;
import twentyoz.viven.webapi.client.vi.form.RoomSvForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.RoomSvForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "RoomSvFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class RoomSvFormMapper {

  public abstract ViRoomSv toViRoomSv(Add in);

  public abstract ViRoomSv toViRoomSv(Modify in);

  public abstract Get toGet(ViRoomSv in);

  public abstract GetAll toGetAll(ViRoomSv in);

  public abstract List<GetAll> toGetAllList(List<ViRoomSv> in);
}
