package twentyoz.viven.webapi.dts.vi.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestResultForm {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {

        @ApiModelProperty("문제 식별 번호")
        private UUID questId;

        @ApiModelProperty("답")
        private List<String> answers = new ArrayList<>();
    }


    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Output {

        @ApiModelProperty("정답여부")
        private Boolean result = true;

        @ApiModelProperty("점수 (없을수도 있음)")
        private Double score = 2.5;
    }
}
