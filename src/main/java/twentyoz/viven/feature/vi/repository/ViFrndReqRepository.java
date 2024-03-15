package twentyoz.viven.feature.vi.repository;

import java.util.UUID;
import twentyoz.viven.feature.vi.model.ViFrndReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViFrndReqRepository
    extends JpaRepository<ViFrndReq, UUID>, QuerydslPredicateExecutor<ViFrndReq> {}
