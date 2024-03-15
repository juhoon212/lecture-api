package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.vi.model.QViExam;
import twentyoz.viven.feature.vi.model.QViExamItem;
import twentyoz.viven.feature.vi.model.QViExamQuest;
import twentyoz.viven.feature.vi.model.ViExamQuestDto;

import java.util.List;
import java.util.UUID;

import static twentyoz.viven.feature.vi.model.QViExam.*;
import static twentyoz.viven.feature.vi.model.QViExamItem.*;
import static twentyoz.viven.feature.vi.model.QViExamQuest.*;

@Repository
public class ViExamQuestRepositorySupport extends QuerydslRepositorySupport {


    public ViExamQuestRepositorySupport() {
        super(ViExamQuestRepositorySupport.class);
    }

    public List<ViExamQuestDto> getExamQuestsByExamId(Predicate predicate) {

        JPQLQuery<ViExamQuestDto> query = getQuery(predicate);
        return query.fetch();
    }

    private JPQLQuery<ViExamQuestDto> getQuery(Predicate predicate
                                               ) {

        final JPQLQuery<ViExamQuestDto> query =
                from(viExam)
                        .select(
                                Projections.constructor(
                                        ViExamQuestDto.class,
                                        viExam.examId,
                                        viExamQuest.examQuestId,
                                        viExamQuest.mbrId,
                                        viExamQuest.questName,
                                        viExamQuest.questTypeCode,
                                        viExamQuest.questCont,
                                        viExamQuest.choiceCont,
                                        viExamQuest.answer,
                                        viExamQuest.goldenbellYn,
                                        viExamQuest.questFileGroupId,
                                        viExamQuest.keyword,
                                        viExamItem.sortOrd,
                                        viExamItem.examItemId,
                                        viExamItem.score
                                ))
                        .leftJoin(viExamItem)
                        .on(viExam.examId.eq(viExamItem.examId))
                        .leftJoin(viExamQuest)
                        .on(viExamItem.examQuestId.eq(viExamQuest.examQuestId))
                        .where(predicate);

        return query;
    }
}
