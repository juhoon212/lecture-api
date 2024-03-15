package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.UseYnEntity;
import twentyoz.viven.util.UidUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;

/** 방 */
@Entity
@Table(schema = "vi", name = "vi_room")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViRoom extends UseYnEntity implements Identifiable<UUID> {

  public static final String PREFIX_ROOM_NO = "ROOM";

  /** 방식별번호 */
  @Id
  @Column(name = "room_id", nullable = false)
  private UUID roomId;

  /** 방번호 */
  @Column(name = "room_no")
  private String roomNo;

  /** 방이름 */
  @Column(name = "room_name")
  private String roomName;

  /** 방서버식별번호 */
  @Column(name = "room_sv_id")
  private UUID roomSvId;

  /** 콘텐츠식별번호 */
  @Column(name = "ctt_id")
  private UUID cttId;

  /** 회원식별번호 */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /** 공개여부 */
  @Column(name = "public_yn")
  private String publicYn;

  /** 방비밀번호 */
  @Column(name = "room_pw")
  private String roomPw;

  /** 전시여부 */
  @Column(name = "dp_yn")
  private String dpYn;

  /** 예약여부 */
  @Column(name = "reserv_yn")
  private String reservYn;

  /** 온라인여부 */
  @Column(name = "online_yn")
  private String onlineYn;

  /** 시작예약시간 */
  @Column(name = "start_reserv_time")
  private DateTime startReservTime;

  /** 종료예약시간 */
  @Column(name = "end_reserv_time")
  private DateTime endReservTime;

  /** 인원제한 */
  @Column(name = "per_limit")
  private Integer perLimit;

  /** 접속인원 */
  @Column(name = "conn_per")
  private Integer connPer;

  /** 초대링크 */
  @Column(name = "inv_link")
  private String invLink;

  /** 설명내용 */
  @Column(name = "desc_cont")
  private String descCont;

  /** 키워드 */
  @Column(name = "keyword")
  private String keyword;

  /** 마이룸여부 */
  @Column(name = "myroom_yn")
  private String myroomYn;

  /** 사용여부 */
  @Column(name = "use_yn")
  private String useYn;

  /** 삭제여부 */
  @Column(name = "del_yn")
  private String delYn;

  /** 방 유형 코드 */
  @Column(name = "room_type_code")
  private String roomTypeCode;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return roomId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  @Override
  public void defaultValue() {
    super.defaultValue();
    // @GeneratedValue 사용 시 PK를 강제 지정할 수 없어 사용 제외하고 default 로 처리
    roomId = ObjectUtils.defaultIfNull(roomId, UidUtils.getUuid());

    useYn = ObjectUtils.defaultIfNull(useYn, "Y");
    delYn = ObjectUtils.defaultIfNull(delYn, "N");
    onlineYn = ObjectUtils.defaultIfNull(onlineYn, "N");
    publicYn = ObjectUtils.defaultIfNull(publicYn, "N");
    dpYn = ObjectUtils.defaultIfNull(dpYn, "N");
    reservYn = ObjectUtils.defaultIfNull(reservYn, "N");
    myroomYn = ObjectUtils.defaultIfNull(myroomYn, "N");
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
