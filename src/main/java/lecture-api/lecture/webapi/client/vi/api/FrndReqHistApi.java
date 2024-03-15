package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;

import twentyoz.viven.webapi.client.vi.form.FrndReqHistForm;
import twentyoz.viven.webapi.client.vi.mapper.FrndReqHistFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.FrndReqHistFormPredicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
    value = "FrndReqHist",
    tags = {"FrndReqHist"})
@RequestMapping(value = "/vi/frnd-req-hist", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("FrndReqHistApi")
@RequiredArgsConstructor
public class FrndReqHistApi {

  private final FrndReqHistFormMapper formMapper;

  private final FrndReqHistService service;

  @SneakyThrows
  @ApiOperation("친구요청이력 목록 조회")
  @GetMapping("/get-list")
  public List<FrndReqHistForm.Output.GetAll> getList(@Valid FrndReqHistForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(FrndReqHistFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("친구요청이력 페이징 조회")
  @GetMapping("/get-page")
  public Page<FrndReqHistForm.Output.GetAll> getPage(
      @Valid FrndReqHistForm.Input.GetAll in, Pageable page) {
    return service.getPage(FrndReqHistFormPredicate.search(in), page).map(formMapper::toGetAll);
  }
}
