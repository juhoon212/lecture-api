package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.webapi.client.vi.form.CttJudgeReqForm;
import twentyoz.viven.webapi.client.vi.mapper.CttBinFormMapper;
import twentyoz.viven.webapi.client.vi.mapper.CttJudgeReqFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.CttJudgeReqFormPredicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
    value = "CttJudgeReq",
    tags = {"CttJudgeReq"})
@RequestMapping(value = "/vi/ctt-judge-req", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("CttJudgeReqApi")
@RequiredArgsConstructor
public class CttJudgeReqApi {

  private final CttJudgeReqFormMapper formMapper;

  private final CttBinFormMapper cttBinFormMapper;

  private final CttJudgeReqService service;

  @SneakyThrows
  @ApiOperation("콘텐츠심사요청 목록 조회")
  @GetMapping("/get-list")
  public List<CttJudgeReqDto> getList(@Valid CttJudgeReqForm.Input.GetAll in) {
    return service.getSupportList(CttJudgeReqFormPredicate.search(in));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠심사요청 페이징 조회")
  @GetMapping("/get-page")
  public Page<CttJudgeReqDto> getPage(@Valid CttJudgeReqForm.Input.GetAll in, Pageable page) {
    return service.getSupportPage(CttJudgeReqFormPredicate.search(in), page);
  }

  @SneakyThrows
  @ApiOperation("콘텐츠심사요청 조회")
  @GetMapping("/get/{id}")
  public CttJudgeReqDto get(@PathVariable UUID id) {
    return service.getSupport(id, UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("콘텐츠의 진행중인 심사요청 정보 조회")
  @GetMapping("/get-active-req")
  public CttJudgeReqForm.Output.GetActive getActiveCttJudgeReq(
      CttJudgeReqForm.Input.GetActiveReq in) {
    ViCttJudgeReq result = service.getActiveCttJudgeReq(in.getCttId());
    return formMapper.toGetActive(result);
  }

  @SneakyThrows
  @ApiOperation("콘텐츠심사요청 등록")
  @PostMapping("/add")
  public CttJudgeReqForm.Output.Get add(@Valid @RequestBody CttJudgeReqForm.Input.Add in) {
    return formMapper.toGet(
        service.addCttJudgeReq(
            UserInfo.getUserId(),
            formMapper.toViCttJudgeReq(in),
            cttBinFormMapper.toViCttBin(in.getCttBin())));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠심사요청 수정")
  @PostMapping("/modify/{id}")
  public CttJudgeReqForm.Output.Get modify(
      @PathVariable UUID id,
      Boolean binDelYn,
      @Valid @RequestBody CttJudgeReqForm.Input.Modify in) {
    return formMapper.toGet(
        service.modifyCttJudgeReq(
            id,
            UserInfo.getUserId(),
            formMapper.toViCttJudgeReq(in),
            cttBinFormMapper.toViCttBin(in.getCttBin()),
            binDelYn));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠심사요청 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.removeCttJudgeReq(id);
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 심사 승인요청")
  @PostMapping("/approval-request")
  public CttJudgeReqForm.Output.Get approvalRequest(
      @Valid @RequestBody CttJudgeReqForm.Input.ApprovalRequest in) {
    return formMapper.toGet(
        service.approvalRequest(UserInfo.getUserId(), formMapper.toViCttJudgeReq(in)));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 심사 승인요청 수정")
  @PostMapping("/approval-request-modify")
  public CttJudgeReqForm.Output.Get approvalRequestModify(
      @Valid @RequestBody CttJudgeReqForm.Input.ApprovalRequest in) {
    return formMapper.toGet(
        service.approvalRequestModify(
            in.getCttJudgeReqId(), UserInfo.getUserId(), formMapper.toViCttJudgeReq(in)));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 심사 승인요청 취소")
  @PostMapping("/approval-request-cancel/{id}")
  public void approvalRequestCancel(@PathVariable UUID id) {
    service.approvalRequestCancel(id);
  }
}
