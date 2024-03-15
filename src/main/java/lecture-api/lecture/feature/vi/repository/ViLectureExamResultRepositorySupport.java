package twentyoz.viven.feature.vi.repository;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.vi.model.*;
import twentyoz.viven.feature.vi.model.QViLectureExamResult;

import java.util.List;
import java.util.UUID;

import static twentyoz.viven.feature.mb.model.QMbMbr.*;
import static twentyoz.viven.feature.vi.model.QViLectureExam.*;
import static twentyoz.viven.feature.vi.model.QViLectureExamResult.*;
import static twentyoz.viven.feature.vi.model.QViLectureQuest.*;
import static twentyoz.viven.feature.vi.model.QViLectureQuestResult.*;

@Repository
public class ViLectureExamResultRepositorySupport extends QuerydslRepositorySupport {


    public ViLectureExamResultRepositorySupport() {
        super(ViLectureExamResultRepositorySupport.class);
    }

    public List<ViLectureExamResultListDto> getSupportList(Predicate predicate) {
        JPQLQuery<ViLectureExamResultListDto> query = getSupportQuery(predicate);
        return query.fetch();
    }

    public ViLectureExamResultDto getSupport(Predicate predicate, UUID mbrId) {
        JPQLQuery<ViLectureExamResultDto> query = getQuery(predicate, mbrId);
        return query.fetchOne();
    }

    public ViLectureExamResultDto getSchedulerQuery(Predicate predicate) {
        JPQLQuery<ViLectureExamResultDto> query = getScheduleQuery(predicate);
        return query.fetchOne();
    }


    private JPQLQuery<ViLectureExamResultDto> getQuery(Predicate predicate, UUID mbrId) {

        final JPQLQuery<ViLectureExamResultDto> query =
                from(viLectureExamResult)
                        .select(
                                Projections.constructor(
                                        ViLectureExamResultDto.class,
                                        viLectureExam.examPeriod,
                                        viLectureQuestResult.mbrScore.sum().as("mbrScore"),
                                        viLectureQuest.score.sum().as("totalScore"),
                                        viLectureExamResult.lectureExamResultId,
                                        viLectureExamResult.examStartDt,
                                        viLectureExamResult.examEndDt,
                                        viLectureExamResult.mbrId,
                                        viLectureExamResult.regDt,
                                        viLectureExamResult.markingYn
                                ))
                        .leftJoin(viLectureQuestResult)
                        .on(viLectureExamResult.lectureExamResultId.eq(viLectureQuestResult.lectureExamResultId))
                        .leftJoin(viLectureQuest)
                        .on(viLectureQuestResult.lectureQuestId.eq(viLectureQuest.lectureQuestId))
                        .leftJoin(viLectureExam)
                        .on(viLectureQuest.lectureExamId.eq(viLectureExam.lectureExamId))
                        .leftJoin(mbMbr)
                        .on(viLectureExamResult.mbrId.eq(mbMbr.mbrId))
                        .where(predicate)
                        .groupBy(
                                viLectureExamResult.lectureExamResultId,
                                viLectureExam.examPeriod,
                                viLectureExamResult.examStartDt,
                                viLectureExamResult.examEndDt,
                                viLectureExamResult.mbrId,
                                viLectureExamResult.regDt,
                                viLectureExamResult.markingYn
                        );
        return query;
    }

    private JPQLQuery<ViLectureExamResultListDto> getSupportQuery(Predicate predicate) {

        final JPQLQuery<ViLectureExamResultListDto> query =
                from(viLectureExamResult)
                        .select(
                                Projections.fields(
                                        ViLectureExamResultListDto.class,
                                        viLectureExam.examId,
                                        viLectureExam.examName,
                                        viLectureExam.examTypeCode,
                                        viLectureQuestResult.mbrScore.sum().as("mbrScore"),
                                        viLectureExam.examPeriod,
                                        viLectureExam.descCont,
                                        mbMbr.nickname.as("nickName"),
                                        viLectureQuest.score.sum().as("totalScore"),
                                        viLectureExamResult.lectureExamResultId,
                                        viLectureExamResult.examStartDt,
                                        viLectureExamResult.examEndDt,
                                        viLectureExamResult.mbrId,
                                        viLectureExamResult.regDt,
                                        viLectureExamResult.markingYn
                                ))
                        .leftJoin(viLectureQuestResult)
                        .on(viLectureExamResult.lectureExamResultId.eq(viLectureQuestResult.lectureExamResultId))
                        .leftJoin(viLectureQuest)
                        .on(viLectureQuestResult.lectureQuestId.eq(viLectureQuest.lectureQuestId))
                        .leftJoin(viLectureExam)
                        .on(viLectureQuest.lectureExamId.eq(viLectureExam.lectureExamId))
                        .leftJoin(mbMbr)
                        .on(viLectureExamResult.mbrId.eq(mbMbr.mbrId))
                        .where(predicate)
                        .groupBy(
                                viLectureExam.examId,
                                viLectureExamResult.lectureExamResultId,
                                viLectureExam.examName,
                                viLectureExam.examTypeCode,
                                viLectureExam.examPeriod,
                                viLectureExam.descCont,
                                mbMbr.nickname,
                                viLectureExamResult.examStartDt,
                                viLectureExamResult.examEndDt,
                                viLectureExamResult.mbrId,
                                viLectureExamResult.regDt,
                                viLectureExamResult.markingYn
                        );
        return query;
    }


    private JPQLQuery<ViLectureExamResultDto> getScheduleQuery(Predicate predicate) {

        final JPQLQuery<ViLectureExamResultDto> query =
                from(viLectureExamResult)
                        .select(
                                Projections.constructor(
                                        ViLectureExamResultDto.class,
                                        viLectureExam.examPeriod,
                                        viLectureQuestResult.mbrScore,
                                        viLectureQuest.score.as("totalScore"),
                                        viLectureExamResult.lectureExamResultId,
                                        viLectureExamResult.examStartDt,
                                        viLectureExamResult.examEndDt,
                                        viLectureExamResult.mbrId,
                                        viLectureExamResult.regDt,
                                        viLectureExamResult.markingYn
                                ))
                        .leftJoin(viLectureQuestResult)
                        .on(viLectureExamResult.lectureExamResultId.eq(viLectureQuestResult.lectureExamResultId))
                        .where(predicate);

        return query;
    }










}
