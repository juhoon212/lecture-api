package twentyoz.viven.feature.vi.model;

import lombok.*;
import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ViLectureQuestResultDto {

    private UUID lectureExamResultId;
    private UUID lectureId;
    private UUID lectureQuestResultId;
    private List<String> mbrAnswer;
    private String choiceCont;
    private Integer score;
    private String questTypeCode;
    private UUID mbrId;
    private Integer mbrScore;
    private DateTime modDt;
    private UUID modId;
    private DateTime regDt;
    private UUID regId;
    private UUID lectureQuestId;
    private UUID lectureExamId;
    private UUID examItemId;
    private String questName;
    private String questCont;
    private UUID questFileGroupId;
    private List<String> answer;
    private String goldenbellYn;
    private Integer sortOrd;


}
