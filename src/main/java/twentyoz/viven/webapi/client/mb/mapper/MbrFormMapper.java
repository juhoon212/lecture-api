package twentyoz.viven.webapi.client.mb.mapper;

import java.util.List;
import twentyoz.viven.feature.mb.model.MbMbr;
import twentyoz.viven.webapi.client.mb.form.MbrForm.Input.Add;
import twentyoz.viven.webapi.client.mb.form.MbrForm.Input.Modify;
import twentyoz.viven.webapi.client.mb.form.MbrForm.Input.ModifyPw;
import twentyoz.viven.webapi.client.mb.form.MbrForm.Input.ModifyTempPw;
import twentyoz.viven.webapi.client.mb.form.MbrForm.Output.Get;
import twentyoz.viven.webapi.client.mb.form.MbrForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "MbrFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class MbrFormMapper {

  public abstract MbMbr toMbMbr(Add in);

  public abstract MbMbr toMbMbr(Modify in);

  public abstract Get toGet(MbMbr in);

  public abstract GetAll toGetAll(MbMbr in);

  public abstract List<GetAll> toGetAllList(List<MbMbr> in);

  public abstract MbMbr modifyTempPw(ModifyTempPw in);

  public abstract MbMbr modifyPw(ModifyPw in);
}
