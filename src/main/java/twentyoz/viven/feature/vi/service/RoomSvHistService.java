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
import twentyoz.viven.feature.vi.mapper.ViRoomSvHistMapper;
import twentyoz.viven.feature.vi.model.QViRoomSvHist;
import twentyoz.viven.feature.vi.model.ViRoomSvHist;
import twentyoz.viven.feature.vi.repository.ViRoomSvHistRepository;

/** 방서버이력 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoomSvHistService {

  private final ViRoomSvHistRepository repository;
  private final ViRoomSvHistMapper mapper;

  /**
   * 방서버이력 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViRoomSvHist> getList(Predicate search) {
    return (List<ViRoomSvHist>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 방서버이력 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViRoomSvHist> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 방서버이력 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViRoomSvHist get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViRoomSvHist.viRoomSvHist.roomSvHistId.eq(id)))
        .orElse(null);
  }
}
