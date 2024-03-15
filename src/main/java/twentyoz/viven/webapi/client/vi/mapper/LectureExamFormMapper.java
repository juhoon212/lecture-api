package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Mapper;
import java.util.List;
import java.util.UUID;

import org.mapstruct.Builder;
import twentyoz.viven.feature.vi.model.ViLectureExam;
import twentyoz.viven.webapi.client.vi.form.LectureExamForm;

@Mapper(
    implementationName = "LectureExamFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class LectureExamFormMapper {

  public abstract ViLectureExam toViLectureExam(LectureExamForm.Input.Add in);

  public abstract ViLectureExam toViLectureExam(LectureExamForm.Input.Modify in);

  public abstract LectureExamForm.Output.Get toGet(ViLectureExam in);

  public abstract LectureExamForm.Output.GetAll toGetAll(ViLectureExam in);

  public abstract List<LectureExamForm.Output.GetAll> toGetAllList(List<ViLectureExam> in);

}
