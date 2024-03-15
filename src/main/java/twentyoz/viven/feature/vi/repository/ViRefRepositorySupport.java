package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.vi.model.*;
import twentyoz.viven.feature.vi.model.QViRef;

import java.util.List;
import java.util.UUID;

@Repository
public class ViRefRepositorySupport extends QuerydslRepositorySupport {

    public ViRefRepositorySupport() {
        super(ViRefRepositorySupport.class);
    }

    public List<ViRefDto> getList(Predicate predicate, UUID mbrId) {
        final JPQLQuery<ViRefDto> query = getQuery(predicate, mbrId);
        return query.fetch();
    }

    public Page<ViRefDto> getPage(Predicate predicate, Pageable page, UUID mbrId) {
        final JPQLQuery<ViRefDto> query =
                getQuery(predicate, mbrId)
                        .offset(((long) page.getPageNumber() * page.getPageSize()))
                        .limit(page.getPageSize());
        return new PageImpl<>(query.fetch(), page, query.fetchCount());
    }

    private JPQLQuery<ViRefDto> getQuery(Predicate predicate, UUID mbrId) {
        QMbMbr qMbMbr = QMbMbr.mbMbr;
        QViRef qViRef = QViRef.viRef;

        final JPQLQuery<ViRefDto> query =
                from(qViRef)
                        .select(
                                Projections.constructor(
                                        ViRefDto.class,
                                        qViRef.refId,
                                        qViRef.mbrId,
                                        qViRef.refName,
                                        qViRef.refTypeCode,
                                        qViRef.refLink,
                                        qViRef.attachFileGroupId,
                                        qViRef.descCont,
                                        qViRef.delYn,
                                        qMbMbr.nickname,
                                        qViRef.regId,
                                        qViRef.regDt,
                                        qViRef.modId,
                                        qViRef.modDt))
                        .join(qMbMbr)
                        .on(qViRef.mbrId.eq(qMbMbr.mbrId))
                        .where(predicate, qViRef.mbrId.eq(mbrId))
                        .orderBy(qViRef.modDt.desc());
        return query;
    }
}
