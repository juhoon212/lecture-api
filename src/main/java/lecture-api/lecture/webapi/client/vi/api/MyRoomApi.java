package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.service.MyRoomService;
import twentyoz.viven.webapi.client.vi.form.MyRoomForm;
import twentyoz.viven.webapi.client.vi.form.MyRoomForm.Output.Get;
import twentyoz.viven.webapi.client.vi.mapper.MyRoomFormMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
    value = "MyRoom",
    tags = {"MyRoom"})
@RequestMapping(value = "/vi/my-room", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("MyRoomApi")
@RequiredArgsConstructor
public class MyRoomApi {

  private final MyRoomFormMapper formMapper;

  private final MyRoomService service;

  @SneakyThrows
  @ApiOperation("마이룸 맵 수정")
  @PostMapping("/modify-map")
  public Get modifyMap(@Valid @RequestBody MyRoomForm.Input.ModifyMap in) {
    return formMapper.toGet(service.modifyRoomMyMap(UserInfo.getUserId(), in.getCttId()));
  }

  @SneakyThrows
  @ApiOperation("방 조회")
  @GetMapping("/get")
  public RoomDto get() {
    return service.getMyRoom(UserInfo.getUserId());
  }
}
