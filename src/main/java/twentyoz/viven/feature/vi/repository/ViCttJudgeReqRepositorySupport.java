package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import java.util.UUID;
import twentyoz.viven.feature.sy.model.QSyAttachFile;
import twentyoz.viven.feature.vi.model.CttJudgeReqDto;
import twentyoz.viven.feature.vi.model.QViCttBin;
import twentyoz.viven.feature.vi.model.QViCttJudgeReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViCttJudgeReqRepositorySupport extends QuerydslRepositorySupport {

  public ViCttJudgeReqRepositorySupport() {
    super(ViCttJudgeReqRepositorySupport.class);
  }

  /**
   * 콘텐츠심사요청 목록 조회
   *
   * @param predicate
   * @return
   */
  public List<CttJudgeReqDto> getList(Predicate predicate) {
    final JPQLQuery<CttJudgeReqDto> query = getQuery(predicate);
    return query.fetch();
  }

  /**
   * 콘텐츠심사요청 페이징 조회
   *
   * @param predicate
   * @param page
   * @return
   */
  public Page<CttJudgeReqDto> getPage(Predicate predicate, Pageable page) {
    final JPQLQuery<CttJudgeReqDto> query =
        getQuery(predicate)
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetchCount());
  }

  /**
   * 콘텐츠심사요청 조회
   *
   * @param id 식별번호
   * @param reqUserId 요청사용자식별번호
   * @return
   */
  public CttJudgeReqDto get(UUID id, UUID reqUserId) {
    QViCttJudgeReq qViCttJudgeReq = QViCttJudgeReq.viCttJudgeReq;
    BooleanBuilder predicate = new BooleanBuilder(qViCttJudgeReq.cttJudgeReqId.eq(id));
    predicate.and(qViCttJudgeReq.reqUserId.eq(reqUserId));
    final JPQLQuery<CttJudgeReqDto> query = getQuery(predicate);
    return query.fetchOne();
  }

  /** 방 Query (with 서버, 콘텐츠) */
  private JPQLQuery<CttJudgeReqDto> getQuery(Predicate predicate) {
    QViCttJudgeReq qViCttJudgeReq = QViCttJudgeReq.viCttJudgeReq;
    QViCttBin qViCttBin = QViCttBin.viCttBin;
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;

    final JPQLQuery<CttJudgeReqDto> query =
        from(qViCttJudgeReq)
            .select(
                Projections.constructor(
                    CttJudgeReqDto.class,
                    qViCttJudgeReq.cttJudgeReqId,
                    qViCttJudgeReq.cttId,
                    qViCttJudgeReq.cttTypeCode,
                    qViCttJudgeReq.cttBinId,
                    qViCttJudgeReq.judgeTypeCode,
                    qViCttJudgeReq.judgeReqCont,
                    qViCttJudgeReq.cttName,
                    qViCttJudgeReq.cttDpName,
                    qViCttJudgeReq.remarkCont,
                    qViCttJudgeReq.judgeStatusCode,
                    qViCttJudgeReq.rejectTypeCode,
                    qViCttJudgeReq.rejectReason,
                    qViCttJudgeReq.reqUserId,
                    qViCttJudgeReq.reqDt,
                    qViCttJudgeReq.procUserId,
                    qViCttJudgeReq.procDt,
                    qViCttJudgeReq.regId,
                    qViCttJudgeReq.regDt,
                    qViCttJudgeReq.modId,
                    qViCttJudgeReq.modDt,
                    qViCttJudgeReq.descCont,
                    qViCttJudgeReq.attachFileGroupId,
                    qViCttJudgeReq.avtImgGroupId,
                    qViCttBin.binVal,
                    qViCttBin.descCont,
                    qViCttBin.attachImgGroupId,
                    qViCttBin.attachFileGroupId,
                    qSyAttachFile.fileFullPath))
            .leftJoin(qViCttBin)
            .on(
                qViCttJudgeReq
                    .cttId
                    .eq(qViCttBin.cttId)
                    .and(qViCttJudgeReq.cttBinId.eq(qViCttBin.cttBinId)))
            .leftJoin(qSyAttachFile)
            .on(
                qSyAttachFile
                    .attachFileGroupId
                    .eq(qViCttJudgeReq.attachFileGroupId)
                    .and(qSyAttachFile.attachDivVal.eq("main"))
                    .and(qSyAttachFile.delYn.eq("N")))
            .where(predicate)
            .orderBy(qViCttJudgeReq.regDt.desc());
    return query;
  }
}
