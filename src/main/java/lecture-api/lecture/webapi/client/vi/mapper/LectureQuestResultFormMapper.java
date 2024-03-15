package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Mapper;
import java.util.List;
import org.mapstruct.Builder;
import twentyoz.viven.feature.vi.model.ViLectureQuestResult;
import twentyoz.viven.webapi.client.vi.form.LectureQuestResultForm;

@Mapper(
    implementationName = "LectureQuestResultFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class LectureQuestResultFormMapper {


  public abstract ViLectureQuestResult toViLectureQuestResult(LectureQuestResultForm.Input.Modify in);

  public abstract LectureQuestResultForm.Output.Get toGet(ViLectureQuestResult in);

  public abstract LectureQuestResultForm.Output.GetAll toGetAll(ViLectureQuestResult in);

  public abstract List<LectureQuestResultForm.Output.GetAll> toGetAllList(List<ViLectureQuestResult> in);

}
