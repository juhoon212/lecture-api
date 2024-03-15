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
import twentyoz.viven.feature.vi.model.CttBinDto;
import twentyoz.viven.feature.vi.model.QViCttBin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViCttBinRepositorySupport extends QuerydslRepositorySupport {

  public ViCttBinRepositorySupport() {
    super(ViCttBinRepositorySupport.class);
  }

  /**
   * 콘텐츠버전 목록 조회
   *
   * @param predicate
   * @return
   */
  public List<CttBinDto> getList(Predicate predicate) {
    final JPQLQuery<CttBinDto> query = getQuery(predicate);
    return query.fetch();
  }

  /**
   * 콘텐츠버전 페이징 조회
   *
   * @param predicate
   * @param page
   * @return
   */
  public Page<CttBinDto> getPage(Predicate predicate, Pageable page) {
    final JPQLQuery<CttBinDto> query =
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
  public CttBinDto get(UUID id) {
    QViCttBin qViCttBin = QViCttBin.viCttBin;
    BooleanBuilder predicate = new BooleanBuilder(qViCttBin.cttBinId.eq(id));
    final JPQLQuery<CttBinDto> query = getQuery(predicate);
    return query.fetchOne();
  }

  private JPQLQuery<CttBinDto> getQuery(Predicate predicate) {
    QViCttBin qViCttBin = QViCttBin.viCttBin;
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;

    final JPQLQuery<CttBinDto> query =
        from(qViCttBin)
            .select(
                Projections.constructor(
                    CttBinDto.class,
                    qViCttBin.cttBinId,
                    qViCttBin.cttId,
                    qViCttBin.binVal,
                    qViCttBin.cttPropCont,
                    qViCttBin.attachImgGroupId,
                    qViCttBin.attachFileGroupId,
                    qViCttBin.descCont,
                    qViCttBin.activeYn,
                    qViCttBin.regId,
                    qViCttBin.regDt,
                    qViCttBin.modId,
                    qViCttBin.modDt,
                    ExpressionUtils.as(
                        JPAExpressions.select(qSyAttachFile.fileFullPath)
                            .from(qSyAttachFile)
                            .where(
                                qViCttBin
                                    .attachImgGroupId
                                    .eq(qSyAttachFile.attachFileGroupId)
                                    .and(qSyAttachFile.delYn.eq("N"))),
                        "attachImgPath"),
                    ExpressionUtils.as(
                        JPAExpressions.select(qSyAttachFile.oriFileName)
                            .from(qSyAttachFile)
                            .where(
                                qViCttBin
                                    .attachFileGroupId
                                    .eq(qSyAttachFile.attachFileGroupId)
                                    .and(qSyAttachFile.delYn.eq("N"))),
                        "attachFileName"),
                    ExpressionUtils.as(
                        JPAExpressions.select(qSyAttachFile.fileFullPath)
                            .from(qSyAttachFile)
                            .where(
                                qViCttBin
                                    .attachFileGroupId
                                    .eq(qSyAttachFile.attachFileGroupId)
                                    .and(qSyAttachFile.delYn.eq("N"))),
                        "attachFilePath")))
            .where(predicate)
            .orderBy(qViCttBin.regDt.desc());
    return query;
  }
}
