package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import java.util.UUID;

import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.sy.model.QSyAttachFile;
import twentyoz.viven.feature.vi.model.QViCtt;
import twentyoz.viven.feature.vi.model.QViRoom;
import twentyoz.viven.feature.vi.model.QViRoomConnHist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViRoomConnHistRepositorySupport extends QuerydslRepositorySupport {

  public ViRoomConnHistRepositorySupport() {
    super(ViRoomConnHistRepositorySupport.class);
  }

  /**
   * 나의 방 접속이력 목록 조회
   *
   * @return
   */
  public List<ViRoomConnHistDto> getList(UUID mbrId) {
    final JPQLQuery<ViRoomConnHistDto> query = getQuery(mbrId);
    return query.fetch();
  }

  /**
   * 나의 방 접속이력 페이징 조회
   *
   * @param page
   * @return
   */
  public Page<ViRoomConnHistDto> getPage(Pageable page, UUID mbrId) {
    final JPQLQuery<ViRoomConnHistDto> query =
        getQuery(mbrId)
            .offset((page.getPageNumber() * page.getPageSize()))
            .limit(page.getPageSize());
    return new PageImpl<>(query.fetch(), page, query.fetch().size());
  }

  /** 나의 방 접속이력 목록 조회 쿼리 */
  private JPQLQuery<ViRoomConnHistDto> getQuery(UUID mbrId) {
    QViRoomConnHist qViRoomConnHist = QViRoomConnHist.viRoomConnHist;
    QViRoom qViRoom = QViRoom.viRoom;
    QMbMbr qMbMbr = QMbMbr.mbMbr;
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;
    QViCtt qViCtt = QViCtt.viCtt;

    BooleanBuilder builder = new BooleanBuilder();
    builder.and(qViRoomConnHist.mbrId.eq(mbrId));
    builder.and(qViRoomConnHist.roomConnTypeCode.eq(Code.ROOM_001_ENTER.getCode()));

    final JPQLQuery<ViRoomConnHistDto> query =
        from(qViRoomConnHist)
            .select(
                Projections.constructor(
                    ViRoomConnHistDto.class,
                    qViRoomConnHist.roomId,
                    qViRoomConnHist.roomNo,
                    qViRoomConnHist.roomName,
                    qViRoom.descCont,
                    qViRoom.keyword,
                    qMbMbr.nickname,
                    qViRoom.publicYn,
                    qViRoom.perLimit,
                    qViRoom.connPer,
                    qViRoom.reservYn,
                    qViRoom.startReservTime,
                    qViRoom.endReservTime,
                    qViRoom.roomTypeCode,
                    qViCtt.cttDpName,
                    qSyAttachFile.fileFullPath,
                    qSyAttachFile.attachFileId))
            .leftJoin(qViRoom)
            .on(qViRoomConnHist.roomId.eq(qViRoom.roomId))
            .leftJoin(qMbMbr)
            .on(qMbMbr.mbrId.eq(qViRoomConnHist.mbrId))
            .leftJoin(qViCtt)
            .on(qViRoom.cttId.eq(qViCtt.cttId))
            .leftJoin(qSyAttachFile)
            .on(
                qSyAttachFile
                    .attachFileGroupId
                    .eq(qViCtt.attachFileGroupId)
                    .and(qSyAttachFile.delYn.eq("N"))
                    .and(qSyAttachFile.attachDivVal.eq("main")))
            .where(builder)
            .groupBy(
                qViRoomConnHist.roomId,
                qViRoomConnHist.roomNo,
                qViRoomConnHist.roomName,
                qViRoom.descCont,
                qViRoom.keyword,
                qMbMbr.nickname,
                qViRoom.publicYn,
                qViRoom.perLimit,
                qViRoom.connPer,
                qViRoom.reservYn,
                qViRoom.startReservTime,
                qViRoom.endReservTime,
                qViRoom.roomTypeCode,
                qViCtt.cttDpName,
                qSyAttachFile.fileFullPath,
                qSyAttachFile.attachFileId)
            .orderBy(qViRoomConnHist.connDt.max().desc());
    return query;
  }
}
