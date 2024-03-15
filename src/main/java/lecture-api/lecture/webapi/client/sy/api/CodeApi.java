package twentyoz.viven.webapi.client.sy.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;

import twentyoz.viven.webapi.client.sy.form.CodeForm;
import twentyoz.viven.webapi.client.sy.mapper.CodeFormMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
    value = "Code",
    tags = {"Code"})
@RequestMapping(value = "/sy/code", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class CodeApi {

  private final CodeFormMapper formMapper;

  @SneakyThrows
  @ApiOperation("코드 목록 조회(조회값 없으면 전체, 코드그룹만 검색)")
  @GetMapping("/get-list")
  public List<CodeForm.Output.GetAll> getList(@Valid CodeForm.Input.GetAll in) {
    return formMapper.toGetAllList(Code.findByCodeGroup(in.getCodeGroup()));
  }

  @SneakyThrows
  @ApiOperation("코드 조회 (코드로 단일 조회)")
  @GetMapping("/get/{id}")
  public CodeForm.Output.Get get(@PathVariable String id) {
    return formMapper.toGet(Code.findByCode(id));
  }
}
