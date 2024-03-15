package twentyoz.viven.feature.vi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViRoomRepository
    extends JpaRepository<ViRoom, UUID>, QuerydslPredicateExecutor<ViRoom> {}
