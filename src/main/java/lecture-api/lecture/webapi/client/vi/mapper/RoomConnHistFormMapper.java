package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.vi.form.RoomConnHistForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.RoomConnHistForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
    implementationName = "RoomConnHistFormMapperImpl",
    builder = @Builder(disableBuilder = true))
public abstract class RoomConnHistFormMapper {

  public abstract Get toGet(ViRoomConnHist in);

  public abstract GetAll toGetAll(ViRoomConnHist in);

  public abstract List<GetAll> toGetAllList(List<ViRoomConnHist> in);
}
