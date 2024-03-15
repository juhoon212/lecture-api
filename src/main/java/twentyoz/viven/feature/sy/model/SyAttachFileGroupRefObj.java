package twentyoz.viven.feature.sy.model;

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

@Entity
@Table(schema = "sy", name = "sy_attach_file_group_ref_obj")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SyAttachFileGroupRefObj extends RegModEntity implements Identifiable<UUID> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "attach_file_group_ref_obj_id")
  private UUID attachFileGroupRefObjId;

  @Column(name = "attach_file_group_id")
  private UUID attachFileGroupId;

  @Column(name = "ref_obj_name")
  private String refObjName;

  @Column(name = "ref_obj_id")
  private UUID refObjId;

  @Override
  public UUID getId() {
    return attachFileGroupRefObjId;
  }

  public void setId(UUID attachFileGroupRefObjId) {
    this.attachFileGroupRefObjId = attachFileGroupRefObjId;
  }
}
