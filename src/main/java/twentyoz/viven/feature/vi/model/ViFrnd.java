package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegEntity;
import lombok.*;

/** 친구 */
@Entity
@Table(schema = "vi", name = "vi_frnd")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViFrnd extends RegEntity implements Identifiable<UUID> {

  /** 친구식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "frnd_id", nullable = false)
  private UUID frndId;

  /** 회원식별번호 */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /** 친구회원식별번호 */
  @Column(name = "frnd_mbr_id")
  private UUID frndMbrId;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return frndId;
  }
}
