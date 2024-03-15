package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import java.util.UUID;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.vi.model.FrndReqDto;
import twentyoz.viven.feature.vi.model.QViFrndReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViFrndReqRepositorySupport extends QuerydslRepositorySupport {

  public ViFrndReqRepositorySupport() {
    super(ViFrndReqRepositorySupport.class);
  }

  /**
   * 요청 목록 조회
   *
   * @return
   */
  public List<FrndReqDto> getReqList(UUID mbrId) {
    final JPQLQuery<FrndReqDto> query = getReqQuery(mbrId);
    return query.fetch();
  }

  /**
   * 요청 페이징 조회
   *
   * @param page
   * @return
   */
  public Page<FrndReqDto> getReqPage(Pageable page, UUID mbrId) {
    final JPQLQuery<FrndReqDto> query =
        getReqQuery(mbrId)
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetchCount());
  }

  /**
   * 요청대기 목록 조회
   *
   * @return
   */
  public List<FrndReqDto> getResList(UUID mbrId) {
    final JPQLQuery<FrndReqDto> query = getResQuery(mbrId);
    return query.fetch();
  }

  /**
   * 요청대기 페이징 조회
   *
   * @param page
   * @return
   */
  public Page<FrndReqDto> getResPage(Pageable page, UUID mbrId) {
    final JPQLQuery<FrndReqDto> query =
        getResQuery(mbrId)
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetchCount());
  }

  /** 친구요청 쿼리 */
  private JPQLQuery<FrndReqDto> getReqQuery(UUID mbrId) {
    QViFrndReq qViFrndReq = QViFrndReq.viFrndReq;
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    final JPQLQuery<FrndReqDto> query =
        from(qViFrndReq)
            .select(
                Projections.constructor(
                    FrndReqDto.class,
                    qViFrndReq.frndReqId,
                    qViFrndReq.reqMbrId,
                    qViFrndReq.resMbrId,
                    qViFrndReq.reqStatusCode,
                    qViFrndReq.regId,
                    qViFrndReq.regDt,
                    qViFrndReq.modId,
                    qViFrndReq.modDt,
                    qMbMbr.mbrId,
                    qMbMbr.nickname,
                    qMbMbr.avtBodyFilePath,
                    qMbMbr.avtProfileFilePath,
                    qMbMbr.profileBgColor,
                    qMbMbr.onlineYn,
                    qMbMbr.lastLoginDt))
            .where(QViFrndReq.viFrndReq.reqMbrId.eq(mbrId))
            .where(QViFrndReq.viFrndReq.reqStatusCode.eq(Code.FRND_001_001.getCode()))
            .leftJoin(qMbMbr)
            .on(qMbMbr.mbrId.eq(qViFrndReq.resMbrId))
            .orderBy(qViFrndReq.regDt.desc());
    return query;
  }

  /** 친구요청 대기 쿼리 */
  private JPQLQuery<FrndReqDto> getResQuery(UUID mbrId) {
    QViFrndReq qViFrndReq = QViFrndReq.viFrndReq;
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    final JPQLQuery<FrndReqDto> query =
        from(qViFrndReq)
            .select(
                Projections.constructor(
                    FrndReqDto.class,
                    qViFrndReq.frndReqId,
                    qViFrndReq.reqMbrId,
                    qViFrndReq.resMbrId,
                    qViFrndReq.reqStatusCode,
                    qViFrndReq.regId,
                    qViFrndReq.regDt,
                    qViFrndReq.modId,
                    qViFrndReq.modDt,
                    qMbMbr.mbrId,
                    qMbMbr.nickname,
                    qMbMbr.avtBodyFilePath,
                    qMbMbr.avtProfileFilePath,
                    qMbMbr.profileBgColor,
                    qMbMbr.onlineYn,
                    qMbMbr.lastLoginDt))
            .where(QViFrndReq.viFrndReq.resMbrId.eq(mbrId))
            .where(QViFrndReq.viFrndReq.reqStatusCode.eq(Code.FRND_001_001.getCode()))
            .leftJoin(qMbMbr)
            .on(qMbMbr.mbrId.eq(qViFrndReq.reqMbrId))
            .orderBy(qViFrndReq.regDt.desc());
    return query;
  }
}
