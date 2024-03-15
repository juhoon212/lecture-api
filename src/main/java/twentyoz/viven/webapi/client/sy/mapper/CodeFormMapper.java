package twentyoz.viven.webapi.client.sy.mapper;

import java.util.List;
import twentyoz.viven.feature.Code;
import twentyoz.viven.webapi.client.sy.form.CodeForm.Output.Get;
import twentyoz.viven.webapi.client.sy.form.CodeForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "CodeFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class CodeFormMapper {

  public abstract List<GetAll> toGetAllList(List<Code> byCodeGroup);

  public abstract Get toGet(Code byCode);
}
