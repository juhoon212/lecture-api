package twentyoz.viven.feature.mb.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import twentyoz.viven.feature.mb.model.MbMbrEmailAuthDto;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.mb.model.QMbMbrEmailAuth;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MbMbrEmailAuthRepositorySupport extends QuerydslRepositorySupport {

  public MbMbrEmailAuthRepositorySupport() {
    super(MbMbrEmailAuthRepositorySupport.class);
  }

  /**
   * 이메일 인증 조회(다건)
   *
   * @param predicate
   * @return
   */
  public List<MbMbrEmailAuthDto> getList(Predicate predicate) {
    final JPQLQuery<MbMbrEmailAuthDto> query = getQuery(predicate);
    return query.fetch();
  }

  /** 이메일 인증 Query(with 회원) */
  private JPQLQuery<MbMbrEmailAuthDto> getQuery(Predicate predicate) {
    QMbMbrEmailAuth qMbMbrEmailAuth = QMbMbrEmailAuth.mbMbrEmailAuth; // 이메일 인증
    QMbMbr qMbMbr = QMbMbr.mbMbr; // 회원

    final JPQLQuery<MbMbrEmailAuthDto> query =
        from(qMbMbrEmailAuth)
            .select(
                Projections.constructor(
                    MbMbrEmailAuthDto.class,
                    qMbMbrEmailAuth.mbrEmailAuthId,
                    qMbMbrEmailAuth.authVal,
                    qMbMbrEmailAuth.authEndDt,
                    qMbMbrEmailAuth.authComplYn,
                    qMbMbr.mbrId,
                    qMbMbr.mbrTypeCode,
                    qMbMbr.mbrStatusCode,
                    qMbMbr.loginId,
                    qMbMbr.mbrName,
                    qMbMbr.email))
            .leftJoin(qMbMbr)
            .on(qMbMbrEmailAuth.mbrId.eq(qMbMbr.mbrId))
            .where(predicate)
            .orderBy(qMbMbrEmailAuth.regDt.desc());
    return query;
  }
}
