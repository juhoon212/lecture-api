package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.vi.form.CttJudgeHistForm.Input.Add;
import twentyoz.viven.webapi.client.vi.form.CttJudgeHistForm.Input.Modify;
import twentyoz.viven.webapi.client.vi.form.CttJudgeHistForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.CttJudgeHistForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
    implementationName = "CttJudgeHistFormMapperImpl",
    builder = @Builder(disableBuilder = true))
public abstract class CttJudgeHistFormMapper {

  public abstract ViCttJudgeHist toViCttJudgeHist(Add in);

  public abstract ViCttJudgeHist toViCttJudgeHist(Modify in);

  public abstract Get toGet(ViCttJudgeHist in);

  public abstract GetAll toGetAll(ViCttJudgeHist in);

  public abstract List<GetAll> toGetAllList(List<ViCttJudgeHist> in);
}
