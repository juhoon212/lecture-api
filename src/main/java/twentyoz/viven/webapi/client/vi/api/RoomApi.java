package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.RoomDto;
import twentyoz.viven.feature.vi.model.ViRoomSv;
import twentyoz.viven.feature.vi.service.RoomService;
import twentyoz.viven.webapi.client.vi.form.RoomForm;
import twentyoz.viven.webapi.client.vi.mapper.RoomFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.RoomFormPredicate;
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
    value = "Room",
    tags = {"Room"})
@RequestMapping(value = "/vi/room", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("RoomApi")
@RequiredArgsConstructor
public class RoomApi {

  private final RoomFormMapper formMapper;

  private final RoomService service;

  @SneakyThrows
  @ApiOperation("방 목록 조회")
  @GetMapping("/get-list")
  public List<RoomDto> getList(@Valid RoomForm.Input.GetAll in) {
    return service.getSupportList(RoomFormPredicate.search(in));
  }

  @SneakyThrows
  @ApiOperation("방 페이징 조회")
  @GetMapping("/get-page")
  public Page<RoomDto> getPage(@Valid RoomForm.Input.GetAll in, Pageable page) {
    return service.getSupportPage(RoomFormPredicate.search(in), page);
  }

  @SneakyThrows
  @ApiOperation("방 조회")
  @GetMapping("/get/{id}")
  public RoomDto get(@PathVariable UUID id) {
    return service.getSupport(id);
  }

  @SneakyThrows
  @ApiOperation("나의 방 페이징 조회")
  @GetMapping("/get-my-room-page")
  public Page<RoomDto> getMyRoomPage(@Valid RoomForm.Input.GetAll in, Pageable page) {
    return service.getMyRoomSupportPage(RoomFormPredicate.search(in), page, UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("방 등록")
  @PostMapping("/add")
  public RoomForm.Output.Get add(
      @Valid @RequestBody RoomForm.Input.Add in, HttpServletRequest request) {
    return formMapper.toGet(
        service.addViRoom(UserInfo.getUserId(), formMapper.toViRoom(in), request));
  }

  @SneakyThrows
  @ApiOperation("방 수정")
  @PostMapping("/modify/{id}")
  public RoomForm.Output.Get modify(
      @PathVariable UUID id, @Valid @RequestBody RoomForm.Input.Modify in) {
    return formMapper.toGet(service.modifyViRoom(id, formMapper.toViRoom(in)));
  }

  @SneakyThrows
  @ApiOperation("방 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }

  @SneakyThrows
  @ApiOperation("방 다중 삭제")
  @PostMapping("/remove-list")
  public void remove(@Valid @RequestBody RoomForm.Input.Remove in) {
    service.remove(in.getIds());
  }

  @SneakyThrows
  @ApiOperation("방 비밀번호 확인")
  @PostMapping("/check-password/{id}")
  public RoomForm.Output.Get checkPassword(
      @PathVariable UUID id, @Valid @RequestBody RoomForm.Input.CheckPassword in) {
    return formMapper.toGet(service.checkPassword(id, in.getRoomPw()));
  }

  @SneakyThrows
  @ApiOperation("방 참가 - 가용한 방서버 정보")
  @GetMapping("/dts/join/{id}")
  public ViRoomSv joinRoom(@PathVariable UUID id) {

    return service.joinRoom(
        id,
        UserInfo.getUserId(),
        UserInfo.getUserName(),
        UserInfo.getNickName(),
        UserInfo.getLoginId());
  }
}
