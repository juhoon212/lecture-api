package twentyoz.viven.feature.mb.repository;

import java.util.UUID;
import twentyoz.viven.feature.mb.model.MbMbr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MbMbrRepository
    extends JpaRepository<MbMbr, UUID>, QuerydslPredicateExecutor<MbMbr> {}
