package twentyoz.viven.feature.vi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import twentyoz.viven.feature.vi.model.ViRef;

import java.util.UUID;

public interface ViRefRepository extends JpaRepository<ViRef, UUID>, QuerydslPredicateExecutor<ViRef> {
}
