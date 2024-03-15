package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.vi.service.AlarmService;
import twentyoz.viven.webapi.client.vi.form.AlarmForm;
import twentyoz.viven.webapi.client.vi.form.AlarmForm.Output.GetAll;
import twentyoz.viven.webapi.client.vi.mapper.AlarmFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.AlarmFormPredicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
    value = "Alarm",
    tags = {"Alarm"})
@RequestMapping(value = "/vi/alarm", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("AlarmApi")
@RequiredArgsConstructor
public class AlarmApi {

  private final AlarmFormMapper formMapper;

  private final AlarmService service;

  @SneakyThrows
  @ApiOperation("알람 목록 조회 - 읽지 않은 알람만 조회")
  @GetMapping("/get-list")
  public List<GetAll> getList(@Valid AlarmForm.Input.GetAll in) {
    if (!UserInfo.isLogin()) {
      return null;
    }

    return formMapper.toGetAllList(service.getList(AlarmFormPredicate.search(in)));
  }

  @SneakyThrows
  @ApiOperation("알람 페이징 조회 - 읽지 않은 알람 조회")
  @GetMapping("/get-page")
  public Page<AlarmForm.Output.GetAll> getPage(@Valid AlarmForm.Input.GetAll in, Pageable page) {
    return service.getPage(AlarmFormPredicate.search(in), page).map(formMapper::toGetAll);
  }

  @SneakyThrows
  @ApiOperation("알람 조회")
  @GetMapping("/get/{id}")
  public AlarmForm.Output.Get get(@PathVariable UUID id) {
    return formMapper.toGet(service.getAlarm(id, UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("알람 읽기")
  @PostMapping("/read/{id}")
  public AlarmForm.Output.Get read(@PathVariable UUID id) {
    return formMapper.toGet(service.readAlarm(id));
  }

  @SneakyThrows
  @ApiOperation("알람 모두 읽기")
  @PostMapping("/read-all")
  public void readAll() {
    service.readAllAlarm(UserInfo.getUserId());
  }
}
