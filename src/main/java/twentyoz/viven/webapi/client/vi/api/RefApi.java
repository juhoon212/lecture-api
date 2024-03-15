package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.model.ViRefDto;
import twentyoz.viven.feature.vi.service.RefService;
import twentyoz.viven.webapi.client.vi.form.RefForm;
import twentyoz.viven.webapi.client.vi.form.RefForm.Output.Get;
import twentyoz.viven.webapi.client.vi.mapper.RefFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.ViRefFormPredicate;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static twentyoz.viven.webapi.client.vi.form.RefForm.*;

@Api(
        value = "Ref",
        tags = {"Ref"})
@RequestMapping(value = "/vi/ref", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class RefApi {

    private final RefService service;
    private final RefFormMapper formMapper;

    @SneakyThrows
    @ApiOperation("자료 등록")
    @PostMapping("/add")
    public Get add(
            @Valid Input.Add in) {
        return formMapper.toGet(service.addRef(formMapper.toViRef(in), UserInfo.getUserId()));
    }

    @SneakyThrows
    @ApiOperation("자료 조회")
    @GetMapping("/get/{id}")
    public Get get(@PathVariable UUID id) {
        return formMapper.toGet(service.get(id));
    }

    @SneakyThrows
    @ApiOperation("자료 수정")
    @PostMapping("/modify")
    public Get modify(@Valid Input.Modify in) {
        return formMapper.toGet(service.modifyRef(formMapper.toViRef(in), UserInfo.getUserId()));
    }

    @SneakyThrows
    @ApiOperation("자료 삭제")
    @PostMapping("/delete/{id}")
    public Get delete(@PathVariable UUID id) {
        return formMapper.toGet(service.delete(id));
    }

    @SneakyThrows
    @ApiOperation("자료 목록 조회")
    @GetMapping("/get-list")
    public List<ViRefDto> getList(Input.GetAll in) {
        return service.getList(ViRefFormPredicate.search(in), UserInfo.getUserId());
    }

    @SneakyThrows
    @ApiOperation("자료 목록 페이징 조회")
    @GetMapping("/get-page")
    public Page<ViRefDto> getPage(RefForm.Input.GetAll in, Pageable page) {
        return service.getPage(ViRefFormPredicate.search(in), page, UserInfo.getUserId());
    }









}
