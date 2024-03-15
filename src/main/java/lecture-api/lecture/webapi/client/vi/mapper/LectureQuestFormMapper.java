package twentyoz.viven.webapi.client.vi.mapper;

import org.mapstruct.Mapper;
import java.util.List;
import org.mapstruct.Builder;
import twentyoz.viven.feature.vi.model.ViLectureQuest;
import twentyoz.viven.webapi.client.vi.form.LectureQuestForm;

@Mapper(
    implementationName = "LectureQuestFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class LectureQuestFormMapper {

  public abstract ViLectureQuest toViLectureQuest(LectureQuestForm.Input.Add in);

  public abstract ViLectureQuest toViLectureQuest(LectureQuestForm.Input.Modify in);

  public abstract LectureQuestForm.Output.Get toGet(ViLectureQuest in);

  public abstract LectureQuestForm.Output.GetAll toGetAll(ViLectureQuest in);

  public abstract List<LectureQuestForm.Output.GetAll> toGetAllList(List<ViLectureQuest> in);

  public abstract List<LectureQuestForm.Output.GetNoAnswer> toGetAllListWithNoAnswer(List<ViLectureQuest> in);

}
