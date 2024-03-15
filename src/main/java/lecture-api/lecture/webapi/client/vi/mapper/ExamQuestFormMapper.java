package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViExamQuest;
import twentyoz.viven.webapi.client.vi.form.ExamQuestForm;

import java.util.List;

@Mapper(
    implementationName = "ExamQuestFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class ExamQuestFormMapper {

  public abstract ViExamQuest toViExamQuest(ExamQuestForm.Input.Add in);
  public abstract ViExamQuest toViExamQuest(ExamQuestForm.Input.GetAll in);

  public abstract ViExamQuest toViExamQuest(ExamQuestForm.Input.Modify in);

  public abstract ExamQuestForm.Output.Get toGet(ViExamQuest in);

  public abstract ExamQuestForm.Output.GetAll toGetAll(ViExamQuest in);

  public abstract List<ExamQuestForm.Output.GetAll> toGetAllList(List<ViExamQuest> in);


}
