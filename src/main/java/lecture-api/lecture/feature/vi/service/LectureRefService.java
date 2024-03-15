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

import twentyoz.viven.feature.vi.model.ViLectureRef;
import twentyoz.viven.feature.vi.model.QViLectureRef;
import twentyoz.viven.feature.vi.model.ViLectureRefDto;
import twentyoz.viven.feature.vi.repository.ViLectureRefRepository;
import twentyoz.viven.feature.vi.mapper.ViLectureRefMapper;
import twentyoz.viven.feature.vi.repository.ViLectureRefRepositorySupport;

import java.util.UUID;

/**
 * 강의자료 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LectureRefService {

  private final ViLectureRefRepository repository;
  private final ViLectureRefMapper mapper;
  private final ViLectureRefRepositorySupport repositorySupport;

  /**
   * 강의자료 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLectureRef> getList(Predicate search) {
    return (List<ViLectureRef>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLectureRefDto> getSupportList(Predicate search, UUID mbrId) {
    return repositorySupport.getSupportList(search, mbrId);
  }

  /**
   * 강의자료 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViLectureRef> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }


  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViLectureRefDto> getSupportPage(Predicate search, Pageable page, UUID mbrId) {
    return repositorySupport.getSupportPage(search, page, mbrId);
  }

  /**
   * 강의자료 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViLectureRef get(UUID id) {

    return repository
        .findOne(new BooleanBuilder(QViLectureRef.viLectureRef.lectureRefId.eq(id)))
        .orElse(null);
  }

  /**
   * 강의자료 등록
   *
   * @param entity
   * @return
   */
  private ViLectureRef add(ViLectureRef entity) {
    return repository.save(entity);
  }

  /**
   * 강의자료 수정
   *
   * @param entity
   * @return
   */
  private ViLectureRef modify(UUID id, ViLectureRef entity) {
    return mapper.modify(entity, get(id));
  }

  /**
  * 강의자료 삭제
  *
  * @param id 식별번호
  */
  public void remove(UUID id){
    this.get(id).delTrue();
  }


  /**
  * 강의자료 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  public void remove(List<UUID> ids){
    if(!ids.isEmpty()) {
      List<ViLectureRef> list = this.getList(
          new BooleanBuilder(QViLectureRef.viLectureRef.lectureRefId.in(ids)));
      for(ViLectureRef item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 강의자료 등록
   *
   * @param entity
   * @return
   */
  public ViLectureRef addLectureRef(ViLectureRef entity) {
    return this.add(entity);
  }

  /**
   * 강의자료 수정
   *
   * @param entity
   * @return
   */
  public ViLectureRef modifyLectureRef(ViLectureRef entity) {

    String inCode = entity.getRefTypeCode();

    ViLectureRef findLectureRef = get(entity.getLectureRefId());

    String findLectureRefCode = findLectureRef.getRefTypeCode();

//    // 들어온 코드와 원래 코드가 다르고 && (Code 타입이 REF_001_001 || REF_001_002 이면)
//    if(!inCode.equals(findLectureRefCode) &&
//            (inCode.equals(Code.REF_001_001.getCode()) || inCode.equals(Code.REF_001_002.getCode()))) {
//      // 들어온 데이터의 링크값이 있으면 attachFileGroupId = null 이다
//      if(StringUtils.isNotEmpty(entity.getRefLink())) {
//        findLectureRef.setAttachFileGroupId(null);
//        // AttachFileGroupId가 있으면 link 파일 업데이트
//      } else if(entity.getAttachFileGroupId() != null) {
//          findLectureRef.setRefLink(null);
//      }
//    }

    UUID id = entity.getLectureRefId();

    return this.modify(id, entity);
  }
}
