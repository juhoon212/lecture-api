package twentyoz.viven.webapi.client.sy.mapper;

import java.util.List;
import twentyoz.viven.feature.sy.model.SyAttachFile;
import twentyoz.viven.webapi.client.sy.form.AttachFileForm.Input.Add;
import twentyoz.viven.webapi.client.sy.form.AttachFileForm.Input.Modify;
import twentyoz.viven.webapi.client.sy.form.AttachFileForm.Output.Get;
import twentyoz.viven.webapi.client.sy.form.AttachFileForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "AttachFileFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class AttachFileFormMapper {

  public abstract SyAttachFile toSyAttachFile(Add in);

  public abstract SyAttachFile toSyAttachFileModify(Modify in);

  public abstract Get toGet(SyAttachFile in);

  public abstract GetAll toGetAll(SyAttachFile in);

  public abstract List<GetAll> toGetAllList(List<SyAttachFile> in);
}
