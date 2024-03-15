package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.vi.model.QViLectureQuest;
import twentyoz.viven.feature.vi.model.QViLectureQuestResult;
import twentyoz.viven.feature.vi.model.ViLectureQuestResultDto;

import java.util.List;
import java.util.UUID;

import static twentyoz.viven.feature.vi.model.QViLectureQuest.*;
import static twentyoz.viven.feature.vi.model.QViLectureQuestResult.*;

@Repository
public class ViLectureQuestResultRepositorySupport extends QuerydslRepositorySupport {
    public ViLectureQuestResultRepositorySupport() {
        super(ViLectureRefRepositorySupport.class);
    }


    public List<ViLectureQuestResultDto> getSupportList(Predicate predicate) {
        final JPQLQuery<ViLectureQuestResultDto> query = getQuery(predicate);

        return query.fetch();
    }

    public List<ViLectureQuestResultDto> getNoAnswerSupportList(Predicate predicate) {
        final JPQLQuery<ViLectureQuestResultDto> query = getNoAnswerQuery(predicate);

        return query.fetch();
    }

    private JPQLQuery<ViLectureQuestResultDto> getQuery(Predicate predicate) {

        final JPQLQuery<ViLectureQuestResultDto> query =

                from(viLectureQuestResult)
                        .select(
                                Projections.fields(
                                        ViLectureQuestResultDto.class,
                                        viLectureQuestResult.lectureQuestResultId,
                                        viLectureQuestResult.lectureId,
                                        viLectureQuestResult.lectureQuestResultId,
                                        viLectureQuestResult.mbrAnswer,
                                        viLectureQuest.choiceCont,
                                        viLectureQuest.score,
                                        viLectureQuest.questTypeCode,
                                        viLectureQuestResult.mbrId,
                                        viLectureQuestResult.mbrScore,
                                        viLectureQuestResult.modDt,
                                        viLectureQuestResult.modId,
                                        viLectureQuestResult.regDt,
                                        viLectureQuestResult.regId,
                                        viLectureQuest.lectureQuestId,
                                        viLectureQuest.lectureExamId,
                                        viLectureQuest.examItemId,
                                        viLectureQuest.questName,
                                        viLectureQuest.questCont,
                                        viLectureQuest.questFileGroupId,
                                        viLectureQuest.answer,
                                        viLectureQuest.goldenbellYn,
                                        viLectureQuest.sortOrd
                                ))
                        .leftJoin(viLectureQuest)
                        .on(viLectureQuestResult.lectureQuestId.eq(viLectureQuest.lectureQuestId))
                        .where(predicate);


        return query;
    }

    private JPQLQuery<ViLectureQuestResultDto> getNoAnswerQuery(Predicate predicate) {

        final JPQLQuery<ViLectureQuestResultDto> query =

                from(viLectureQuestResult)
                        .select(
                                Projections.fields(
                                        ViLectureQuestResultDto.class,
                                        viLectureQuestResult.lectureQuestResultId,
                                        viLectureQuestResult.lectureId,
                                        viLectureQuestResult.lectureQuestResultId,
                                        viLectureQuestResult.mbrAnswer,
                                        viLectureQuest.choiceCont,
                                        viLectureQuest.score,
                                        viLectureQuest.questTypeCode,
                                        viLectureQuestResult.mbrId,
                                        viLectureQuestResult.mbrScore,
                                        viLectureQuestResult.modDt,
                                        viLectureQuestResult.modId,
                                        viLectureQuestResult.regDt,
                                        viLectureQuestResult.regId,
                                        viLectureQuest.lectureQuestId,
                                        viLectureQuest.lectureExamId,
                                        viLectureQuest.examItemId,
                                        viLectureQuest.questName,
                                        viLectureQuest.questCont,
                                        viLectureQuest.questFileGroupId,
                                        viLectureQuest.goldenbellYn,
                                        viLectureQuest.sortOrd
                                ))
                        .leftJoin(viLectureQuest)
                        .on(viLectureQuestResult.lectureQuestId.eq(viLectureQuest.lectureQuestId))
                        .where(predicate);


        return query;
    }

//    private JPQLQuery<ViLectureQuestResultDto> getNoAnswerQuery(Predicate predicate) {
//
//        final JPQLQuery<ViLectureQuestResultDto> query =
//
//                from(viLectureQuestResult)
//                        .select(
//                                Projections.fields(
//                                        ViLectureQuestResultDto.class,
//                                        viLectureQuestResult.lectureQuestResultId,
//                                        viLectureQuestResult.lectureId,
//                                        viLectureQuestResult.lectureQuestResultId,
//                                        viLectureQuestResult.mbrAnswer,
//                                        viLectureQuest.choiceCont,
//                                        viLectureQuest.score,
//                                        viLectureQuest.questTypeCode,
//                                        viLectureQuestResult.mbrId,
//                                        viLectureQuestResult.mbrScore,
//                                        viLectureQuestResult.modDt,
//                                        viLectureQuestResult.modId,
//                                        viLectureQuestResult.regDt,
//                                        viLectureQuestResult.regId,
//                                        viLectureQuest.lectureQuestId,
//                                        viLectureQuest.lectureExamId,
//                                        viLectureQuest.examItemId,
//                                        viLectureQuest.questName,
//                                        viLectureQuest.questCont,
//                                        viLectureQuest.questFileGroupId,
//                                        viLectureQuest.goldenbellYn,
//                                        viLectureQuest.sortOrd
//                                ))
//                        .leftJoin(viLectureQuest)
//                        .on(viLectureQuestResult.lectureQuestId.eq(viLectureQuest.lectureQuestId))
//                        .where(predicate);
//
//
//        return query;
//    }



}
