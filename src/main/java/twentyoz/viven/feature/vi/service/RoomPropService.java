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
import twentyoz.viven.feature.vi.mapper.ViRoomPropMapper;
import twentyoz.viven.feature.vi.model.QViRoomProp;
import twentyoz.viven.feature.vi.model.ViRoomProp;
import twentyoz.viven.feature.vi.repository.ViRoomPropRepository;

/** 방속성 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoomPropService {

  private final ViRoomPropRepository repository;
  private final ViRoomPropMapper mapper;

  /**
   * 방속성 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViRoomProp> getList(Predicate search) {
    return (List<ViRoomProp>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 방속성 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViRoomProp> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 방속성 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViRoomProp get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViRoomProp.viRoomProp.roomPropId.eq(id)))
        .orElse(null);
  }

  /**
   * 방속성 상세 조회
   *
   * @param id 방 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViRoomProp roomIdGet(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViRoomProp.viRoomProp.roomId.eq(id)))
        .orElse(null);
  }

  /**
   * 방속성 등록
   *
   * @param entity
   * @return
   */
  private ViRoomProp add(ViRoomProp entity) {
    return repository.save(entity);
  }

  /**
   * 방속성 수정
   *
   * @param entity
   * @return
   */
  private ViRoomProp modify(UUID id, ViRoomProp entity) {
    return mapper.modify(entity, get(id));
  }

  /**
   * 방속성 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    repository.deleteById(id);
  }

  /**
   * 방속성 다중 삭제
   *
   * @param ids 식별번호 목록
   */
  public void remove(List<UUID> ids) {
    if (ids.size() > 0) {
      List<ViRoomProp> list =
          this.getList(new BooleanBuilder(QViRoomProp.viRoomProp.roomPropId.in(ids)));
      for (ViRoomProp item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 방속성 등록
   *
   * @param entity
   * @return
   */
  public ViRoomProp addViRoomProp(ViRoomProp entity) {
    return this.add(entity);
  }

  /**
   * 방속성 수정
   *
   * @param entity
   * @return
   */
  public ViRoomProp modifyViRoomProp(UUID id, ViRoomProp entity) {
    return this.modify(id, entity);
  }
}
