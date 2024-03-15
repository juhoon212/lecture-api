package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import twentyoz.viven.feature.vi.model.CttJudgeHistDto;
import twentyoz.viven.feature.vi.model.CttJudgeHistDtoFile;
import twentyoz.viven.feature.vi.service.CttJudgeHistService;
import twentyoz.viven.webapi.client.vi.form.CttJudgeHistForm;
import twentyoz.viven.webapi.client.vi.mapper.CttJudgeHistFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.CttJudgeHistFormPredicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
    value = "CttJudgeHist",
    tags = {"CttJudgeHist"})
@RequestMapping(value = "/vi/ctt-judge-hist", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("CttJudgeHistApi")
@RequiredArgsConstructor
public class CttJudgeHistApi {

  private final CttJudgeHistFormMapper formMapper;

  private final CttJudgeHistService service;

  @SneakyThrows
  @ApiOperation("콘텐츠심사이력 목록 조회")
  @GetMapping("/get-list")
  public List<CttJudgeHistDto> getList(@Valid CttJudgeHistForm.Input.GetAll in) {
    return service.getSupportList(CttJudgeHistFormPredicate.search(in));
  }

  @SneakyThrows
  @ApiOperation("콘텐츠심사이력 페이징 조회")
  @GetMapping("/get-page")
  public Page<CttJudgeHistDto> getPage(@Valid CttJudgeHistForm.Input.GetAll in, Pageable page) {
    return service.getSupportPage(CttJudgeHistFormPredicate.search(in), page);
  }

  @SneakyThrows
  @ApiOperation("콘텐츠심사이력 조회")
  @GetMapping("/get/{id}")
  public CttJudgeHistDto get(@PathVariable UUID id) {
    return service.getSupport(id);
  }

  @SneakyThrows
  @ApiOperation("콘텐츠심사이력 조회")
  @GetMapping("/get-file/{id}")
  public CttJudgeHistDtoFile getFile(@PathVariable UUID id) {
    return service.getSupportFile(id);
  }
}
