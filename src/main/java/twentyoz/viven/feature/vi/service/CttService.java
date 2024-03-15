package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.vi.mapper.ViCttMapper;
import twentyoz.viven.feature.vi.model.CttDto;
import twentyoz.viven.feature.vi.model.QViCtt;
import twentyoz.viven.feature.vi.model.ViCtt;
import twentyoz.viven.feature.vi.repository.ViCttRepository;
import twentyoz.viven.feature.vi.repository.ViCttRepositorySupport;
import twentyoz.viven.util.UidUtils;

/** 콘텐츠 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class CttService {

  @Value("${app.attach.baseUrl}")
  private String attachBaseUrl;

  private final ViCttRepository repository;

  private final ViCttMapper mapper;

  private final ViCttRepositorySupport repositorySupport;

  /**
   * 콘텐츠 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViCtt> getList(Predicate search) {
    return (List<ViCtt>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 콘텐츠 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViCtt> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 콘텐츠 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViCtt get(UUID id) {
    return repository.findOne(new BooleanBuilder(QViCtt.viCtt.cttId.eq(id))).orElse(null);
  }

  /**
   * 콘텐츠 목록(w. 콘텐츠버전, 콘텐츠심사요청)
   *
   * @param search
   * @param isMyCtt 나의 콘텐츠 여부
   * @param mbrId 회원식별번호
   * @return
   */
  public List<CttDto> getSupportList(Predicate search, boolean isMyCtt, UUID mbrId) {
    return repositorySupport.getList(search, isMyCtt, mbrId);
  }

  /**
   * 콘텐츠 페이징 목록(w. 콘텐츠버전, 콘텐츠심사요청)
   *
   * @param search
   * @param page
   * @param isMyCtt 나의 콘텐츠 여부
   * @param mbrId 회원식별번호
   * @return
   */
  public Page<CttDto> getSupportPage(Predicate search, Pageable page, boolean isMyCtt, UUID mbrId) {
    return repositorySupport.getPage(search, page, isMyCtt, mbrId);
  }

  /**
   * 콘텐츠 조회(w. 콘텐츠버전, 콘텐츠심사요청)
   *
   * @param id
   * @param isMyCtt 나의 콘텐츠 여부
   * @param mbrId 회원식별번호
   * @return
   */
  public CttDto getSupport(UUID id, boolean isMyCtt, UUID mbrId) {
    return repositorySupport.get(id, isMyCtt, mbrId);
  }

  public String getSupportMainUrl(UUID id, UUID mbrId) {
    return attachBaseUrl + repositorySupport.get(id, false, mbrId).getMainFilePath();
  }

  /**
   * 콘텐츠 등록
   *
   * @param entity
   * @return
   */
  private ViCtt add(ViCtt entity) {
    return repository.save(entity);
  }

  /**
   * 콘텐츠 등록
   *
   * @param entity
   * @return
   */
  public ViCtt addCtt(ViCtt entity) {

    UUID uuid = UidUtils.getUuid();
    entity.setId(uuid);

    // 콘텐츠번호 자동생성
    entity.setCttNo(
        UidUtils.getUniqueId(
            ViCtt.PREFIX_CTT_NO, UidUtils.ID_PREFIX_ZEROFILL, uuid.toString().split("-")[0]));

    // 콘텐츠전시명 미입력시 --> 콘텐츠명 자동입력
    if (StringUtils.isEmpty(entity.getCttDpName())) {
      entity.setCttDpName(entity.getCttName());
    }

    entity.setSellStatusCode(Code.CT_002_001.getCode());

    return this.add(entity);
  }

  /**
   * 콘텐츠 수정
   *
   * @param entity
   * @return
   */
  private ViCtt modify(UUID id, ViCtt entity) {
    return mapper.modify(entity, get(id));
  }

  /**
   * 콘텐츠 수정
   *
   * @param entity
   * @return
   */
  public ViCtt modifyCtt(UUID id, ViCtt entity) {

    // 전시여부가 Y
    // 전시 null, null
    // 현재시점, 9999 처리

    // 둘 중 하나라도 있을 때
    // start  지금시점으로 설정
    // end를 9999로 설정

    // 기간 start, end 둘 다 필수
    if ("Y".equals(entity.getDpYn())) {
      if (entity.getDpEndDt() == null) {
        entity.setDpEndDt(new DateTime("9999-12-31"));
      }
      if (entity.getDpStartDt() == null) {
        entity.setDpStartDt(new DateTime());
      }
    }
    // 전시여부 N
    // 기간 null,null
    else {
      entity.setDpStartDt(null);
      entity.setDpEndDt(null);
    }

    return this.modify(id, entity);
  }

  /**
   * 콘텐츠 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    this.get(id).delTrue();
  }

  /**
   * 콘텐츠 다중 삭제
   *
   * @param ids 식별번호 목록
   */
  public void remove(List<UUID> ids) {
    if (ids.size() > 0) {
      List<ViCtt> list = this.getList(new BooleanBuilder(QViCtt.viCtt.cttId.in(ids)));
      for (ViCtt item : list) {
        this.remove(item.getId());
      }
    }
  }
}
