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
import twentyoz.viven.feature.vi.service.LectureService;
import twentyoz.viven.webapi.client.vi.form.LectureForm;
import twentyoz.viven.webapi.client.vi.mapper.LectureFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.LectureFormPredicate;
import java.util.UUID;

@Api(value = "Lecture", tags = {"Lecture"})
@RequestMapping(value = "/vi/lecture", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("LectureApi")
@RequiredArgsConstructor
public class LectureApi {

  private final LectureFormMapper formMapper;

  private final LectureService service;

  @SneakyThrows
  @ApiOperation("강의 목록 조회")
  @GetMapping("/get-list")
  public List<LectureForm.Output.GetAll> getList(@Valid LectureForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(LectureFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("강의 페이징 조회")
  @GetMapping("/get-page")
  public Page<LectureForm.Output.GetAll> getPage(@Valid LectureForm.Input.GetAll in, Pageable page) {
    return service.getPage(LectureFormPredicate.search(in), page)
    .map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("강의 조회")
  @GetMapping("/get/{id}")
  public LectureForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("방 Id로 강의 조회")
  @GetMapping("/get-lecture/{roomId}")
  public LectureForm.Output.Get getLectureByRoomId(@PathVariable UUID roomId) {
    return formMapper.toGet(service.getRoomId(roomId));
  }



  @SneakyThrows
  @ApiOperation("강의 등록")
  @PostMapping("/add")
  public LectureForm.Output.Get add(@Valid LectureForm.Input.Add in) {
    return formMapper.toGet(service.addLecture(formMapper.toViLecture(in)));
  }

  @SneakyThrows
  @ApiOperation("강의 수정")
  @PostMapping("/modify")
  public LectureForm.Output.Get modify(@Valid LectureForm.Input.Modify in) {
    return formMapper.toGet(service.modifyLecture(formMapper.toViLecture(in)));
  }

  @SneakyThrows
  @ApiOperation("강의 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("강의 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody LectureForm.Input.Remove in) {
    service.remove(in.getIds());
  }

}
