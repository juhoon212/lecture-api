package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.vi.form.CttBmForm.Input.Add;
import twentyoz.viven.webapi.client.vi.form.CttBmForm.Input.Modify;
import twentyoz.viven.webapi.client.vi.form.CttBmForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.CttBmForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "CttBmFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class CttBmFormMapper {

  public abstract ViCttBm toViCttBm(Add in);

  public abstract ViCttBm toViCttBm(Modify in);

  public abstract Get toGet(ViCttBm in);

  public abstract GetAll toGetAll(ViCttBm in);

  public abstract List<GetAll> toGetAllList(List<ViCttBm> in);
}
