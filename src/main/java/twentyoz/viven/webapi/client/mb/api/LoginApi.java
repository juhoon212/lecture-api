package twentyoz.viven.webapi.client.mb.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import twentyoz.viven.feature.mb.model.MbrDto;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.mb.service.MbrLoginService;
import twentyoz.viven.feature.mb.service.MbrService;
import twentyoz.viven.webapi.client.mb.form.LoginForm;
import twentyoz.viven.webapi.client.mb.form.LoginForm.Output.Login;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
    value = "Login",
    tags = {"Login"})
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class LoginApi {

  private final MbrService mbrService;

  private final MbrLoginService mbrLoginService;

  @SneakyThrows
  @ApiOperation("로그인 토근 요청")
  @PostMapping("/get-token")
  public Login getToken(@Valid LoginForm.Input.Login in) {
    String token = mbrLoginService.login(in.getLoginId(), in.getPw());

    return LoginForm.Output.Login.builder().token(token).build();
  }

  @SneakyThrows
  @ApiOperation("로그인 사용자 조회")
  @GetMapping("/user-info")
  public MbrDto userInfo() {
    return mbrService.getDtoSupport(UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("로그아웃")
  @GetMapping("/log-out")
  public LoginForm.Output.Logout logout() {
    return LoginForm.Output.Logout.builder().success(true).build();
  }
}
