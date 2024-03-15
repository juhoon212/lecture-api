package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViExamItem;
import twentyoz.viven.webapi.client.vi.form.ExamItemForm;

import java.util.List;

@Mapper(
    implementationName = "ExamItemFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class ExamItemFormMapper {

  public abstract ViExamItem toViExamItem(ExamItemForm.Input.AddAll in);

  public abstract ExamItemForm.Output.Get toGet(ViExamItem in);

  public abstract ExamItemForm.Output.GetAll toGetAll(ViExamItem in);

  public abstract List<ExamItemForm.Output.GetAll> toGetAllList(List<ViExamItem> in);

}
