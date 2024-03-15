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

import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.ViLectureExamResultDto;
import twentyoz.viven.feature.vi.model.ViLectureExamResultListDto;
import twentyoz.viven.feature.vi.service.LectureExamResultService;
import twentyoz.viven.webapi.client.vi.form.LectureExamResultForm;
import twentyoz.viven.webapi.client.vi.mapper.LectureExamResultFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.LectureExamResultFormPredicate;
import java.util.UUID;

@Api(value = "LectureExamResult", tags = {"LectureExamResult"})
@RequestMapping(value = "/vi/lecture-exam-result", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("LectureExamResultApi")
@RequiredArgsConstructor
public class LectureExamResultApi {

  private final LectureExamResultFormMapper formMapper;

  private final LectureExamResultService service;

  @SneakyThrows
  @ApiOperation("강의시험지결과 목록 조회")
  @GetMapping("/get-list")

  public List<ViLectureExamResultListDto> getList(@Valid LectureExamResultForm.Input.GetAll in) {
      return service.getSupportList(LectureExamResultFormPredicate.search(in), in, UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("강의시험지결과 페이징 조회")
  @GetMapping("/get-page")
  public Page<LectureExamResultForm.Output.GetAll> getPage(@Valid LectureExamResultForm.Input.GetAll in, Pageable page) {
    return service.getPage(LectureExamResultFormPredicate.search(in), page)
    .map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("강의시험지결과 상세 조회")
  @GetMapping("/get/{id}")
  public ViLectureExamResultDto get(@PathVariable UUID id) {
    return service.getSupport(LectureExamResultFormPredicate.search(id) , UserInfo.getUserId());
  }

//  @SneakyThrows
//  @ApiOperation("강의시험지결과 등록")
//  @PostMapping("/add")
//  public LectureExamResultForm.Output.Get add(@Valid @RequestBody LectureExamResultForm.Input.Add in) {
//    return formMapper.toGet(service.addLectureExamResult(in, UserInfo.getUserId()));
//  }

  @SneakyThrows
  @ApiOperation("강의시험지결과 수정")
  @PostMapping("/modify/{id}")
  public LectureExamResultForm.Output.Get modify(@PathVariable UUID id, @Valid @RequestBody LectureExamResultForm.Input.Modify in) {
    return formMapper.toGet(service.modifyLectureExamResult(id, in));
  }

  @SneakyThrows
  @ApiOperation("강의시험지결과 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("강의시험지결과 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody LectureExamResultForm.Input.Remove in) {
    service.remove(in.getIds());
  }

}
