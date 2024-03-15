package twentyoz.viven.feature.vi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.Predicate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import java.util.List;

import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.vi.model.*;
import twentyoz.viven.feature.vi.model.QViExamItem;
import twentyoz.viven.feature.vi.model.QViExamQuest;
import twentyoz.viven.feature.vi.repository.ViExamItemRepository;
import twentyoz.viven.feature.vi.repository.ViExamQuestRepository;
import twentyoz.viven.feature.vi.mapper.ViExamQuestMapper;
import twentyoz.viven.feature.vi.repository.ViExamQuestRepositorySupport;

import java.util.UUID;

/**
 * 시험문제 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ExamQuestService {

  private final ViExamQuestRepository repository;
  private final ViExamQuestMapper mapper;
  private final ExamItemService examItemService;
  private final ViExamItemRepository examItemRepository;
  private final ViExamQuestRepositorySupport repositorySupport;

  /**
   * 시험문제 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  @SneakyThrows
  public List<ViExamQuest> getList(Predicate search) {

//    // 객관식일 경우
//    if(in.getQuestTypeCode().equals(Code.EXAM_001_001.getCode()) || in.getQuestTypeCode().equals(Code.EXAM_001_002.getCode())) {
//
//
//    }

    return (List<ViExamQuest>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 시험지에 해당하는 시험문제 리스트 조회
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  @SneakyThrows
  public List<ViExamQuestDto> getExamQuestsByExamId(Predicate search) {

    return repositorySupport.getExamQuestsByExamId(search);
  }

  /**
   * 시험문제 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViExamQuest> getPage(Predicate search, Pageable page) {

    return repository.findAll(search, page);
  }

  /**
   * 시험문제 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViExamQuest get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViExamQuest.viExamQuest.examQuestId.eq(id)))
        .orElse(null);
  }

  /**
   * 시험문제 등록
   *
   * @param entity
   * @return
   */
  private ViExamQuest add(ViExamQuest entity) {
    return repository.save(entity);
  }

  /**
   * 시험문제 수정
   *
   * @param entity
   * @return
   */
  private ViExamQuest modify(UUID id, ViExamQuest entity) {

    return mapper.modify(entity, get(id));
  }

  /**
  * 시험문제 삭제
  *
  * @param id 식별번호
  */
  public void remove(UUID id){
    repository.deleteById(id);
  }


  /**
  * 시험문제 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  public void remove(List<UUID> ids){
    if(!ids.isEmpty()) {
      List<ViExamQuest> list = this.getList(
          new BooleanBuilder(QViExamQuest.viExamQuest.examQuestId.in(ids)));
        repository.deleteAll(list);

      /**
       * 시험지문제의 문제도 같이 삭제해야 된다.
       */
      List<ViExamItem> deleteExamItemList = examItemService.
              getList(new BooleanBuilder(QViExamItem.viExamItem.examQuestId.in(ids)));

      examItemRepository.deleteAll(deleteExamItemList);

    }


  }

  /**
   * 시험문제 등록
   *
   * @param entity
   * @return
   */
  @SneakyThrows
  public ViExamQuest addExamQuest(ViExamQuest entity, UUID mbrId) {

    entity.setMbrId(mbrId);
    // OX 일 경우 골든벨 가능
    if(entity.getQuestTypeCode().equals(Code.EXAM_001_003.getCode())) {
      entity.setGoldenbellYn("Y");
    }

    return this.add(entity);
  }

  /**
   * 시험문제 수정
   *
   * @param entity
   * @return
   */
  public ViExamQuest modifyExamQuest(UUID id, ViExamQuest entity) {

    // OX 일 경우 골든벨 가능
    if(entity.getQuestTypeCode().equals(Code.EXAM_001_003.getCode())) {
      entity.setGoldenbellYn("Y");
    }

    return this.modify(id, entity);
  }
}
