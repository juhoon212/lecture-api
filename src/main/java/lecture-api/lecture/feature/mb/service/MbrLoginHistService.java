package twentyoz.viven.feature.mb.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import twentyoz.viven.feature.mb.mapper.MbMbrLoginHistMapper;
import twentyoz.viven.feature.mb.model.QMbMbrLoginHist;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/** 회원로그인 이력 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class MbrLoginHistService {

  private final MbMbrLoginHistRepository repository;
  private final MbMbrLoginHistMapper mapper;

  /**
   * 회원로그인 이력 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<MbMbrLoginHist> getList(Predicate search) {
    return (List<MbMbrLoginHist>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 회원로그인 이력 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<MbMbrLoginHist> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 회원로그인 이력 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public MbMbrLoginHist get(UUID id) {
    return repository.findOne(
        new BooleanBuilder(QMbMbrLoginHist.mbMbrLoginHist.mbrLoginHistId.eq(id))).orElse(null);
  }

  /**
   * 회원로그인 이력 등록
   *
   * @param entity
   * @return
   */
  public MbMbrLoginHist add(MbMbrLoginHist entity) {
    return repository.save(entity);
  }

  /**
   * 회원로그인 이력 수정
   *
   * @param entity
   * @return
   */
  public MbMbrLoginHist modify(UUID id, MbMbrLoginHist entity) {
    return mapper.modify(entity, get(id));
  }

  /**
   * 회원로그인 이력 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    repository.deleteById(id);
  }

  /**
   * 회원로그인 이력 다중 삭제
   *
   * @param ids 식별번호 목록
   */
  public void remove(List<UUID> ids) {
    if (ids.size() > 0) {
      List<MbMbrLoginHist> list =
          this.getList(new BooleanBuilder(QMbMbrLoginHist.mbMbrLoginHist.mbrLoginHistId.in(ids)));
      for (MbMbrLoginHist item : list) {
        this.remove(item.getId());
      }
    }
  }
}
