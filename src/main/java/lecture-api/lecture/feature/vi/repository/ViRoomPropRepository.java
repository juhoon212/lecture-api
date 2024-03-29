package twentyoz.viven.feature.vi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViRoomPropRepository
    extends JpaRepository<ViRoomProp, UUID>, QuerydslPredicateExecutor<ViRoomProp> {}
