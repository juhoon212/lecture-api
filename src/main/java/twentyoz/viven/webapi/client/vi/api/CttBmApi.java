package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.CttBmDto;
import twentyoz.viven.feature.vi.service.CttBmService;
import twentyoz.viven.webapi.client.vi.form.CttBmForm;
import twentyoz.viven.webapi.client.vi.form.CttBmForm.Output.GetAll;
import twentyoz.viven.webapi.client.vi.mapper.CttBmFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.CttBmFormPredicate;
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
    value = "CttBm",
    tags = {"CttBm"})
@RequestMapping(value = "/vi/ctt-bm", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("CttBmApi")
@RequiredArgsConstructor
public class CttBmApi {

  private final CttBmFormMapper formMapper;

  private final CttBmService service;

  @SneakyThrows
  @ApiOperation("콘텐츠 즐겨찾기 목록 조회")
  @GetMapping("/get-list")
  public List<GetAll> getList(@Valid CttBmForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(CttBmFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 즐겨찾기 페이징 조회 (DTO)")
  @GetMapping("/get-page-dto")
  public Page<CttBmDto> getPageDto(@Valid CttBmForm.Input.GetAll in, Pageable page) {
    return service.getSupportPage(CttBmFormPredicate.search(in), page);
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 즐겨찾기여부 조회")
  @GetMapping("/check-bm/{id}")
  public CttBmForm.Output.Get checkBm(@PathVariable UUID id) {
    return formMapper.toGet(service.checkBmYn(id, UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 즐겨찾기 등록")
  @PostMapping("/add")
  public CttBmForm.Output.Get add(@Valid @RequestBody CttBmForm.Input.Add in) {
    return formMapper.toGet(service.addCttBm(UserInfo.getUserId(), formMapper.toViCttBm(in)));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 즐겨찾기 삭제")
  @PostMapping("/remove/{id}")
  public void remove(@PathVariable UUID id) {
    service.remove(id);
  }
}
