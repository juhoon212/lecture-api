package twentyoz.viven.feature.mb.repository;

import java.util.UUID;
import twentyoz.viven.feature.mb.model.MbMbrStatusInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MbMbrStatusInfoRepository
    extends JpaRepository<MbMbrStatusInfo, UUID>, QuerydslPredicateExecutor<MbMbrStatusInfo> {}
