package twentyoz.viven.webapi.dts.vi.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestAnswerForm {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {

        @ApiModelProperty("시험 문제 식별 번호")
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
        @ApiModelProperty("답안")
        private List<String> answer = new ArrayList<>();
    }
}
