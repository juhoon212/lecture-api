package twentyoz.viven.feature.vi.service;

import com.querydsl.core.types.Predicate;
import java.util.List;
import twentyoz.viven.feature.vi.mapper.ViFrndReqHistMapper;
import twentyoz.viven.feature.vi.model.ViFrndReqHist;
import twentyoz.viven.feature.vi.repository.ViFrndReqHistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/** 친구요청이력 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class FrndReqHistService {

  private final ViFrndReqHistRepository repository;
  private final ViFrndReqHistMapper mapper;

  /**
   * 친구요청이력 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViFrndReqHist> getList(Predicate search) {
    return (List<ViFrndReqHist>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 친구요청이력 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViFrndReqHist> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 친구요청이력 등록
   *
   * @param entity
   * @return
   */
  public ViFrndReqHist add(ViFrndReqHist entity) {
    return repository.save(entity);
  }
}
