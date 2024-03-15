package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.common.model.RegEntity;
import twentyoz.viven.util.RequestUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;

/** 방접속이력 */
@Entity
@Table(schema = "vi", name = "vi_room_conn_hist")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViRoomConnHist extends RegEntity implements Identifiable<UUID> {

  /** 방접속이력식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "room_conn_hist_id", nullable = false)
  private UUID roomConnHistId;

  /** 방식별번호 */
  @Column(name = "room_id")
  private UUID roomId;

  /** 방번호 */
  @Column(name = "room_no")
  private String roomNo;

  /** 방이름 */
  @Column(name = "room_name")
  private String roomName;

  /** 회원식별번호 */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /** 로그인아이디 */
  @Column(name = "login_id")
  private String loginId;

  /** 회원명 */
  @Column(name = "mbr_name")
  private String mbrName;

  /** 닉네임 */
  @Column(name = "nickname")
  private String nickname;

  /** 아이피주소 */
  @Column(name = "ip_addr")
  private String ipAddr;

  /** 방접속유형코드 */
  @Column(name = "room_conn_type_code")
  private String roomConnTypeCode;

  /** 접속일시 */
  @Column(name = "conn_dt")
  private DateTime connDt;

  /** 접속해제일시 */
  @Column(name = "dcn_dt")
  private DateTime dcnDt;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return roomConnHistId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  /** 엔티티 기본 값 설정 */
  @Override
  public void defaultValue() {
    super.defaultValue();

    ipAddr =
        ObjectUtils.defaultIfNull(ipAddr, RequestUtils.getCurrentIP(RequestUtils.getRequest()));
    roomConnTypeCode = ObjectUtils.defaultIfNull(roomConnTypeCode, Code.ROOM_001_ENTER.getCode());
  }
}
