//package twentyoz.viven.webapi.dts.vi.form;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
///**
// * 문제 목록
// */
//public class LectureQuestFormD {
//
//
//    @Data
//    @Builder
//    @ToString
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class Input {
//
//        @Data
//        @Builder
//        @ToString
//        @NoArgsConstructor
//        @AllArgsConstructor
//        public static class GetAll {
//
//            @ApiModelProperty(value = "강의 식별번호")
//            private UUID lectureId;
//
//            @ApiModelProperty(value = "시험지 이름")
//            private String examName;
//
//            @ApiModelProperty(value = "설명 내용")
//            private String descCont;
//        }
//
//    }
//
//
//
//    @Data
//    @Builder
//    @ToString
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class Output {
//
//        @ApiModelProperty("시험 식별번호")
//        private UUID examId = UUID.fromString("cfb19e33-1558-47b7-b136-da885b2e4fd5");
//
//        @ApiModelProperty("시험 이름")
//        private String examName = "2024 데이터베이스 번개 퀴즈";
//
//        @ApiModelProperty("시험 유형 코드")
//        private String examTypeCode = "EXAM_002_001";
//
//        @ApiModelProperty("설명내용")
//        private String descCont = "5월에 진행하는 번개 퀴즈입니다.";
//
//        @ApiModelProperty("시험 입장 시작시각")
//        private String examStartDt = "2023-01-01 00:00:00";
//
//        @ApiModelProperty("시험 입장 종료시각")
//        private String examEndDt = "2023-01-01 05:00:00"; // 더미 데이터라 스트링 타입으로 작성
//
//        @ApiModelProperty("시험시간 (예시: 45분)")
//        private String examPeriod = "45";
//
//        @ApiModelProperty("문제 식별번호 목록(배열)")
//        private List<QuestIds> questIds = new ArrayList<>();
//
//        public void addQuestIds() {
//            questIds.add(new QuestIds(UUID.fromString("ad901602-5862-4a38-94b1-bfb7fc8be28d"), 1));
//            questIds.add(new QuestIds(UUID.fromString("b7c2b5ad-57a6-4643-9011-20137803b49e"), 2));
//            questIds.add(new QuestIds(UUID.fromString("1233ec8c-7b42-4cdf-9eac-d297b8d390eb"), 3));
//        }
//
//        @Data
//        @Builder
//        @ToString
//        @NoArgsConstructor
//        @AllArgsConstructor
//        public static class QuestIds {
//
//            private UUID questId;
//            private Integer sortOrd;
//        }
//    }
//
//
//}
