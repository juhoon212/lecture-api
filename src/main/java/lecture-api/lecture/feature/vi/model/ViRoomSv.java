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
import twentyoz.viven.feature.common.model.UseYnEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

/** 방서버 */
@Entity
@Table(schema = "vi", name = "vi_room_sv")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViRoomSv extends UseYnEntity implements Identifiable<UUID> {

  public static final String PREFIX_ROOM_SV_NO = "ROOM_SV";

  /** 방서버식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "room_sv_id", nullable = false)
  private UUID roomSvId;

  /** 방서버번호 */
  @Column(name = "room_sv_no")
  private String roomSvNo;

  /** 방서버명 */
  @Column(name = "room_sv_name")
  private String roomSvName;

  /** 방서버상태코드 */
  @Column(name = "room_sv_status_code")
  private String roomSvStatusCode;

  /** 액세스토큰 */
  @Column(name = "access_token")
  private String accessToken;

  /** 아이피주소 */
  @Column(name = "ip_addr")
  private String ipAddr;

  /** 설명내용 */
  @Column(name = "desc_cont")
  private String descCont;

  /** 사용여부 */
  @Column(name = "use_yn")
  private String useYn;

  /** 삭제여부 */
  @Column(name = "del_yn")
  private String delYn;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return roomSvId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  @Override
  public void defaultValue() {
    super.defaultValue();
    useYn = ObjectUtils.defaultIfNull(useYn, "Y");
    delYn = ObjectUtils.defaultIfNull(delYn, "N");
  }

  /** 사용여부 : Y 처리 */
  public void useTrue() {
    useYn = "Y";
  }

  /** 사용여부 : N 처리 */
  public void useFalse() {
    useYn = "N";
  }

  /** 삭제여부 : Y 처리 */
  public void delTrue() {
    delYn = "Y";
  }

  /** 삭제여부 : N 처리 */
  public void delFalse() {
    delYn = "N";
  }
}
