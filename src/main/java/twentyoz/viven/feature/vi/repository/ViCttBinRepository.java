package twentyoz.viven.feature.vi.repository;

import java.util.UUID;
import twentyoz.viven.feature.vi.model.ViCttBin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViCttBinRepository
    extends JpaRepository<ViCttBin, UUID>, QuerydslPredicateExecutor<ViCttBin> {}
