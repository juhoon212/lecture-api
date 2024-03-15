package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.vi.form.CttForm.Input.Modify;
import twentyoz.viven.webapi.client.vi.form.CttForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.CttForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "CttFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class CttFormMapper {

  public abstract ViCtt toViCtt(Modify in);

  public abstract Get toGet(ViCtt in);

  public abstract GetAll toGetAll(ViCtt in);

  public abstract List<GetAll> toGetAllList(List<ViCtt> in);
}
