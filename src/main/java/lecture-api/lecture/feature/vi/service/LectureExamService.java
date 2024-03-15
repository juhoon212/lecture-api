package twentyoz.viven.feature.vi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.Predicate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;

import java.util.HashSet;
import java.util.List;

import twentyoz.viven.feature.vi.model.*;
import twentyoz.viven.feature.vi.model.QViExam;
import twentyoz.viven.feature.vi.model.QViLectureExamResult;
import twentyoz.viven.feature.vi.model.QViLectureQuest;
import twentyoz.viven.feature.vi.repository.ViLectureExamRepository;
import twentyoz.viven.feature.vi.mapper.ViLectureExamMapper;
import twentyoz.viven.feature.vi.repository.ViLectureExamRepositorySupport;
import twentyoz.viven.feature.vi.repository.ViLectureExamResultRepository;
import twentyoz.viven.feature.vi.repository.ViLectureQuestRepository;
import twentyoz.viven.util.DateTimeUtils;
import twentyoz.viven.webapi.client.vi.form.LectureExamForm;
import twentyoz.viven.webapi.client.vi.form.LectureExamResultForm;
import twentyoz.viven.webapi.dts.vi.form.DtsLectureExamDto;

import java.util.UUID;
import java.util.stream.Collectors;

import static twentyoz.viven.feature.vi.model.QViLectureExam.*;

/**
 * 강의시험지 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LectureExamService {

  private final ViLectureExamRepository repository;
  private final ViLectureExamMapper mapper;
  private final ExamQuestService examQuestService;
  private final LectureQuestService lectureQuestService;
  private final ViLectureExamRepositorySupport repositorySupport;
  private final LectureExamResultService lectureExamResultService;
  private final LectureQuestResultService lectureQuestResultService;
  private final LectureService lectureService;
  private final RoomService roomService;
  private final ViLectureExamResultRepository lectureExamResultRepository;
  private final ViLectureQuestRepository lectureQuestRepository;


  /**
   * 강의시험지 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViLectureExam> getList(Predicate search) {

    return (List<ViLectureExam>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public DtsLectureExamDto getDtsSupport(Predicate search) {

    DtsLectureExamDto dtsSupport = repositorySupport.getDtsSupport(search);

    UUID lectureExamId = dtsSupport.getLectureExamId();

    List<DtsLectureExamDto.questIds> collect = ((List<ViLectureQuest>) lectureQuestRepository.findAll(new BooleanBuilder(
            QViLectureQuest.viLectureQuest.lectureExamId.eq(lectureExamId)
    ))).stream().map((data) -> new DtsLectureExamDto.questIds(data.getLectureQuestId(), data.getSortOrd()))
            .collect(Collectors.toList());

    dtsSupport.setQuestIds(collect);


    return dtsSupport;
  }

  /**
   * 강의시험지 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViLectureExam> getPage(Predicate search, Pageable page) {



    return repository.findAll(search, page);
  }

  /**
   * 강의시험지 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViLectureExam get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(viLectureExam.lectureExamId.eq(id)))
        .orElse(null);
  }

  @Transactional(readOnly = true)
  public ViLectureExamDto getSupport(UUID id) {
    ViLectureExamDto viLectureExamDto =
            repositorySupport.getSupport(new BooleanBuilder(viLectureExam.lectureExamId.eq(id)));

    String examStatusCode = setExamStatus(viLectureExamDto);

    viLectureExamDto.setExamStatus(examStatusCode);

    return viLectureExamDto;
  }

  // 시험 상태 코드 설정
  public String setExamStatus(ViLectureExamDto viLectureExamDto) {

    long now = System.currentTimeMillis();

    // 시작시간 (초)
    long startDt = viLectureExamDto.getExamStartDt().getMillis();
    // 끝시간 (초)
    long endDt = viLectureExamDto.getExamEndDt().getMillis();

    if (startDt > now) {
      return Code.EXAM_003_001.getCode(); // 예정
    } else if (startDt < now && endDt > now) {
      return Code.EXAM_003_002.getCode(); // 진행
    } else if (endDt < now) {
      return Code.EXAM_003_003.getCode(); // 종료
    }

    return null;
  }

  /**
   * 시험 시작
   */
  @Transactional(rollbackFor = IllegalAccessException.class)
  public LectureExamResultForm.Output.Get start(UUID lectureExamId, UUID mbrId) throws IllegalAccessException{

    ViLectureExam findLectureExam = this.get(lectureExamId);

    ViLectureExamResult isStart = lectureExamResultService.checkIsStart(lectureExamId, mbrId);
    if(isStart != null) {
      throw new IllegalAccessException("이미 응시한 시험을 다시 응시할 수 없습니다. 관리자에게 문의하십시오");
    }
    ViLectureExamResult viLectureExamResult = lectureExamResultService.addLectureExamResult(findLectureExam, mbrId);

      return LectureExamResultForm.Output.Get.builder()
            .lectureExamResultId(viLectureExamResult.getLectureExamResultId())
            .build();
  }




  /**
   * 시험 제출
   */

  public void submit(LectureExamForm.Input.Submit in) throws IllegalAccessException, JsonProcessingException {

    // 강의시험지 결과 식별 번호
    UUID lectureExamResultId = in.getLectureExamResultId();

    // 강의 시험지 번호로 찾은 시험지
    ViLectureExam findLectureExam = this.get(in.getLectureExamId());

    // 강의시험 시간이 지났을 경우
    if(findLectureExam.getExamEndDt().getMillis() < System.currentTimeMillis()) {
      throw new IllegalAccessException("시험 시간이 초과되었습니다");
    }


    // 강의 시험지 결과 식별번호로 찾은 강의 시험지 결과
    ViLectureExamResult findLectureExamResult = lectureExamResultService.get(lectureExamResultId);

    // 파라미터로 들어온 시험지 문제 list
    List<LectureExamForm.Input.Submit.SubmitList> inLectureQuestIds = in.getLectureQuestIds();

    for (LectureExamForm.Input.Submit.SubmitList inLectureQuestId : inLectureQuestIds) {

      UUID lectureQuestId = inLectureQuestId.getLectureQuestId();

      // 문제 조회
      ViLectureQuest findLectureQuest = lectureQuestService.get(lectureQuestId);

      // 문제 정답
      List<String> realAnswers = findLectureQuest.getAnswer();
      // 사용자 정답
      List<String> mbrAnswers = inLectureQuestId.getMbrAnswer();

      String choiceCont = findLectureQuest.getChoiceCont();

      // 문제결과 생성
      ViLectureQuestResult viLectureQuestResult = createNewLectureQuestResult(
              findLectureExamResult, lectureExamResultId, findLectureQuest, mbrAnswers);
      // 채점
      markingQuest(findLectureQuest, viLectureQuestResult, realAnswers, mbrAnswers, choiceCont);

      // 문제 결과 추가
      lectureQuestResultService.addLectureQuestResult(viLectureQuestResult);

      // 시험지결과 종료 시간, 채점상태 update update
      modifyEndDt(lectureExamResultId);
    }
  }

  private void modifyEndDt(UUID lectureExamResultId) {
    lectureExamResultService.modifyEndDt(lectureExamResultId, System.currentTimeMillis());
  }

  /**
   * 강의 시험 응시여부 초기화
   */

  @SneakyThrows
  @Transactional(rollbackFor = IllegalAccessException.class)
  public void reset(LectureExamForm.Input.Reset in, UUID loginId) {


    // 해당 강의 시험지 찾고
    ViLectureExam findLectureExam = this.get(in.getLectureExamId());
    // 해당 강의시험지가 등록된 강의를 찾고
    ViLecture findLecture = lectureService.get(findLectureExam.getLectureId());
    // 강의가 등록된 강의실을 찾는다.
    ViRoom findRoom = roomService.get(findLecture.getRoomId());

    // 방을 만든 사람이 아니면
    if(!findRoom.getMbrId().equals(loginId)) {
      throw new IllegalAccessException("방 관리자가 아닙니다");
    }

    // 시험을 본사람
    List<UUID> mbrIdList = in.getMbrIdList();

    for (UUID mbrId : mbrIdList) {

      // 시험을 본사람의 시험지 결과 리스트 추출
      List<ViLectureExamResult> list = (List<ViLectureExamResult>) lectureExamResultRepository.findAll(
              new BooleanBuilder(QViLectureExamResult.viLectureExamResult.mbrId.eq(mbrId)));

      if(list.isEmpty()) {
        throw new IllegalArgumentException("리셋할 시험지가 없습니다");
      }

      // 초기화(삭제)
      list.forEach(viLectureExamResult -> viLectureExamResult.setDelYn("Y"));
    }


  }

  /**
   *
   * 문제 결과 생성
   */
  private static ViLectureQuestResult createNewLectureQuestResult(ViLectureExamResult findLectureExamResult, UUID lectureExamResultId, ViLectureQuest findLectureQuest, List<String> mbrAnswers) {
    ViLectureQuestResult viLectureQuestResult = new ViLectureQuestResult();
    viLectureQuestResult.setLectureId(findLectureExamResult.getLectureId());
    viLectureQuestResult.setLectureExamResultId(lectureExamResultId);
    viLectureQuestResult.setLectureQuestId(findLectureQuest.getLectureQuestId());
    viLectureQuestResult.setMbrId(findLectureExamResult.getMbrId());
    viLectureQuestResult.setMbrAnswer(mbrAnswers);
    return viLectureQuestResult;
  }

  /**
   * 채점
   */
  private static void markingQuest(ViLectureQuest findLectureQuest,
                                   ViLectureQuestResult findLectureQuestResult,
                                   List<String> realAnswers, List<String> mbrAnswers,
                                   String choiceCont) throws JsonProcessingException {

    if(findLectureQuest == null) {
      throw new IllegalArgumentException("해당 문제는 없는 문제입니다");
    }

    log.info("questTypeCode = {}", findLectureQuest.getQuestTypeCode());

    // 서술형
    if(findLectureQuest.getQuestTypeCode().equals(Code.EXAM_001_005.getCode())) {
      findLectureQuestResult.setMbrScore(0);
      return;
      // 서술형 단답
    } else if(findLectureQuest.getQuestTypeCode().equals(Code.EXAM_001_004.getCode())) {

      ObjectMapper objectMapper = new ObjectMapper();

      List<ChoiceCont> choiceContList = objectMapper.readValue(choiceCont, new TypeReference<List<ChoiceCont>>() {
      });

      log.info("parseData = {}", choiceContList);

      for (ChoiceCont cont : choiceContList) {
        if(mbrAnswers.contains(cont.getTitle())) {
          findLectureQuestResult.setMbrScore(findLectureQuest.getScore());
          return;
        }

        findLectureQuestResult.setMbrScore(0);
      }
      // 객관식 복수
    } else if(findLectureQuest.getQuestTypeCode().equals(Code.EXAM_001_002.getCode())) {
      if(mbrAnswers.isEmpty() || !new HashSet<>(mbrAnswers).containsAll(realAnswers)) {
        findLectureQuestResult.setMbrScore(0);
        return;
      }
    }

    if(new HashSet<>(realAnswers).containsAll(mbrAnswers)) {
        findLectureQuestResult.setMbrScore(findLectureQuest.getScore());
    } else if (realAnswers.isEmpty()){
      findLectureQuestResult.setMbrScore(0);
    } else {
      findLectureQuestResult.setMbrScore(0);
    }
  }




  /**
   * 강의시험지 등록
   *
   * @param entity
   * @return
   */
  private ViLectureExam add(ViLectureExam entity) {
    return repository.save(entity);
  }

  /**
   * 강의시험지 수정
   *
   * @param entity
   * @return
   */
  private ViLectureExam modify(UUID id, ViLectureExam entity) {
    return mapper.modify(entity, get(id));
  }

  /**
  * 강의시험지 삭제
  *
  * @param id 식별번호
  */
  @SneakyThrows
  public void remove(UUID id){

    ViLectureExam findLectureExam = this.get(id);

    // 이미 시험을 시작했으면
    if(findLectureExam.getExamStartDt().getMillis() < System.currentTimeMillis()) {
      throw new IllegalAccessException("이미 시작한 시험입니다");
    }

    repository.deleteById(id);
  }


  /**
  * 강의시험지 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  @SneakyThrows
  public void remove(List<UUID> ids){
    if(!ids.isEmpty()) {
      List<ViLectureExam> list = this.getList(
          new BooleanBuilder(viLectureExam.lectureExamId.in(ids)));
      for(ViLectureExam item : list) {
        // 이미 시험을 시작했으면
        if(item.getExamStartDt().getMillis() < System.currentTimeMillis()) {
          throw new IllegalAccessException("이미 시작한 시험입니다");
        }

        this.remove(item.getId());
      }
    }
  }

  /**
   * 강의시험지 등록
   * @return
   */
  public ViLectureExam addLectureExam(LectureExamForm.Input.Add in, UUID mbrId) {

    ViLectureExam addViLectureExam = changeInputToLectureExam(in);

    // 추가 된 시험지
    ViLectureExam addedLectureExam = this.add(addViLectureExam);

    log.info("추가된 시험지 = {}", addedLectureExam);
    // 추가 된 시험지의 시험지 아이디
    UUID lectureExamId = addedLectureExam.getLectureExamId();
    // 추가된 시험의 강의 아이디
    UUID addedLectureId = addedLectureExam.getLectureId();
    // 추가된 시험의 시험지 아이디
    UUID addedExamId = addedLectureExam.getExamId();

    log.info("추가된 시험지 아이디 = {}", addedExamId);

    // 시험지에 해당 하는 문제들
    List<ViExamQuestDto> findExamQuests =
            examQuestService.getExamQuestsByExamId(new BooleanBuilder(
                    QViExam.viExam.examId.eq(addedExamId)
                            .and(QViExam.viExam.mbrId.eq(mbrId)))
            );
    // 문제들 추가
    addLectureQuests(findExamQuests, addedLectureId, lectureExamId);


    return addedLectureExam;
  }

  // 강의 시험지 추가 시 강의시험문제도 같이 등록
  private void addLectureQuests(List<ViExamQuestDto> findExamQuests, UUID addedLectureId, UUID lectureExamId) {

    for (ViExamQuestDto findExamQuest : findExamQuests) {

      ViLectureQuest viLectureQuest = ViLectureQuest.builder()
              .lectureId(addedLectureId)
              .lectureExamId(lectureExamId)
              .examItemId(findExamQuest.getExamItemId())
              .examQuestId(findExamQuest.getExamQuestId())
              .questName(findExamQuest.getQuestName())
              .questName(findExamQuest.getQuestName())
              .questTypeCode(findExamQuest.getQuestTypeCode())
              .questCont(findExamQuest.getQuestCont())
              .questFileGroupId(findExamQuest.getQuestFileGroupId())
              .choiceCont(findExamQuest.getChoiceCont())
              .score(findExamQuest.getScore())
              .answer(findExamQuest.getAnswer())
              .goldenbellYn(findExamQuest.getGoldenbellYn())
              .sortOrd(findExamQuest.getSortOrd())
              .build();

      lectureQuestService.addLectureQuest(viLectureQuest);
    }
  }

  /**
   * 강의시험지 수정
   *
   * @param in
   * @return
   */
  @Transactional(rollbackFor = IllegalAccessException.class)
  public ViLectureExam modifyLectureExam(LectureExamForm.Input.Modify in) throws IllegalAccessException{

    ViLectureExam findLectureExam = get(in.getLectureExamId());

    // 이미 시작한 수업이면
    if(findLectureExam.getExamStartDt().getMillis() < System.currentTimeMillis()) {
      throw new IllegalAccessException("이미 시작한 시험입니다");
    }


    modifyLectureExam(in, findLectureExam);

    return findLectureExam;
  }



  /**
   *
   */

  /**
   * 수정 in to ViLectureExam
   * @param in
   * @param findLectureExam
   */
  private static void modifyLectureExam(LectureExamForm.Input.Modify in, ViLectureExam findLectureExam) {
    if(StringUtils.isNotEmpty(in.getExamName())) {
      findLectureExam.setExamName(in.getExamName());
    }

    if(StringUtils.isNotEmpty(in.getExamTypeCode())) {
      findLectureExam.setExamTypeCode(in.getExamTypeCode());
    }

    if(StringUtils.isNotEmpty(in.getDescCont())) {
      findLectureExam.setDescCont(in.getDescCont());
    }

    if(StringUtils.isNotEmpty(in.getExamStartDt())) {
      findLectureExam.setExamStartDt(DateTimeUtils.getStringToDateTime(in.getExamStartDt()));
    }

    if(StringUtils.isNotEmpty(in.getExamEndDt())) {
      findLectureExam.setExamEndDt(DateTimeUtils.getStringToDateTime(in.getExamEndDt()));
    }

    if(in.getExamPeriod() != null) {
      findLectureExam.setExamPeriod(in.getExamPeriod());
    }

    if(in.getGoldenbellCttId() != null) {
      findLectureExam.setGoldenbellCttId(in.getGoldenbellCttId());
    }

    if(StringUtils.isNotEmpty(in.getMarkingYn())) {
      findLectureExam.setMarkingYn(in.getMarkingYn());
    }
  }

  private ViLectureExam changeInputToLectureExam(LectureExamForm.Input.Add in) {

    ViLectureExam viLectureExam = new ViLectureExam();
    viLectureExam.setLectureId(in.getLectureId());
    viLectureExam.setExamId(in.getExamId());
    viLectureExam.setExamName(in.getExamName());
    viLectureExam.setExamTypeCode(in.getExamTypeCode());
    viLectureExam.setDescCont(in.getDescCont());
    viLectureExam.setExamStartDt(DateTimeUtils.getStringToDateTime(in.getExamStartDt()));
    viLectureExam.setExamEndDt(DateTimeUtils.getStringToDateTime(in.getExamEndDt()));
    viLectureExam.setExamPeriod(in.getExamPeriod());
    viLectureExam.setGoldenbellCttId(in.getGoldenbellCttId());

    return viLectureExam;
  }
}
