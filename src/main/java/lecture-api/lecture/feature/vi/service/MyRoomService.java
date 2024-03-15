package twentyoz.viven.feature.vi.service;

import java.util.UUID;
import twentyoz.viven.feature.vi.mapper.ViRoomMapper;
import twentyoz.viven.feature.vi.model.ViRoom;
import twentyoz.viven.feature.vi.repository.ViRoomRepository;
import twentyoz.viven.util.UidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 마이룸 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class MyRoomService {

  private final MyRoomRepositorySupport repositorySupport;

  private final Environment environment;

  private final ViRoomRepository repository;

  private final ViRoomMapper mapper;

  /**
   * 내 방 조회
   *
   * @param mbrId 회원식별번호
   * @return
   */
  public RoomDto getMyRoom(UUID mbrId) {
    return repositorySupport.getMyRoom(mbrId);
  }

  /**
   * 마이룸 등록
   *
   * @param mbrId 회원식별번호
   * @param nickName 닉네임
   * @return
   */
  public ViRoom addMyViRoom(UUID mbrId, String nickName) {
    String invLink = environment.getProperty("app.myRoom.shareUrl") + "/" + UidUtils.getUuid();

    ViRoom entity = new ViRoom();
    entity.setRoomId(mbrId);
    entity.setRoomName(nickName + "님의 방");
    entity.setCttId(UUID.fromString(environment.getProperty("app.myroom.defaultMapId")));
    entity.setMbrId(mbrId);
    entity.setOnlineYn("Y");
    entity.setPublicYn("Y");
    entity.setDpYn("Y");
    entity.setReservYn("N");
    entity.setPerLimit(Integer.parseInt(environment.getProperty("app.myroom.defaultPerLimit")));
    entity.setInvLink(invLink);
    entity.setDescCont(nickName + "님의 방에 놀러오세요!!");
    entity.setKeyword(nickName);
    entity.setMyroomYn("Y");
    entity.setRegId(mbrId);
    // 임시 확인해야함
    entity.setRoomTypeCode("ROOM_002");

    ViRoom room = repository.save(entity);
    String roomNo =
        UidUtils.getUniqueId(
            ViRoom.PREFIX_ROOM_NO,
            UidUtils.ID_PREFIX_ZEROFILL,
            room.getId().toString().split("-")[0]);
    room.setRoomNo(roomNo);

    return room;
  }

  /**
   * 마이룸 맵 수정
   *
   * @param mbrId 회원식별번호
   * @param cttId 콘텐츠식별번호 (맵 식별번호)
   * @return
   */
  public ViRoom modifyRoomMyMap(UUID mbrId, UUID cttId) {
    // 방식별번호는 회원식별번호와 동일
    ViRoom room = repository.getOne(mbrId);
    if (room == null) {
      throw new IllegalArgumentException("마이룸이 없습니다.");
    }
    ViRoom entity = new ViRoom();
    entity.setCttId(cttId);

    return mapper.modify(entity, room);
  }
}
