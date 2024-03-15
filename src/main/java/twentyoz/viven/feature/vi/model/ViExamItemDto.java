package twentyoz.viven.feature.vi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ViExamItemDto {

    private UUID examItemId;

    /**
     * 시험지식별번호
     */
    private UUID examId;

    /**
     * 시험문제식별번호
     */
    private UUID examQuestId;

    /**
     * 회원식별번호
     */
    private UUID mbrId;

    /**
     * 배점
     */
    private Integer score;

    /**
     * 정렬순서
     */
    private Integer sortOrd;

    /**
     * 시험문제 이름
     */
    private String questName;

    /**
     * 시험 문제 유형 코드
     */
    private String questTypeCode;


    public ViExamItemDto(
            UUID examItemId,
            UUID examId,
            UUID examQuestId,
            UUID mbrId,
            Integer score,
            Integer sortOrd,
            String questName,
            String questTypeCode
            ) {
        this.examItemId = examItemId;
        this.examId = examId;
        this.examQuestId = examQuestId;
        this.mbrId = mbrId;
        this.score = score;
        this.sortOrd = sortOrd;
        this.questName = questName;
        this.questTypeCode = questTypeCode;
    }
}
