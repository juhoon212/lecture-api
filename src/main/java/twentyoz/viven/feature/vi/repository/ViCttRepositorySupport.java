package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import java.util.UUID;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.sy.model.QSyAttachFile;
import twentyoz.viven.feature.vi.model.CttDto;
import twentyoz.viven.feature.vi.model.QViCtt;
import twentyoz.viven.feature.vi.model.QViCttBin;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViCttRepositorySupport extends QuerydslRepositorySupport {

  public ViCttRepositorySupport() {
    super(ViCttRepositorySupport.class);
  }

  /**
   * 콘텐츠 목록 조회 (판매중)
   *
   * @param predicate
   * @param isMyCtt 나의 콘텐츠 여부
   * @param mbrId 회원식별번호
   * @return
   */
  public List<CttDto> getList(Predicate predicate, boolean isMyCtt, UUID mbrId) {
    // 나의 콘텐츠를 요청하였으나 로그인하지 않은 경우 return null
    if (isMyCtt && mbrId == null) {
      return null;
    }

    BooleanBuilder builder = getSellCond(predicate, isMyCtt, mbrId);
    final JPQLQuery<CttDto> query = getQuery(builder, mbrId);
    return query.fetch();
  }

  /**
   * 콘텐츠 페이징 조회 (판매중)
   *
   * @param predicate
   * @param page
   * @param isMyCtt 나의 콘텐츠 여부
   * @param mbrId 회원식별번호
   * @return
   */
  public Page<CttDto> getPage(Predicate predicate, Pageable page, boolean isMyCtt, UUID mbrId) {
    // 나의 콘텐츠를 요청하였으나 로그인하지 않은 경우 return null
    if (isMyCtt && mbrId == null) {
      return null;
    }

    BooleanBuilder builder = getSellCond(predicate, isMyCtt, mbrId);
    final JPQLQuery<CttDto> query =
        getQuery(builder, mbrId)
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetchCount());
  }

  /**
   * 콘텐츠 조회 (판매중)
   *
   * @param id
   * @param isMyCtt 나의 콘텐츠 여부
   * @param mbrId 회원식별번호
   * @return
   */
  public CttDto get(UUID id, boolean isMyCtt, UUID mbrId) {
    // 나의 콘텐츠를 요청하였으나 로그인하지 않은 경우 return null
    if (isMyCtt && mbrId == null) {
      return null;
    }

    QViCtt qViCtt = QViCtt.viCtt;
    BooleanBuilder builder = getSellCond(new BooleanBuilder(qViCtt.cttId.eq(id)), isMyCtt, mbrId);
    final JPQLQuery<CttDto> query = getQuery(builder, mbrId);
    return query.fetchOne();
  }

  /**
   * 판매중인 콘텐츠의 기본 조건
   *
   * @param predicate
   * @param isMyCtt 나의 콘텐츠 여부
   * @param mbrId 회원식별번호
   * @return
   */
  public BooleanBuilder getSellCond(Predicate predicate, boolean isMyCtt, UUID mbrId) {
    DateTime now = new DateTime();
    QViCtt qViCtt = QViCtt.viCtt;
    BooleanBuilder builder = new BooleanBuilder();
    builder.and(qViCtt.sellStatusCode.eq(Code.CT_002_002.getCode())); // 판매중
    builder.and(qViCtt.useYn.eq("Y")); // 사용중
    builder.and(qViCtt.delYn.eq("N")); // 삭제안함

    // 나의 콘텐츠인 경우
    if (isMyCtt) {
      builder.and(qViCtt.mbrId.eq(mbrId)); // 회원식별번호

      // 나의 콘텐츠인 경우에는 전시중이 아닌 콘텐츠에도 접근 가능
    } else {
      builder.and(qViCtt.dpYn.eq("Y")); // 전시중
      builder.and(qViCtt.dpStartDt.loe(now)); // 전시기간 내
      builder.and(qViCtt.dpEndDt.goe(now)); // 전시기간 내
    }

    builder.and(predicate);
    return builder;
  }

  /**
   * 목록 Query
   *
   * @param predicate
   * @return
   */
  private JPQLQuery<CttDto> getQuery(Predicate predicate, UUID mbrId) {
    QViCtt qViCtt = QViCtt.viCtt;
    QViCttBin qViCttBin = QViCttBin.viCttBin;
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    final JPQLQuery<CttDto> query =
        from(qViCtt)
            .select(
                Projections.constructor(
                    CttDto.class,
                    qViCtt.cttId,
                    qViCtt.cttNo,
                    qViCtt.cttTypeCode,
                    qViCtt.cttName,
                    qViCtt.cttDpName,
                    qViCtt.descCont,
                    qViCtt.sellStatusCode,
                    qViCtt.cttDataTypeCode,
                    qViCtt.cttMapType,
                    qViCtt.dpYn,
                    qViCtt.dpStartDt,
                    qViCtt.dpEndDt,
                    qViCtt.attachFileGroupId,
                    qViCtt.avtImgGroupId,
                    qViCtt.useYn,
                    qViCtt.delYn,
                    qViCtt.regId,
                    qViCtt.regDt,
                    qViCtt.modId,
                    qViCtt.modDt,
                    qViCttBin.cttBinId,
                    qViCttBin.binVal,
                    qViCttBin.cttPropCont,
                    qSyAttachFile.fileFullPath,
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
                                    .avtImgGroupId
                                    .eq(qSyAttachFile.attachFileGroupId)
                                    .and(qSyAttachFile.attachDivVal.eq("profile"))
                                    .and(qSyAttachFile.delYn.eq("N"))),
                        "avtProfileFilePath"),
                    qMbMbr.nickname))
            .leftJoin(qMbMbr)
            .on(qViCtt.mbrId.eq(qMbMbr.mbrId))
            .leftJoin(qViCttBin)
            .on(qViCtt.cttId.eq(qViCttBin.cttId).and(qViCttBin.activeYn.eq("Y")))
            .leftJoin(qSyAttachFile)
            .on(
                qViCtt
                    .attachFileGroupId
                    .eq(qSyAttachFile.attachFileGroupId)
                    .and(qSyAttachFile.attachDivVal.eq("main").and(qSyAttachFile.delYn.eq("N"))))
            .where(predicate)
            .orderBy(qViCtt.regDt.desc());
    return query;
  }
}
