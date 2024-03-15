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

import java.util.ArrayList;
import java.util.List;
import twentyoz.viven.feature.vi.model.ViExamItem;
import twentyoz.viven.feature.vi.model.QViExamItem;
import twentyoz.viven.feature.vi.model.ViExamItemDto;
import twentyoz.viven.feature.vi.repository.ViExamItemRepository;
import twentyoz.viven.feature.vi.mapper.ViExamItemMapper;
import twentyoz.viven.feature.vi.repository.ViExamItemRepositorySupport;
import twentyoz.viven.webapi.client.vi.form.ExamItemForm;

import java.util.UUID;

/**
 * 시험지문제 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ExamItemService {

  private final ViExamItemRepository repository;
  private final ViExamItemMapper mapper;
  private final ViExamItemRepositorySupport repositorySupport;

  /**
   * 시험지문제 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViExamItem> getList(Predicate search) {
    return (List<ViExamItem>) repository.findAll(search, Sort.by(new Order(Direction.ASC,"sortOrd")));
  }

  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViExamItemDto> getSupportList(Predicate search) {
      return repositorySupport.getSupportList(search);
  }

  /**
   * 시험지문제 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViExamItem> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViExamItemDto> getSupportPage(Predicate search, Pageable page) {
    return repositorySupport.getSupportPage(search, page);
  }





  /**
   * 시험지문제 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViExamItem get(UUID id) {
    return repository
        .findOne(new BooleanBuilder(QViExamItem.viExamItem.examItemId.eq(id)))
        .orElse(null);
  }

  /**
   * 시험지문제 등록
   * @return
   */
  private List<ViExamItem> add(List<ViExamItem> entity) {

    return repository.saveAll(entity);

  }

  /**
   * 시험지문제 수정
   *
   * @return
   */

  private ViExamItem modify(ExamItemForm.Input.ModifyAll.Modify examItem, ViExamItem findExamItem) {
    return mapper.modify(examItem, findExamItem);
  }


  /**
  * 시험지문제 삭제
  *
  * @param id 식별번호
  */
  public void remove(UUID id){
    repository.deleteById(id);
  }


  /**
  * 시험지문제 다중 삭제
  *
  * @param ids 식별번호 목록
  */
  public void remove(List<UUID> ids){
    if(!ids.isEmpty()) {
      List<ViExamItem> list = this.getList(
          new BooleanBuilder(QViExamItem.viExamItem.examItemId.in(ids)));
        for (ViExamItem viExamItem : list) {
            UUID id = viExamItem.getId();
            remove(id);
        }
    }
  }

  /**
   * 시험지문제 등록
   *
   * @return
   */
  public List<ViExamItem> addExamItem(ExamItemForm.Input.AddAll entities, UUID mbrId) {

    List<ExamItemForm.Input.AddAll.Add> examItemList = entities.getAddList();

    List<ViExamItem> addList = new ArrayList<>();

    for (ExamItemForm.Input.AddAll.Add examItem : examItemList) {

//      if(examItem.getSortOrd() == null) {
//        examItem.setSortOrd(1);
//      }

      ViExamItem viExamItemBuilder = ViExamItem.builder()
              .examId(examItem.getExamId())
              .examQuestId(examItem.getExamQuestId())
              .mbrId(mbrId)
              .score(examItem.getScore())
              .sortOrd(examItem.getSortOrd())
              .build();

      addList.add(viExamItemBuilder);
    }

    return this.add(addList);
  }

  /**
   * 시험지문제 다중 수정
   *
   * @return
   */
  public List<ViExamItem> modifyExamItem(ExamItemForm.Input.ModifyAll entities) {

    List<ExamItemForm.Input.ModifyAll.Modify> in = entities.getModifyList();

    List<ViExamItem> modifyList = new ArrayList<>();

    for (ExamItemForm.Input.ModifyAll.Modify examItem : in) {

      ViExamItem findExamItem = this.get(examItem.getExamItemId());
      ViExamItem modifyExamItem = modify(examItem, findExamItem);

      modifyList.add(modifyExamItem);
    }

    return modifyList;
  }


}
