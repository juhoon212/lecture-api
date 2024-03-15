package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.Predicate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;

import java.util.*;

import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.vi.model.ViLectureQuest;
import twentyoz.viven.feature.vi.model.ViLectureQuestResult;
import twentyoz.viven.feature.vi.model.QViLectureQuestResult;
import twentyoz.viven.feature.vi.model.ViLectureQuestResultDto;
import twentyoz.viven.feature.vi.repository.ViLectureQuestRepository;
import twentyoz.viven.feature.vi.repository.ViLectureQuestResultRepository;
import twentyoz.viven.feature.vi.mapper.ViLectureQuestResultMapper;
import twentyoz.viven.feature.vi.repository.ViLectureQuestResultRepositorySupport;
import twentyoz.viven.webapi.client.vi.form.LectureQuestResultForm;
import twentyoz.viven.webapi.dts.vi.form.DtsLectureQuestDto;
import twentyoz.viven.webapi.dts.vi.form.DtsLectureQuestResultDto;

/**
 * 강의시험문제결과 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LectureQuestResultService {

  private final ViLectureQuestResultRepository lectureQuestResultRepository;
  private final ViLectureQuestResultMapper mapper;
  private final ViLectureQuestRepository lectureQuestRepository;
  private final ViLectureQuestResultRepositorySupport repositorySupport;

  /**
   * 강의시험문제결과 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLectureQuestResult> getList(Predicate search) {
    return (List<ViLectureQuestResult>) lectureQuestResultRepository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLectureQuestResultDto> getSupportList(Predicate search) {
    return repositorySupport.getSupportList(search);
  }

  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLectureQuestResultDto> getNoAnswerSupportList(Predicate search) {
    return repositorySupport.getNoAnswerSupportList(search);
  }

  /**
   * 강의시험문제결과 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViLectureQuestResult> getPage(Predicate search, Pageable page) {
    return lectureQuestResultRepository.findAll(search, page);
  }

  /**
   * 강의시험문제결과 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViLectureQuestResult get(UUID id) {
    return lectureQuestResultRepository
        .findOne(new BooleanBuilder(QViLectureQuestResult.viLectureQuestResult.lectureQuestResultId.eq(id)))
        .orElse(null);
  }





  /**
   * 강의시험문제결과 등록
   *
   * @param entity
   * @return
   */
  private ViLectureQuestResult add(ViLectureQuestResult entity) {
    return lectureQuestResultRepository.save(entity);
  }

  /**
   * 강의시험문제결과 수정
   *
   * @param entity
   * @return
   */
  private ViLectureQuestResult modify(UUID id, ViLectureQuestResult entity) {
    return mapper.modify(entity, get(id));
  }

  /**
  * 강의시험문제결과 삭제
  *
  * @param id 식별번호
  */
  public void remove(UUID id){
    lectureQuestResultRepository.deleteById(id);
  }


  /**
  * 강의시험문제결과 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  public void remove(List<UUID> ids){
    if(ids.size() > 0) {
      List<ViLectureQuestResult> list = this.getList(
          new BooleanBuilder(QViLectureQuestResult.viLectureQuestResult.lectureQuestResultId.in(ids)));
      for(ViLectureQuestResult item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 문제 결과 수정
   */
  public List<ViLectureQuestResult> modifyLectureQuestResult(LectureQuestResultForm.Input.Modify in) {

    List<LectureQuestResultForm.Input.Modify.ModifyAll> modifyList = in.getModifyList();

    List<ViLectureQuestResult> output = new ArrayList<>();

    for (LectureQuestResultForm.Input.Modify.ModifyAll lectureQuestResult : modifyList) {
      ViLectureQuestResult findLectureQuestResult = lectureQuestResultRepository.findById(lectureQuestResult.getLectureQuestResultId())
              .orElseThrow(() -> new IllegalStateException("찾으실 문제가 없습니다"));

      findLectureQuestResult.setMbrScore(lectureQuestResult.getMbrScore());

      output.add(findLectureQuestResult);
    }

    return output;
  }

  /**
   * 문제 결과 수정
   */

  public ViLectureQuestResult addLectureQuestResult(ViLectureQuestResult viLectureQuestResult) {

    if (viLectureQuestResult == null) {
      throw new IllegalArgumentException("해당 문제가 저장되지 않았습니다");
    }
    
      return this.add(viLectureQuestResult);
  }


  /**
   * dts 채점 로직
   */

  public DtsLectureQuestResultDto markingQuests(UUID questId, List<String> mbrAnswer) throws IllegalArgumentException {

    List<String> answers = lectureQuestRepository.findById(questId).
            orElseThrow(() -> new IllegalArgumentException("찾으시는 문제가 없습니다"))
            .getAnswer();

    log.info("originAnswers = {}", answers);

    log.info("mbrAnswers = {}", mbrAnswer);

    Boolean result = answers.stream().map(data -> mbrAnswer.stream().anyMatch(answer -> answer.equals(data)))
            .findAny().orElseThrow(() -> new IllegalArgumentException("바르지 못한 값입니다"));



    if(result) {
      return new DtsLectureQuestResultDto("true");
    }

    return new DtsLectureQuestResultDto("false");

  }
}
