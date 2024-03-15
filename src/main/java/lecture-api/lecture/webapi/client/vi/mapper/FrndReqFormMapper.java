package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.vi.form.FrndReqForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.FrndReqForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "FrndReqFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class FrndReqFormMapper {

  public abstract Get toGet(ViFrndReq in);

  public abstract GetAll toGetAll(ViFrndReq in);

  public abstract List<GetAll> toGetAllList(List<ViFrndReq> in);
}
