package twentyoz.viven.feature.sy.repository;

import java.util.UUID;
import twentyoz.viven.feature.sy.model.SyAttachFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SyAttachFileRepository
    extends JpaRepository<SyAttachFile, UUID>, QuerydslPredicateExecutor<SyAttachFile> {

  SyAttachFile findByFileFullPathAndDelYn(String fileFullPath, String delYn);
}
