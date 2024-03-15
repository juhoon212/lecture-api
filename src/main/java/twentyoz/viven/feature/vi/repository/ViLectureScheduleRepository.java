package twentyoz.viven.feature.vi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.vi.model.ViLectureSchedule;
import java.util.UUID;

@Repository
public interface ViLectureScheduleRepository extends JpaRepository<ViLectureSchedule, UUID>,
    QuerydslPredicateExecutor<ViLectureSchedule> {

}
