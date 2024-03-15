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
import twentyoz.viven.feature.vi.model.ViExam;
import twentyoz.viven.feature.vi.model.QViExam;
import twentyoz.viven.feature.vi.model.ViExamDto;
import twentyoz.viven.feature.vi.repository.ViExamRepository;
import twentyoz.viven.feature.vi.mapper.ViExamMapper;
import twentyoz.viven.feature.vi.repository.ViExamRepositorySupport;

import java.util.UUID;

/**
 * 시험지 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {

  private final ViExamRepository repository;
  private final ViExamMapper mapper;
  private final ViExamRepositorySupport support;

  /**
   * 시험지 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViExam> getList(Predicate search) {
    return (List<ViExam>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViExamDto> getSupportList(Predicate search) {
    return support.getList(search);
  }

  /**
   * 시험지 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViExam> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  @Transactional(readOnly = true)
  public Page<ViExamDto> getSupportPage(Predicate predicate, Pageable page) {
    return support.getPage(predicate, page);
  }

  /**
   * 시험지 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViExam get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViExam.viExam.examId.eq(id)))
        .orElse(null);
  }

  /**
   * 시험지 등록
   *
   * @param entity
   * @return
   */
  private ViExam add(ViExam entity) {
    return repository.save(entity);
  }

  /**
   * 시험지 수정
   *
   * @param entity
   * @return
   */
  private ViExam modify(ViExam entity) {
    return mapper.modify(entity, get(entity.getExamId()));
  }

  /**
  * 시험지 삭제
  *
  * @param id 식별번호
  */
  public void remove(UUID id){
    this.get(id).delTrue();
  }


  /**
  * 시험지 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  public void remove(List<UUID> ids){
    if(!ids.isEmpty()) {
      List<ViExam> list = this.getList(
          new BooleanBuilder(QViExam.viExam.examId.in(ids)));
        list.forEach(ViExam::delTrue);
    }
  }

  /**
   * 시험지 등록
   *
   * @param entity
   * @return
   */
  public ViExam addExam(ViExam entity, UUID mbrId) {

    entity.setMbrId(mbrId);

    return this.add(entity);
  }

  /**
   * 시험지 수정
   *
   * @param entity
   * @return
   */
  public ViExam modifyExam(ViExam entity, UUID mbrId) {

    entity.setMbrId(mbrId);

    return this.modify(entity);
  }
}
