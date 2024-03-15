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
import twentyoz.viven.feature.vi.model.ViLecture;
import twentyoz.viven.feature.vi.model.QViLecture;
import twentyoz.viven.feature.vi.model.ViRoom;
import twentyoz.viven.feature.vi.repository.ViLectureRepository;
import twentyoz.viven.feature.vi.mapper.ViLectureMapper;
import java.util.UUID;

/**
 * 강의 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LectureService {

  private final ViLectureRepository repository;
  private final ViLectureMapper mapper;
  private final RoomService roomService;

  /**
   * 강의 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLecture> getList(Predicate search) {
    return (List<ViLecture>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 강의 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViLecture> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 강의 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViLecture get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViLecture.viLecture.lectureId.eq(id)))
        .orElse(null);
  }

  @Transactional(readOnly = true)
  public ViLecture getRoomId(UUID roomId) {
    return repository
            .findOne(new BooleanBuilder(QViLecture.viLecture.roomId.eq(roomId)))
            .orElse(null);
  }



  /**
   * 강의 등록
   *
   * @param entity
   * @return
   */
  private ViLecture add(ViLecture entity) {
    return repository.save(entity);
  }

  /**
   * 강의 수정
   *
   * @param entity
   * @return
   */
  private ViLecture modify(UUID id, ViLecture entity) {
    return mapper.modify(entity, get(id));
  }

  /**
  * 강의 삭제
  *
  * @param id 식별번호
  */
  public void remove(UUID id){
    repository.deleteById(id);
  }


  /**
  * 강의 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  public void remove(List<UUID> ids){
    if(!ids.isEmpty()) {
      List<ViLecture> list = this.getList(
          new BooleanBuilder(QViLecture.viLecture.lectureId.in(ids)));
      for(ViLecture item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 강의 등록
   *
   * @param entity
   * @return
   */
  public ViLecture addLecture(ViLecture entity) {

    ViRoom findRoom = roomService.get(entity.getRoomId());

    entity.setMapCttId(findRoom.getCttId());

    return this.add(entity);
  }

  /**
   * 강의 수정
   *
   * @param entity
   * @return
   */
  public ViLecture modifyLecture(ViLecture entity) {

    UUID id = entity.getLectureId();

    return this.modify(id, entity);
  }
}
