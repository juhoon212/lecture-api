package twentyoz.viven.feature.vi.model;

import lombok.*;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;

import java.util.UUID;
import org.apache.commons.lang3.ObjectUtils;
import twentyoz.viven.feature.common.model.RegModEntity;

/**
 * 시험지
 */
@Entity
@Table(schema = "vi", name = "vi_exam")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViExam extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 시험지식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "exam_id", nullable = false)
  private UUID examId;

  /**
   * 회원식별번호
   */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /**
   * 시험지이름
   */
  @Column(name = "exam_name")
  private String examName;

  /**
   * 시험지유형코드
   */
  @Column(name = "exam_type_code")
  private String examTypeCode;

  /**
   * 설명내용
   */
  @Column(name = "desc_cont")
  private String descCont;

  /**
   * 키워드
   */
  @Column(name = "keyword")
  private String keyword;

  /**
   * 삭제여부
   */
  @Column(name = "del_yn")
  private String delYn;

  /**
   * 시험 소요 시간(분)
   */
  @Column(name = "exam_period")
  private Integer examPeriod;

  /**
   * 사용 여부
   */
  @Column(name = "use_yn")
  private String useYn;


  /**
  * 식별번호 조회
  */
  @Override
  public UUID getId(){
    return examId;
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

