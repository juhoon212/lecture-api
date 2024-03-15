package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Mapper;
import java.util.List;
import org.mapstruct.Builder;
import twentyoz.viven.feature.vi.model.ViExam;
import twentyoz.viven.webapi.client.vi.form.ExamForm;

@Mapper(
    implementationName = "ExamFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class ExamFormMapper {

  public abstract ViExam toViExam(ExamForm.Input.Add in);

  public abstract ViExam toViExam(ExamForm.Input.Modify in);

  public abstract ExamForm.Output.Get toGet(ViExam in);

  public abstract ExamForm.Output.GetAll toGetAll(ViExam in);

  public abstract List<ExamForm.Output.GetAll> toGetAllList(List<ViExam> in);

}
