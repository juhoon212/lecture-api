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
import twentyoz.viven.feature.vi.model.QViLectureRef;
import twentyoz.viven.feature.vi.model.QViRef;
import twentyoz.viven.feature.vi.model.ViLectureRefDto;

import java.util.List;
import java.util.UUID;

@Repository
public class ViLectureRefRepositorySupport extends QuerydslRepositorySupport {
    public ViLectureRefRepositorySupport() {
        super(ViLectureRefRepositorySupport.class);
    }

    public List<ViLectureRefDto> getSupportList(Predicate predicate, UUID mbrId) {

        final JPQLQuery<ViLectureRefDto> query = getQuery(predicate, mbrId);
        return query.fetch();
    }

    public Page<ViLectureRefDto> getSupportPage(Predicate predicate, Pageable page, UUID mbrId) {
        final JPQLQuery<ViLectureRefDto> query = getQuery(predicate, mbrId)
                .offset((long) page.getPageNumber() * page.getPageSize())
                .limit(page.getPageSize());
        return new PageImpl<>(query.fetch(), page, query.fetch().size());
    }

    private JPQLQuery<ViLectureRefDto> getQuery(Predicate predicate, UUID mbrId) {
        QViRef qViRef = QViRef.viRef;
        QViLectureRef qViLectureRef = QViLectureRef.viLectureRef;
        QMbMbr qMbMbr = QMbMbr.mbMbr;

        final JPQLQuery<ViLectureRefDto> query =
                from(qViLectureRef)
                        .select(
                                Projections.constructor(
                                        ViLectureRefDto.class,
                                        qViLectureRef.lectureRefId,
                                        qViLectureRef.lectureId,
                                        qViLectureRef.refId,
                                        qViLectureRef.refName,
                                        qViLectureRef.refTypeCode,
                                        qViLectureRef.refLink,
                                        qViLectureRef.attachFileGroupId,
                                        qViLectureRef.descCont,
                                        qViLectureRef.delYn,
                                        qViLectureRef.regId,
                                        qViLectureRef.regDt,
                                        qViLectureRef.modId,
                                        qViLectureRef.modDt,
                                        qMbMbr.nickname
                                        ))
                        .leftJoin(qMbMbr)
                        .on(qMbMbr.mbrId.eq(mbrId))
                        .where(predicate)
                        .orderBy(qViLectureRef.regDt.desc());

        return query;
    }
}
