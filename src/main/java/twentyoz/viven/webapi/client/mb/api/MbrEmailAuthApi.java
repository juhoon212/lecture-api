package twentyoz.viven.webapi.client.mb.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.UUID;
import javax.validation.Valid;
import twentyoz.viven.feature.mb.model.MbMbr;
import twentyoz.viven.feature.mb.model.MbMbrEmailAuth;
import twentyoz.viven.feature.mb.service.MbrEmailAuthService;
import twentyoz.viven.webapi.client.mb.form.MbrEmailAuthForm;
import twentyoz.viven.webapi.client.mb.mapper.MbrEmailAuthFormMapper;
import twentyoz.viven.webapi.client.mb.mapper.MbrFormMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
    value = "MbrEmailAuth",
    tags = {"MbrEmailAuth"})
@RequestMapping(value = "/mb/mbr-email-auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("MbrEmailAuthApi")
@RequiredArgsConstructor
public class MbrEmailAuthApi {

  private final MbrEmailAuthFormMapper formMapper;
  private final MbrFormMapper mbrFormMapper;
  private final MbrEmailAuthService service;

  @SneakyThrows
  @ApiOperation("인증번호 이메일 발송")
  @GetMapping("/auth-number-send")
  public MbMbrEmailAuth authNumberInit(@Valid MbrEmailAuthForm.Input.FindPwAuthEmail in) {
    return service.FindPwAuthEmail(in.getLoginId(), in.getMbrName(), in.getEmail());
  }

  @SneakyThrows
  @ApiOperation("연장하기")
  @PostMapping("/extension-time/{id}")
  public MbrEmailAuthForm.Output.Get extensionTime(@PathVariable UUID id) {
    return formMapper.toGet(service.extensionTime(id));
  }

  @SneakyThrows
  @ApiOperation("아이디 메일 발송")
  @GetMapping("/find-id")
  public MbrEmailAuthForm.Output.FindId sendFindId(@Valid MbrEmailAuthForm.Input.FindId in) {
    return service.sendFindId(in.getMbrName(), in.getEmail());
  }

  @SneakyThrows
  @ApiOperation("임시비밀번호 발송")
  @PostMapping("/send-temp-pw")
  public MbMbr findPw(@Valid @RequestBody MbrEmailAuthForm.Input.FindPw in) {
    return service.sendTempPw(in.getLoginId(), in.getMbrName(), in.getEmail(), in.getAuthVal());
  }

  @SneakyThrows
  @ApiOperation("인증번호 발급 정보 조회")
  @GetMapping("/auth-number-time/{id}")
  public MbrEmailAuthForm.Output.Get findAuthNumTime(@PathVariable UUID id) {
    return formMapper.toGet(service.get(id));
  }
}
