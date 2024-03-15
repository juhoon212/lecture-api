package twentyoz.viven.feature.vi.scheduler;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twentyoz.viven.feature.vi.model.ViLectureExamResult;
import twentyoz.viven.feature.vi.repository.ViLectureExamCustomRepository;
import twentyoz.viven.feature.vi.repository.ViLectureExamRepository;
import twentyoz.viven.feature.vi.repository.ViLectureQuestRepository;
import twentyoz.viven.feature.vi.repository.ViLectureQuestResultRepository;
import twentyoz.viven.feature.vi.service.LectureExamResultService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class LectureExamScheduler {

    private final ViLectureExamCustomRepository customRepository;
    private final ViLectureQuestResultRepository lectureQuestResultRepository;



//    /**
//     *
//     */
//    @Scheduled(cron = "60 * * * * ?")
//    public void checkLectureExamResult() {
//
//        // 시험이 시작된 시험을 전체 조회 후 examEndDt가 null인 요소만 해당 시험지에 연관되어 있는 문제들을 0점 처리하는 로직
//        List<ViLectureExamResult> resultList = customRepository.findByCurrentTime(DateTime.now());
//
//        resultList.forEach(data -> log.info(String.valueOf(data)));
//
//        resultList.stream().
//                filter(data -> data.getExamEndDt() == null).
//                collect(Collectors.toList())
//                .forEach(filterData -> lectureQuestResultRepository.findById(filterData.getLectureExamResultId())
//                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 시험문제입니다.")).setMbrScore(0));
//    }

}
