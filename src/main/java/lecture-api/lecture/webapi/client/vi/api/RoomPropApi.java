package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import twentyoz.viven.webapi.client.vi.form.RoomPropForm;
import twentyoz.viven.webapi.client.vi.mapper.RoomPropFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.RoomPropFormPredicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
    value = "RoomProp",
    tags = {"RoomProp"})
@RequestMapping(value = "/vi/room-prop", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("RoomPropApi")
@RequiredArgsConstructor
public class RoomPropApi {

  private final RoomPropFormMapper formMapper;

  private final RoomPropService service;

  @SneakyThrows
  @ApiOperation("방속성 목록 조회")
  @GetMapping("/get-list")
  public List<RoomPropForm.Output.GetAll> getList(@Valid RoomPropForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(RoomPropFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("방속성 페이징 조회")
  @GetMapping("/get-page")
  public Page<RoomPropForm.Output.GetAll> getPage(
      @Valid RoomPropForm.Input.GetAll in, Pageable page) {
    return service.getPage(RoomPropFormPredicate.search(in), page).map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("방속성 조회")
  @GetMapping("/get/{id}")
  public RoomPropForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("방속성 조회 (방 식별번호)")
  @GetMapping("/get/room-id/{id}")
  public RoomPropForm.Output.Get roomIdGet(@PathVariable UUID id) {
    return formMapper.toGet(service.roomIdGet(id));
  }

  @SneakyThrows
  @ApiOperation("방속성 등록")
  @PostMapping("/add")
  public RoomPropForm.Output.Get add(@Valid @RequestBody RoomPropForm.Input.Add in) {
    return formMapper.toGet(service.addViRoomProp(formMapper.toViRoomProp(in)));
  }

  @SneakyThrows
  @ApiOperation("방속성 수정")
  @PostMapping("/modify/{id}")
  public RoomPropForm.Output.Get modify(
      @PathVariable UUID id, @Valid @RequestBody RoomPropForm.Input.Modify in) {
    return formMapper.toGet(service.modifyViRoomProp(id, formMapper.toViRoomProp(in)));
  }

  @SneakyThrows
  @ApiOperation("방속성 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("방속성 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody RoomPropForm.Input.Remove in) {
    service.remove(in.getIds());
  }
}
