package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import java.util.UUID;
import twentyoz.viven.feature.sy.model.QSyAttachFile;
import twentyoz.viven.feature.vi.model.CttJudgeHistDto;
import twentyoz.viven.feature.vi.model.CttJudgeHistDtoFile;
import twentyoz.viven.feature.vi.model.QViCttJudgeHist;
import twentyoz.viven.feature.vi.model.QViCttJudgeReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViCttJudgeHistRepositorySupport extends QuerydslRepositorySupport {

  public ViCttJudgeHistRepositorySupport() {
    super(ViCttJudgeHistRepositorySupport.class);
  }

  /**
   * 콘텐츠심사요청 목록 조회
   *
   * @param predicate
   * @return
   */
  public List<CttJudgeHistDto> getList(Predicate predicate) {
    final JPQLQuery<CttJudgeHistDto> query = getQuery(predicate);
    return query.fetch();
  }

  /**
   * 콘텐츠심사요청 페이징 조회
   *
   * @param predicate
   * @param page
   * @return
   */
  public Page<CttJudgeHistDto> getPage(Predicate predicate, Pageable page) {
    final JPQLQuery<CttJudgeHistDto> query =
        getQuery(predicate)
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetchCount());
  }

  /**
   * 콘텐츠심사요청 조회
   *
   * @param id 식별번호
   * @return
   */
  public CttJudgeHistDto get(UUID id) {
    QViCttJudgeHist qViCttJudgeHist = QViCttJudgeHist.viCttJudgeHist;
    BooleanBuilder predicate = new BooleanBuilder(qViCttJudgeHist.cttJudgeHistId.eq(id));
    final JPQLQuery<CttJudgeHistDto> query = getQuery(predicate);
    return query.fetchOne();
  }

  public CttJudgeHistDtoFile getFile(UUID id) {
    QViCttJudgeHist qViCttJudgeHist = QViCttJudgeHist.viCttJudgeHist;
    BooleanBuilder predicate = new BooleanBuilder(qViCttJudgeHist.cttJudgeHistId.eq(id));
    final JPQLQuery<CttJudgeHistDtoFile> query = getQueryFile(predicate);
    return query.fetchOne();
  }

  /** 방 Query (with 서버, 콘텐츠) */
  private JPQLQuery<CttJudgeHistDto> getQuery(Predicate predicate) {
    QViCttJudgeHist qViCttJudgeHist = QViCttJudgeHist.viCttJudgeHist;
    QViCttJudgeReq qViCttJudgeReq = QViCttJudgeReq.viCttJudgeReq;
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;

    final JPQLQuery<CttJudgeHistDto> query =
        from(qViCttJudgeHist)
            .select(
                Projections.constructor(
                    CttJudgeHistDto.class,
                    qViCttJudgeHist.cttJudgeHistId,
                    qViCttJudgeHist.cttJudgeReqId,
                    qViCttJudgeHist.cttTypeCode,
                    qViCttJudgeHist.cttName,
                    qViCttJudgeHist.cttDpName,
                    qViCttJudgeHist.descCont,
                    qViCttJudgeHist.binVal,
                    qViCttJudgeHist.judgeTypeCode,
                    qViCttJudgeHist.judgeReqCont,
                    qViCttJudgeHist.remarkCont,
                    qViCttJudgeHist.judgeStatusCode,
                    qViCttJudgeHist.rejectTypeCode,
                    qViCttJudgeHist.rejectReason,
                    qViCttJudgeHist.binImgGroupId,
                    qViCttJudgeHist.binFileGroupId,
                    qViCttJudgeHist.reqUserId,
                    qViCttJudgeHist.reqDt,
                    qViCttJudgeHist.procUserId,
                    qViCttJudgeHist.procDt,
                    qViCttJudgeHist.regId,
                    qViCttJudgeHist.regDt,
                    qViCttJudgeHist.attachFileGroupId,
                    qViCttJudgeReq.cttId,
                    qViCttJudgeReq.cttBinId,
                    qSyAttachFile.fileFullPath))
            .leftJoin(qViCttJudgeReq)
            .on(qViCttJudgeHist.cttJudgeReqId.eq(qViCttJudgeReq.cttJudgeReqId))
            .leftJoin(qSyAttachFile)
            .on(
                qViCttJudgeHist
                    .attachFileGroupId
                    .eq(qSyAttachFile.attachFileGroupId)
                    .and(qSyAttachFile.attachDivVal.eq("main"))
                    .and(qSyAttachFile.delYn.eq("N")))
            .where(predicate)
            .orderBy(qViCttJudgeHist.regDt.desc());
    return query;
  }

  private JPQLQuery<CttJudgeHistDtoFile> getQueryFile(Predicate predicate) {
    QViCttJudgeHist qViCttJudgeHist = QViCttJudgeHist.viCttJudgeHist;
    QViCttJudgeReq qViCttJudgeReq = QViCttJudgeReq.viCttJudgeReq;
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;

    final JPQLQuery<CttJudgeHistDtoFile> query =
        from(qViCttJudgeHist)
            .select(
                Projections.constructor(
                    CttJudgeHistDtoFile.class,
                    qViCttJudgeHist.cttJudgeHistId,
                    qViCttJudgeHist.cttJudgeReqId,
                    qViCttJudgeHist.cttTypeCode,
                    qViCttJudgeHist.cttName,
                    qViCttJudgeHist.cttDpName,
                    qViCttJudgeHist.descCont,
                    qViCttJudgeHist.binVal,
                    qViCttJudgeHist.judgeTypeCode,
                    qViCttJudgeHist.judgeReqCont,
                    qViCttJudgeHist.remarkCont,
                    qViCttJudgeHist.judgeStatusCode,
                    qViCttJudgeHist.rejectTypeCode,
                    qViCttJudgeHist.rejectReason,
                    qViCttJudgeHist.binImgGroupId,
                    qViCttJudgeHist.binFileGroupId,
                    qViCttJudgeHist.reqUserId,
                    qViCttJudgeHist.reqDt,
                    qViCttJudgeHist.procUserId,
                    qViCttJudgeHist.procDt,
                    qViCttJudgeHist.regId,
                    qViCttJudgeHist.regDt,
                    qViCttJudgeHist.attachFileGroupId,
                    qViCttJudgeReq.cttId,
                    qViCttJudgeReq.cttBinId,
                    qViCttJudgeReq.descCont,
                    ExpressionUtils.as(
                        JPAExpressions.select(qSyAttachFile.oriFileName)
                            .from(qSyAttachFile)
                            .where(
                                qViCttJudgeHist
                                    .binFileGroupId
                                    .eq(qSyAttachFile.attachFileGroupId)
                                    .and(qSyAttachFile.delYn.eq("N"))),
                        "binFileName"),
                    ExpressionUtils.as(
                        JPAExpressions.select(qSyAttachFile.fileFullPath)
                            .from(qSyAttachFile)
                            .where(
                                qViCttJudgeHist
                                    .binImgGroupId
                                    .eq(qSyAttachFile.attachFileGroupId)
                                    .and(qSyAttachFile.delYn.eq("N"))),
                        "binImgPath")))
            .leftJoin(qViCttJudgeReq)
            .on(qViCttJudgeHist.cttJudgeReqId.eq(qViCttJudgeReq.cttJudgeReqId))
            .where(predicate)
            .orderBy(qViCttJudgeHist.regDt.desc());
    return query;
  }
}
