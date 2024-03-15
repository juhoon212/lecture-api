package twentyoz.viven.feature.vi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViLectureExamResultListDto {

    /**
     * 강의 시험
     */
    private String examName;

    /**
     * 강의 시험 문제결과
     */
    private String examTypeCode;

    /**
     * 시험지 결과 식별번호
     */
    private UUID lectureExamResultId;

    /**
     * 회원 닉네임
     */
    private Integer mbrScore;

    /**
     * 제한시간
     */
    private Integer examPeriod;

    /**
     * 강의 아이디
     */
    private UUID examId;

    /**
     * 시험 문제 아이디
     */
    private UUID lectureQuestId;


    /**
     * 내용
     */
    private String descCont;

    private String nickName;

    private DateTime examEndDt;

    private UUID mbrId;

    private DateTime examStartDt;

    private Integer totalScore;

    private DateTime regDt;

    private String markingYn;

    private String questTypeCode;



}
