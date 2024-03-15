package twentyoz.viven.feature.vi.repository;

import java.util.UUID;
import twentyoz.viven.feature.vi.model.ViRoomSv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViRoomSvRepository
    extends JpaRepository<ViRoomSv, UUID>, QuerydslPredicateExecutor<ViRoomSv> {

  ViRoomSv findTop1ByRoomSvStatusCodeAndUseYnAndDelYnOrderByModDtDesc(
      String roomSvStatusCode, String useYn, String delYn);
}
