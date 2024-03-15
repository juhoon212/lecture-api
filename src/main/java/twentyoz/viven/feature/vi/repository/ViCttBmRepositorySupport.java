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
import twentyoz.viven.feature.vi.model.CttBmDto;
import twentyoz.viven.feature.vi.model.QViCtt;
import twentyoz.viven.feature.vi.model.QViCttBm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViCttBmRepositorySupport extends QuerydslRepositorySupport {

  public ViCttBmRepositorySupport() {
    super(ViCttBmRepositorySupport.class);
  }

  /**
   * 콘텐츠버전 목록 조회
   *
   * @param predicate
   * @return
   */
  public List<CttBmDto> getList(Predicate predicate) {
    final JPQLQuery<CttBmDto> query = getQuery(predicate);
    return query.fetch();
  }

  /**
   * 콘텐츠버전 페이징 조회
   *
   * @param predicate
   * @param page
   * @return
   */
  public Page<CttBmDto> getPage(Predicate predicate, Pageable page) {
    final JPQLQuery<CttBmDto> query =
        getQuery(predicate)
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetchCount());
  }

  /**
   * 콘텐츠버전 조회
   *
   * @param id 식별번호
   * @return
   */
  public CttBmDto get(UUID id) {
    QViCttBm qViCttBm = QViCttBm.viCttBm;
    BooleanBuilder predicate = new BooleanBuilder(qViCttBm.cttBmId.eq(id));
    final JPQLQuery<CttBmDto> query = getQuery(predicate);
    return query.fetchOne();
  }

  private JPQLQuery<CttBmDto> getQuery(Predicate predicate) {
    QViCttBm qViCttBm = QViCttBm.viCttBm;
    QViCtt qViCtt = QViCtt.viCtt;
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;

    final JPQLQuery<CttBmDto> query =
        from(qViCttBm)
            .select(
                Projections.constructor(
                    CttBmDto.class,
                    qViCttBm.cttBmId,
                    qViCttBm.cttId,
                    qViCttBm.cttBinId,
                    qViCttBm.mbrId,
                    qViCttBm.regId,
                    qViCttBm.regDt,
                    qSyAttachFile.fileFullPath,
                    qViCtt.cttTypeCode,
                    qViCtt.cttDpName,
                    qViCtt.descCont,
                    ExpressionUtils.as(
                        JPAExpressions.select(qSyAttachFile.fileFullPath)
                            .from(qSyAttachFile)
                            .where(
                                qViCtt
                                    .avtImgGroupId
                                    .eq(qSyAttachFile.attachFileGroupId)
                                    .and(qSyAttachFile.attachDivVal.eq("body"))
                                    .and(qSyAttachFile.delYn.eq("N"))),
                        "avtBodyFilePath"),
                    ExpressionUtils.as(
                        JPAExpressions.select(qSyAttachFile.fileFullPath)
                            .from(qSyAttachFile)
                            .where(
                                qViCtt
                                    .attachFileGroupId
                                    .eq(qSyAttachFile.attachFileGroupId)
                                    .and(qSyAttachFile.attachDivVal.eq("main"))
                                    .and(qSyAttachFile.delYn.eq("N"))),
                        "mainFilePath"),
                    qViCtt.mbrId))
            .leftJoin(qViCtt)
            .on(qViCtt.cttId.eq(qViCttBm.cttId))
            .leftJoin(qSyAttachFile)
            .on(
                qSyAttachFile
                    .attachFileGroupId
                    .eq(qViCtt.avtImgGroupId)
                    .and(qSyAttachFile.attachDivVal.eq("profile"))
                    .and(qSyAttachFile.delYn.eq("N")))
            .where(predicate)
            .orderBy(qViCtt.regDt.desc());
    return query;
  }
}
