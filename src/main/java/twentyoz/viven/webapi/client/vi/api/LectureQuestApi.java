package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import twentyoz.viven.feature.vi.service.LectureQuestService;

import twentyoz.viven.webapi.client.vi.form.LectureQuestForm;
import twentyoz.viven.webapi.client.vi.mapper.LectureQuestFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.LectureQuestFormPredicate;

import java.util.List;
import java.util.UUID;

@Api(value = "LectureQuest", tags = {"LectureQuest"})
@RequestMapping(value = "/vi/lecture-quest", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("LectureQuestApi")
@RequiredArgsConstructor
public class LectureQuestApi {

  private final LectureQuestFormMapper formMapper;

  private final LectureQuestService service;

  @SneakyThrows
  @ApiOperation("강의시험문제 목록 조회")
  @GetMapping("/get-list")
  public List<LectureQuestForm.Output.GetAll> getList(@Valid LectureQuestForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(LectureQuestFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("강의시험문제 목록 조회(답안x)")
  @GetMapping("/get-list-no-answer")
  public List<LectureQuestForm.Output.GetNoAnswer> getListWithNoAnswer(@Valid LectureQuestForm.Input.GetAll in) {
    return formMapper.toGetAllListWithNoAnswer(service.getList(LectureQuestFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("강의시험문제 페이징 조회")
  @GetMapping("/get-page")
  public Page<LectureQuestForm.Output.GetAll> getPage(@Valid LectureQuestForm.Input.GetAll in, Pageable page) {
    return service.getPage(LectureQuestFormPredicate.search(in), page)
    .map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("강의시험문제 조회")
  @GetMapping("/get/{id}")
  public LectureQuestForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

//  @SneakyThrows
//  @ApiOperation("강의시험문제 등록")
//  @PostMapping("/add")
//  public LectureQuestForm.Output.Get add(@Valid @RequestBody LectureQuestForm.Input.AddOne in) {
//    return formMapper.toGet(service.addLectureQuest(formMapper.toViLectureQuest(in)));
//  }

  @SneakyThrows
  @ApiOperation("강의시험문제 다중 등록")
  @PostMapping("/addList")
  public List<LectureQuestForm.Output.GetAll> add(@Valid @RequestBody LectureQuestForm.Input.Add in) {
    return formMapper.toGetAllList(service.addLectureQuest(in));
  }

  @SneakyThrows
  @ApiOperation("강의시험문제 수정")
  @PostMapping("/modify/{id}")
  public LectureQuestForm.Output.Get modify(@PathVariable UUID id, @Valid @RequestBody LectureQuestForm.Input.Modify in) {
    return formMapper.toGet(service.modifyLectureQuest(id, formMapper.toViLectureQuest(in)));
  }

  @SneakyThrows
  @ApiOperation("강의시험문제 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("강의시험문제 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody LectureQuestForm.Input.Remove in) {
    service.remove(in.getIds());
  }

}
