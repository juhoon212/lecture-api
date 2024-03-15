package twentyoz.viven.feature.mb.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.UUID;
import twentyoz.viven.feature.mb.model.MbrDto;
import twentyoz.viven.feature.mb.model.MbrDtoDetail;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.sy.model.QSyAttachFile;
import twentyoz.viven.feature.vi.model.QViCtt;
import twentyoz.viven.feature.vi.model.QViCttBin;
import twentyoz.viven.feature.vi.model.QViFrnd;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MbrRepositorySupport extends QuerydslRepositorySupport {

  public MbrRepositorySupport() {
    super(MbrRepositorySupport.class);
  }

  /**
   * 회원정보 + 바이너리파일경로
   *
   * @param id
   * @return
   */
  public MbrDto getDto(UUID id) {
    QMbMbr qMbMbr = QMbMbr.mbMbr;
    BooleanBuilder predicate = new BooleanBuilder(qMbMbr.mbrId.eq(id));
    final JPQLQuery<MbrDto> query = getQuery(predicate);
    return query.fetchOne();
  }

  private JPQLQuery<MbrDto> getQuery(Predicate predicate) {
    QMbMbr qMbMbr = QMbMbr.mbMbr;
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;
    QViCtt qViCtt = QViCtt.viCtt;
    QViCttBin qViCttBin = QViCttBin.viCttBin;

    final JPQLQuery<MbrDto> query =
        from(qMbMbr)
            .select(
                Projections.constructor(
                    MbrDto.class,
                    qMbMbr.mbrId,
                    qMbMbr.loginId,
                    qMbMbr.mbrName,
                    qMbMbr.nickname,
                    qMbMbr.mbrStatusCode,
                    qMbMbr.profileBgColor,
                    qMbMbr.avtBodyFilePath,
                    qMbMbr.avtProfileFilePath,
                    qMbMbr.tempPwYn,
                    qMbMbr.pwValidDt,
                    qSyAttachFile.fileFullPath,
                    qViCtt.cttId,
                    qViCtt.cttDpName,
                    qViCtt.cttTypeCode,
                    qViCtt.cttDataTypeCode,
                    qViCttBin.cttBinId,
                    qViCttBin.binVal))
            .leftJoin(qViCtt)
            .on(qMbMbr.cttId.eq(qViCtt.cttId))
            .leftJoin(qViCttBin)
            .on(qMbMbr.cttId.eq(qViCttBin.cttId).and(qViCttBin.activeYn.eq("Y")))
            .leftJoin(qSyAttachFile)
            .on(qViCttBin.attachFileGroupId.eq(qSyAttachFile.attachFileGroupId))
            .where(predicate)
            .orderBy(qMbMbr.regDt.desc());
    return query;
  }

  /**
   * 회원 조회
   *
   * @param id
   * @return
   */
  public MbrDtoDetail get(UUID id) {
    final JPQLQuery<MbrDtoDetail> query = getQueryOne(id);
    return query.fetchOne();
  }

  /** 회원 단건 조회 (with 친구 정보) */
  private JPQLQuery<MbrDtoDetail> getQueryOne(UUID id) {
    QViFrnd qViFrnd = QViFrnd.viFrnd;
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    BooleanBuilder predicate = new BooleanBuilder();
    predicate.and(qMbMbr.mbrId.eq(id));

    BooleanBuilder builder = new BooleanBuilder();
    builder.and(qViFrnd.mbrId.eq(id));
    builder.and(qViFrnd.frndMbrId.eq(UserInfo.getUserId()));

    final JPQLQuery<MbrDtoDetail> query =
        from(qMbMbr)
            .select(
                Projections.constructor(
                    MbrDtoDetail.class,
                    qMbMbr.mbrId,
                    qMbMbr.nickname,
                    qMbMbr.lastLoginDt,
                    qMbMbr.onlineYn,
                    qMbMbr.profileBgColor,
                    qMbMbr.avtBodyFilePath,
                    qMbMbr.avtProfileFilePath,
                    qViFrnd.frndId))
            .leftJoin(qViFrnd)
            .on(builder)
            .where(predicate);
    return query;
  }
}
