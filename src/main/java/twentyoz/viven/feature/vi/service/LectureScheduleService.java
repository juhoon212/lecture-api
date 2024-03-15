package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.Predicate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import java.util.List;
import twentyoz.viven.feature.vi.model.ViLectureSchedule;
import twentyoz.viven.feature.vi.model.QViLectureSchedule;
import twentyoz.viven.feature.vi.repository.ViLectureScheduleRepository;
import twentyoz.viven.feature.vi.mapper.ViLectureScheduleMapper;
import twentyoz.viven.util.DateTimeUtils;
import twentyoz.viven.webapi.client.vi.form.LectureScheduleForm;

import java.util.UUID;

/**
 * 강의계획서 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LectureScheduleService {

  private final ViLectureScheduleRepository repository;
  private final ViLectureScheduleMapper mapper;

  /**
   * 강의계획서 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLectureSchedule> getList(Predicate search) {
    return (List<ViLectureSchedule>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 강의계획서 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViLectureSchedule> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 강의계획서 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViLectureSchedule get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViLectureSchedule.viLectureSchedule.lectureScheduleId.eq(id)))
        .orElse(null);
  }

  /**
   * 강의계획서 등록
   *
   * @param entity
   * @return
   */
  private ViLectureSchedule add(ViLectureSchedule entity) {

    return repository.save(entity);
  }

  /**
   * 강의계획서 수정
   *
   * @param entity
   * @return
   */
  private ViLectureSchedule modify(UUID id, ViLectureSchedule entity) {
    return mapper.modify(entity, get(id));
  }

  /**
  * 강의계획서 삭제
  *
  * @param id 식별번호
  */
  public void remove(UUID id){
    repository.deleteById(id);
  }


  /**
  * 강의계획서 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  public void remove(List<UUID> ids){
    if(!ids.isEmpty()) {
      List<ViLectureSchedule> list = this.getList(
          new BooleanBuilder(QViLectureSchedule.viLectureSchedule.lectureScheduleId.in(ids)));
      for(ViLectureSchedule item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 강의계획서 등록
   *
   * @param entity
   * @return
   */
  public ViLectureSchedule addLectureSchedule(LectureScheduleForm.Input.Add entity) {

    ViLectureSchedule viLectureSchedule = changeInputToViLectureSchedule(entity);

    return this.add(viLectureSchedule);
  }

  private ViLectureSchedule changeInputToViLectureSchedule(LectureScheduleForm.Input.Add in) {

    ViLectureSchedule viLectureSchedule = new ViLectureSchedule();
    viLectureSchedule.setLectureId(in.getLectureId());
    viLectureSchedule.setLectureScheduleName(in.getLectureScheduleName());
    viLectureSchedule.setDescCont(in.getDescCont());
    viLectureSchedule.setStartDt(DateTimeUtils.getStringToDateTime(in.getStartDt()));
    viLectureSchedule.setEndDt(DateTimeUtils.getStringToDateTime(in.getEndDt()));

    return viLectureSchedule;
  }

  /**
   * 강의계획서 수정
   */
  public ViLectureSchedule modifyLectureSchedule(UUID id, LectureScheduleForm.Input.Modify in) {

    ViLectureSchedule findLectureSchedule = get(id);

    modifyLectureSchedule(in, findLectureSchedule);

    return findLectureSchedule;
  }

  private static void modifyLectureSchedule(LectureScheduleForm.Input.Modify in, ViLectureSchedule findLectureSchedule) {
    if(StringUtils.isNotEmpty(in.getLectureScheduleName())) {
      findLectureSchedule.setLectureScheduleName(in.getLectureScheduleName());
    }
    if(StringUtils.isNotEmpty(in.getDescCont())) {
      findLectureSchedule.setDescCont(in.getDescCont());
    }

    if(StringUtils.isNotEmpty(in.getStartDt())) {
      findLectureSchedule.setStartDt(DateTimeUtils.getStringToDateTime(in.getStartDt()));
    }

    if(StringUtils.isNotEmpty(in.getEndDt())) {
      findLectureSchedule.setEndDt(DateTimeUtils.getStringToDateTime(in.getEndDt()));
    }
  }
}
