package twentyoz.viven.webapi.client.mb.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.UUID;
import javax.validation.Valid;
import twentyoz.viven.feature.mb.model.MbrAvtFileImgPathDto;
import twentyoz.viven.feature.mb.model.MbrDtoDetail;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.mb.service.MbrService;
import twentyoz.viven.webapi.client.mb.form.MbrForm;
import twentyoz.viven.webapi.client.mb.form.MbrForm.Output.Get;
import twentyoz.viven.webapi.client.mb.mapper.MbrFormMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
    value = "Mbr",
    tags = {"Mbr"})
@RequestMapping(value = "/mb/mbr", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("MbrApi")
@RequiredArgsConstructor
public class MbrApi {

  private final MbrFormMapper formMapper;
  private final MbrService service;

  @SneakyThrows
  @ApiOperation("회원 조회")
  @GetMapping("/get")
  public Get get() {
    return formMapper.toGet(service.get(UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("회원 등록")
  @PostMapping("/addMember")
  public Get add(@Valid @RequestBody MbrForm.Input.Add in) {
    return formMapper.toGet(service.addMember(formMapper.toMbMbr(in)));
  }

  @SneakyThrows
  @ApiOperation("회원 수정")
  @PostMapping("/modify")
  public MbrForm.Output.Get modify(@Valid @RequestBody MbrForm.Input.Modify in) {
    return formMapper.toGet(service.modifyMember(UserInfo.getUserId(), formMapper.toMbMbr(in)));
  }

  @SneakyThrows
  @ApiOperation("회원 아바타 수정")
  @PostMapping("/modify-avatar")
  public MbrForm.Output.Get modifyAvt(@Valid @RequestBody MbrForm.Input.ModifyAvt in) {
    return formMapper.toGet(service.modifyAvt(UserInfo.getUserId(), in.getCttId()));
  }

  @SneakyThrows
  @ApiOperation("회원 탈퇴")
  @PostMapping("/secession")
  public void secession(@Valid @RequestBody MbrForm.Input.Secession in) {
    service.secession(
        UserInfo.getUserId(), in.getSecessionReasonTypeCode(), in.getStatusChgReason());
  }

  @SneakyThrows
  @ApiOperation("비밀번호 확인")
  @PostMapping("/checkPw")
  public MbrForm.Output.CheckPw checkPw(@Valid @RequestBody MbrForm.Input.CheckPw in) {
    return service.checkPw(UserInfo.getUserId(), in.getPw());
  }

  @SneakyThrows
  @ApiOperation("임시 비밀번호 수정")
  @PostMapping("/modifyTempPw")
  public MbrForm.Output.Get modify(@Valid @RequestBody MbrForm.Input.ModifyTempPw in) {
    return formMapper.toGet(
        service.modifyTempPassword(UserInfo.getUserId(), formMapper.modifyTempPw(in)));
  }

  @SneakyThrows
  @ApiOperation("비밀번호 수정")
  @PostMapping("/modifyPw")
  public MbrForm.Output.Get modify(@Valid @RequestBody MbrForm.Input.ModifyPw in) {
    return formMapper.toGet(
        service.modifyPassword(UserInfo.getUserId(), in.getCurPw(), in.getPw()));
  }

  @SneakyThrows
  @ApiOperation("회원 정보 조회(회원 로그인아이디 중복 검사용으로 사용 가능)")
  @PostMapping("/get-loginId/{loginId}")
  public MbrForm.Output.Get getLoginId(@PathVariable String loginId) {
    return formMapper.toGet(service.getByUserLoginId(loginId));
  }

  @SneakyThrows
  @ApiOperation("회원 정보 조회(회원 닉네임 중복 검사용으로 사용 가능)")
  @PostMapping("/get-nickname/{nickname}")
  public MbrForm.Output.Get getNickname(@PathVariable String nickname) {
    return formMapper.toGet(service.getByUserNickname(nickname));
  }

  @SneakyThrows
  @ApiOperation("회원 정보 조회(회원 이메일 중복 검사용으로 사용 가능)")
  @PostMapping("/get-email/{email}")
  public MbrForm.Output.Get getEmail(@PathVariable String email) {
    return formMapper.toGet(service.getByUserEmail(email));
  }

  @SneakyThrows
  @ApiOperation("친구 회원상세 조회")
  @GetMapping("/get/detail/{mbrId}")
  public MbrDtoDetail getMbr(@PathVariable UUID mbrId) {
    return service.getSupport(mbrId);
  }

  @SneakyThrows
  @ApiOperation("아바타 이미지 경로 조회")
  @GetMapping("/get/avt-profile/{mbrId}")
  public MbrAvtFileImgPathDto getAvtImgPath(@PathVariable UUID mbrId) {
    return service.getAvtImgPath(mbrId);
  }
}
