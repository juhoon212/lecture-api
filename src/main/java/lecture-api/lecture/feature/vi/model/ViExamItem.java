package twentyoz.viven.feature.vi.model;

import lombok.*;
import javax.persistence.*;

import org.apache.commons.lang3.ObjectUtils;
import twentyoz.stella.misc.hibernate.Identifiable;

import java.util.UUID;
import twentyoz.viven.feature.common.model.RegModEntity;

/**
 * 시험지문제
 */
@Entity
@Table(schema = "vi", name = "vi_exam_item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViExamItem extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 시험지문제식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "exam_item_id", nullable = false)
  private UUID examItemId;

  /**
   * 시험지식별번호
   */
  @Column(name = "exam_id")
  private UUID examId;

  /**
   * 시험문제식별번호
   */
  @Column(name = "exam_quest_id")
  private UUID examQuestId;

  /**
   * 회원식별번호
   */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /**
   * 배점
   */
  @Column(name = "score")
  private Integer score;

  /**
   * 정렬순서
   */
  @Column(name = "sort_ord")
  private Integer sortOrd;


  /**
  * 식별번호 조회
  */
  @Override
  public UUID getId(){
    return examItemId;
  }

  @PrePersist
  public void onPersist() {
    this.defaultValue();
  }

  @Override
  public void defaultValue() {
    super.defaultValue();
  }

  
}

