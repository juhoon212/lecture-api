package twentyoz.viven.feature.sy.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegModEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

@Entity
@Table(schema = "sy", name = "sy_attach_file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SyAttachFile extends RegModEntity implements Identifiable<UUID> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "attach_file_id", nullable = false)
  private UUID attachFileId;

  @Column(name = "attach_file_group_id")
  private UUID attachFileGroupId;

  @Column(name = "attach_div_val")
  private String attachDivVal;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "file_full_path")
  private String fileFullPath;

  @Column(name = "file_ext")
  private String fileExt;

  @Column(name = "file_size")
  private Long fileSize;

  @Column(name = "file_mime_type_val")
  private String fileMimeTypeVal;

  @Column(name = "ori_file_name")
  private String oriFileName;

  @Column(name = "ori_file_capa")
  private Long oriFileCapa;

  @Column(name = "sort_ord")
  private Integer sortOrd;

  @Column(name = "remark_cont")
  private String remarkCont;

  @Column(name = "del_yn")
  private String delYn;

  @Override
  public UUID getId() {
    return attachFileId;
  }

  public void setId(UUID attachFileId) {
    this.attachFileId = attachFileId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  /** 엔티티 기본 값 설정 */
  @Override
  public void defaultValue() {
    super.defaultValue();
    delYn = ObjectUtils.defaultIfNull(delYn, "N");
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
