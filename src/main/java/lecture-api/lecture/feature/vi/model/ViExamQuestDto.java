package twentyoz.viven.feature.vi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViExamQuestDto {

    /**
     * 시험지 문제 식별번호
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
     * 시험문제 이름
     */
    private String questName;

    /**
     * 시험문제 유형 코드
     */
    private String questTypeCode;

    /**
     * 시험문제 내용
     */
    private String questCont;

    /**
     * 객관식문항설정정보
     */
    private String choiceCont;

    /**
     * 정답
     */
    private List<String> answer;

    /**
     * 골든벨가능여부
     */
    private String goldenbellYn;

    /**
     * 시험문제 첨부파일 식별번호
     */

    private UUID questFileGroupId;

    /**
     * 키워드
     */
    private String keyword;

    /**
     * 정렬 순서
     */
    private Integer sortOrd;

    /**
     * 시험지문제 식별번호
     */
    private UUID examItemId;

    /**
     * 배점(시험지문제)
     */
    private Integer score;

}
