package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Mapper;
import java.util.List;
import org.mapstruct.Builder;
import twentyoz.viven.webapi.client.vi.form.RoomAuthForm;

@Mapper(
    implementationName = "RoomAuthFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class RoomAuthFormMapper {

  public abstract ViRoomAuth toViRoomAuth(RoomAuthForm.Input.Add in);

  public abstract ViRoomAuth toViRoomAuth(RoomAuthForm.Input.Modify in);

  public abstract RoomAuthForm.Output.Get toGet(ViRoomAuth in);

  public abstract RoomAuthForm.Output.GetAll toGetAll(ViRoomAuth in);

  public abstract List<RoomAuthForm.Output.GetAll> toGetAllList(List<ViRoomAuth> in);

}
