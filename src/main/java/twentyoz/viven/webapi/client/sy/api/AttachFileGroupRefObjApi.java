package twentyoz.viven.webapi.client.sy.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import twentyoz.viven.feature.sy.service.AttachFileGroupRefObjService;
import twentyoz.viven.webapi.client.sy.form.AttachFileGroupRefObjForm;
import twentyoz.viven.webapi.client.sy.form.AttachFileGroupRefObjForm.Output.GetAll;
import twentyoz.viven.webapi.client.sy.mapper.AttachFileGroupRefObjFormMapper;
import twentyoz.viven.webapi.client.sy.predicate.AttachFileGroupRefObjFormPredicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
    value = "AttachFileGroupRef",
    description = "첨부파일그룹참조 API",
    tags = {"AttachFileGroupRef"})
@RequestMapping(value = "/attach/file/group/ref", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class AttachFileGroupRefObjApi {

  private final AttachFileGroupRefObjFormMapper formMapper;

  private final AttachFileGroupRefObjService service;

  @SneakyThrows
  @ApiOperation("첨부파일그룹참조 목록 조회")
  @GetMapping("/get-list")
  public List<GetAll> getList(@Valid AttachFileGroupRefObjForm.Input.GetAll in) {
    return formMapper.toGetAllList(service.getList(AttachFileGroupRefObjFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("첨부파일그룹참조 등록")
  @PostMapping("/add")
  public AttachFileGroupRefObjForm.Output.Get add(@Valid AttachFileGroupRefObjForm.Input.Add in) {
    return formMapper.toGet(service.add(formMapper.toSyAttachFileGroupRef(in)));
  }

  @SneakyThrows
  @ApiOperation("첨부파일그룹참조 수정")
  @PostMapping("/modify")
  public AttachFileGroupRefObjForm.Output.Get modify(
      @Valid AttachFileGroupRefObjForm.Input.Modify in) {
    return formMapper.toGet(
        service.modify(in.getAttachFileGroupRefObjId(), formMapper.toSyAttachFileGroupRef(in)));
  }

  @SneakyThrows
  @ApiOperation("첨부파일그룹참조 삭제")
  @PostMapping
  public void remove(@Valid AttachFileGroupRefObjForm.Input.Remove in) {
    service.remove(in.getAttachFileGroupRefObjId());
  }
}
