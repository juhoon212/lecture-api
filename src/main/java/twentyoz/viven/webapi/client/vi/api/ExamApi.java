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
import twentyoz.viven.feature.vi.model.ViExamDto;
import twentyoz.viven.feature.vi.service.ExamService;
import twentyoz.viven.webapi.client.vi.form.ExamForm;
import twentyoz.viven.webapi.client.vi.mapper.ExamFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.ExamFormPredicate;
import java.util.UUID;

@Api(value = "Exam", tags = {"Exam"})
@RequestMapping(value = "/vi/exam", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("ExamApi")
@RequiredArgsConstructor
public class ExamApi {

  private final ExamFormMapper formMapper;

  private final ExamService service;

  @SneakyThrows
  @ApiOperation("시험지 목록 조회")
  @GetMapping("/get-list")
  public List<ExamForm.Output.GetAll> getList(@Valid ExamForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(ExamFormPredicate.search(in, UserInfo.getUserId())));
  }

  @SneakyThrows
  @ApiOperation("시험지 강의 관련 점수총합 목록 조회")
  @GetMapping("/get-score-list")
  public List<ViExamDto> getSupportList(@Valid ExamForm.Input.GetAll in) {
    return service.getSupportList((ExamFormPredicate.search(in, UserInfo.getUserId())));
  }

  @SneakyThrows
  @ApiOperation("시험지 페이징 조회")
  @GetMapping("/get-page")
  public Page<ExamForm.Output.GetAll> getPage(@Valid ExamForm.Input.GetAll in, Pageable page) {
    return service.getPage(ExamFormPredicate.search(in, UserInfo.getUserId()), page)
    .map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("시험지 강의관련 점수총합 페이징 조회")
  @GetMapping("/get-score-page")
  public Page<ViExamDto> getSupportPage(@Valid ExamForm.Input.GetAll in, Pageable page) {
    return service.getSupportPage(ExamFormPredicate.search(in, UserInfo.getUserId()), page);
  }

  @SneakyThrows
  @ApiOperation("시험지 조회")
  @GetMapping("/get/{id}")
  public ExamForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("시험지 등록")
  @PostMapping("/add")
  public ExamForm.Output.Get add(@Valid ExamForm.Input.Add in) {
    return formMapper.toGet(service.addExam(formMapper.toViExam(in), UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("시험지 수정")
  @PostMapping("/modify")
  public ExamForm.Output.Get modify(@Valid ExamForm.Input.Modify in) {
    return formMapper.toGet(service.modifyExam(formMapper.toViExam(in), UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("시험지 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("시험지 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody ExamForm.Input.Remove in) {
    service.remove(in.getIds());
  }

}
