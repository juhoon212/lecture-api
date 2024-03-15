package twentyoz.viven.feature.mb.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.stella.mail.MailSender;
import twentyoz.stella.mail.SendRequest.MailAddr;
import twentyoz.stella.misc.IO;
import twentyoz.viven.feature.mb.mapper.MbMbrEmailAuthMapper;
import twentyoz.viven.feature.mb.model.MbMbr;
import twentyoz.viven.feature.mb.model.MbMbrEmailAuth;
import twentyoz.viven.feature.mb.model.MbMbrEmailAuthDto;
import twentyoz.viven.feature.mb.model.QMbMbrEmailAuth;
import twentyoz.viven.feature.mb.repository.MbMbrEmailAuthRepository;
import twentyoz.viven.feature.mb.repository.MbMbrEmailAuthRepositorySupport;
import twentyoz.viven.feature.mb.repository.MbMbrRepository;
import twentyoz.viven.webapi.client.mb.form.MbrEmailAuthForm;

/** 이메일 인증 서비스 */
@Slf4j // log 찍기위한 어노테이션
@Service
@Transactional
@RequiredArgsConstructor
public class MbrEmailAuthService {

  private final MbMbrRepository mbMbrRepository;
  private final MbMbrEmailAuthRepository repository;
  private final MbMbrEmailAuthMapper mapper;
  private final Environment environment;

  private final MbrService mbrService;
  private static MailSender sender;
  private MbMbr mbmbr;

  private final MbMbrEmailAuthRepositorySupport repositorySupport;

  /**
   * 이메일 인증 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<MbMbrEmailAuth> getList(Predicate search) {
    return (List<MbMbrEmailAuth>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 이메일 인증 목록 (with 회원)
   *
   * @param search
   * @return
   */
  public List<MbMbrEmailAuthDto> getSupportList(Predicate search) {
    return repositorySupport.getList(search);
  }

  /**
   * 이메일 인증 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<MbMbrEmailAuth> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 이메일 인증 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public MbMbrEmailAuth get(UUID id) {
    return repository.findOne(
        new BooleanBuilder(QMbMbrEmailAuth.mbMbrEmailAuth.mbrEmailAuthId.eq(id)))
            .orElse(null);
  }

  /**
   * 이메일 인증 등록
   *
   * @param entity
   * @return
   */
  public MbMbrEmailAuth add(MbMbrEmailAuth entity) {
    return repository.save(entity);
  }

  /**
   * 이메일 인증 수정
   *
   * @param entity
   * @return
   */
  public MbMbrEmailAuth modify(UUID id, MbMbrEmailAuth entity) {
    return mapper.modify(entity, get(id));
  }

  /**
   * 이메일 인증 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    repository.deleteById(id);
  }

  /**
   * 이메일 인증 다중 삭제
   *
   * @param ids 식별번호 목록
   */
  public void remove(List<UUID> ids) {
    if (ids.size() > 0) {
      List<MbMbrEmailAuth> list =
          this.getList(new BooleanBuilder(QMbMbrEmailAuth.mbMbrEmailAuth.mbrEmailAuthId.in(ids)));
      for (MbMbrEmailAuth item : list) {
        this.remove(item.getId());
      }
    }
  }

  /** 인증번호 생성 (6자리의 난수) */
  public int makeRandomNumber() {
    // 난수의 범위 111111 ~ 999999 (6자리 난수) -> 인증번호
    Random r = new Random();
    int checkNum = r.nextInt(888888) + 111111;

    return checkNum;
  }

  /**
   * 이메일 발송_인증번호
   *
   * @return
   */
  @SneakyThrows
  public MbMbrEmailAuth FindPwAuthEmail(String loginId, String mbrName, String email) {
    MbMbr mbr = mbrService.getMbrByLoginIdAndMbrNameAndEmail(loginId, mbrName, email);
    if (mbr == null) {
      return null;
    }

    String authNumber = String.valueOf(makeRandomNumber()); // 6자리의 난수로 생성된 인증번호를 문자로 형변환

    String users = email;

    MailAddr to = MailAddr.of(users);

    sender =
        MailSender.gmail587(
            environment.getProperty("app.mail.gmail.username"),
            environment.getProperty("app.mail.gmail.password"));

    String content = IO.readFile("classpath:templates/mail/AuthNumber.min.html");

    content = content.replaceAll("\\{\\{authNumber}}", authNumber);
    MailAddr from =
        MailAddr.of(
            environment.getProperty("app.mail.from"), environment.getProperty("app.mail.fromName"));

    // 요청생성
    twentyoz.stella.mail.SendRequest request =
        twentyoz.stella.mail.SendRequest.builder()
            .from(from)
            .to(to)
            .subject("[인증코드: " + authNumber + "][VIVEN] 인증메일")
            .content(content)
            .build();

    // 발송
    twentyoz.stella.mail.SendResponse response = sender.send(request);

    if (response.isSuccess()) {
      MbrEmailAuthForm.Output.TimeExtension.builder().success(true).build();
    }
    // 발송 내역을 저장하기 위해 mbrId가 필요
    // mbrService에 만든 email로 회원 찾는 부분을 통해서 mbrId를 가져오기

    //    repository.save(MbMbrEmailAuth.initSave(mbrId,authNumber)).getMbrEmailAuthId();

    return repository.save(MbMbrEmailAuth.initSave(mbr.getMbrId(), authNumber));
  }

  /**
   * 인증번호 시간 연장하기
   *
   * @param id
   * @return
   */
  public MbMbrEmailAuth extensionTime(UUID id) {

    MbMbrEmailAuth entity = repository.findById(id).orElse(null);

    entity.setAuthEndDt(new DateTime().plusMinutes(10));

    return this.modify(id, entity);
  }

  public MbMbrEmailAuth findId(Predicate search) {
    MbMbrEmailAuth entity = (MbMbrEmailAuth) getSupportList(search);

    UUID emailAuthId = entity.getMbrEmailAuthId();

    entity.setAuthComplYn("Y");

    return this.modify(emailAuthId, entity);
  }

  /**
   * 이메일 발송_아이디 찾기
   *
   * @return
   */
  @SneakyThrows
  public MbrEmailAuthForm.Output.FindId sendFindId(String mbrName, String email) {

    String userFindId = mbrService.findId(mbrName, email).getLoginId();
    if (userFindId != null) {
      String users = email;

      MailAddr to = MailAddr.of(users);

      sender =
          MailSender.gmail587(
              environment.getProperty("app.mail.gmail.username"),
              environment.getProperty("app.mail.gmail.password"));

      String content = IO.readFile("classpath:templates/mail/FindId.min.html");

      content = content.replaceAll("\\{\\{FindId}}", userFindId);
      MailAddr from =
          MailAddr.of(
              environment.getProperty("app.mail.from"),
              environment.getProperty("app.mail.fromName"));

      // 요청생성
      twentyoz.stella.mail.SendRequest request =
          twentyoz.stella.mail.SendRequest.builder()
              .from(from)
              .to(to)
              .subject("[VIVEN] 아이디 찾기 메일")
              .content(content)
              .build();

      // 발송
      twentyoz.stella.mail.SendResponse response = sender.send(request);

      if (response.isSuccess()) {
        MbrEmailAuthForm.Output.FindId.builder().success(true).build();
      }
    }
    return null;
  }

  /** 임시비밀번호 생성 */
  private String tempPw() {
    String[] specialString = {"!", "@", "#", "$", "%", "+", "_", "&"};

    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
    String generatedString =
        random
                .ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
            + specialString[random.nextInt(specialString.length)];

    // 2. 배열 생성 (문자열 길이)
    char[] arr = shuffle(generatedString.toCharArray());

    String password = "";

    for (int x = 0; x < arr.length; x++) {
      password += arr[x];
    }

    return password;
  }

  public char[] shuffle(char[] str) {
    for (int x = 0; x < str.length; x++) {
      char i = (char) (Math.random() * str.length);
      char j = (char) (Math.random() * str.length);

      char tmp = str[i];
      str[i] = str[j];
      str[j] = tmp;
    }

    return str;
  }

  /** 임시비밀번호 발송 */
  @SneakyThrows
  public MbMbr sendTempPw(String loginId, String mbrName, String email, String authVal) {
    MbMbr mbr = mbrService.getMbrByLoginIdAndMbrNameAndEmail(loginId, mbrName, email);
    if (mbr != null) {
      String TempPw = tempPw();
      String users = email;

      MailAddr to = MailAddr.of(users);

      sender =
          MailSender.gmail587(
              environment.getProperty("app.mail.gmail.username"),
              environment.getProperty("app.mail.gmail.password"));

      String content = IO.readFile("classpath:templates/mail/SendTempPw.min.html");

      content = content.replaceAll("\\{\\{TempPw}}", TempPw);
      MailAddr from =
          MailAddr.of(
              environment.getProperty("app.mail.from"),
              environment.getProperty("app.mail.fromName"));

      // 요청생성
      twentyoz.stella.mail.SendRequest request =
          twentyoz.stella.mail.SendRequest.builder()
              .from(from)
              .to(to)
              .subject("[VIVEN] 임시비밀번호 발급")
              .content(content)
              .build();

      // 발송
      twentyoz.stella.mail.SendResponse response = sender.send(request);

      if (response.isSuccess()) {
        MbrEmailAuthForm.Output.TimeExtension.builder().success(true).build();
      }
      // 비밀번호 업데이트를 위해 mbrId가 필요
      // mbrService에 만든 email로 회원 찾는 부분을 통해서 mbrId를 가져오기
      UUID mbrId = mbrService.getByUserEmail(email).getMbrId();

      MbMbr entity = mbMbrRepository.findById(mbrId).orElse(null);
      //    임시비밀번호로 변경
      entity.setPw(TempPw);
      entity.setTempPwYn("Y");

      return mbrService.modifyTempPw(mbrId, entity);
    }

    return null;
  }
}
