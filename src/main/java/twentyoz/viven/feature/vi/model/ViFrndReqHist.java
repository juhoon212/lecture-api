package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegEntity;
import lombok.*;

/** 친구요청이력 */
@Entity
@Table(schema = "vi", name = "vi_frnd_req_hist")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViFrndReqHist extends RegEntity implements Identifiable<UUID> {

  /** 친구요청이력식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "frnd_req_hist_id", nullable = false)
  private UUID frndReqHistId;

  /** 요청회원식별번호 */
  @Column(name = "req_mbr_id")
  private UUID reqMbrId;

  /** 응답회원식별번호 */
  @Column(name = "res_mbr_id")
  private UUID resMbrId;

  /** 요청상태코드 */
  @Column(name = "req_status_code")
  private String reqStatusCode;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return frndReqHistId;
  }
}
