package twentyoz.viven.feature.vi.repository;

import org.joda.time.DateTime;
import twentyoz.viven.feature.vi.model.ViLectureExamResult;

import java.time.LocalDateTime;
import java.util.List;

public interface ViLectureExamCustomRepository {

    List<ViLectureExamResult> findByCurrentTime(DateTime time);


}
