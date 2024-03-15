package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.Predicate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;

import java.util.ArrayList;
import java.util.List;

import twentyoz.viven.feature.vi.model.*;
import twentyoz.viven.feature.vi.model.QViLectureQuest;
import twentyoz.viven.feature.vi.repository.ViLectureQuestRepository;
import twentyoz.viven.feature.vi.mapper.ViLectureQuestMapper;
import twentyoz.viven.webapi.client.vi.form.LectureExamForm;
import twentyoz.viven.webapi.client.vi.form.LectureQuestForm;
import twentyoz.viven.webapi.dts.vi.form.DtsLectureQuestDto;

import java.util.UUID;

/**
 * 강의시험문제 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LectureQuestService {

  private final ViLectureQuestRepository repository;
  private final ViLectureQuestMapper mapper;

  /**
   * 강의시험문제 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLectureQuest> getList(Predicate search) {
    return (List<ViLectureQuest>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 강의시험문제 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViLectureQuest> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 강의시험문제 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViLectureQuest get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViLectureQuest.viLectureQuest.lectureQuestId.eq(id)))
        .orElse(null);
  }

  @Transactional(readOnly = true)
  public DtsLectureQuestDto getDtsSupport(UUID id) throws IllegalAccessException {
    ViLectureQuest viLectureQuest = repository
            .findOne(new BooleanBuilder(QViLectureQuest.viLectureQuest.lectureQuestId.eq(id)))
            .orElse(null);

    DtsLectureQuestDto lectureQuestDto = new DtsLectureQuestDto();
    lectureQuestDto.setLectureQuestId(viLectureQuest.getLectureQuestId());
    lectureQuestDto.setAnswer(viLectureQuest.getAnswer());

    return lectureQuestDto;
  }

  /**
   * 강의시험문제 등록
   *
   * @param entity
   * @return
   */
  private ViLectureQuest add(ViLectureQuest entity) {
    return repository.save(entity);
  }

  // 강의시험 문제 등록(시험지 저장시)
  public void addLectureQuest(ViLectureQuest viLectureQuest) {

    this.add(viLectureQuest);
  }

  /**
   * 강의시험문제 수정
   *
   * @param entity
   * @return
   */
  private ViLectureQuest modify(UUID id, ViLectureQuest entity) {
    return mapper.modify(entity, get(id));
  }

  /**
  * 강의시험문제 삭제
  *
  * @param id 식별번호
  */
  public void remove(UUID id){
    repository.deleteById(id);
  }


  /**
  * 강의시험문제 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  public void remove(List<UUID> ids){
    if(ids.size() > 0) {
      List<ViLectureQuest> list = this.getList(
          new BooleanBuilder(QViLectureQuest.viLectureQuest.lectureQuestId.in(ids)));
      for(ViLectureQuest item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 강의시험문제 등록
   *
   * @param in
   * @return
   */
  public List<ViLectureQuest> addLectureQuest(LectureQuestForm.Input.Add in) {

    List<LectureQuestForm.Input.Add.AddList> lectureExamList = in.getLectureExamList();

    List<ViLectureQuest> addLectureQuestList = new ArrayList<>();

    for (LectureQuestForm.Input.Add.AddList lectureQuest : lectureExamList) {

      ViLectureQuest viLectureQuest = ViLectureQuest.builder()
              .lectureId(lectureQuest.getLectureId())
              .lectureExamId(lectureQuest.getLectureExamId())
              .examItemId(lectureQuest.getExamItemId())
              .examQuestId(lectureQuest.getExamQuestId())
              .questName(lectureQuest.getQuestName())
              .questTypeCode(lectureQuest.getQuestTypeCode())
              .questCont(lectureQuest.getQuestCont())
              .questFileGroupId(lectureQuest.getQuestFileGroupId())
              .choiceCont(lectureQuest.getChoiceCont())
              .score(lectureQuest.getScore())
              .answer(lectureQuest.getAnswer())
              .goldenbellYn(lectureQuest.getGoldenbellYn())
              .sortOrd(lectureQuest.getSortOrd())
              .build();

      addLectureQuestList.add(viLectureQuest);
      this.add(viLectureQuest);
    }

    return addLectureQuestList;
  }

  /**
   * 강의시험문제 수정
   *
   * @param entity
   * @return
   */
  public ViLectureQuest modifyLectureQuest(UUID id, ViLectureQuest entity) {
    return this.modify(id, entity);
  }
}
