package twentyoz.viven.webapi.client.vi.api;

import com.querydsl.core.BooleanBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.webapi.client.vi.form.CttForm;
import twentyoz.viven.webapi.client.vi.mapper.CttFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.CttFormPredicate;
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
    value = "Ctt",
    tags = {"Ctt"})
@RequestMapping(value = "/vi/ctt", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("CttApi")
@RequiredArgsConstructor
public class CttApi {

  private final CttFormMapper formMapper;

  private final CttService service;

  @SneakyThrows
  @ApiOperation("콘텐츠 목록 조회")
  @GetMapping("/get-list")
  public List<CttDto> getList(@Valid CttForm.Input.GetAll in) {
    return service.getSupportList(
        CttFormPredicate.search(in), in.getIsMyCtt(), UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 페이징 조회")
  @GetMapping("/get-page")
  public Page<CttDto> getPage(@Valid CttForm.Input.GetAll in, Pageable page) {
    return service.getSupportPage(
        CttFormPredicate.search(in), page, in.getIsMyCtt(), UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 조회")
  @GetMapping("/get")
  public CttDto get(@Valid CttForm.Input.Get in) {
    return service.getSupport(in.getCttId(), in.getIsMyCtt(), UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 대표이미지 조회")
  @GetMapping(value = "/get-main-url", produces = MediaType.TEXT_HTML_VALUE)
  public String getMainUrl(@Valid CttForm.Input.GetId in) {
    return service.getSupportMainUrl(in.getCttId(), UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 수정")
  @PostMapping("/modify/{id}")
  public CttForm.Output.Get modify(
      @PathVariable UUID id, @Valid @RequestBody CttForm.Input.Modify in) {
    return formMapper.toGet(service.modifyCtt(id, formMapper.toViCtt(in)));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠 전체 페이징 조회")
  @GetMapping("/get-page-all")
  public Page<CttDto> getPageAvt(@Valid CttForm.Input.GetAllAvt in, Pageable page) {
    BooleanBuilder builder = new BooleanBuilder();
    builder.and(CttFormPredicate.searchAvt(in));

    return service.getSupportPage(builder, page, false, UserInfo.getUserId());
  }
}
