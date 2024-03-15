package twentyoz.viven.feature.vi.model;

import lombok.*;
import org.joda.time.DateTime;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class ViExamDto {

    /**
     * 시험지식별번호
     */
    private UUID examId;

    /**
     * 회원식별번호
     */
    private UUID mbrId;

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
     * 키워드
     */
    private String keyword;

    /**
     * 삭제여부
     */
    private String delYn;

    /**
     * 시험 소요 시간(분)
     */
    private Integer examPeriod;

    /**
     * 사용 여부
     */
    private String useYn;

    /**
     * 점수
     */
    private Integer totalScore;

    public ViExamDto(UUID examId,
                     UUID mbrId,
                     String examName,
                     String examTypeCode,
                     String descCont,
                     String keyword,
                     String delYn,
                     Integer examPeriod,
                     String useYn,
                     Integer totalScore
                     ) {


        this.examId = examId;
        this.mbrId = mbrId;
        this.examName = examName;
        this.examTypeCode = examTypeCode;
        this.descCont = descCont;
        this.keyword = keyword;
        this.delYn = delYn;
        this.examPeriod = examPeriod;
        this.useYn = useYn;
        this.totalScore = totalScore;
    }


}
