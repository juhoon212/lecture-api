package twentyoz.viven.feature.sy.service;

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
import twentyoz.viven.feature.sy.mapper.SyAttachFileGroupRefObjMapper;
import twentyoz.viven.feature.sy.model.QSyAttachFileGroupRefObj;
import twentyoz.viven.feature.sy.model.SyAttachFileGroupRefObj;
import twentyoz.viven.feature.sy.repository.SyAttachFileGroupRefObjRepository;

/** 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class AttachFileGroupRefObjService {

  private final SyAttachFileGroupRefObjRepository repository;

  private final SyAttachFileGroupRefObjMapper mapper;

  /**
   * 목록 조회
   *
   * @param search 검색 조건
   * @return 검색된 목록
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<SyAttachFileGroupRefObj> getList(Predicate search) {
    return (List<SyAttachFileGroupRefObj>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 페이징 조회
   *
   * @param search 검색 조건
   * @param page 페이징 조건
   * @return 검색된 목록
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<SyAttachFileGroupRefObj> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public SyAttachFileGroupRefObj get(UUID id) {
    return repository
        .findOne(
            new BooleanBuilder(
                QSyAttachFileGroupRefObj.syAttachFileGroupRefObj.attachFileGroupRefObjId.eq(id)))
        .orElse(null);
  }

  /**
   * 등록
   *
   * @param entity
   * @return
   */
  public SyAttachFileGroupRefObj add(SyAttachFileGroupRefObj entity) {
    return repository.save(entity);
  }

  /**
   * 수정
   *
   * @param entity
   * @return
   */
  public SyAttachFileGroupRefObj modify(UUID id, SyAttachFileGroupRefObj entity) {
    entity.setId(id);
    return mapper.modify(entity, get(entity.getId()));
  }

  /**
   * 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    repository.deleteById(id);
  }
}
