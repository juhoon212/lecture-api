package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.vi.model.ViLectureExamDto;
import twentyoz.viven.webapi.dts.vi.form.DtsLectureExamDto;

import java.util.List;

import static twentyoz.viven.feature.vi.model.QViLectureExam.*;
import static twentyoz.viven.feature.vi.model.QViLectureQuest.*;

@Repository
public class ViLectureExamRepositorySupport extends QuerydslRepositorySupport {


    public ViLectureExamRepositorySupport() {
        super(ViLectureExamRepositorySupport.class);
    }

    public ViLectureExamDto getSupport(Predicate predicate) {
        JPQLQuery<ViLectureExamDto> query = getQuery(predicate);
        return query.fetchOne();
    }

    public DtsLectureExamDto getDtsSupport(Predicate predicate) {
        JPQLQuery<DtsLectureExamDto> query = getDtsQuery(predicate);
        return query.fetchOne();
    }



    private JPQLQuery<DtsLectureExamDto> getDtsQuery(Predicate predicate) {

        final JPQLQuery<DtsLectureExamDto> query =
                from(viLectureExam)
                        .select(
                                Projections.fields(
                                        DtsLectureExamDto.class,
                                        viLectureExam.lectureExamId,
                                        viLectureExam.examId,
                                        viLectureExam.examName,
                                        viLectureExam.examTypeCode,
                                        viLectureExam.descCont,
                                        viLectureExam.examStartDt,
                                        viLectureExam.examEndDt,
                                        viLectureExam.examPeriod
                                ))
                        .where(predicate);

        return query;
    }

    private JPQLQuery<ViLectureExamDto> getQuery(Predicate predicate) {

        final JPQLQuery<ViLectureExamDto> query =
                from(viLectureExam)
                        .select(
                                Projections.fields(
                                        ViLectureExamDto.class,
                                        viLectureExam.lectureExamId,
                                        viLectureExam.lectureId,
                                        viLectureExam.examId,
                                        viLectureExam.examName,
                                        viLectureExam.examTypeCode,
                                        viLectureExam.descCont,
                                        viLectureExam.examStartDt,
                                        viLectureExam.examEndDt,
                                        viLectureExam.examPeriod,
                                        viLectureExam.goldenbellCttId,
                                        viLectureExam.markingYn,
                                        viLectureQuest.score.sum().as("totalScore")
                                ))
                        .leftJoin(viLectureQuest)
                        .on(viLectureExam.lectureExamId.eq(viLectureQuest.lectureExamId))
                        .where(predicate)
                        .groupBy(viLectureExam.lectureExamId);

        return query;
    }




}
