package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;
import twentyoz.viven.feature.vi.model.ViCttBin;
import twentyoz.viven.webapi.client.vi.form.CttBinForm.Input.Add;
import twentyoz.viven.webapi.client.vi.form.CttBinForm.Input.Modify;
import twentyoz.viven.webapi.client.vi.form.CttBinForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.CttBinForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "CttBinFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class CttBinFormMapper {

  public abstract ViCttBin toViCttBin(Add in);

  public abstract ViCttBin toViCttBin(Modify in);

  public abstract Get toGet(ViCttBin in);

  public abstract GetAll toGetAll(ViCttBin in);

  public abstract List<GetAll> toGetAllList(List<ViCttBin> in);
}
