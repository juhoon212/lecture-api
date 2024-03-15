package twentyoz.viven.feature.vi.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import twentyoz.viven.feature.vi.model.QViAlarm;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViAlarmRepositorySupport extends QuerydslRepositorySupport {

  @PersistenceContext private EntityManager entityManager;

  public ViAlarmRepositorySupport() {
    super(ViAlarmRepositorySupport.class);
  }

  /** 콘텐츠에 해당하는 버전 전체 비활성화 처리 */
  public void readAllAlarmByMbrId(UUID mbrId) {
    QViAlarm qViAlarm = QViAlarm.viAlarm;
    JPAQueryFactory factory = new JPAQueryFactory(entityManager);
    factory.update(qViAlarm).where(qViAlarm.mbrId.eq(mbrId)).set(qViAlarm.readYn, "Y").execute();
  }
}
