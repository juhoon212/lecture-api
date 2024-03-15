package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

/** 방서버이력 */
@Entity
@Table(schema = "vi", name = "vi_room_sv_hist")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViRoomSvHist extends RegEntity implements Identifiable<UUID> {

  /** 방서버이력식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "room_sv_hist_id", nullable = false)
  private UUID roomSvHistId;

  /** 방서버식별번호 */
  @Column(name = "room_sv_id")
  private UUID roomSvId;

  /** 방서버번호 */
  @Column(name = "room_sv_no")
  private String roomSvNo;

  /** 방서버명 */
  @Column(name = "room_sv_name")
  private String roomSvName;

  /** 서버이력유형코드 */
  @Column(name = "sv_hist_type_code")
  private String svHistTypeCode;

  /** 로그유형코드 */
  @Column(name = "log_type_code")
  private String logTypeCode;

  /** 메시지내용 */
  @Column(name = "msg_cont")
  private String msgCont;

  /** 아이피주소 */
  @Column(name = "ip_addr")
  private String ipAddr;

  /** 발생일시 */
  @Column(name = "occur_dt")
  private DateTime occurDt;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return roomSvHistId;
  }
}
