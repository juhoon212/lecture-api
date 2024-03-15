package twentyoz.viven.feature.mb.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.mb.mapper.MbMbrMapper;
import twentyoz.viven.feature.mb.model.*;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.mb.repository.MbMbrAgreeRepository;
import twentyoz.viven.feature.mb.repository.MbMbrRepository;
import twentyoz.viven.feature.mb.repository.MbMbrStatusInfoRepository;
import twentyoz.viven.feature.mb.repository.MbrRepositorySupport;
import twentyoz.viven.feature.vi.service.MyRoomService;
import twentyoz.viven.security.BcryptHelper;
import twentyoz.viven.util.UidUtils;
import twentyoz.viven.webapi.client.mb.form.MbrForm;

/** 회원 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class MbrService {

  private final MbMbrRepository repository;
  private final MbMbrAgreeRepository agreeRepository;
  private final MbMbrStatusInfoRepository statusRepository;
  private final MbMbrMapper mapper;
  private final CttService cttService;
  private final MbrLoginHistService mbrLoginHistService;

  private final MbrRepositorySupport repositorySupport;

  private final Environment environment;

  private final CttBmService cttBmService;

  private final MyRoomService myRoomService;

  private final FrndService frndService;

  /**
   * 회원 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<MbMbr> getList(Predicate search) {
    return (List<MbMbr>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 회원 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<MbMbr> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 회원 상세 조회
   *
   * @return
   */
  @Transactional(readOnly = true)
  public MbMbr get(UUID id) {
    return repository.findOne(new BooleanBuilder(QMbMbr.mbMbr.mbrId.eq(id))).orElse(null);
  }

  @Transactional(readOnly = true)
  public MbrDto getDtoSupport(UUID id) {
    return repositorySupport.getDto(id);
  }

  /**
   * 회원 상세 조회
   *
   * @return
   */
  @Transactional(readOnly = true)
  public MbMbr getMbr(UUID id) {
    QMbMbr qMbMbr = QMbMbr.mbMbr;
    return repository
        .findOne(
            new BooleanBuilder(
                qMbMbr
                    .mbrId
                    .eq(id)
                    .and(qMbMbr.mbrTypeCode.eq(Code.MB_001_MBR.getCode()))
                    .and(qMbMbr.mbrStatusCode.eq(Code.MB_002_001.getCode()))
                    .and(qMbMbr.useYn.eq("Y"))
                    .and(qMbMbr.delYn.eq("N"))))
        .orElse(null);
  }

  /**
   * 회원 등록
   *
   * @param entity
   * @return
   */
  private MbMbr add(MbMbr entity) {
    return repository.save(entity);
  }

  /**
   * 회원 수정
   *
   * @param entity
   * @return
   */
  private MbMbr modify(UUID id, MbMbr entity) {
    return mapper.modify(entity, get(id));
  }

  /**
   * 회원 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    this.get(id).delTrue();
  }

  /**
   * 회원 조회 (친구정보 포함)
   *
   * @return
   */
  public MbrDtoDetail getSupport(UUID mbrId) {
    return repositorySupport.get(mbrId);
  }

  public MbrAvtFileImgPathDto getAvtImgPath(UUID mbrId) {

    MbMbr mbr = getMbr(mbrId);
    return new MbrAvtFileImgPathDto(
        mbr.getProfileBgColor(), mbr.getAvtBodyFilePath(), mbr.getAvtProfileFilePath());
  }

  /**
   * 회원 탈퇴
   *
   * @param secessionReasonTypeCode, statusChgReason
   * @return
   */
  public void secession(UUID id, String secessionReasonTypeCode, String statusChgReason) {
    MbMbr mbr = this.get(id);
    if (mbr != null) {
      String[] inSecessCode = secessionReasonTypeCode.split(",");

      for (String se : inSecessCode) {
        // 회원상태 테이블 저장
        statusRepository.save(
            MbMbrStatusInfo.of(
                mbr.getMbrId(), se, statusChgReason, new DateTime(9999, 12, 31, 00, 00)));
      }

      // 회원 탈퇴처리
      MbMbr entity = new MbMbr();
      entity.setId(id);
      mapper.secession(entity, mbr);
    }
  }

  /**
   * 비밀번호 확인
   *
   * @param id 식별번호
   * @param pw 비밀번호
   * @return
   * @throws IllegalAccessException
   */
  public MbrForm.Output.CheckPw checkPw(UUID id, String pw) throws IllegalAccessException {
    MbMbr mbr = this.get(id);

    // 원래 비밀번호
    String hashedPw = mbr.getPw();
    if (!BcryptHelper.compare(hashedPw, pw)) {
      throw new IllegalAccessException("현재 비밀번호가 일치하지 않습니다.");
    } else {
      return MbrForm.Output.CheckPw.builder().success(true).build();
    }
  }

  /** 회원 수정 */
  public MbMbr modifyMember(UUID id, MbMbr entity) {
    if (id == null) {
      throw new IllegalStateException("잘못된 접근입니다.");
    }

    MbMbr mbr = this.get(id);
    if (entity.getNickname() != null) {
      String myNickname = mbr.getNickname();
      // 닉네임 중복체크
      if (this.getByUserNicknameExceptMyNickname(myNickname, entity.getNickname()) != null) {
        throw new IllegalStateException("사용중인 닉네임 입니다.");
      }
    }

    if (entity.getEmail() != null) {
      // 이메일 중복체크
      String myEmail = mbr.getEmail();
      if (this.getByUserEmailExceptMyEmail(myEmail, entity.getEmail()) != null) {
        throw new IllegalStateException("사용중인 이메일 입니다.");
      }
    }

    return this.modify(id, entity);
  }

  /**
   * 아바타 수정
   *
   * @param id 식별번호
   * @param cttId 콘텐츠식별번호
   * @return
   */
  public MbMbr modifyAvt(UUID id, UUID cttId) {
    CttDto cttDto = cttService.getSupport(cttId, false, id);

    MbMbr mbMbr = new MbMbr();

    mbMbr.setMbrId(id);
    mbMbr.setCttId(cttId);

    String avtBodyFilePath = cttDto.getAvtBodyFilePath();
    if (avtBodyFilePath != null) {
      mbMbr.setAvtBodyFilePath(avtBodyFilePath);
    }

    return this.modify(id, mbMbr);
  }

  /**
   * 회원 비밀번호 수정(임시비밀번호)
   *
   * @param id 식별번호
   */
  public MbMbr modifyTempPw(UUID id, MbMbr entity) {

    // 비밀번호 암호화
    entity.setPw(BcryptHelper.hash(entity.getPw()));

    return this.modify(id, entity);
  }

  /** 회원 임시 비밀번호 수정 */
  public MbMbr modifyTempPassword(UUID id, MbMbr entity) {
    entity.setTempPwYn("N");

    // 비밀번호 암호화
    entity.setPw(BcryptHelper.hash(entity.getPw()));

    // 비밀번호 유효일시
    entity.setPwValidDt(new DateTime().plusMonths(6));
    return this.modify(id, entity);
  }

  /**
   * 회원 비밀번호 수정
   *
   * @param id 식별번호
   * @param curPw 현재비밀번호
   * @param pw 비밀번호
   * @return
   * @throws IllegalAccessException
   */
  public MbMbr modifyPassword(UUID id, String curPw, String pw) throws IllegalAccessException {
    MbMbr mbr = this.get(id);
    mbr.setTempPwYn("N");

    // 원래 비밀번호
    String hashedPw = mbr.getPw();

    if (!BcryptHelper.compare(hashedPw, curPw)) {
      throw new IllegalAccessException("현재 비밀번호가 일치하지 않습니다.");
    } else {
      // 비밀번호 암호화
      mbr.setPw(BcryptHelper.hash(pw));

      // 비밀번호 유효일시
      mbr.setPwValidDt(new DateTime().plusMonths(6));
    }

    return this.modify(id, mbr);
  }

  /**
   * 회원 등록
   *
   * @param entity
   * @return
   */
  @OneToOne
  @JoinColumn(name = "mbr_id")
  public MbMbr addMember(MbMbr entity) {
    // 로그인아이디 중복체크
    if (this.getByUserLoginId(entity.getLoginId()) != null) {
      throw new IllegalStateException("사용중인 로그인아이디 입니다.");
    }

    // 이메일 중복체크
    if (this.getByUserEmail(entity.getEmail()) != null) {
      throw new IllegalStateException("사용중인 이메일 입니다.");
    }

    // 비밀번호 암호화
    entity.setPw(BcryptHelper.hash(entity.getPw()));

    // 임시비밀번호 여부
    entity.setTempPwYn("N");

    // 기본 프로필 배경 색상
    entity.setProfileBgColor(environment.getProperty("app.avatar.defaultBackgroundColor"));

    // 기본 아바타 설정
    UUID cttId = UUID.fromString(environment.getProperty("app.avatar.defaultId"));
    CttDto ctt = cttService.getSupport(cttId, false, null);
    entity.setCttId(cttId);
    entity.setAvtBodyFilePath(ctt.getAvtBodyFilePath());
    entity.setAvtProfileFilePath(ctt.getAvtProfileFilePath());

    // 가입일시
    entity.setJoinDt(new DateTime());
    // 비밀번호 유효일시
    entity.setPwValidDt(new DateTime().plusMonths(6));

    MbMbr mbr = this.add(entity);
    UUID mbrId = mbr.getMbrId();
    mbr.setMbrNo(
        UidUtils.getUniqueId(
            MbMbr.PREFIX_MBR_NO, UidUtils.ID_PREFIX_ZEROFILL, mbrId.toString().split("-")[0]));

    // 기본아바타 즐겨찾기 설정
    cttBmService.addCttBm(
        mbrId, ViCttBm.builder().cttBinId(ctt.getCttBinId()).mbrId(mbrId).cttId(cttId).build());

    // 개인정보수집동의
    agreeRepository.save(MbMbrAgree.of(mbrId, Code.MB_003_002.getCode()));

    // 이용약관동의
    agreeRepository.save(
        MbMbrAgree.of(mbrId, Code.MB_003_001.getCode())); // agreeRepository 를 호출하여 저장

    // 마이룸 생성
    myRoomService.addMyViRoom(mbrId, mbr.getNickname());
    return mbr;
  }

  /**
   * 로그인 아이디로 사용자 조회
   *
   * @param loginId 로그인아이디
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public MbMbr getByUserLoginId(String loginId) {
    QMbMbr qMbMbr = QMbMbr.mbMbr;
    return repository
        .findOne(
            new BooleanBuilder(
                qMbMbr
                    .loginId
                    .eq(loginId)
                    .and(qMbMbr.useYn.eq("Y"))
                    .and(qMbMbr.delYn.eq("N"))
                    .and(qMbMbr.mbrTypeCode.eq(Code.MB_001_MBR.getCode()))
                    .and(qMbMbr.mbrStatusCode.eq(Code.MB_002_001.getCode()))))
        .orElse(null);
  }

  /**
   * 닉네임으로 사용자 조회
   *
   * @param nickname 닉네임
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public MbMbr getByUserNickname(String nickname) {
    return repository.findOne(new BooleanBuilder(QMbMbr.mbMbr.nickname.eq(nickname))).orElse(null);
  }

  /**
   * 내 닉네임 제외 닉네임으로 사용자 조회
   *
   * @param nickname 닉네임
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public MbMbr getByUserNicknameExceptMyNickname(String myNickname, String nickname) {
    return repository
        .findOne(
            new BooleanBuilder(QMbMbr.mbMbr.nickname.eq(nickname))
                .and(QMbMbr.mbMbr.nickname.notIn(myNickname)))
        .orElse(null);
  }

  /**
   * 이메일로 사용자 조회
   *
   * @param email 이메일
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public MbMbr getByUserEmail(String email) {
    return repository.findOne(new BooleanBuilder(QMbMbr.mbMbr.email.eq(email))).orElse(null);
  }

  /**
   * 내 이메일 제외하고 이메일로 사용자 조회
   *
   * @param myEmail
   * @param email
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public MbMbr getByUserEmailExceptMyEmail(String myEmail, String email) {
    return repository
        .findOne(
            new BooleanBuilder(QMbMbr.mbMbr.email.eq(email)).and(QMbMbr.mbMbr.email.notIn(myEmail)))
        .orElse(null);
  }

  /**
   * 최근 로그인 일시 변경
   *
   * @param loginId 로그인아이디
   */
  public void modifyLoginDt(String loginId) {
    MbMbr user = this.getByUserLoginId(loginId);
    if (user != null) {
      DateTime dateTime = new DateTime();
      user.setLastLoginDt(dateTime);
      mbrLoginHistService.add(
          MbMbrLoginHist.builder()
              .mbrId(user.getMbrId())
              .loginDt(dateTime)
              .loginTypeCode("MB_004_PC")
              .build());
    }
    //    todo : loginTypeCode("MB_004_PC") 수정 필요 -> 현재 테스트를 위해 defaultValue 설정
  }

  /**
   * 회원이름, 이메일로 사용자 아이디 조회
   *
   * @param mbrName 회원이름, email 이메일
   * @param email
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public MbMbr findId(String mbrName, String email) {
    return repository
        .findOne(
            new BooleanBuilder(QMbMbr.mbMbr.mbrName.eq(mbrName).and(QMbMbr.mbMbr.email.eq(email))))
        .orElse(null);
  }

  /**
   * 로그인아이디, 회원이름, 이메일로 사용자 아이디 조회
   *
   * @param loginId 로그인아이디
   * @param mbrName 회원이름,
   * @param email 이메일
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public MbMbr getMbrByLoginIdAndMbrNameAndEmail(String loginId, String mbrName, String email) {
    return repository
        .findOne(
            new BooleanBuilder(
                QMbMbr.mbMbr
                    .loginId
                    .eq(loginId)
                    .and(QMbMbr.mbMbr.mbrName.eq(mbrName).and(QMbMbr.mbMbr.email.eq(email)))))
        .orElse(null);
  }
}
