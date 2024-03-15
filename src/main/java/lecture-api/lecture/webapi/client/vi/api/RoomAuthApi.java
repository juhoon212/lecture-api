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

import twentyoz.viven.webapi.client.vi.form.RoomAuthForm;
import twentyoz.viven.webapi.client.vi.mapper.RoomAuthFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.RoomAuthFormPredicate;
import java.util.UUID;

@Api(value = "RoomAuth", tags = {"RoomAuth"})
@RequestMapping(value = "/vi/room-auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("RoomAuthApi")
@RequiredArgsConstructor
public class RoomAuthApi {

  private final RoomAuthFormMapper formMapper;

  private final RoomAuthService service;

  @SneakyThrows
  @ApiOperation("방권한 목록 조회")
  @GetMapping("/get-list")
  public List<RoomAuthForm.Output.GetAll> getList(@Valid RoomAuthForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(RoomAuthFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("방권한 페이징 조회")
  @GetMapping("/get-page")
  public Page<RoomAuthForm.Output.GetAll> getPage(@Valid RoomAuthForm.Input.GetAll in, Pageable page) {
    return service.getPage(RoomAuthFormPredicate.search(in), page)
    .map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("방권한 조회")
  @GetMapping("/get/{id}")
  public RoomAuthForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("방권한 등록")
  @PostMapping("/add")
  public RoomAuthForm.Output.Get add(@Valid @RequestBody RoomAuthForm.Input.Add in) {
    return formMapper.toGet(service.addRoomAuth(formMapper.toViRoomAuth(in)));
  }

  @SneakyThrows
  @ApiOperation("방권한 수정")
  @PostMapping("/modify/{id}")
  public RoomAuthForm.Output.Get modify(@PathVariable UUID id, @Valid @RequestBody RoomAuthForm.Input.Modify in) {
    return formMapper.toGet(service.modifyRoomAuth(id, formMapper.toViRoomAuth(in)));
  }

  @SneakyThrows
  @ApiOperation("방권한 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("방권한 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody RoomAuthForm.Input.Remove in) {
    service.remove(in.getIds());
  }

}
