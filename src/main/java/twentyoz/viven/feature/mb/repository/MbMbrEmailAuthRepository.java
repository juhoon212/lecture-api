package twentyoz.viven.feature.mb.repository;

import java.util.UUID;
import twentyoz.viven.feature.mb.model.MbMbrEmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MbMbrEmailAuthRepository
    extends JpaRepository<MbMbrEmailAuth, UUID>, QuerydslPredicateExecutor<MbMbrEmailAuth> {}
