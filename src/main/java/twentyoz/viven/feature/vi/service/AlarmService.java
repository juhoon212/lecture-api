package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.vi.mapper.ViAlarmMapper;
import twentyoz.viven.feature.vi.model.QViAlarm;
import twentyoz.viven.feature.vi.model.ViAlarm;
import twentyoz.viven.feature.vi.repository.ViAlarmRepository;
import twentyoz.viven.feature.vi.repository.ViAlarmRepositorySupport;

/** 알람 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class AlarmService {

  private final ViAlarmRepository repository;
  private final ViAlarmRepositorySupport repositorySupport;
  private final ViAlarmMapper mapper;

  /**
   * 알람 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViAlarm> getList(Predicate search) {

    // 읽지 않은 알람만 조회
    QViAlarm viAlarm = QViAlarm.viAlarm;
    BooleanBuilder builder = new BooleanBuilder();
    builder.and(search);
    builder.and(viAlarm.readYn.eq("N"));

    return (List<ViAlarm>) repository.findAll(builder, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 알람 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViAlarm> getPage(Predicate search, Pageable page) {

    // 읽지 않은 알람만 조회
    QViAlarm viAlarm = QViAlarm.viAlarm;
    BooleanBuilder builder = new BooleanBuilder();
    builder.and(search);
    builder.and(viAlarm.readYn.eq("N"));

    return repository.findAll(builder, page);
  }

  /**
   * 알람 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViAlarm get(UUID id) {
    return repository.findOne(new BooleanBuilder(QViAlarm.viAlarm.alarmId.eq(id))).orElse(null);
  }

  /**
   * 알람 등록
   *
   * @param entity
   * @return
   */
  private ViAlarm add(ViAlarm entity) {
    return repository.save(entity);
  }

  public ViAlarm addAlarm(ViAlarm entity) {
    return this.add(entity);
  }

  /**
   * 알람 읽음
   *
   * @return
   */
  private ViAlarm modify(UUID id, ViAlarm entity) {
    return mapper.modify(entity, get(id));
  }

  public ViAlarm readAlarm(UUID id) {
    ViAlarm viAlarm = this.get(id);
    viAlarm.setReadYn("Y");

    return this.modify(id, viAlarm);
  }

  public void readAllAlarm(UUID mbrId) {
    repositorySupport.readAllAlarmByMbrId(mbrId);
  }

  /**
   * 알람 조회
   *
   * @param id 알람식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViAlarm getAlarm(UUID id, UUID mbrId) {
    return repository
        .findOne(
            new BooleanBuilder(
                QViAlarm.viAlarm.alarmId.eq(id).and(QViAlarm.viAlarm.mbrId.eq(mbrId))))
        .orElse(null);
  }
}
