package twentyoz.viven.webapi.client.mb.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.mb.form.MbrEmailAuthForm;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
    implementationName = "MbrEmailAuthFormMapperImpl",
    builder = @Builder(disableBuilder = true))
public abstract class MbrEmailAuthFormMapper {

  public abstract MbMbrEmailAuth toMbMbrEmailAuth(MbrEmailAuthForm.Input.Add in);

  public abstract MbMbrEmailAuth toMbMbrEmailAuth(MbrEmailAuthForm.Input.Modify in);

  public abstract MbrEmailAuthForm.Output.Get toGet(MbMbrEmailAuth in);

  public abstract MbrEmailAuthForm.Output.GetAll toGetAll(MbMbrEmailAuth in);

  public abstract List<MbrEmailAuthForm.Output.GetAll> toGetAllList(List<MbMbrEmailAuth> in);
}
