package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.mb.model.MbrFrndDto;
import twentyoz.viven.feature.vi.mapper.ViFrndMapper;
import twentyoz.viven.feature.vi.model.FrndDto;
import twentyoz.viven.feature.vi.model.QViFrnd;
import twentyoz.viven.feature.vi.model.ViFrnd;
import twentyoz.viven.feature.vi.model.ViFrndReqHist;
import twentyoz.viven.feature.vi.repository.ViFrndRepository;
import twentyoz.viven.feature.vi.repository.ViFrndRepositorySupport;

/** 친구 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class FrndService {

  private final ViFrndRepository repository;
  private final ViFrndMapper mapper;
  private final FrndReqHistService frndReqHistService;
  private final ViFrndRepositorySupport repositorySupport;

  /**
   * 친구 목록 조회
   *
   * @param predicate 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<FrndDto> getList(Predicate predicate, UUID mbrId) {
    return repositorySupport.getList(predicate, mbrId);
  }

  /**
   * 친구 페이징 조회
   *
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<FrndDto> getPage(Predicate predicate, Pageable page, UUID mbrId) {
    return repositorySupport.getPage(predicate, page, mbrId);
  }

  /**
   * 친구 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViFrnd get(UUID id) {
    return repository.findOne(new BooleanBuilder(QViFrnd.viFrnd.frndId.eq(id))).orElse(null);
  }

  /**
   * 친구 상세 조회 (친구회원식별번호, 회원식별번호)
   *
   * @param mbrId 식별번호
   * @param frndMbrId 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViFrnd getWithMbr(UUID mbrId, UUID frndMbrId) {
    BooleanBuilder builder = new BooleanBuilder();
    builder.and(QViFrnd.viFrnd.mbrId.eq(mbrId));
    builder.and(QViFrnd.viFrnd.frndMbrId.eq(frndMbrId));

    return repository.findOne(builder).orElse(null);
  }

  /**
   * 친구 등록
   *
   * @param entity
   * @return
   */
  public ViFrnd add(ViFrnd entity) {
    return repository.save(entity);
  }

  /**
   * 친구 수정
   *
   * @param entity
   * @return
   */
  private ViFrnd modify(UUID id, ViFrnd entity) {
    return mapper.modify(entity, get(id));
  }

  private void remove(UUID id) {
    repository.deleteById(id);
  }

  /** 친구 삭제 */
  public void removeTarget(UUID id) {
    ViFrnd frndMy = this.get(id);
    ViFrnd frndTarget = this.getWithMbr(frndMy.getFrndMbrId(), frndMy.getMbrId());

    // 친구요청이력 등록 - 친구삭제
    ViFrndReqHist frndReqHist = new ViFrndReqHist();
    frndReqHist.setReqMbrId(frndMy.getMbrId());
    frndReqHist.setResMbrId(frndMy.getFrndMbrId());
    frndReqHist.setReqStatusCode(Code.FRND_001_004.getCode());
    frndReqHistService.add(frndReqHist);

    this.remove(frndMy.getId());
    this.remove(frndTarget.getId());
  }

  /**
   * 친구 검색 목록
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<MbrFrndDto> getPageSupport(Predicate search, Pageable page, UUID mbrId) {
    return repositorySupport.getSearchPage(search, page, mbrId);
  }
}
