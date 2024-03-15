package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Mapper;
import java.util.List;
import org.mapstruct.Builder;
import twentyoz.viven.feature.vi.model.ViLectureRef;
import twentyoz.viven.webapi.client.vi.form.LectureRefForm;

@Mapper(
    implementationName = "LectureRefFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class LectureRefFormMapper {

  public abstract ViLectureRef toViLectureRef(LectureRefForm.Input.Add in);

  public abstract ViLectureRef toViLectureRef(LectureRefForm.Input.Modify in);

  public abstract LectureRefForm.Output.Get toGet(ViLectureRef in);

  public abstract LectureRefForm.Output.GetAll toGetAll(ViLectureRef in);

  public abstract List<LectureRefForm.Output.GetAll> toGetAllList(List<ViLectureRef> in);

}
