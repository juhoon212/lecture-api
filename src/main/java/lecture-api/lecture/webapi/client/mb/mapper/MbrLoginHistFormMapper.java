package twentyoz.viven.webapi.client.mb.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.mb.form.MbrLoginHistForm.Output.Get;
import twentyoz.viven.webapi.client.mb.form.MbrLoginHistForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
    implementationName = "MbrLoginHistFormMapperImpl",
    builder = @Builder(disableBuilder = true))
public abstract class MbrLoginHistFormMapper {

  public abstract Get toGet(MbMbrLoginHist in);

  public abstract GetAll toGetAll(MbMbrLoginHist in);

  public abstract List<GetAll> toGetAllList(List<MbMbrLoginHist> in);
}
