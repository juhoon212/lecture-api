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
import twentyoz.viven.feature.vi.mapper.ViCttJudgeHistMapper;
import twentyoz.viven.feature.vi.model.CttJudgeHistDto;
import twentyoz.viven.feature.vi.model.CttJudgeHistDtoFile;
import twentyoz.viven.feature.vi.model.QViCttJudgeHist;
import twentyoz.viven.feature.vi.model.ViCttJudgeHist;
import twentyoz.viven.feature.vi.repository.ViCttJudgeHistRepository;
import twentyoz.viven.feature.vi.repository.ViCttJudgeHistRepositorySupport;

/** 콘텐츠심사이력 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class CttJudgeHistService {

  private final ViCttJudgeHistRepository repository;
  private final ViCttJudgeHistMapper mapper;

  private final ViCttJudgeHistRepositorySupport repositorySupport;

  /**
   * 콘텐츠심사이력 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViCttJudgeHist> getList(Predicate search) {
    return (List<ViCttJudgeHist>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 콘텐츠심사이력 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViCttJudgeHist> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 콘텐츠심사이력 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViCttJudgeHist get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViCttJudgeHist.viCttJudgeHist.cttJudgeHistId.eq(id)))
        .orElse(null);
  }

  /**
   * 콘텐츠심사이력 목록(w. 콘텐츠심사요청)
   *
   * @param search
   * @return
   */
  public List<CttJudgeHistDto> getSupportList(Predicate search) {
    return repositorySupport.getList(search);
  }

  /**
   * 콘텐츠심사이력 페이징 목록(w. 콘텐츠심사요청)
   *
   * @param search
   * @return
   */
  public Page<CttJudgeHistDto> getSupportPage(Predicate search, Pageable page) {
    return repositorySupport.getPage(search, page);
  }

  /**
   * 콘텐츠심사이력 조회(w. 콘텐츠심사요청)
   *
   * @param id 식별번호
   * @return
   */
  public CttJudgeHistDto getSupport(UUID id) {
    return repositorySupport.get(id);
  }

  public CttJudgeHistDtoFile getSupportFile(UUID id) {
    return repositorySupport.getFile(id);
  }

  /**
   * 콘텐츠심사이력 등록
   *
   * @param entity
   * @return
   */
  private ViCttJudgeHist add(ViCttJudgeHist entity) {
    return repository.save(entity);
  }

  /**
   * 콘텐츠심사이력 등록
   *
   * @param entity
   * @return
   */
  public ViCttJudgeHist addCttJudgeHist(ViCttJudgeHist entity) {
    if (entity.getCttJudgeReqId() == null) {
      return null;
    }

    return this.add(entity);
  }
}
