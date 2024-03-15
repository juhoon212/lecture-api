package twentyoz.viven.feature.sy.repository;

import java.util.UUID;
import twentyoz.viven.feature.sy.model.SyAttachFileGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SyAttachFileGroupRepository
    extends JpaRepository<SyAttachFileGroup, UUID>, QuerydslPredicateExecutor<SyAttachFileGroup> {}
