package twentyoz.viven.feature.mb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.mb.model.MbMbr;
import twentyoz.viven.security.BcryptHelper;
import twentyoz.viven.security.Jwt401Exception;
import twentyoz.viven.security.JwtHelper;
import twentyoz.viven.security.JwtPayload;

/** 사용자 인증 서비스 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MbrLoginService {

  private final MbrService mbrService;

  public String login(String loginId, String pw) {
    // 아이디, 비번 입력값 확인
    if (loginId == null || pw == null) {
      throw new Jwt401Exception();
    }

    // 아이디로 사용자 정보 조회
    MbMbr user = mbrService.getByUserLoginId(loginId);
    if (user == null) {
      throw new Jwt401Exception("\"아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다.\\n 입력하신 내용을 다시 확인해주세요.\"");
    }

    // 비밀번호 검증
    String hashedPw = user.getPw();
    if (!BcryptHelper.compare(hashedPw, pw)) {
      throw new Jwt401Exception();
    }

    // JWT 토큰생성
    String token =
        JwtHelper.sign(
            JwtPayload.builder()
                .mbrId(user.getMbrId().toString())
                .loginId(user.getLoginId())
                .mbrName(user.getMbrName())
                .nickname(user.getNickname())
                .createdAt(System.currentTimeMillis())
                .build());

    // 로그인시간 갱신
    mbrService.modifyLoginDt(loginId);

    return token;
  }
}
