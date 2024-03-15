package twentyoz.viven.feature.sy.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import twentyoz.stella.misc.hibernate.Identifiable;

@Entity
@Table(schema = "sy", name = "sy_attach_file_group")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class SyAttachFileGroup implements Identifiable<UUID> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "attach_file_group_id", nullable = false)
  private UUID attachFileGroupId;

  @Override
  public UUID getId() {
    return attachFileGroupId;
  }

  public void setId(UUID attachFileGroupId) {
    this.attachFileGroupId = attachFileGroupId;
  }
}
