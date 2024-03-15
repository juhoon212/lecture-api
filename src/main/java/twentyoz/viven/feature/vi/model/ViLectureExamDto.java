package twentyoz.viven.feature.vi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViLectureExamDto {

    /**
     * 강의시험지식별번호
     */
    private UUID lectureExamId;

    /**
     * 강의식별번호
     */
    private UUID lectureId;

    /**
     * 시험지식별번호
     */
    private UUID examId;

    /**
     * 시험지이름
     */
    private String examName;

    /**
     * 시험지유형코드
     */
    private String examTypeCode;

    /**
     * 설명내용
     */
    private String descCont;

    /**
     * 시험시작시간
     */
    private DateTime examStartDt;

    /**
     * 시험종료시간
     */
    private DateTime examEndDt;

    /**
     * 시험소요시간(분)
     */
    private Integer examPeriod;

    /**
     * 골든벨 콘텐츠 식별번호
     */
    private UUID goldenbellCttId;

    /**
     * 채점 여부
     */
    private String markingYn;

    /**
     * 총 점수
     */
    private Integer totalScore;

    /**
     * 시험 상태
     */
    private String examStatus;

}
