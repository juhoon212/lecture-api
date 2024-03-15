package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.sy.model.SyAttachFile;
import twentyoz.viven.feature.sy.service.AttachFileService;
import twentyoz.viven.feature.vi.mapper.ViCttJudgeReqMapper;
import twentyoz.viven.feature.vi.model.CttJudgeReqDto;
import twentyoz.viven.feature.vi.model.QViCttJudgeReq;
import twentyoz.viven.feature.vi.model.ViCtt;
import twentyoz.viven.feature.vi.model.ViCttBin;
import twentyoz.viven.feature.vi.model.ViCttJudgeHist;
import twentyoz.viven.feature.vi.model.ViCttJudgeReq;
import twentyoz.viven.feature.vi.repository.ViCttJudgeReqRepository;
import twentyoz.viven.feature.vi.repository.ViCttJudgeReqRepositorySupport;

/** 콘텐츠심사요청 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class CttJudgeReqService {

  private final CttJudgeHistService cttJudgeHistService;
  private final ViCttJudgeReqRepository repository;
  private final ViCttJudgeReqMapper mapper;
  private final CttService cttService;
  private final CttBinService cttBinService;

  private final AttachFileService attachFileService;

  private final ViCttJudgeReqRepositorySupport repositorySupport;

  /**
   * 콘텐츠심사요청 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViCttJudgeReq> getList(Predicate search) {
    return (List<ViCttJudgeReq>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 콘텐츠심사요청 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViCttJudgeReq> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 콘텐츠심사요청 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViCttJudgeReq get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViCttJudgeReq.viCttJudgeReq.cttJudgeReqId.eq(id)))
        .orElse(null);
  }

  /**
   * 콘텐츠심사요청 등록
   *
   * @param entity
   * @return
   */
  private ViCttJudgeReq add(ViCttJudgeReq entity) {
    return repository.save(entity);
  }

  /**
   * 콘텐츠심사요청 수정
   *
   * @param entity
   * @return
   */
  private ViCttJudgeReq modify(UUID id, ViCttJudgeReq entity) {
    return mapper.modify(entity, get(id));
  }

  private void remove(UUID id) {
    repository.deleteById(id);
  }

  /**
   * 콘텐츠심사요청 목록 (w. 콘텐츠바이너리)
   *
   * @param search
   * @return
   */
  public List<CttJudgeReqDto> getSupportList(Predicate search) {
    return repositorySupport.getList(search);
  }

  /**
   * 콘텐츠심사요청 페이징 목록 (w. 콘텐츠바이너리)
   *
   * @param search
   * @return
   */
  public Page<CttJudgeReqDto> getSupportPage(Predicate search, Pageable page) {
    return repositorySupport.getPage(search, page);
  }

  /**
   * 콘텐츠심사요청 조회 (w. 콘텐츠바이너리)
   *
   * @param id
   * @param reqUserId 요청사용자식별번호
   * @return
   */
  public CttJudgeReqDto getSupport(UUID id, UUID reqUserId) {
    return repositorySupport.get(id, reqUserId);
  }

  /**
   * 콘텐츠심사요청 등록
   *
   * @param entity
   */
  public ViCttJudgeReq addCttJudgeReq(UUID mbrId, ViCttJudgeReq entity, ViCttBin bin)
      throws IllegalAccessException {
    if (mbrId == null) {
      throw new IllegalAccessException("잘못된 접근입니다.");
    }
    entity.setReqUserId(mbrId);

    // 콘텐츠전시명이 없는 경우 콘텐츠명으로 등록되도록 처리
    if (entity.getCttDpName() == null) {
      entity.setCttDpName(entity.getCttName());
    }

    // 콘텐츠식별번호가 없는 경우 콘텐츠 등록 처리
    if (entity.getCttId() == null) {
      ViCtt ctt = cttService.addCtt(mapper.toViCtt(entity));
      entity.setCttId(ctt.getCttId());
    }

    // 콘텐츠심사요청 등록
    ViCttJudgeReq cttJudgeReq = this.add(entity);

    // 콘텐츠바이너리 등록
    if (bin != null && bin.getAttachFileGroupId() != null) {
      ViCttBin cttBin = cttBinService.addCttBin(bin, cttJudgeReq.getCttJudgeReqId());
      cttJudgeReq.setCttBinId(cttBin.getCttBinId());
    }

    return cttJudgeReq;
  }

  /**
   * 콘텐츠심사요청 수정
   *
   * @param entity
   * @return
   */
  public ViCttJudgeReq modifyCttJudgeReq(
      UUID id, UUID mbrId, ViCttJudgeReq entity, ViCttBin bin, Boolean binDelYn)
      throws IllegalAccessException {
    if (mbrId == null) {
      throw new IllegalAccessException("잘못된 접근입니다.");
    }

    entity.setCttJudgeReqId(id);

    // 콘텐츠심사요청 수정
    ViCttJudgeReq viCttJudgeReq = this.modify(id, entity);

    // 바이너리 삭제
    if (binDelYn != null && binDelYn) {
      if (viCttJudgeReq.getCttBinId() == null) {
        throw new IllegalAccessException("삭제할 바이너리 파일이 없습니다.");
      }

      ViCttBin cttBin = cttBinService.get(viCttJudgeReq.getCttBinId());
      if (cttBin == null) {
        throw new IllegalAccessException("존재하지 않는 바이너리 파일입니다.");
      }

      cttBinService.remove(cttBin.getCttBinId());
      // 콘텐츠버전 업데이트
      viCttJudgeReq.setCttBinId(null);
    } else if (bin != null) {
      // 바이너리파일 등록
      if (viCttJudgeReq.getCttBinId() == null) {
        ViCttBin cttBin = cttBinService.addCttBin(bin, id);
        viCttJudgeReq.setCttBinId(cttBin.getCttBinId());
      }

      // 바이너리파일 수정
      if (viCttJudgeReq.getCttBinId() != null) {
        cttBinService.modifyCttBin(viCttJudgeReq.getCttBinId(), bin);
      }
    }

    return viCttJudgeReq;
  }

  /**
   * 콘텐츠심사요청 삭제
   *
   * @param id 식별번호
   */
  public void removeCttJudgeReq(UUID id) throws IllegalAccessException {
    ViCttJudgeReq viCttJudgeReq = get(id);

    // 심사상태가 임시저장인 경우만 삭제 가능
    if (!viCttJudgeReq.getJudgeStatusCode().equals(Code.CT_003_006.getCode())) {
      throw new IllegalStateException("임시저장중인 콘텐츠심사만 삭제 가능합니다.");
    }

    // 콘텐츠심사요청 삭제
    this.remove(id);

    // 콘텐츠 삭제
    ViCtt viCtt = cttService.get(viCttJudgeReq.getCttId());
    if (Code.CT_002_001.getCode().equals(viCtt.getSellStatusCode())
        && "N".equals(viCtt.getDpYn())) {
      cttService.remove(viCttJudgeReq.getCttId());
    }

    // 콘텐츠 버전 삭제
    if (viCttJudgeReq.getCttBinId() != null) {
      cttBinService.remove(viCttJudgeReq.getCttBinId());
    }
  }

  /**
   * 콘텐츠 심사 승인요청
   *
   * @param entity
   * @return
   */
  public ViCttJudgeReq approvalRequest(UUID mbrId, ViCttJudgeReq entity)
      throws IllegalAccessException {
    if (mbrId == null) {
      throw new IllegalAccessException("잘못된 접근입니다.");
    }

    // 콘텐츠심사요청식별번호가 있는 경우에만 심사요청 가능
    if (entity.getCttJudgeReqId() == null) {
      throw new IllegalStateException("기본정보를 등록 후 심사를 요청할 수 있습니다.");
    }

    // 콘텐츠심사요청 조회
    ViCttJudgeReq cttJudgeReq = this.get(entity.getCttJudgeReqId());
    if (cttJudgeReq == null) {
      throw new IllegalAccessException("잘못된 접근입니다.");
    }

    // 삼사상태가 임시저장인 경우에만 심사 요청 가능
    if (!Code.CT_003_006.getCode().equals(cttJudgeReq.getJudgeStatusCode())) {
      throw new IllegalStateException("이미 심사가 진행중입니다.");
    }

    // 콘텐츠심사요청에 연결된 바이너리식별번호 설정
    entity.setCttBinId(cttJudgeReq.getCttBinId());

    // validation
    this.validation(entity);

    entity.setJudgeStatusCode(Code.CT_003_001.getCode());
    entity.setReqUserId(mbrId);
    entity.setReqDt(new DateTime());
    entity.setProcDt(new DateTime());

    // 심사요청 등록
    ViCttJudgeReq result = this.modify(entity.getCttJudgeReqId(), entity);

    // 심사이력 등록
    this.addHist(result.getCttJudgeReqId(), Code.CT_003_001.getCode());

    return result;
  }

  /**
   * 콘텐츠 심사 승인요청 수정
   *
   * @param entity
   * @return
   */
  public ViCttJudgeReq approvalRequestModify(UUID id, UUID mbrId, ViCttJudgeReq entity)
      throws IllegalAccessException {
    if (mbrId == null) {
      throw new IllegalAccessException("잘못된 접근입니다.");
    }

    // 콘텐츠심사요청식별번호가 있는 경우에만 심사요청 가능
    if (entity.getCttJudgeReqId() == null) {
      throw new IllegalStateException("기본정보를 등록 후 심사를 요청할 수 있습니다.");
    }

    // 콘텐츠심사요청 조회
    ViCttJudgeReq cttJudgeReq = this.get(entity.getCttJudgeReqId());
    if (cttJudgeReq == null) {
      throw new IllegalAccessException("잘못된 접근입니다.");
    }

    // 삼사상태가 심사요청인 경우에만 심사 수정 가능
    if (!Code.CT_003_001.getCode().equals(cttJudgeReq.getJudgeStatusCode())) {
      throw new IllegalStateException("심사요청 상태가 아닙니다.");
    }

    // 콘텐츠심사요청에 연결된 바이너리식별번호 설정
    entity.setCttBinId(cttJudgeReq.getCttBinId());

    // validation
    this.validation(entity);

    return this.modify(id, entity);
  }

  /**
   * 콘텐츠 심사 승인요청 취소
   *
   * @param id 식별번호
   */
  public void approvalRequestCancel(UUID id) {
    ViCttJudgeReq cttJudgeReq = get(id);

    if (!cttJudgeReq.getJudgeStatusCode().equals(Code.CT_003_001.getCode())) {
      throw new IllegalStateException("심사요청인 콘텐츠만 취소 가능합니다.");
    }

    cttJudgeReq.setJudgeStatusCode(Code.CT_003_003.getCode());
    cttJudgeReq.setProcDt(new DateTime());

    // 심사이력 등록
    this.addHist(id, Code.CT_003_003.getCode());
  }

  /**
   * 심사이력 등록
   *
   * @param id
   */
  public void addHist(UUID id, String judgeStatusCode) {
    ViCttJudgeReq cttJudgeReq = get(id);
    ViCttJudgeHist hist = mapper.toViCttJudgeHist(cttJudgeReq);
    hist.setJudgeStatusCode(judgeStatusCode);

    // 바이너리파일 정보 조회
    if (cttJudgeReq.getCttBinId() != null) {
      ViCttBin bin = cttBinService.get(cttJudgeReq.getCttBinId());
      hist.setBinImgGroupId(bin.getAttachImgGroupId());
      hist.setBinFileGroupId(bin.getAttachFileGroupId());
    }
    cttJudgeHistService.addCttJudgeHist(hist);
  }

  /**
   * 활성화된 콘텐츠 심사요청 조회
   *
   * @param cttId 콘텐츠식별번호
   * @return
   */
  public ViCttJudgeReq getActiveCttJudgeReq(UUID cttId) {
    QViCttJudgeReq qViCttJudgeReq = QViCttJudgeReq.viCttJudgeReq;

    BooleanBuilder statusBuilder = new BooleanBuilder();
    statusBuilder.or(qViCttJudgeReq.judgeStatusCode.eq(Code.CT_003_001.getCode()));
    statusBuilder.or(qViCttJudgeReq.judgeStatusCode.eq(Code.CT_003_002.getCode()));
    statusBuilder.or(qViCttJudgeReq.judgeStatusCode.eq(Code.CT_003_006.getCode()));

    List<ViCttJudgeReq> list =
        this.getList(new BooleanBuilder(qViCttJudgeReq.cttId.eq(cttId).and(statusBuilder)));

    // 활성화된 상태의 심사요청이 있는 경우 return
    if (list.size() > 0) {
      return list.get(0);
    }

    return null;
  }

  /**
   * 콘텐츠심사요청 데이터 validation
   *
   * @param entity
   */
  public void validation(ViCttJudgeReq entity) {
    // 아바타인 경우 아바타 이미지 validation
    if (Code.CT_001_AVT.equals(entity.getCttTypeCode())) {
      if (entity.getAvtImgGroupId() == null) {
        throw new IllegalStateException("아바타 이미지 등록해주세요.");
      } else {
        List<SyAttachFile> list =
            attachFileService.getAttachFilesByGroupId(entity.getAvtImgGroupId());
        int pcount = 0, bcount = 0;
        if (list.size() > 0) {
          for (SyAttachFile file : list) {
            if ("profile".equals(file.getAttachDivVal())) {
              pcount++;
            }
            if ("body".equals(file.getAttachDivVal())) {
              bcount++;
            }
          }

          if (pcount < 1) {
            throw new IllegalStateException("아바타프로필을 등록해주세요.");
          }

          if (bcount < 1) {
            throw new IllegalStateException("아바타 전신이미지를 등록해주세요.");
          }
        }
      }
    }

    // 콘텐츠미리보기 validation
    if (entity.getAttachFileGroupId() != null) {
      List<SyAttachFile> list =
          attachFileService.getAttachFilesByGroupId(entity.getAttachFileGroupId());
      if (list.size() == 0) {
        throw new IllegalStateException("콘텐츠 미리보기를 등록해주세요.");
      }
    }

    // 심사유형이 바이너리파일이거나 기본정보+바이너리파일인 경우 심사대기중인 콘텐츠바이너리식별번호 조회
    if (Code.CT_004_FILE.getCode().equals(entity.getJudgeTypeCode())
        || Code.CT_004_ALL.getCode().equals(entity.getJudgeTypeCode())) {
      // 콘텐츠 바이너리 파일이 없으면 심사 불가
      if (entity.getCttBinId() == null) {
        throw new IllegalStateException("바이너리 파일을 등록 후 심사를 요청할 수 있습니다.");
      }
    }
  }
}
