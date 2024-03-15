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
import java.util.List;

import twentyoz.viven.feature.vi.model.*;
import twentyoz.viven.feature.vi.repository.ViLectureExamRepository;
import twentyoz.viven.feature.vi.repository.ViLectureExamResultRepository;
import twentyoz.viven.feature.vi.mapper.ViLectureExamResultMapper;
import twentyoz.viven.feature.vi.repository.ViLectureExamResultRepositorySupport;
import twentyoz.viven.util.DateTimeUtils;
import twentyoz.viven.webapi.client.vi.form.LectureExamResultForm;
import java.util.UUID;

import static twentyoz.viven.feature.vi.model.QViLectureExamResult.*;


/**
 * 강의시험지결과 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LectureExamResultService {

  private final ViLectureExamResultRepository repository;
  private final ViLectureExamResultMapper mapper;
  private final ViLectureExamResultRepositorySupport repositorySupport;
  private final LectureService lectureService;
  private final RoomService roomService;
  private final ViLectureExamRepository viLectureExamRepository;
  private final ViLectureExamResultRepositorySupport lectureExamResultRepositorySupport;


  /**
   * 강의시험지결과 목록 조회
   *
   * @param search 검색 조건
   * @return
   */

  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLectureExamResult> getList(Predicate search) {
      return (List<ViLectureExamResult>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
    }

  /**
   * 강의시험지결과 목록 조회(강의 생성자에 따라서)
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLectureExamResultListDto> getSupportList(Predicate search, LectureExamResultForm.Input.GetAll in, UUID loginId) {


    // 해당 강의 시험지 찾고
    ViLectureExam findLectureExam = viLectureExamRepository.findById(in.getLectureExamId()).orElseThrow(
            () -> new IllegalArgumentException("찾는 강의시험이 없습니다"));
    // 해당 강의시험지가 등록된 강의를 찾고
    ViLecture findLecture = lectureService.get(findLectureExam.getLectureId());
    // 강의가 등록된 강의실을 찾는다.
    ViRoom findRoom = roomService.get(findLecture.getRoomId());


    // 교수자
    if(findRoom.getMbrId().equals(loginId)) {

        return lectureExamResultRepositorySupport.getSupportList(search);
    }

    // 시험응시자
    List<ViLectureExamResultListDto> mbrList = lectureExamResultRepositorySupport.getSupportList(
            new BooleanBuilder(viLectureExamResult.mbrId.eq(loginId))
                    .and(viLectureExamResult.lectureExamId.eq(in.getLectureExamId())));


      return mbrList;


    }
  /**
   * 강의시험지결과 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViLectureExamResult> getPage(Predicate search, Pageable page) {



    return repository.findAll(search, page);
  }

  /**
   * 강의시험지결과 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViLectureExamResult get(UUID id) {

    ViLectureExamResult findLectureExamResult = repository
            .findOne(new BooleanBuilder(viLectureExamResult.lectureExamResultId.eq(id)))
            .orElseThrow(() -> new IllegalArgumentException("찾는 강의결과가 없습니다"));

    return findLectureExamResult;
  }

  // 조건에 맞는 검색
  public ViLectureExamResult checkIsStart(UUID lectureExamId, UUID mbrId) {

    return repository.findOne(
            new BooleanBuilder(
                    viLectureExamResult.lectureExamId.eq(lectureExamId))
                    .and(viLectureExamResult.mbrId.eq(mbrId))
    ).orElse(null);
  }



  @Transactional(readOnly = true)
  public ViLectureExamResultDto getSupport(Predicate predicate, UUID mbrId) {

      return repositorySupport.getSupport(predicate, mbrId);
  }




  /**
   * 강의시험지결과 등록
   *
   * @param entity
   * @return
   */
  private ViLectureExamResult add(ViLectureExamResult entity) {
    return repository.save(entity);
  }

  /**
   * 강의시험지결과 수정
   *
   * @param entity
   * @return
   */
  private ViLectureExamResult modify(UUID id, ViLectureExamResult entity) {
    return mapper.modify(entity, get(id));
  }

  /**
  * 강의시험지결과 삭제
  *
  * @param id 식별번호
  */
  public void remove(UUID id){
    repository.deleteById(id);
  }


  /**
  * 강의시험지결과 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  public void remove(List<UUID> ids){
    if(ids.size() > 0) {
      List<ViLectureExamResult> list = this.getList(
          new BooleanBuilder(viLectureExamResult.lectureExamResultId.in(ids)));
      for(ViLectureExamResult item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 강의시험지결과 등록
   *
   * @param
   * @return
   */
  public ViLectureExamResult addLectureExamResult(ViLectureExam in, UUID mbrId) {

    ViLectureExamResult viLectureExamResult = setLectureExamResult(in, mbrId);


    ViLectureExamResult addExamResult = this.add(viLectureExamResult);

    Integer examPeriod = in.getExamPeriod();
    long examPeriodToMillis = examPeriod * 60 * 1000;

    boolean result = isTesting(viLectureExamResult.getLectureExamResultId(), examPeriodToMillis);

    return addExamResult;
  }



  /**
   * 강의시험지결과 수정
   *
   * @return
   */
  public ViLectureExamResult modifyLectureExamResult(UUID id, LectureExamResultForm.Input.Modify in) {

    ViLectureExamResult findLectureExamResult = get(id);

    findLectureExamResult.setMarkingYn(in.getMarkingYn());

    return findLectureExamResult;
  }

  public void modifyEndDt(UUID id, long millis) {

    ViLectureExamResult findLectureExamResult = get(id);

    if(findLectureExamResult == null) {
      throw new IllegalArgumentException("찾는 시험지 문제결과가 없습니다");
    }

    findLectureExamResult.setExamEndDt(DateTimeUtils.getMillSecToDateTime(millis));

  }

  /**
   *  시험결과 조회 후 종료시간이 null 이면 점수 0으로
   */
  public boolean isTesting(UUID lectureExamResultId, long examPeriod) {

    ViLectureExamResult findLectureExamResult = repository.findById(lectureExamResultId)
            .orElseThrow(() -> new IllegalArgumentException("찾으시는 시험결과 식별번호가 없습니다"));

//    LectureExamScheduler.lectureExamResultId = findLectureExamResult.getLectureExamResultId();

    return true;
  }



  private static ViLectureExamResult setLectureExamResult(ViLectureExam in, UUID mbrId) {
    ViLectureExamResult viLectureExamResult = new ViLectureExamResult();
    viLectureExamResult.setLectureId(in.getLectureId());
    viLectureExamResult.setLectureExamId(in.getLectureExamId());
    viLectureExamResult.setMbrId(mbrId);
    viLectureExamResult.setExamStartDt(DateTimeUtils.getMillSecToDateTime(System.currentTimeMillis()));
    viLectureExamResult.setExamEndDt(null);
    viLectureExamResult.setMarkingYn(in.getMarkingYn());
    return viLectureExamResult;
  }
}

