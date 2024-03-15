package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.Predicate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import java.util.List;
import twentyoz.viven.feature.vi.model.ViRoomAuth;
import twentyoz.viven.feature.vi.model.QViRoomAuth;
import twentyoz.viven.feature.vi.repository.ViRoomAuthRepository;
import twentyoz.viven.feature.vi.mapper.ViRoomAuthMapper;
import java.util.UUID;

/**
 * 방권한 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoomAuthService {

  private final ViRoomAuthRepository repository;
  private final ViRoomAuthMapper mapper;

  /**
   * 방권한 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViRoomAuth> getList(Predicate search) {
    return (List<ViRoomAuth>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 방권한 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViRoomAuth> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 방권한 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViRoomAuth get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViRoomAuth.viRoomAuth.roomAuthId.eq(id)))
        .orElse(null);
  }

  /**
   * 방권한 등록
   *
   * @param entity
   * @return
   */
  private ViRoomAuth add(ViRoomAuth entity) {
    return repository.save(entity);
  }

  /**
   * 방권한 수정
   *
   * @param entity
   * @return
   */
  private ViRoomAuth modify(UUID id, ViRoomAuth entity) {
    return mapper.modify(entity, get(id));
  }

  /**
  * 방권한 삭제
  *
  * @param id 식별번호
  */
  public void remove(UUID id){
    repository.deleteById(id);
  }


  /**
  * 방권한 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  public void remove(List<UUID> ids){
    if(ids.size() > 0) {
      List<ViRoomAuth> list = this.getList(
          new BooleanBuilder(QViRoomAuth.viRoomAuth.roomAuthId.in(ids)));
      for(ViRoomAuth item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 방권한 등록
   *
   * @param entity
   * @return
   */
  public ViRoomAuth addRoomAuth(ViRoomAuth entity) {
    return this.add(entity);
  }

  /**
   * 방권한 수정
   *
   * @param entity
   * @return
   */
  public ViRoomAuth modifyRoomAuth(UUID id, ViRoomAuth entity) {
    return this.modify(id, entity);
  }
}
