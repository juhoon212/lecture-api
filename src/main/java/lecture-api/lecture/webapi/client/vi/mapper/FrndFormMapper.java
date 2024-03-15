package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.vi.form.FrndForm.Input.Add;
import twentyoz.viven.webapi.client.vi.form.FrndForm.Input.Modify;
import twentyoz.viven.webapi.client.vi.form.FrndForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.FrndForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "FrndFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class FrndFormMapper {

  public abstract ViFrnd toViFrnd(Add in);

  public abstract ViFrnd toViFrnd(Modify in);

  public abstract Get toGet(ViFrnd in);

  public abstract GetAll toGetAll(ViFrnd in);

  public abstract List<FrndDto> toGetAllList(List<FrndDto> in);
}
