package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegEntity;
import lombok.*;

// TODO: ERD 와 맞지 않아 확인 필요

/** 콘텐츠 */
@Entity
@Table(schema = "vi", name = "vi_ctt_bm")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViCttBm extends RegEntity implements Identifiable<UUID> {

  /** 콘텐츠식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ctt_bm_id", nullable = false)
  private UUID cttBmId;

  /** 회원식별번호 */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /** 콘텐츠바이너리식별번호 */
  @Column(name = "ctt_bin_id")
  private UUID cttBinId;

  @Column(name = "ctt_id")
  private UUID cttId;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return cttBmId;
  }
}
