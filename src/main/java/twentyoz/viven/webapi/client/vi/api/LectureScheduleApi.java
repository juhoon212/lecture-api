package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import java.util.List;
import twentyoz.viven.feature.vi.service.LectureScheduleService;
import twentyoz.viven.webapi.client.vi.form.LectureScheduleForm;
import twentyoz.viven.webapi.client.vi.mapper.LectureScheduleFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.LectureScheduleFormPredicate;
import java.util.UUID;

@Api(value = "LectureSchedule", tags = {"LectureSchedule"})
@RequestMapping(value = "/vi/lecture-schedule", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("LectureScheduleApi")
@RequiredArgsConstructor
public class LectureScheduleApi {

  private final LectureScheduleFormMapper formMapper;

  private final LectureScheduleService service;

  @SneakyThrows
  @ApiOperation("강의수업 목록 조회")
  @GetMapping("/get-list")
  public List<LectureScheduleForm.Output.GetAll> getList(@Valid LectureScheduleForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(LectureScheduleFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("강의수업 페이징 조회")
  @GetMapping("/get-page")
  public Page<LectureScheduleForm.Output.GetAll> getPage(@Valid LectureScheduleForm.Input.GetAll in, Pageable page) {
    return service.getPage(LectureScheduleFormPredicate.search(in), page)
    .map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("강의수업 조회")
  @GetMapping("/get/{id}")
  public LectureScheduleForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("강의수업 등록")
  @PostMapping("/add")
  public LectureScheduleForm.Output.Get add(@Valid @RequestBody LectureScheduleForm.Input.Add in) {
    return formMapper.toGet(service.addLectureSchedule(in));
  }

  @SneakyThrows
  @ApiOperation("강의수업 수정")
  @PostMapping("/modify/{id}")
  public LectureScheduleForm.Output.Get modify(@PathVariable UUID id, @Valid @RequestBody LectureScheduleForm.Input.Modify in) {
    return formMapper.toGet(service.modifyLectureSchedule(id, in));
  }

  @SneakyThrows
  @ApiOperation("강의수업 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("강의수업 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody LectureScheduleForm.Input.Remove in) {
    service.remove(in.getIds());
  }

}
