package twentyoz.viven.feature.vi.model;

import lombok.*;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;

import java.util.UUID;
import twentyoz.viven.feature.common.model.RegModEntity;

/**
 * 방권한
 */
@Entity
@Table(schema = "vi", name = "vi_room_auth")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViRoomAuth extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 방권한식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "room_auth_id", nullable = false)
  private UUID roomAuthId;

  /**
   * 방식별번호
   */
  @Column(name = "room_id")
  private UUID roomId;

  /**
   * 방권한명
   */
  @Column(name = "room_auth_name")
  private String roomAuthName;


  /**
  * 식별번호 조회
  */
  @Override
  public UUID getId(){
    return roomAuthId;
  }

  
}

