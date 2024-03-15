package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.vi.mapper.ViCttBinMapper;
import twentyoz.viven.feature.vi.model.CttBinDto;
import twentyoz.viven.feature.vi.model.QViCttBin;
import twentyoz.viven.feature.vi.model.QViCttJudgeReq;
import twentyoz.viven.feature.vi.model.ViCttBin;
import twentyoz.viven.feature.vi.model.ViCttJudgeReq;
import twentyoz.viven.feature.vi.repository.ViCttBinRepository;
import twentyoz.viven.feature.vi.repository.ViCttBinRepositorySupport;
import twentyoz.viven.feature.vi.repository.ViCttJudgeReqRepository;
import twentyoz.viven.webapi.client.vi.form.CttBinForm;

/** 콘텐츠버전 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class CttBinService {

  @Value("${app.attach.baseUrl}")
  private String attachBaseUrl;

  private final ViCttBinRepository repository;
  private final ViCttJudgeReqRepository cttJudgeReqRepository;

  private final ViCttBinMapper mapper;

  private final ViCttBinRepositorySupport repositorySupport;

  /**
   * 콘텐츠버전 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViCttBin> getList(Predicate search) {
    return (List<ViCttBin>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 콘텐츠버전 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViCttBin> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 콘텐츠버전 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViCttBin get(UUID id) {
    return repository.findOne(new BooleanBuilder(QViCttBin.viCttBin.cttBinId.eq(id))).orElse(null);
  }

  /**
   * 콘텐츠버전 목록 (with 콘텐츠심사요청)
   *
   * @param search
   * @return
   */
  public List<CttBinDto> getSupportList(Predicate search) {
    return repositorySupport.getList(search);
  }

  /**
   * 콘텐츠버전 페이징 목록(w. 콘텐츠버전, 콘텐츠심사요청)
   *
   * @param search
   * @return
   */
  public Page<CttBinDto> getSupportPage(Predicate search, Pageable page) {
    return repositorySupport.getPage(search, page);
  }

  public String getSupportFileUrl(UUID id) {

    CttBinDto cttBinDto = repositorySupport.get(id);

    if (cttBinDto != null) {
      return attachBaseUrl + cttBinDto.getAttachFilePath();
    }
    return null;
  }

  /**
   * 콘텐츠버전 조회(w. 콘텐츠버전, 콘텐츠심사요청)
   *
   * @param id 식별번호
   * @return
   */
  public CttBinDto getSupport(UUID id) {
    return repositorySupport.get(id);
  }

  public UUID getId(CttBinForm.Input.GetId in) {
    QViCttBin viCttBin = QViCttBin.viCttBin;

    BooleanBuilder builder = new BooleanBuilder();
    builder.and(viCttBin.cttId.eq(in.getCttId()));
    builder.and(viCttBin.binVal.eq(in.getBinVal()));

    ViCttBin cttBin = repository.findOne(builder).orElse(null);

    if (cttBin == null) {
      return null;
    }

    return cttBin.getCttBinId();
  }

  /**
   * 콘텐츠버전 등록
   *
   * @param entity
   * @return
   */
  private ViCttBin add(ViCttBin entity) {
    return repository.save(entity);
  }

  public ViCttBin addCttBin(ViCttBin entity, UUID cttJudgeReqId) {
    ViCttJudgeReq cttJudgeReq =
        cttJudgeReqRepository
            .findOne(
                new BooleanBuilder(QViCttJudgeReq.viCttJudgeReq.cttJudgeReqId.eq(cttJudgeReqId)))
            .orElse(null);

    entity.setCttId(cttJudgeReq.getCttId());
    ViCttBin cttBin = this.add(entity);

    // 콘텐츠버전식별번호 업데이트
    cttJudgeReq.setCttBinId(cttBin.getCttBinId());

    return cttBin;
  }

  /**
   * 콘텐츠버전 수정
   *
   * @param entity
   * @return
   */
  private ViCttBin modify(UUID id, ViCttBin entity) {
    return mapper.modify(entity, get(id));
  }

  public ViCttBin modifyCttBin(UUID id, ViCttBin entity) {
    return this.modify(id, entity);
  }

  /**
   * 콘텐츠버전 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    repository.deleteById(id);
  }

  /** 콘텐츠버전 업데이트 */
  public void update(UUID id) {
    ViCttBin cttBin = get(id);

    List<ViCttBin> list = this.getList(new BooleanBuilder(QViCttBin.viCttBin.activeYn.eq("Y")));
    for (ViCttBin item : list) {
      item.setActiveYn("N");
    }

    cttBin.setActiveYn("Y");
  }

  /**
   * 심사 대기중인 바이너리 조회
   *
   * @return
   */
  public ViCttBin getJudgeWaitingBin(UUID cttId) {
    QViCttBin qViCttBin = QViCttBin.viCttBin;
    ViCttBin result =
        repository
            .findOne(
                new BooleanBuilder(
                    qViCttBin
                        .cttId
                        .eq(cttId)
                        .and(qViCttBin.binFileStatusCode.eq(Code.CT_008_001.getCode()))))
            .orElse(null);
    return result;
  }
}
