package twentyoz.viven.webapi.client.sy.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import twentyoz.viven.feature.sy.service.AttachFileService;
import twentyoz.viven.webapi.client.sy.form.AttachFileForm;
import twentyoz.viven.webapi.client.sy.form.AttachFileForm.Output.GetAll;
import twentyoz.viven.webapi.client.sy.mapper.AttachFileFormMapper;
import twentyoz.viven.webapi.client.sy.predicate.AttachFileFormPredicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(
    value = "AttachFile",
    description = "첨부파일 API",
    tags = {"AttachFile"})
@RequestMapping(value = "/attach/file", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class AttachFileApi {

  private final AttachFileFormMapper formMapper;

  private final AttachFileService service;

  @SneakyThrows
  @ApiOperation("첨부파일 목록 조회")
  @GetMapping("/get-list")
  public List<GetAll> getList(@Valid AttachFileForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(AttachFileFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("첨부파일 페이징 조회")
  @GetMapping("/get-page")
  public Page<GetAll> getPage(
      @Valid AttachFileForm.Input.GetAll in,
      @PageableDefault(size = 20, sort = "regDt", direction = Sort.Direction.DESC) Pageable page) {
    return service.getPage(AttachFileFormPredicate.search(in), page).map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("첨부파일 조회")
  @GetMapping("/get/{id}")
  public AttachFileForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }

  @SneakyThrows
  @ApiOperation("첨부파일 등록")
  @PostMapping("/add")
  public AttachFileForm.Output.Get add(
      @Valid AttachFileForm.Input.Add in, @RequestPart MultipartFile file) {
    return formMapper.toGet(service.addFile(formMapper.toSyAttachFile(in), file, in.getRootDir()));
  }

  @SneakyThrows
  @ApiOperation("첨부파일 수정")
  @PostMapping("/modify/{id}")
  public AttachFileForm.Output.Get modify(
      @PathVariable UUID id, @Valid @RequestBody AttachFileForm.Input.Modify in) {
    return formMapper.toGet(service.modify(id, formMapper.toSyAttachFileModify(in)));
  }

  @SneakyThrows
  @ApiOperation("첨부파일 삭제")
  @PostMapping("/remove")
  public void remove(@Valid @RequestBody AttachFileForm.Input.Remove in) {
    service.removeFiles(in.getAttachFileIds());
  }
}
