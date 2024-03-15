package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import java.util.UUID;

import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.sy.model.QSyAttachFile;
import twentyoz.viven.feature.vi.model.QViCtt;
import twentyoz.viven.feature.vi.model.QViCttBin;
import twentyoz.viven.feature.vi.model.QViRoom;
import twentyoz.viven.feature.vi.model.QViRoomSv;
import twentyoz.viven.feature.vi.model.RoomDto;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViRoomRepositorySupport extends QuerydslRepositorySupport {

  public ViRoomRepositorySupport() {
    super(ViRoomRepositorySupport.class);
  }

  /**
   * 방 목록 조회
   *
   * @param predicate
   * @return
   */
  public List<RoomDto> getList(Predicate predicate) {
    final JPQLQuery<RoomDto> query = getQuery(predicate, "Y");
    return query.fetch();
  }

  /**
   * 방 페이징 조회
   *
   * @param predicate
   * @param page
   * @return
   */
  public Page<RoomDto> getPage(Predicate predicate, Pageable page) {
    final JPQLQuery<RoomDto> query =
        getQuery(predicate, "Y")
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetchCount());
  }

  /**
   * 방 조회
   *
   * @param id 식별번호
   * @return
   */
  public RoomDto get(UUID id) {
    QViRoom qViRoom = QViRoom.viRoom;
    BooleanBuilder predicate = new BooleanBuilder(qViRoom.roomId.eq(id));
    final JPQLQuery<RoomDto> query = getQuery(predicate, null);
    return query.fetchOne();
  }

  /**
   * 나의 방 페이징 조회
   *
   * @param predicate
   * @param page
   * @return
   */
  public Page<RoomDto> getMyRoomPage(Predicate predicate, Pageable page, UUID mbrId) {
    BooleanBuilder builder = new BooleanBuilder(predicate);
    builder.and(QMbMbr.mbMbr.mbrId.eq(mbrId));
    final JPQLQuery<RoomDto> query =
        getQuery(builder, null)
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetchCount());
  }

  /** 방 Query (with 서버, 콘텐츠, 회원) */
  private JPQLQuery<RoomDto> getQuery(Predicate predicate, String dpYn) {
    QViRoom qViRoom = QViRoom.viRoom;
    QViRoomSv qViRoomSv = QViRoomSv.viRoomSv;
    QViCtt qViCtt = QViCtt.viCtt;
    QViCttBin qViCttBin = QViCttBin.viCttBin;
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    // 방 조건
    BooleanBuilder builder = new BooleanBuilder(predicate);

    if (dpYn != null) {
      builder.and(qViRoom.dpYn.eq(dpYn));
    }

    // 방서버 조건
    BooleanBuilder svBuilder = new BooleanBuilder();
    svBuilder.and(qViRoom.useYn.eq("Y"));
    svBuilder.and(qViRoom.delYn.eq("N"));

    // 콘텐츠 조건
    DateTime now = new DateTime();
    BooleanBuilder cttBuilder = new BooleanBuilder();
    cttBuilder.and(qViCtt.delYn.eq("N"));
    cttBuilder.and(qViCtt.useYn.eq("Y"));

    // 콘텐츠바이너리 조건
    BooleanBuilder cttBinBuilder = new BooleanBuilder();
    cttBinBuilder.and(qViCttBin.activeYn.eq("Y"));
    cttBinBuilder.and(qViCttBin.judgeYn.eq("Y"));

    // 회원 조건
    BooleanBuilder mbrBuilder = new BooleanBuilder();
    mbrBuilder.and(qMbMbr.useYn.eq("Y"));
    mbrBuilder.and(qMbMbr.delYn.eq("N"));
    mbrBuilder.and(qMbMbr.mbrTypeCode.eq(Code.MB_001_MBR.getCode()));
    mbrBuilder.and(qMbMbr.mbrStatusCode.eq(Code.MB_002_001.getCode()));

    // 첨부파일 조건
    BooleanBuilder attachBuilder = new BooleanBuilder();
    attachBuilder.and(qSyAttachFile.delYn.eq("N"));
    attachBuilder.and(qSyAttachFile.attachDivVal.eq("main"));


    final JPQLQuery<RoomDto> query =
        from(qViRoom)
            .select(
                Projections.constructor(
                    RoomDto.class,
                    qViRoom.roomId,
                    qViRoom.roomNo,
                    qViRoom.roomName,
                    qViRoom.roomSvId,
                    qViRoom.cttId,
                    qViRoom.mbrId,
                    qViRoom.publicYn,
                    qViRoom.reservYn,
                    qViRoom.onlineYn,
                    qViRoom.startReservTime,
                    qViRoom.endReservTime,
                    qViRoom.perLimit,
                    qViRoom.connPer,
                    qViRoom.invLink,
                    qViRoom.descCont,
                    qViRoom.keyword,
                    qViRoom.dpYn,
                    qViRoom.regId,
                    qViRoom.regDt,
                    qViRoom.modId,
                    qViRoom.modDt,
                    qViRoom.roomTypeCode,
                    qViCtt.cttName,
                    qViCtt.cttDpName,
                    qViCtt.cttDataTypeCode,
                    qViRoomSv.roomSvName,
                    qViCttBin.cttBinId,
                    qViCttBin.binVal,
                    qViCttBin.cttPropCont,
                    qMbMbr.nickname,
                    qMbMbr.avtProfileFilePath,
                    qSyAttachFile.fileFullPath,
                    qSyAttachFile.attachFileId))
            .leftJoin(qViRoomSv)
            .on(qViRoom.roomSvId.eq(qViRoomSv.roomSvId).and(svBuilder))
            .leftJoin(qViCtt)
            .on(qViRoom.cttId.eq(qViCtt.cttId).and(cttBuilder))
            .leftJoin(qViCttBin)
            .on(qViRoom.cttId.eq(qViCttBin.cttId).and(cttBinBuilder))
            .leftJoin(qMbMbr)
            .on(qViRoom.mbrId.eq(qMbMbr.mbrId).and(mbrBuilder))
            .leftJoin(qSyAttachFile)
            .on(qSyAttachFile.attachFileGroupId.eq(qViCtt.attachFileGroupId).and(attachBuilder))
            .where(builder)
            .orderBy(qViRoom.regDt.desc());
    return query;
  }
}
