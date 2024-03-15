package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.vi.model.QViRoomConnHist;
import twentyoz.viven.feature.vi.model.ViRoomConnHist;
import twentyoz.viven.feature.vi.model.ViRoomConnHistDto;
import twentyoz.viven.feature.vi.repository.ViRoomConnHistRepository;
import twentyoz.viven.feature.vi.repository.ViRoomConnHistRepositorySupport;

/** 방접속이력 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoomConnHistService {

  private final ViRoomConnHistRepository repository;

  private final ViRoomConnHistRepositorySupport repositorySupport;

  /**
   * 방접속이력 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViRoomConnHist> getList(Predicate search) {
    return (List<ViRoomConnHist>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 방접속이력 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViRoomConnHist> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 방접속이력 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViRoomConnHist get(UUID id) {
    return repository.findOne(
        new BooleanBuilder(QViRoomConnHist.viRoomConnHist.roomConnHistId.eq(id))).orElse(null);
  }

  /**
   * 방 접속이력 등록
   *
   * @param entity
   * @return
   */
  private ViRoomConnHist add(ViRoomConnHist entity) {
    return repository.save(entity);
  }

  /**
   * 방 접속이력 등록
   *
   * @param entity
   * @return
   */
  public ViRoomConnHist addRoomConnHist(ViRoomConnHist entity) {
    return this.add(entity);
  }

  /**
   * 나의 방 접속이력 목록
   *
   * @param mbrId 회원식별번호
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViRoomConnHistDto> getListSupport(UUID mbrId) {
    return repositorySupport.getList(mbrId);
  }

  /**
   * 나의 방 접속이력 페이징
   *
   * @param page
   * @param mbrId 회원식별번호
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViRoomConnHistDto> getPageSupport(Pageable page, UUID mbrId) {
    return repositorySupport.getPage(page, mbrId);
  }
}
