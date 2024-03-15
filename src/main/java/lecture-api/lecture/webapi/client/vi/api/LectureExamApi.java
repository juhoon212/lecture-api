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
import twentyoz.viven.feature.vi.model.ViExamItemDto;
import twentyoz.viven.feature.vi.model.ViLectureExamDto;
import twentyoz.viven.feature.vi.model.ViLectureExamResult;
import twentyoz.viven.feature.vi.scheduler.LectureExamScheduler;
import twentyoz.viven.feature.vi.service.LectureExamService;
import twentyoz.viven.webapi.client.vi.form.LectureExamForm;
import twentyoz.viven.webapi.client.vi.form.LectureExamResultForm;
import twentyoz.viven.webapi.client.vi.mapper.LectureExamFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.LectureExamFormPredicate;
import java.util.UUID;

@Api(value = "LectureExam", tags = {"LectureExam"})
@RequestMapping(value = "/vi/lecture-exam", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("LectureExamApi")
@RequiredArgsConstructor
public class LectureExamApi {

  private final LectureExamFormMapper formMapper;

  private final LectureExamService service;

  private final LectureExamScheduler lectureExamScheduler;

  @SneakyThrows
  @ApiOperation("강의시험지 목록 조회")
  @GetMapping("/get-list")
  public List<LectureExamForm.Output.GetAll> getList(@Valid LectureExamForm.Input.GetAll in) {

    return formMapper.toGetAllList(service.getList(LectureExamFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("강의시험지 페이징 조회")
  @GetMapping("/get-page")
  public Page<LectureExamForm.Output.GetAll> getPage(@Valid LectureExamForm.Input.GetAll in, Pageable page) {
    return service.getPage(LectureExamFormPredicate.search(in), page)
    .map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("강의시험지 상세 조회")
  @GetMapping("/get/{id}")
  public ViLectureExamDto get(@PathVariable UUID id) {
    return service.getSupport(id);
  }

  @SneakyThrows
  @ApiOperation("강의시험지 등록")
  @PostMapping("/add")
  public LectureExamForm.Output.Get add(@Valid @RequestBody LectureExamForm.Input.Add in) {
    return formMapper.toGet(service.addLectureExam(in, UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("강의시험지 수정")
  @PostMapping("/modify")
  public LectureExamForm.Output.Get modify(@Valid @RequestBody LectureExamForm.Input.Modify in) {
    return formMapper.toGet(service.modifyLectureExam(in));
  }

  @SneakyThrows
  @ApiOperation("강의시험지 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("강의시험지 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody LectureExamForm.Input.Remove in) {
    service.remove(in.getIds());
  }

  @SneakyThrows
  @ApiOperation("강의 시험 시작")
  @PostMapping("/start")
  public LectureExamResultForm.Output.Get start(@Valid @RequestBody LectureExamForm.Input.Start in) {
    return service.start(in.getLectureExamId(), UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("강의 시험 제출")
  @PostMapping("/submit")
  public ResponseEntity<?> submit(@Valid @RequestBody LectureExamForm.Input.Submit in) {
    service.submit(in);
    return ResponseEntity.ok().build();
  }

  @SneakyThrows
  @ApiOperation("강의 시험 응시여부 초기화")
  @PostMapping("/reset")
  public ResponseEntity<?> reset(@Valid @RequestBody LectureExamForm.Input.Reset in) {
    service.reset(in, UserInfo.getUserId());
    return ResponseEntity.ok().build();
  }


}
