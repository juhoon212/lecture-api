package twentyoz.viven.feature.vi.model;

import lombok.*;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;

import java.util.UUID;
import org.apache.commons.lang3.ObjectUtils;
import twentyoz.viven.feature.common.model.RegModEntity;

/**
 * 강의자료
 */
@Entity
@Table(schema = "vi", name = "vi_lecture_ref")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViLectureRef extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 강의자료식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "lecture_ref_id", nullable = false)
  private UUID lectureRefId;

  /**
   * 강의식별번호
   */
  @Column(name = "lecture_id")
  private UUID lectureId;

  /**
   * 자료식별번호
   */
  @Column(name = "ref_id")
  private UUID refId;

  /**
   * 자료이름
   */
  @Column(name = "ref_name")
  private String refName;

  /**
   * 자료유형코드
   */
  @Column(name = "ref_type_code")
  private String refTypeCode;

  /**
   * 링크
   */
  @Column(name = "ref_link")
  private String refLink;

  /**
   * 첨부파일그룹식별번호
   */
  @Column(name = "attach_file_group_id")
  private UUID attachFileGroupId;

  /**
   * 설명내용
   */
  @Column(name = "desc_cont")
  private String descCont;

  /**
   * 삭제여부
   */
  @Column(name = "del_yn")
  private String delYn;


  /**
  * 식별번호 조회
  */
  @Override
  public UUID getId(){
    return lectureRefId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }


  @Override
  public void defaultValue() {
    super.defaultValue();
    delYn = ObjectUtils.defaultIfNull(delYn, "N");
  }

  /**
   * 삭제여부 : Y 처리
   */
  public void delTrue() {
    delYn = "Y";
  }

  /**
   * 삭제여부 : N 처리
   */
  public void delFalse() {
    delYn = "N";
  }

  
}

