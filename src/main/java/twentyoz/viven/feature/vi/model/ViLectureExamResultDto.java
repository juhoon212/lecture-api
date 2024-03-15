package twentyoz.viven.feature.vi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import twentyoz.viven.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViLectureExamResultDto {

    // 시험 제목, 문제유형, 점수, 제한시간, 시험 지침,
    // 닉네임, 시간, 점수, 채점상태

    private ViLectureExamResult viLectureExamResult;

    /**
     * 시험 시간
     */
    private Integer examPeriod;

    /**
     * 시험지 총합
     */
    private Integer totalScore;

    /**
     * 회원 총점수
     */
    private Integer mbrScore;

    public ViLectureExamResultDto(
        Integer examPeriod,
        Integer mbrScore,
        Integer totalScore,
            UUID lectureExamResultId,
        DateTime examStartDt,
        DateTime examEndDt,
        UUID mbrId,
        DateTime regDt,
        String markingYn
    ) {

        ViLectureExamResult lectureExamResult = new ViLectureExamResult();
        lectureExamResult.setLectureExamResultId(lectureExamResultId);
        lectureExamResult.setExamStartDt(examStartDt);
        lectureExamResult.setExamEndDt(examEndDt);
        lectureExamResult.setMbrId(mbrId);
        lectureExamResult.setRegDt(regDt);
        lectureExamResult.setMarkingYn(markingYn);

        this.examPeriod = examPeriod;
        this.totalScore = totalScore;
        this.viLectureExamResult = lectureExamResult;
        this.mbrScore = mbrScore;
    }
}
