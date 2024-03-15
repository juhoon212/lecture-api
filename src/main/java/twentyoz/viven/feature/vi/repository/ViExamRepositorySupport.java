package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.vi.model.QViExam;
import twentyoz.viven.feature.vi.model.QViExamItem;
import twentyoz.viven.feature.vi.model.ViExamDto;

import java.util.List;

@Repository
public class ViExamRepositorySupport extends QuerydslRepositorySupport {
    public ViExamRepositorySupport() {
        super(ViExamRepositorySupport.class);
    }

    public List<ViExamDto> getList(Predicate predicate) {
        final JPQLQuery<ViExamDto> query = getQuery(predicate);
        return query.fetch();
    }

    public Page<ViExamDto> getPage(Predicate predicate, Pageable page) {
        final JPQLQuery<ViExamDto> query = getQuery(predicate);
        return new PageImpl<>(query.fetch(), page, query.fetch().size());
    }

    public JPQLQuery<ViExamDto> getQuery(Predicate predicate) {

        QViExam qViExam = QViExam.viExam;
        QViExamItem qViExamItem = QViExamItem.viExamItem;

        final JPQLQuery<ViExamDto> query =
                from(qViExam)
                        .select(
                                Projections.constructor(
                                        ViExamDto.class,
                                        qViExam.examId,
                                        qViExam.mbrId,
                                        qViExam.examName,
                                        qViExam.examTypeCode,
                                        qViExam.descCont,
                                        qViExam.keyword,
                                        qViExam.delYn,
                                        qViExam.examPeriod,
                                        qViExam.useYn,
                                        qViExamItem.score.sum()
                                        ))
                        .leftJoin(qViExamItem)
                        .on(qViExam.examId.eq(qViExamItem.examId))
                        .where(predicate)
                        .groupBy(qViExam.examId)
                        .orderBy(qViExam.regDt.desc());
        return query;
    }





}
