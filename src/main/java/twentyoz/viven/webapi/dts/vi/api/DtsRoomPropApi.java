package twentyoz.viven.webapi.dts.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twentyoz.viven.feature.vi.service.RoomPropService;
import twentyoz.viven.webapi.dts.vi.form.DtsRoomPropForm;
import twentyoz.viven.webapi.dts.vi.mapper.DtsRoomPropFormMapper;

@Api(
    value = "DtsRoomProp",
    tags = {"DtsRoomProp"})
@RequestMapping(value = "/dts/vi/room-prop", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("DtsRoomPropApi")
@RequiredArgsConstructor
public class DtsRoomPropApi {

  private final DtsRoomPropFormMapper formMapper;

  private final RoomPropService service;

  @SneakyThrows
  @ApiOperation("방속성 수정")
  @PostMapping("/modify/{id}")
  public DtsRoomPropForm.Output.Get modify(
      @PathVariable UUID id, @Valid @RequestBody DtsRoomPropForm.Input.Modify in) {
    return formMapper.toGet(service.modifyViRoomProp(id, formMapper.toViRoomProp(in)));
  }

}
