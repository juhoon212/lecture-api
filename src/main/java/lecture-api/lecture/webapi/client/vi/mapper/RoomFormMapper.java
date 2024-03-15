package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.vi.form.RoomForm.Input.Add;
import twentyoz.viven.webapi.client.vi.form.RoomForm.Input.Modify;
import twentyoz.viven.webapi.client.vi.form.RoomForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.RoomForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "RoomFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class RoomFormMapper {

  public abstract ViRoom toViRoom(Add in);

  public abstract ViRoom toViRoom(Modify in);

  public abstract Get toGet(ViRoom in);

  public abstract GetAll toGetAll(ViRoom in);

  public abstract List<GetAll> toGetAllList(List<ViRoom> in);
}
