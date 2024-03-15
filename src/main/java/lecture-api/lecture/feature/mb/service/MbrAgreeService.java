package twentyoz.viven.feature.mb.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.mb.model.MbMbrAgree;
import twentyoz.viven.feature.mb.model.QMbMbrAgree;
import twentyoz.viven.feature.mb.repository.MbMbrAgreeRepository;

/** 회원동의 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class MbrAgreeService {

  private final MbMbrAgreeRepository repository;

  /**
   * 회원동의 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<MbMbrAgree> getList(Predicate search) {
    return (List<MbMbrAgree>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 회원동의 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<MbMbrAgree> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 회원동의 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public MbMbrAgree get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QMbMbrAgree.mbMbrAgree.mbrAgreeId.eq(id)))
        .orElse(null);
  }

  /**
   * 회원동의 등록
   *
   * @param entity
   * @return
   */
  public MbMbrAgree add(MbMbrAgree entity) {
    return repository.save(entity);
  }

  /**
   * 회원동의 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    repository.deleteById(id);
  }

  /**
   * 회원동의 다중 삭제
   *
   * @param ids 식별번호 목록
   */
  public void remove(List<UUID> ids) {
    if (ids.size() > 0) {
      List<MbMbrAgree> list =
          this.getList(new BooleanBuilder(QMbMbrAgree.mbMbrAgree.mbrAgreeId.in(ids)));
      for (MbMbrAgree item : list) {
        this.remove(item.getId());
      }
    }
  }
}
