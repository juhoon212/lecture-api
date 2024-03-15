package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegModEntity;
import lombok.*;

/** 친구요청 */
@Entity
@Table(schema = "vi", name = "vi_frnd_req")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViFrndReq extends RegModEntity implements Identifiable<UUID> {

  /** 친구요청식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "frnd_req_id", nullable = false)
  private UUID frndReqId;

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
    return frndReqId;
  }
}
