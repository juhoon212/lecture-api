package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Mapper;
import java.util.List;
import org.mapstruct.Builder;
import twentyoz.viven.feature.vi.model.ViLectureExamResult;
import twentyoz.viven.webapi.client.vi.form.LectureExamResultForm;

@Mapper(
    implementationName = "LectureExamResultFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class LectureExamResultFormMapper {

  public abstract ViLectureExamResult toViLectureExamResult(LectureExamResultForm.Input.Add in);

  public abstract ViLectureExamResult toViLectureExamResult(LectureExamResultForm.Input.Modify in);

  public abstract LectureExamResultForm.Output.Get toGet(ViLectureExamResult in);

  public abstract LectureExamResultForm.Output.GetAll toGetAll(ViLectureExamResult in);

  public abstract List<LectureExamResultForm.Output.GetAll> toGetAllList(List<ViLectureExamResult> in);

}
