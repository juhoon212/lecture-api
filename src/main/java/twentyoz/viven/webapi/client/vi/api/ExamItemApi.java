package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.ViExamDto;
import twentyoz.viven.feature.vi.model.ViExamItemDto;
import twentyoz.viven.feature.vi.service.ExamItemService;
import twentyoz.viven.webapi.client.vi.form.ExamItemForm;
import twentyoz.viven.webapi.client.vi.mapper.ExamItemFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.ExamItemFormPredicate;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Api(value = "ExamItem", tags = {"ExamItem"})
@RequestMapping(value = "/vi/exam-item", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("ExamItemApi")
@RequiredArgsConstructor
public class ExamItemApi {

  private final ExamItemFormMapper formMapper;

  private final ExamItemService service;

  @SneakyThrows
  @ApiOperation("시험지문제 목록 조회")
  @GetMapping("/get-list")
  public List<ViExamItemDto> getList(@Valid ExamItemForm.Input.GetAll in) {
    return service.getSupportList(ExamItemFormPredicate.search(in, UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("시험지문제 페이징 조회")
  @GetMapping("/get-page")
  public Page<ViExamItemDto> getPage(ExamItemForm.Input.GetAll in, Pageable page) {
    return service.getSupportPage(ExamItemFormPredicate.search(in, UserInfo.getUserId()), page);
  }

  @SneakyThrows
  @ApiOperation("시험지문제 조회")
  @GetMapping("/get/{id}")
  public ExamItemForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("시험지문제 등록")
  @PostMapping("/add")
  public List<ExamItemForm.Output.GetAll> add(@Valid @RequestBody ExamItemForm.Input.AddAll in) {
    return formMapper.toGetAllList(service.addExamItem(in, UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("시험지문제 수정")
  @PostMapping("/modify")
  public List<ExamItemForm.Output.GetAll> modify(@Valid @RequestBody ExamItemForm.Input.ModifyAll in) {
    return formMapper.toGetAllList(service.modifyExamItem(in));
  }

  @SneakyThrows
  @ApiOperation("시험지문제 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid ExamItemForm.Input.Remove in) {
    service.remove(in.getIds());
  }

}
