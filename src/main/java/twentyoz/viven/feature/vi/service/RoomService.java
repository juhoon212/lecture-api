package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.feature.mb.model.MbMbr;
import twentyoz.viven.feature.mb.service.MbrService;
import twentyoz.viven.feature.vi.mapper.ViRoomMapper;
import twentyoz.viven.feature.vi.model.QViRoom;
import twentyoz.viven.feature.vi.model.RoomDto;
import twentyoz.viven.feature.vi.model.ViRoom;
import twentyoz.viven.feature.vi.model.ViRoomConnHist;
import twentyoz.viven.feature.vi.model.ViRoomSv;
import twentyoz.viven.feature.vi.repository.ViRoomRepository;
import twentyoz.viven.feature.vi.repository.ViRoomRepositorySupport;
import twentyoz.viven.util.UidUtils;

/** 방 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

  private final ViRoomRepository repository;
  private final ViRoomMapper mapper;

  private final ViRoomRepositorySupport repositorySupport;

  private final MbrService mbrService;
  private final RoomSvService roomSvService;

  private final Environment environment;

  private final RoomConnHistService roomConnHistService;

  /**
   * 방 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViRoom> getList(Predicate search) {
    return (List<ViRoom>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 방 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViRoom> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 방 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViRoom get(UUID id) {
    return repository.findOne(new BooleanBuilder(QViRoom.viRoom.roomId.eq(id))).orElse(null);
  }

  /**
   * 방 등록
   *
   * @param entity
   * @return
   */
  private ViRoom add(ViRoom entity) {
    return repository.save(entity);
  }

  /**
   * 방 수정
   *
   * @param entity
   * @return
   */
  private ViRoom modify(UUID id, ViRoom entity) {
    return mapper.modify(entity, get(id));
  }

  /**
   * 방 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    this.get(id).delTrue();
  }

  /**
   * 방 다중 삭제
   *
   * @param ids 식별번호 목록
   */
  public void remove(List<UUID> ids) {
    if (ids.size() > 0) {
      List<ViRoom> list = this.getList(new BooleanBuilder(QViRoom.viRoom.roomId.in(ids)));
      for (ViRoom item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 방 번호로 방 조회
   *
   * @param roomNo 방번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViRoom get(String roomNo) {
    return repository.findOne(new BooleanBuilder(QViRoom.viRoom.roomNo.eq(roomNo))).orElse(null);
  }

  /**
   * 방 등록
   *
   * @param entity
   * @return
   */
  public ViRoom addViRoom(UUID mbrId, ViRoom entity, HttpServletRequest request) {
    UUID uuid = UidUtils.getUuid();

    entity.setRoomId(uuid);
    entity.setMbrId(mbrId);
    entity.setRegId(mbrId);
    entity.setUseYn("Y");

    if (StringUtils.isEmpty(entity.getRoomNo())) {
      entity.setRoomNo(
          UidUtils.getUniqueId(
              ViRoom.PREFIX_ROOM_NO, UidUtils.ID_PREFIX_ZEROFILL, uuid.toString().split("-")[0]));
    } else {
      if (this.get(entity.getRoomNo()) != null) {
        throw new IllegalStateException("사용중인 방 번호 입니다.");
      }
    }

    // TODO : 단축 URL 처리 필요
    String invLink = environment.getProperty("app.room.shareUrl") + "/" + UidUtils.getUuid();
    entity.setInvLink(invLink);

    return this.add(entity);
  }

  /**
   * 방 수정
   *
   * @param entity
   * @return
   */
  public ViRoom modifyViRoom(UUID id, ViRoom entity)
      throws IllegalAccessException, IllegalStateException {

    RoomDto roomDto = getSupport(id);
    MbMbr mbr = mbrService.getMbr(roomDto.getRm().getMbrId());
    if (mbr == null) {
      throw new IllegalStateException("잘못된 접근입니다.");
    }

    if (!roomDto.getIsCreator()) {
      throw new IllegalStateException("본인이 생성한 방이 아닙니다.");
    }
    entity.setRoomId(id);
    entity.setModId(mbr.getMbrId());
    ViRoom room = get(id);

    if (entity.getRoomNo() != null) {
      if (!room.getRoomNo().equals(entity.getRoomNo()) && this.get(entity.getRoomNo()) != null) {
        throw new IllegalStateException("사용중인 방 번호 입니다.");
      }
    }

    if ("N".equals(entity.getReservYn())) {
      entity.setEndReservTime(null);
      entity.setStartReservTime(null);
    }

    return mapper.modify(entity, room);
  }

  /**
   * 방 목록 (with 컨텐츠, 서버)
   *
   * @param search
   * @return
   */
  public List<RoomDto> getSupportList(Predicate search) {
    return repositorySupport.getList(search);
  }

  /**
   * 방 페이징 목록 (with 컨텐츠, 서버)
   *
   * @param search
   * @return
   */
  public Page<RoomDto> getSupportPage(Predicate search, Pageable page) {
    return repositorySupport.getPage(search, page);
  }

  /**
   * 나의 방 페이징 목록 (with 컨텐츠, 서버)
   *
   * @param search
   * @return
   */
  public Page<RoomDto> getMyRoomSupportPage(Predicate search, Pageable page, UUID mbrId) {
    return repositorySupport.getMyRoomPage(search, page, mbrId);
  }

  /**
   * 방 조회
   *
   * @param id 식별번호
   * @return
   */
  public RoomDto getSupport(UUID id) {

    // 방 조회
    RoomDto roomDto = repositorySupport.get(id);

    // 1. isCreator: 로그인 한 경우 판단.
    if (UserInfo.isLogin() && UserInfo.getUserId().equals(roomDto.getRm().getMbrId())) {
      roomDto.setIsCreator(true);
    } else {
      roomDto.setIsCreator(false);
    }

    // 2. 권한 검증: 전시여부가 N이고 작성자가 아닐 시.
    if (roomDto.getRm().getDpYn().equals("N") && !roomDto.getIsCreator()) {
      throw new EntityNotFoundException("잘못된 접근입니다.");
    }

    return repositorySupport.get(id);
  }

  /**
   * 방 비밀번호 체크
   *
   * @param id
   * @param pw
   * @return
   */
  public ViRoom checkPassword(UUID id, String pw) {
    ViRoom result = repository.findById(id).orElse(null);
    if (result.getRoomPw().equals(pw)) {
      return result;
    }
    return null;
  }

  /**
   * 방 참가
   *
   * @param id
   * @param mbrId
   * @param mbrName
   * @param nickName
   * @param loginId
   * @return
   */
  public ViRoomSv joinRoom(UUID id, UUID mbrId, String mbrName, String nickName, String loginId) {
    ViRoom room = this.get(id);

    ViRoomSv roomSv = roomSvService.getBestSv(room.getRoomSvId());
    if (roomSv == null) {
      room.setRoomSvId(null);
      throw new IllegalStateException("방 서버가 존재하지 않습니다.");
    }

    room.setRoomSvId(roomSv.getRoomSvId());

    // 방 접속이력 등록
    roomConnHistService.addRoomConnHist(
        ViRoomConnHist.builder()
            .roomId(id)
            .roomNo(room.getRoomNo())
            .roomName(room.getRoomName())
            .mbrId(mbrId)
            .loginId(loginId)
            .mbrName(mbrName)
            .nickname(nickName)
            .connDt(new DateTime())
            .build());

    return roomSv;
  }
}
