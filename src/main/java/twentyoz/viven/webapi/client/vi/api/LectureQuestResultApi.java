package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import java.util.List;

import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.ViLectureQuestResult;
import twentyoz.viven.feature.vi.model.ViLectureQuestResultDto;
import twentyoz.viven.feature.vi.service.LectureQuestResultService;
import twentyoz.viven.webapi.client.vi.form.LectureQuestResultForm;
import twentyoz.viven.webapi.client.vi.mapper.LectureQuestResultFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.LectureQuestResultFormPredicate;
import java.util.UUID;

@Api(value = "LectureQuestResult", tags = {"LectureQuestResult"})
@RequestMapping(value = "/vi/lecture-quest-result", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("LectureQuestResultApi")
@RequiredArgsConstructor
public class LectureQuestResultApi {

  private final LectureQuestResultFormMapper formMapper;

  private final LectureQuestResultService service;

  @SneakyThrows
  @ApiOperation("강의시험문제결과 목록 조회")
  @GetMapping("/get-list")
  public List<ViLectureQuestResultDto> getList(@Valid LectureQuestResultForm.Input.GetAll in) {
    return service.getSupportList(LectureQuestResultFormPredicate.search(in));
  }

  @SneakyThrows
  @ApiOperation("강의시험문제결과 목록 조회")
  @GetMapping("/get-list-no-answer")
  public List<ViLectureQuestResultDto> getWithNoAnswerList(@Valid LectureQuestResultForm.Input.GetAll in) {
    return service.getNoAnswerSupportList(LectureQuestResultFormPredicate.search(in));
  }

  @SneakyThrows
  @ApiOperation("강의시험문제결과 페이징 조회")
  @GetMapping("/get-page")
  public Page<LectureQuestResultForm.Output.GetAll> getPage(@Valid LectureQuestResultForm.Input.GetAll in, Pageable page) {
    return service.getPage(LectureQuestResultFormPredicate.search(in), page)
    .map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("강의시험문제결과 조회")
  @GetMapping("/get/{id}")
  public LectureQuestResultForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("강의시험문제결과 수정")
  @PostMapping("/modify")
  public List<LectureQuestResultForm.Output.GetAll> modify(@Valid @RequestBody LectureQuestResultForm.Input.Modify in) {
    return formMapper.toGetAllList(service.modifyLectureQuestResult(in));
  }

  @SneakyThrows
  @ApiOperation("강의시험문제결과 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("강의시험문제결과 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody LectureQuestResultForm.Input.Remove in) {
    service.remove(in.getIds());
  }
}
