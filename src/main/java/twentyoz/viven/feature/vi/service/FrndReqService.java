package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.mb.model.MbMbr;
import twentyoz.viven.feature.mb.service.MbrService;
import twentyoz.viven.feature.vi.mapper.ViFrndReqMapper;
import twentyoz.viven.feature.vi.model.FrndReqDto;
import twentyoz.viven.feature.vi.model.QViFrndReq;
import twentyoz.viven.feature.vi.model.ViAlarm;
import twentyoz.viven.feature.vi.model.ViFrnd;
import twentyoz.viven.feature.vi.model.ViFrndReq;
import twentyoz.viven.feature.vi.model.ViFrndReqHist;
import twentyoz.viven.feature.vi.repository.ViFrndReqRepository;
import twentyoz.viven.feature.vi.repository.ViFrndReqRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/** 친구요청 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class FrndReqService {

  private final ViFrndReqRepository repository;
  private final ViFrndReqMapper mapper;
  private final FrndReqHistService frndReqHistService;
  private final ViFrndReqRepositorySupport repositorySupport;
  private final FrndService frndService;
  private final MbrService mbrService;
  private final AlarmService alarmService;

  /**
   * 친구요청 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViFrndReq> getList(Predicate search) {
    return (List<ViFrndReq>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 친구요청 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViFrndReq> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 친구요청 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViFrndReq get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViFrndReq.viFrndReq.frndReqId.eq(id)))
        .orElse(null);
  }

  /**
   * 친구요청 등록
   *
   * @param entity
   * @return
   */
  private ViFrndReq add(ViFrndReq entity) {
    return repository.save(entity);
  }

  /**
   * 친구요청 수정
   *
   * @param id 식별번호
   * @param entity
   * @return
   */
  private ViFrndReq modify(UUID id, ViFrndReq entity) {
    return mapper.modify(entity, get(id));
  }

  /**
   * 친구요청 삭제
   *
   * @param id 식별번호
   */
  private void remove(UUID id) {
    repository.deleteById(id);
  }

  /**
   * 친구요청 다중 삭제
   *
   * @param ids 식별번호 목록
   */
  public void remove(List<UUID> ids) {
    if (ids.size() > 0) {
      List<ViFrndReq> list =
          this.getList(new BooleanBuilder(QViFrndReq.viFrndReq.frndReqId.in(ids)));
      for (ViFrndReq item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 친구요청 목록 조회 - 로그인한 회원 본인이 요청한 요청 목록
   *
   * @param mbrId 회원식별번호
   * @return
   */
  public List<FrndReqDto> getSupportReqList(UUID mbrId) {
    return repositorySupport.getReqList(mbrId);
  }

  /**
   * 친구요청 페이징 조회 - 로그인한 회원 본인이 요청한 요청 목록
   *
   * @param page
   * @param mbrId 회원식별번호
   * @return
   */
  public Page<FrndReqDto> getSupportReqPage(Pageable page, UUID mbrId) {
    return repositorySupport.getReqPage(page, mbrId);
  }

  /**
   * 친구요청 목록 조회 - 로그인한 회원 본인이 요청한 요청 목록
   *
   * @param mbrId 회원식별번호
   * @return
   */
  public List<FrndReqDto> getSupportResList(UUID mbrId) {
    return repositorySupport.getResList(mbrId);
  }

  /**
   * 친구요청 페이징 조회 - 로그인한 회원 본인이 요청한 요청 목록
   *
   * @param page
   * @param mbrId 회원식별번호
   * @return
   */
  public Page<FrndReqDto> getSupportResPage(Pageable page, UUID mbrId) {
    return repositorySupport.getResPage(page, mbrId);
  }

  /**
   * 친구요청 상세 조회
   *
   * @param resMbrId 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViFrndReq getWithReqInfo(UUID reqMbrId, UUID resMbrId) {
    BooleanBuilder builder = new BooleanBuilder();
    builder.and(QViFrndReq.viFrndReq.reqMbrId.eq(reqMbrId));
    builder.and(QViFrndReq.viFrndReq.resMbrId.eq(resMbrId));

    return repository.findOne(builder).orElse(null);
  }

  /** 상대방이 나에게 보낸 요청이 있는지 확인하는 함수 */
  @Transactional(readOnly = true)
  public ViFrndReq checkReverseReq(UUID reqMbrId, UUID resMbrId) {
    BooleanBuilder builder = new BooleanBuilder();
    builder.and(QViFrndReq.viFrndReq.resMbrId.eq(reqMbrId));
    builder.and(QViFrndReq.viFrndReq.reqMbrId.eq(resMbrId));

    return repository.findOne(builder).orElse(null);
  }

  /**
   * 친구요청
   *
   * @param id
   * @param mbrId 회원식별번호
   * @return
   */
  public ViFrndReq request(UUID id, UUID mbrId) {
    ViFrndReq checkFrndReq = getWithReqInfo(mbrId, id);
    ViFrndReq checkFrndReverseReq = checkReverseReq(mbrId, id);

    if (checkFrndReverseReq != null && checkFrndReverseReq.getFrndReqId() != null) {
      throw new IllegalStateException("이미 친구가 요청을 보냈습니다.");
    }

    if (checkFrndReq != null && checkFrndReq.getFrndReqId() != null) {
      throw new IllegalStateException("이미 친구 요청하였습니다.");
    }

    ViFrnd checkFrnd = frndService.getWithMbr(mbrId, id);
    if (checkFrnd != null && checkFrnd.getFrndId() != null) {
      throw new IllegalStateException("이미 친구 등록이 되어있습니다.");
    }

    // 친구요청 등록
    ViFrndReq frndReq = new ViFrndReq();
    frndReq.setResMbrId(id);
    frndReq.setReqStatusCode(Code.FRND_001_001.getCode());
    frndReq.setReqMbrId(mbrId);
    frndReq.setRegId(mbrId);
    ViFrndReq result = this.add(frndReq);

    // 친구요청이력 등록 - 친구요청
    this.addHist(mbrId, result.getResMbrId(), Code.FRND_001_001.getCode());

    // 알람 등록
    ViAlarm viAlarm = new ViAlarm();
    viAlarm.setAlarmTypeCode(Code.AL_001_004.getCode());
    viAlarm.setMbrId(result.getResMbrId());

    MbMbr mbMbr = mbrService.getMbr(mbrId);
    viAlarm.setAlarmName(mbMbr.getNickname() + "님이 친구 요청을 보냈습니다!");
    alarmService.addAlarm(viAlarm);

    return repository.save(frndReq);
  }

  /**
   * 친구수락
   *
   * @param id 식별번호
   * @param mbrId 회원식별번호
   */
  public void accept(UUID id, UUID mbrId) {
    ViFrndReq frndReq = this.get(id);

    // 요청을 받은 당사자가 아닌 경우 처리하지 않고 null 로 return
    if (!mbrId.equals(frndReq.getResMbrId())) {
      return;
    }

    // 요청 상태인 경우에만 처리
    if (!Code.FRND_001_001.getCode().equals(frndReq.getReqStatusCode())) {
      return;
    }

    ViFrndReq result = this.get(id);

    // 친구 요청에서 삭제
    List<UUID> ids = new ArrayList<>();
    ids.add(id);
    this.remove(ids);

    // 친구 등록
    ViFrnd viFrndMy = new ViFrnd();
    viFrndMy.setMbrId(result.getReqMbrId());
    viFrndMy.setFrndMbrId(result.getResMbrId());
    viFrndMy.setRegDt(DateTime.now());

    ViFrnd viFrndTarget = new ViFrnd();
    viFrndTarget.setMbrId(result.getResMbrId());
    viFrndTarget.setFrndMbrId(result.getReqMbrId());
    viFrndTarget.setRegDt(DateTime.now());

    frndService.add(viFrndMy);
    frndService.add(viFrndTarget);

    // 친구요청이력 등록 - 친구수락
    this.addHist(mbrId, result.getResMbrId(), Code.FRND_001_002.getCode());
  }

  /**
   * 친구거절
   *
   * @param id 식별번호
   * @param mbrId 회원식별번호
   */
  public void decline(UUID id, UUID mbrId) {
    ViFrndReq frndReq = this.get(id);

    // 요청을 받은 당사자가 아닌 경우 처리하지 않고 null 로 return
    if (!Objects.equals(mbrId, frndReq.getResMbrId())) {
      return;
    }

    // 요청 상태인 경우에만 처리
    if (!Code.FRND_001_001.getCode().equals(frndReq.getReqStatusCode())) {
      return;
    }

    // 친구 거절 처리

    ViFrndReq result = this.get(id);

    // 친구 요청에서 삭제
    List<UUID> ids = new ArrayList<>();
    ids.add(id);
    this.remove(ids);

    // 친구요청이력 등록 - 친구거절
    this.addHist(mbrId, result.getResMbrId(), Code.FRND_001_003.getCode());
  }

  /**
   * 친구 수락 일괄 처리
   *
   * @param ids 식별번호 목록
   * @param mbrId 회원식별번호
   */
  public void acceptAll(List<UUID> ids, UUID mbrId) {
    if (ids.size() > 0) {
      for (UUID id : ids) {
        this.accept(id, mbrId);
      }
    }
  }

  /**
   * 친구 거절 일괄 처리
   *
   * @param ids 식별번호 목록
   * @param mbrId 회원식별번호
   */
  public void declineAll(List<UUID> ids, UUID mbrId) {
    if (ids.size() > 0) {
      for (UUID id : ids) {
        this.decline(id, mbrId);
      }
    }
  }

  /**
   * 대기중인 친구요청 삭제 (로그인한 회원 본인이 요청한 내역 삭제)
   *
   * @param ids 식별번호 목록
   * @param mbrId 회원식별번호
   */
  public void removeReq(List<UUID> ids, UUID mbrId) {
    if (ids.size() > 0) {
      List<ViFrndReq> list =
          this.getList(new BooleanBuilder(QViFrndReq.viFrndReq.frndReqId.in(ids)));
      for (ViFrndReq item : list) {
        // 요청 상태의 친구요청 건만 삭제 가능
        if (Code.FRND_001_001.getCode().equals(item.getReqStatusCode())) {
          // 요청을 한 당사자의 요청만 삭제 처리
          if (Objects.equals(mbrId, item.getReqMbrId())) {
            repository.deleteById(item.getFrndReqId());
          }
        }
      }
    }
  }

  /**
   * 친구요청 받은 내역 삭제
   *
   * @param ids 식별번호 목록
   * @param mbrId 회원식별번호
   */
  public void removeRes(List<UUID> ids, UUID mbrId) {
    if (ids.size() > 0) {
      List<ViFrndReq> list =
          this.getList(new BooleanBuilder(QViFrndReq.viFrndReq.frndReqId.in(ids)));
      for (ViFrndReq item : list) {
        // 요청 상태의 친구요청 건만 삭제 가능
        if (Code.FRND_001_001.getCode().equals(item.getReqStatusCode())) {
          // 요청을 받은 당사자의 요청만 삭제 처리
          if (Objects.equals(mbrId, item.getResMbrId())) {
            repository.deleteById(item.getFrndReqId());
          }
        }
      }
    }
  }

  /**
   * 친구 이력 등록
   *
   * @param reqMbrId 요청회원식별번호
   * @param resMbrId 응답회원식별번호
   * @param ReqStatusCode 요청상태코드
   */
  public void addHist(UUID reqMbrId, UUID resMbrId, String ReqStatusCode) {
    ViFrndReqHist frndReqHist = new ViFrndReqHist();
    frndReqHist.setReqMbrId(reqMbrId);
    frndReqHist.setResMbrId(resMbrId);
    frndReqHist.setReqStatusCode(ReqStatusCode);
    frndReqHistService.add(frndReqHist);
  }
}
