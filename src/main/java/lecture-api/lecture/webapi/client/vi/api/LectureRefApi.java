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
import twentyoz.viven.feature.vi.model.ViLectureRef;
import twentyoz.viven.feature.vi.model.ViLectureRefDto;
import twentyoz.viven.feature.vi.service.LectureRefService;
import twentyoz.viven.webapi.client.vi.form.LectureRefForm;
import twentyoz.viven.webapi.client.vi.mapper.LectureRefFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.LectureRefFormPredicate;
import java.util.UUID;

@Api(value = "LectureRef", tags = {"LectureRef"})
@RequestMapping(value = "/vi/lecture-ref", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("LectureRefApi")
@RequiredArgsConstructor
public class LectureRefApi {

  private final LectureRefFormMapper formMapper;

  private final LectureRefService service;

  @SneakyThrows
  @ApiOperation("강의자료 목록 조회")
  @GetMapping("/get-list")
  public List<ViLectureRefDto> getList(@Valid LectureRefForm.Input.GetAll in) {
    return service.getSupportList(LectureRefFormPredicate.search(in), UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("강의자료 페이징 조회")
  @GetMapping("/get-page")
  public Page<ViLectureRefDto> getPage(@Valid LectureRefForm.Input.GetAll in, Pageable page) {
    return service.getSupportPage(LectureRefFormPredicate.search(in), page, UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("강의자료 조회")
  @GetMapping("/get/{id}")
  public LectureRefForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("강의자료 등록")
  @PostMapping("/add")
  public LectureRefForm.Output.Get add(@Valid LectureRefForm.Input.Add in) {
    return formMapper.toGet(service.addLectureRef(formMapper.toViLectureRef(in)));
  }

  @SneakyThrows
  @ApiOperation("강의자료 수정")
  @PostMapping("/modify")
  public LectureRefForm.Output.Get modify(@Valid LectureRefForm.Input.Modify in) {
    return formMapper.toGet(service.modifyLectureRef(formMapper.toViLectureRef(in)));
  }

  @SneakyThrows
  @ApiOperation("강의자료 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("강의자료 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody LectureRefForm.Input.Remove in) {
    service.remove(in.getIds());
  }

}
