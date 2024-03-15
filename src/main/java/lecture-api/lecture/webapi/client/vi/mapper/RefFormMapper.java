package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import twentyoz.viven.feature.vi.model.ViRef;
import twentyoz.viven.webapi.client.vi.form.RefForm;

import java.util.List;

@Mapper(implementationName = "RefFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class RefFormMapper {

    public abstract RefForm.Output.Get toGet(ViRef in);

    public abstract ViRef toViRef(RefForm.Input.Add in);

    public abstract ViRef toViRef(RefForm.Input.Modify in);

    public abstract List<RefForm.Output.Get> toGet(List<ViRef> in);

    public abstract RefForm.Output.GetAll toGetAll(ViRef in);
}
