package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import twentyoz.viven.webapi.client.vi.form.CttBinForm;
import twentyoz.viven.webapi.client.vi.mapper.CttBinFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.CttBinFormPredicate;

@Api(
    value = "CttBin",
    tags = {"CttBin"})
@RequestMapping(value = "/vi/ctt-bin", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("CttBinApi")
@RequiredArgsConstructor
public class CttBinApi {

  private final CttBinFormMapper formMapper;

  private final CttBinService service;

  @SneakyThrows
  @ApiOperation("콘텐츠바이너리 목록 조회")
  @GetMapping("/get-list")
  public List<CttBinDto> getList(@Valid CttBinForm.Input.GetAll in) {
    return service.getSupportList(CttBinFormPredicate.search(in));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠바이너리 페이징 조회")
  @GetMapping("/get-page")
  public Page<CttBinDto> getPage(@Valid CttBinForm.Input.GetAll in, Pageable page) {
    return service.getSupportPage(CttBinFormPredicate.search(in), page);
  }

  @SneakyThrows
  @ApiOperation("콘텐츠바이너리 조회")
  @GetMapping("/get/{id}")
  public CttBinForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠바이너리 파일 조회")
  @GetMapping("/get-file-url")
  public String getFileUrl(@Valid CttBinForm.Input.GetBinId in) {
    return service.getSupportFileUrl(in.getCttBinId());
  }

  @SneakyThrows
  @ApiOperation("콘텐츠바이너리 조회")
  @GetMapping("/get-id")
  public UUID get(@Valid CttBinForm.Input.GetId in) {
    return service.getId(in);
  }
}
