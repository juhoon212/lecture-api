package twentyoz.viven.webapi.dts.vi.mapper;

import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViRoomProp;
import twentyoz.viven.webapi.dts.vi.form.DtsRoomPropForm.Input.Add;
import twentyoz.viven.webapi.dts.vi.form.DtsRoomPropForm.Input.Modify;
import twentyoz.viven.webapi.dts.vi.form.DtsRoomPropForm.Output.Get;
import twentyoz.viven.webapi.dts.vi.form.DtsRoomPropForm.Output.GetAll;

@Mapper(implementationName = "DtsRoomPropFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class DtsRoomPropFormMapper {

  public abstract ViRoomProp toViRoomProp(Add in);

  public abstract ViRoomProp toViRoomProp(Modify in);

  public abstract Get toGet(ViRoomProp in);

  public abstract GetAll toGetAll(ViRoomProp in);

  public abstract List<GetAll> toGetAllList(List<ViRoomProp> in);
}
