package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Mapper;
import java.util.List;
import org.mapstruct.Builder;
import twentyoz.viven.feature.vi.model.ViLecture;
import twentyoz.viven.webapi.client.vi.form.LectureForm;

@Mapper(
    implementationName = "LectureFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class LectureFormMapper {

  public abstract ViLecture toViLecture(LectureForm.Input.Add in);

  public abstract ViLecture toViLecture(LectureForm.Input.Modify in);

  public abstract LectureForm.Output.Get toGet(ViLecture in);

  public abstract LectureForm.Output.GetAll toGetAll(ViLecture in);

  public abstract List<LectureForm.Output.GetAll> toGetAllList(List<ViLecture> in);

}
