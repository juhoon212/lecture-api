package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.ViRoomConnHistDto;
import twentyoz.viven.feature.vi.service.RoomConnHistService;
import twentyoz.viven.webapi.client.vi.mapper.RoomConnHistFormMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
    value = "RoomConnHist",
    tags = {"RoomConnHist"})
@RequestMapping(value = "/vi/room-conn-hist", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("RoomConnHistApi")
@RequiredArgsConstructor
public class RoomConnHistApi {

  private final RoomConnHistFormMapper formMapper;

  private final RoomConnHistService service;

  @SneakyThrows
  @ApiOperation("최근 방문한 방 페이징 조회")
  @GetMapping("/get-page")
  public Page<ViRoomConnHistDto> getPage(Pageable page) {
    return service.getPageSupport(page, UserInfo.getUserId());
  }
}
