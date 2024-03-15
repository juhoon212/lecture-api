package twentyoz.viven.webapi.client.mb.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;

import twentyoz.viven.feature.mb.service.MbrLoginHistService;
import twentyoz.viven.webapi.client.mb.form.MbrLoginHistForm;
import twentyoz.viven.webapi.client.mb.mapper.MbrLoginHistFormMapper;
import twentyoz.viven.webapi.client.mb.predicate.MbrLoginHistFormPredicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
    value = "MbrLoginHist",
    tags = {"MbrLoginHist"})
@RequestMapping(value = "/mb/mbr-login-hist", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("MbrLoginHistApi")
@RequiredArgsConstructor
public class MbrLoginHistApi {

  private final MbrLoginHistFormMapper formMapper;

  private final MbrLoginHistService service;

  @SneakyThrows
  @ApiOperation("회원로그인 이력 목록 조회")
  @GetMapping("/get-list")
  public List<MbrLoginHistForm.Output.GetAll> getList(@Valid MbrLoginHistForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(MbrLoginHistFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("회원로그인 이력 페이징 조회")
  @GetMapping("/get-page")
  public Page<MbrLoginHistForm.Output.GetAll> getPage(
      @Valid MbrLoginHistForm.Input.GetAll in, Pageable page) {
    return service.getPage(MbrLoginHistFormPredicate.search(in), page).map(formMapper::toGetAll);
  }
}
