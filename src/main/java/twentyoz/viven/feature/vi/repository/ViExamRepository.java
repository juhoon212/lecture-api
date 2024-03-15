package twentyoz.viven.feature.vi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.vi.model.ViExam;
import java.util.UUID;

@Repository
public interface ViExamRepository extends JpaRepository<ViExam, UUID>,
    QuerydslPredicateExecutor<ViExam> {

}
