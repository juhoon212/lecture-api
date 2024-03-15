package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;
import twentyoz.viven.feature.vi.model.ViRoomSvHist;
import twentyoz.viven.webapi.client.vi.form.RoomSvHistForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.RoomSvHistForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "RoomSvHistFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class RoomSvHistFormMapper {

  public abstract Get toGet(ViRoomSvHist in);

  public abstract GetAll toGetAll(ViRoomSvHist in);

  public abstract List<GetAll> toGetAllList(List<ViRoomSvHist> in);
}
