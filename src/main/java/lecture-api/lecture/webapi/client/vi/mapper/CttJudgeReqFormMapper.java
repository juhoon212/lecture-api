package twentyoz.viven.webapi.client.vi.mapper;

import java.util.List;

import twentyoz.viven.webapi.client.vi.form.CttJudgeReqForm.Input.Add;
import twentyoz.viven.webapi.client.vi.form.CttJudgeReqForm.Input.ApprovalRequest;
import twentyoz.viven.webapi.client.vi.form.CttJudgeReqForm.Input.Modify;
import twentyoz.viven.webapi.client.vi.form.CttJudgeReqForm.Output.Get;
import twentyoz.viven.webapi.client.vi.form.CttJudgeReqForm.Output.GetActive;
import twentyoz.viven.webapi.client.vi.form.CttJudgeReqForm.Output.GetAll;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "CttJudgeReqFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class CttJudgeReqFormMapper {

  public abstract ViCttJudgeReq toViCttJudgeReq(Add in);

  public abstract ViCttJudgeReq toViCttJudgeReq(Modify in);

  public abstract ViCttJudgeReq toViCttJudgeReq(ApprovalRequest in);

  public abstract Get toGet(ViCttJudgeReq in);

  public abstract GetActive toGetActive(ViCttJudgeReq in);

  public abstract GetAll toGetAll(ViCttJudgeReq in);

  public abstract List<GetAll> toGetAllList(List<ViCttJudgeReq> in);
}
