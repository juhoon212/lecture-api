package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegModEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** 방속성 */
@Entity
@Table(schema = "vi", name = "vi_room_prop")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViRoomProp extends RegModEntity implements Identifiable<UUID> {

  /** 방속성식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "room_prop_id", nullable = false)
  private UUID roomPropId;

  /** 방식별번호 */
  @Column(name = "room_id")
  private UUID roomId;

  /** 속성내용 */
  @Column(name = "prop_cont")
  private String propCont;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return roomPropId;
  }
}
