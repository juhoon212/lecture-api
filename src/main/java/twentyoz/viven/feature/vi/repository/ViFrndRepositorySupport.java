package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import java.util.UUID;
import twentyoz.viven.feature.mb.model.MbrFrndDto;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.vi.model.FrndDto;
import twentyoz.viven.feature.vi.model.QViFrnd;
import twentyoz.viven.feature.vi.model.QViFrndReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViFrndRepositorySupport extends QuerydslRepositorySupport {

  public ViFrndRepositorySupport() {
    super(ViFrndRepositorySupport.class);
  }

  /**
   * 내 친구 목록 조회
   *
   * @return
   */
  public List<FrndDto> getList(Predicate predicate, UUID mbrId) {
    final JPQLQuery<FrndDto> query = getQuery(predicate, mbrId);
    return query.fetch();
  }

  /**
   * 내 친구 페이징 조회
   *
   * @param page
   * @return
   */
  public Page<FrndDto> getPage(Predicate predicate, Pageable page, UUID mbrId) {
    final JPQLQuery<FrndDto> query =
        getQuery(predicate, mbrId)
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetchCount());
  }

  /** 내 친구 쿼리 */
  private JPQLQuery<FrndDto> getQuery(Predicate predicate, UUID mbrId) {
    QViFrnd qViFrnd = QViFrnd.viFrnd;
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    final JPQLQuery<FrndDto> query =
        from(qViFrnd)
            .select(
                Projections.constructor(
                    FrndDto.class,
                    qViFrnd.frndId,
                    qViFrnd.mbrId,
                    qViFrnd.frndMbrId,
                    qViFrnd.regId,
                    qViFrnd.regDt,
                    qMbMbr.nickname,
                    qMbMbr.avtBodyFilePath,
                    qMbMbr.avtProfileFilePath,
                    qMbMbr.profileBgColor,
                    qMbMbr.onlineYn,
                    qMbMbr.lastLoginDt))
            .leftJoin(qMbMbr)
            .on(qMbMbr.mbrId.eq(qViFrnd.frndMbrId))
            .where(qViFrnd.mbrId.eq(mbrId))
            .where(predicate)
            .orderBy(qMbMbr.nickname.asc());
    return query;
  }

  /**
   * 친구 검색 목록 조회
   *
   * @param predicate
   * @return
   */
  public List<MbrFrndDto> getSearchList(Predicate predicate, UUID id) {
    final JPQLQuery<MbrFrndDto> query = getSearchQuery(predicate, id);
    return query.fetch();
  }

  /**
   * 친구 검색 페이징 조회
   *
   * @param predicate
   * @param page
   * @param id
   * @return
   */
  public Page<MbrFrndDto> getSearchPage(Predicate predicate, Pageable page, UUID id) {
    final JPQLQuery<MbrFrndDto> query =
        getSearchQuery(predicate, id)
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetchCount());
  }

  /** 친구 검색 쿼리 */
  private JPQLQuery<MbrFrndDto> getSearchQuery(Predicate predicate, UUID id) {
    QViFrndReq qViFrndReq = QViFrndReq.viFrndReq;
    QViFrnd qViFrnd = QViFrnd.viFrnd;
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    BooleanBuilder builder = new BooleanBuilder();
    builder.and(qMbMbr.delYn.eq("N"));
    builder.and(qMbMbr.useYn.eq("Y"));

    BooleanBuilder frndBuilder = new BooleanBuilder();
    frndBuilder.and(qViFrnd.mbrId.eq(id));
    frndBuilder.and(qViFrnd.frndMbrId.eq(qMbMbr.mbrId));
    BooleanBuilder frndReqBuilder = new BooleanBuilder();
    frndReqBuilder.and(qViFrndReq.resMbrId.eq(qMbMbr.mbrId));
    frndReqBuilder.and(qViFrndReq.reqMbrId.eq(id));

    final JPQLQuery<MbrFrndDto> query =
        from(qMbMbr)
            .select(
                Projections.constructor(
                    MbrFrndDto.class,
                    qMbMbr.mbrId,
                    qMbMbr.nickname,
                    qMbMbr.lastLoginDt,
                    qMbMbr.onlineYn,
                    qMbMbr.profileBgColor,
                    qMbMbr.avtBodyFilePath,
                    qMbMbr.avtProfileFilePath,
                    qViFrndReq.frndReqId,
                    qViFrndReq.resMbrId,
                    qViFrndReq.reqMbrId,
                    qViFrndReq.reqStatusCode,
                    qViFrnd.frndId))
            .leftJoin(qViFrndReq)
            .on(frndReqBuilder)
            .leftJoin(qViFrnd)
            .on(frndBuilder)
            .where(qMbMbr.mbrId.notIn(id))
            .where(predicate, builder)
            .orderBy(qMbMbr.nickname.asc());
    return query;
  }
}
