package twentyoz.viven.webapi.dts.vi.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;


import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtsLectureExamDto {

    private UUID lectureExamId;

    private UUID examId;

    private String examName;


    private String examTypeCode;

    private String descCont;

    private DateTime examStartDt;

    private DateTime examEndDt;

    private Integer examPeriod;

    private List<questIds> questIds;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class questIds {

        private UUID questId;

        private Integer sortOrd;
    }
}
