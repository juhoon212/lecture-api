package twentyoz.viven.feature.sy.repository;

import java.util.UUID;
import twentyoz.viven.feature.sy.model.SyAttachFileGroupRefObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SyAttachFileGroupRefObjRepository
    extends JpaRepository<SyAttachFileGroupRefObj, UUID>,
        QuerydslPredicateExecutor<SyAttachFileGroupRefObj> {}
