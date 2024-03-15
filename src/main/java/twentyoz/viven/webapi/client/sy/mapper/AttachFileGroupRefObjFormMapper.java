package twentyoz.viven.webapi.client.sy.mapper;

import java.util.List;
import twentyoz.viven.feature.sy.model.SyAttachFileGroupRefObj;
import twentyoz.viven.webapi.client.sy.form.AttachFileGroupRefObjForm.Input.Add;
import twentyoz.viven.webapi.client.sy.form.AttachFileGroupRefObjForm.Input.Modify;
import twentyoz.viven.webapi.client.sy.form.AttachFileGroupRefObjForm.Output.Get;
import twentyoz.viven.webapi.client.sy.form.AttachFileGroupRefObjForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
    implementationName = "AttachFileGroupRefFormMapperImpl",
    builder = @Builder(disableBuilder = true))
public abstract class AttachFileGroupRefObjFormMapper {

  public abstract SyAttachFileGroupRefObj toSyAttachFileGroupRef(
      Add in);

  public abstract SyAttachFileGroupRefObj toSyAttachFileGroupRef(
      Modify in);

  public abstract Get toGet(SyAttachFileGroupRefObj in);

  public abstract List<GetAll> toGetAllList(List<SyAttachFileGroupRefObj> in);
}
