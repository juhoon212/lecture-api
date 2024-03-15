package twentyoz.viven.feature.vi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.vi.model.ViLectureQuestResult;
import java.util.UUID;

@Repository
public interface ViLectureQuestResultRepository extends JpaRepository<ViLectureQuestResult, UUID>,
    QuerydslPredicateExecutor<ViLectureQuestResult> {

}
