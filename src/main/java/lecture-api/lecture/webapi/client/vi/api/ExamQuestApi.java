package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.service.ExamQuestService;
import twentyoz.viven.webapi.client.vi.form.ExamQuestForm;
import twentyoz.viven.webapi.client.vi.mapper.ExamQuestFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.ExamQuestFormPredicate;

import java.util.List;
import java.util.UUID;

@Api(value = "ExamQuest", tags = {"ExamQuest"})
@RequestMapping(value = "/vi/exam-quest", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("ExamQuestApi")
@RequiredArgsConstructor
public class ExamQuestApi {

  private final ExamQuestFormMapper formMapper;

  private final ExamQuestService service;

  @SneakyThrows
  @ApiOperation("시험문제 목록 조회")
  @GetMapping("/get-list")
  public List<ExamQuestForm.Output.GetAll> getList(ExamQuestForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(ExamQuestFormPredicate.search(UserInfo.getUserId(), in)));
  }

  @SneakyThrows
  @ApiOperation("시험문제 페이징 조회")
  @GetMapping("/get-page")
  public Page<ExamQuestForm.Output.GetAll> getPage(ExamQuestForm.Input.GetAll in, Pageable page) {
    return service.getPage(ExamQuestFormPredicate.search(UserInfo.getUserId(), in), page)
            .map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("시험문제 조회")
  @GetMapping("/get/{id}")
  public ExamQuestForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("시험문제 등록")
  @PostMapping("/add")
  public ExamQuestForm.Output.Get add(@Valid ExamQuestForm.Input.Add in) {
    return formMapper.toGet(service.addExamQuest(formMapper.toViExamQuest(in), UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("시험문제 수정")
  @PostMapping("/modify/{id}")
  public ExamQuestForm.Output.Get modify(@PathVariable UUID id, @Valid ExamQuestForm.Input.Modify in) {
    return formMapper.toGet(service.modifyExamQuest(id, formMapper.toViExamQuest(in)));
  }

  @SneakyThrows
  @ApiOperation("시험문제 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid ExamQuestForm.Input.Remove in) {
    service.remove(in.getIds());
  }

}
