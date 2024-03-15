package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Mapper;
import java.util.List;
import org.mapstruct.Builder;
import twentyoz.viven.feature.vi.model.ViLectureSchedule;
import twentyoz.viven.webapi.client.vi.form.LectureScheduleForm;

@Mapper(
    implementationName = "LectureScheduleFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class LectureScheduleFormMapper {

  public abstract ViLectureSchedule toViLectureSchedule(LectureScheduleForm.Input.Add in);

  public abstract ViLectureSchedule toViLectureSchedule(LectureScheduleForm.Input.Modify in);

  public abstract LectureScheduleForm.Output.Get toGet(ViLectureSchedule in);

  public abstract LectureScheduleForm.Output.GetAll toGetAll(ViLectureSchedule in);

  public abstract List<LectureScheduleForm.Output.GetAll> toGetAllList(List<ViLectureSchedule> in);

}
