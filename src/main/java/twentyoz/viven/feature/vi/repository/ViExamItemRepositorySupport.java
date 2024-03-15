package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.vi.model.*;
import twentyoz.viven.feature.vi.model.QViExamQuest;

import java.util.List;

import static twentyoz.viven.feature.vi.model.QViExam.*;
import static twentyoz.viven.feature.vi.model.QViExamItem.*;
import static twentyoz.viven.feature.vi.model.QViExamQuest.*;

@Repository
public class ViExamItemRepositorySupport extends QuerydslRepositorySupport {


    public ViExamItemRepositorySupport() {
        super(ViExamRepositorySupport.class);
    }

    public List<ViExamItemDto> getSupportList(Predicate predicate) {
        JPQLQuery<ViExamItemDto> query = getQuery(predicate);
        return query.fetch();
    }

    public Page<ViExamItemDto> getSupportPage(Predicate predicate, Pageable page) {

        JPQLQuery<ViExamItemDto> query = getQuery(predicate)
                .offset((long) page.getPageSize() * page.getPageNumber())
                .limit(page.getPageSize());

        return new PageImpl<>(query.fetch(), page, query.fetch().size());

    }
    
    
    public JPQLQuery<ViExamItemDto> getQuery(Predicate predicate) {

        final JPQLQuery<ViExamItemDto> query =
                from(viExamItem)
                        .select(
                                Projections.constructor(
                                        ViExamItemDto.class,
                                        viExamItem.examItemId,
                                        viExamItem.examId,
                                        viExamItem.examQuestId,
                                        viExamItem.mbrId,
                                        viExamItem.score,
                                        viExamItem.sortOrd,
                                        viExamQuest.questName,
                                        viExamQuest.questTypeCode
                                ))
                        .leftJoin(viExamQuest)
                        .on(viExamItem.examQuestId.eq(viExamQuest.examQuestId))
                        .where(predicate)
                        .orderBy(viExamItem.sortOrd.asc());

        return query;
    }
    

            
}
