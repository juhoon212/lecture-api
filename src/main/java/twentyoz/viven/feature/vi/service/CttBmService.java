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
import twentyoz.viven.feature.vi.model.CttBmDto;
import twentyoz.viven.feature.vi.model.CttDto;
import twentyoz.viven.feature.vi.model.QViCttBm;
import twentyoz.viven.feature.vi.model.ViCttBm;
import twentyoz.viven.feature.vi.repository.ViCttBmRepository;
import twentyoz.viven.feature.vi.repository.ViCttBmRepositorySupport;

/** 콘텐츠 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class CttBmService {

  private final ViCttBmRepository repository;
  private final CttService cttService;

  private final ViCttBmRepositorySupport repositorySupport;

  /**
   * 콘텐츠 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViCttBm> getList(Predicate search) {
    return (List<ViCttBm>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 콘텐츠 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViCttBm> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  public Page<CttBmDto> getSupportPage(Predicate search, Pageable page) {
    return repositorySupport.getPage(search, page);
  }

  /**
   * 콘텐츠 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViCttBm get(UUID id) {
    return repository.findOne(new BooleanBuilder(QViCttBm.viCttBm.cttBmId.eq(id))).orElse(null);
  }

  /**
   * 콘텐츠 등록
   *
   * @param entity
   * @return
   */
  private ViCttBm add(ViCttBm entity) {
    return repository.save(entity);
  }

  /**
   * 콘텐츠 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    repository.deleteById(id);
  }

  // TODO: ERD 와 맞지 않아 확인 필요

  /**
   * 콘텐츠즐겨찾기 등록
   *
   * @param mbrId 회원식별번호
   * @param entity
   * @return
   */
  public ViCttBm addCttBm(UUID mbrId, ViCttBm entity) {
    entity.setCttId(entity.getCttId());

    CttDto cttDto = cttService.getSupport(entity.getCttId(), false, mbrId);
    entity.setCttBinId(cttDto.getCttBinId());
    entity.setMbrId(mbrId);

    return this.add(entity);
  }

  /**
   * 콘텐츠즐겨찾기 등록여부
   *
   * @param id
   * @param mbrId
   * @return
   */
  public ViCttBm checkBmYn(UUID id, UUID mbrId) {
    QViCttBm qViCttBm = QViCttBm.viCttBm;

    BooleanBuilder builder = new BooleanBuilder();
    builder.and(qViCttBm.cttId.eq(id));
    builder.and(qViCttBm.mbrId.eq(mbrId));

    return repository.findOne(builder).orElse(null);
  }
}
