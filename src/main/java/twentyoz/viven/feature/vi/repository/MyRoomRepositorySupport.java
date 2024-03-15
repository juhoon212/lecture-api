package twentyoz.viven.feature.vi.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.UUID;
import twentyoz.viven.feature.mb.model.QMbMbr;
import twentyoz.viven.feature.sy.model.QSyAttachFile;
import twentyoz.viven.feature.vi.model.QViCtt;
import twentyoz.viven.feature.vi.model.QViCttBin;
import twentyoz.viven.feature.vi.model.QViRoom;
import twentyoz.viven.feature.vi.model.QViRoomSv;
import twentyoz.viven.feature.vi.model.RoomDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MyRoomRepositorySupport extends QuerydslRepositorySupport {

  public MyRoomRepositorySupport() {
    super(MyRoomRepositorySupport.class);
  }

  /**
   * 마이룸 조회
   *
   * @param mbrId 회원식별번호
   * @return
   */
  public RoomDto getMyRoom(UUID mbrId) {
    QViRoom qViRoom = QViRoom.viRoom;
    BooleanBuilder predicate = new BooleanBuilder(qViRoom.roomId.eq(mbrId));
    predicate.and(qViRoom.mbrId.eq(mbrId));
    predicate.and(qViRoom.myroomYn.eq("Y"));
    final JPQLQuery<RoomDto> query = getQuery(predicate, null);
    return query.fetchOne();
  }

  /** 마이룸 Query (with 서버, 콘텐츠, 회원) */
  private JPQLQuery<RoomDto> getQuery(Predicate predicate, String dpYn) {
    QViRoom qViRoom = QViRoom.viRoom;
    QViRoomSv qViRoomSv = QViRoomSv.viRoomSv;
    QViCtt qViCtt = QViCtt.viCtt;
    QViCttBin qViCttBin = QViCttBin.viCttBin;
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;
    QMbMbr qMbMbr = QMbMbr.mbMbr;

    BooleanBuilder builder = new BooleanBuilder();
    builder.and(qViRoom.delYn.eq("N"));
    builder.and(qViRoom.useYn.eq("Y"));
    builder.and(qViRoom.myroomYn.eq("Y"));

    if (dpYn != null) {
      builder.and(qViRoom.dpYn.eq(dpYn));
    }

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
            .on(qViRoom.roomSvId.eq(qViRoomSv.roomSvId))
            .leftJoin(qViCtt)
            .on(qViRoom.cttId.eq(qViCtt.cttId))
            .leftJoin(qViCttBin)
            .on(qViRoom.cttId.eq(qViCttBin.cttId))
            .leftJoin(qMbMbr)
            .on(qViRoom.mbrId.eq(qMbMbr.mbrId))
            .leftJoin(qSyAttachFile)
            .on(
                qSyAttachFile
                    .attachFileGroupId
                    .eq(qViCtt.attachFileGroupId)
                    .and(qSyAttachFile.delYn.eq("N"))
                    .and(qSyAttachFile.attachDivVal.eq("main")))
            .on(qSyAttachFile.attachDivVal.eq("main"))
            .where(predicate, builder)
            .orderBy(qViRoom.regDt.desc());
    return query;
  }
}
