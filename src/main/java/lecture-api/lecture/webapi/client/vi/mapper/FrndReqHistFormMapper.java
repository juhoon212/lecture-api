package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.vi.form.FrndReqHistForm;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "FrndReqHistFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class FrndReqHistFormMapper {

  public abstract FrndReqHistForm.Output.Get toGet(ViFrndReqHist in);

  public abstract FrndReqHistForm.Output.GetAll toGetAll(ViFrndReqHist in);

  public abstract List<FrndReqHistForm.Output.GetAll> toGetAllList(List<ViFrndReqHist> in);
}
