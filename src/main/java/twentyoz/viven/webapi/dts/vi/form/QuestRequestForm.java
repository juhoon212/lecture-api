package twentyoz.viven.webapi.dts.vi.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 문제 항목
 */
public class QuestRequestForm {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {

        @ApiModelProperty("시험문제 식별 번호")
        private UUID questId;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Output {

        @ApiModelProperty("시험 문제 식별 번호")
        private UUID questId;

        @ApiModelProperty("문제 이름")
        private String questName = "데이터베이스 문제 1호";

        @ApiModelProperty("문제 유형 코드")
        private String questTypeCode = "EXAM_001_001";

        @ApiModelProperty("문제 본문")
        private String questCont = "객관식 단일 예시 문제입니다. 옳은 것을 고르시오.";

        @ApiModelProperty("문항 보기")
        private List<ChoiceCont> choiceCont = new ArrayList<>();

        // dummy
        public void addChoiceCont() {
            choiceCont.add(new ChoiceCont("01. 이 문제는 객관식 단일 문항입니다.", 1, "1"));
            choiceCont.add(new ChoiceCont("02. 이 문제는 객관식 단일 문항이 아닙니다.", 2, "2"));
            choiceCont.add(new ChoiceCont("03. 이 문제는 단답형입니다.", 3, "3"));
            choiceCont.add(new ChoiceCont("04. 이 문제는 서술형입니다.", 4, "4"));
        }

        @Data
        @Builder
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ChoiceCont {

            @ApiModelProperty("표시되는 지문")
            private String title;

            @ApiModelProperty("정렬 순서")
            private Integer sortOrd;

            @ApiModelProperty("실제 answer 와 비교할 값")
            private String value;
        }
    }
}
