package twentyoz.viven.webapi.client.vi.mapper;

import twentyoz.viven.feature.vi.model.ViRoom;
import twentyoz.viven.webapi.client.vi.form.MyRoomForm.Output.Get;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "MyRoomFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class MyRoomFormMapper {

  public abstract Get toGet(ViRoom in);
}
