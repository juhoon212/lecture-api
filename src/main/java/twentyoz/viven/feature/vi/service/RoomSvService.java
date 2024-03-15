package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.vi.mapper.ViRoomSvMapper;
import twentyoz.viven.feature.vi.model.QViRoomSv;
import twentyoz.viven.feature.vi.model.ViRoomSv;
import twentyoz.viven.feature.vi.repository.ViRoomSvRepository;
import twentyoz.viven.util.UidUtils;

/** 방서버 서비스 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoomSvService {

  private final ViRoomSvRepository repository;
  private final ViRoomSvMapper mapper;

  /**
   * 방서버 목록 조회
   *
   * @param search 검색 조건
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<ViRoomSv> getList(Predicate search) {
    return (List<ViRoomSv>) repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 방서버 페이징 조회
   *
   * @param search 검색 조건
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<ViRoomSv> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 방서버 상세 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViRoomSv get(UUID id) {
    return repository.findOne(new BooleanBuilder(QViRoomSv.viRoomSv.roomSvId.eq(id))).orElse(null);
  }

  /**
   * 조회
   *
   * @param roomSvNo 방서버번호
   * @return
   */
  @Transactional(readOnly = true)
  public ViRoomSv get(String roomSvNo) {
    return repository
        .findOne(new BooleanBuilder(QViRoomSv.viRoomSv.roomSvNo.eq(roomSvNo)))
        .orElse(null);
  }

  /**
   * 현재 가용한 방서버 조회
   *
   * @param id
   * @return ViRoomSv
   */
  public ViRoomSv getBestSv(UUID id) {
    if (id == null) {
      return repository.findTop1ByRoomSvStatusCodeAndUseYnAndDelYnOrderByModDtDesc(
          Code.SV_001_001.getCode(), "Y", "N");
    }

    ViRoomSv roomSv = repository.getOne(id);

    if (roomSv.getRoomSvStatusCode().equals(Code.MB_002_001.getCode())
        && "Y".equals(roomSv.getUseYn())
        && "N".equals(roomSv.getDelYn())) {
      return roomSv;
    }

    return repository.findTop1ByRoomSvStatusCodeAndUseYnAndDelYnOrderByModDtDesc(
        Code.SV_001_001.getCode(), "Y", "N");
  }

  /**
   * 방서버 등록
   *
   * @param entity
   * @return
   */
  private ViRoomSv add(ViRoomSv entity) {
    UUID uuid = UidUtils.getUuid();

    entity.setRoomSvId(uuid);

    /**
     * @// TODO: 2023/02/08 dts에서 방서버 할당 후, 연결된 ip 매핑 @// TODO: 2023/07/27 스키마를 위한 string 값
     * 받음(ipAddr)
     */
    entity.setAccessToken(RandomStringUtils.randomAlphanumeric(20));

    if (StringUtils.isEmpty(entity.getRoomSvNo())) {
      entity.setRoomSvNo(
          UidUtils.getUniqueId(
              ViRoomSv.PREFIX_ROOM_SV_NO,
              UidUtils.ID_PREFIX_ZEROFILL,
              uuid.toString().split("-")[0]));
    } else {
      if (this.get(entity.getRoomSvNo()) != null) {
        throw new IllegalStateException("사용중인 방 서버 번호 입니다.");
      }
    }

    return repository.save(entity);
  }

  /**
   * 방서버 수정
   *
   * @param entity
   * @return
   */
  private ViRoomSv modify(UUID id, ViRoomSv entity) {
    entity.setRoomSvId(id);
    ViRoomSv roomSv = get(id);

    if (!roomSv.getRoomSvNo().equals(entity.getRoomSvNo())
        && this.get(entity.getRoomSvNo()) != null) {
      throw new IllegalStateException("사용중인 방 서버 번호 입니다.");
    }

    return mapper.modify(entity, get(id));
  }

  /**
   * 방서버 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    this.get(id).delTrue();
  }

  /**
   * 방서버 다중 삭제
   *
   * @param ids 식별번호 목록
   */
  public void remove(List<UUID> ids) {
    if (ids.size() > 0) {
      List<ViRoomSv> list = this.getList(new BooleanBuilder(QViRoomSv.viRoomSv.roomSvId.in(ids)));
      for (ViRoomSv item : list) {
        this.remove(item.getId());
      }
    }
  }

  /**
   * 방서버 등록
   *
   * @param entity
   * @return
   */
  public ViRoomSv addViRoomSv(ViRoomSv entity) {
    return this.add(entity);
  }

  /**
   * 방서버 수정
   *
   * @param entity
   * @return
   */
  public ViRoomSv modifyViRoomSv(UUID id, ViRoomSv entity) {
    return this.modify(id, entity);
  }

  /**
   * 방서버 토큰 재발급
   *
   * @param id
   * @return
   */
  public ViRoomSv reissueToken(UUID id) {
    ViRoomSv roomSv = get(id);

    roomSv.setAccessToken(RandomStringUtils.randomAlphanumeric(20));

    return repository.save(roomSv);
  }
}
