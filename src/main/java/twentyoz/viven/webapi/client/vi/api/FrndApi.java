package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import twentyoz.viven.feature.mb.model.MbrFrndDto;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.FrndDto;
import twentyoz.viven.feature.vi.service.FrndService;
import twentyoz.viven.webapi.client.mb.form.MbrForm;
import twentyoz.viven.webapi.client.mb.predicate.MbrFormPredicate;
import twentyoz.viven.webapi.client.vi.mapper.FrndFormMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
    value = "Frnd",
    tags = {"Frnd"})
@RequestMapping(value = "/vi/frnd", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("FrndApi")
@RequiredArgsConstructor
public class FrndApi {

  private final FrndFormMapper formMapper;

  private final FrndService service;

  @SneakyThrows
  @ApiOperation("내 친구 목록 조회")
  @GetMapping("/get-list")
  public List<FrndDto> getList(@Valid MbrForm.Input.GetAll in) {
    return formMapper.toGetAllList(
        service.getList(MbrFormPredicate.search(in), UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("내 친구 페이징 조회")
  @GetMapping("/get-page")
  public Page<FrndDto> getPage(@Valid MbrForm.Input.GetAll in, Pageable page) {
    return service.getPage(MbrFormPredicate.search(in), page, UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("내 친구 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.removeTarget(id);
  }

  @SneakyThrows
  @ApiOperation("친구 검색 페이징 조회")
  @GetMapping("/get-search-page")
  public Page<MbrFrndDto> getSearchPage(@Valid MbrForm.Input.GetFriendAll in, Pageable page) {
    return service.getPageSupport(MbrFormPredicate.searchFriend(in), page, UserInfo.getUserId());
  }
}
