package twentyoz.viven.feature.vi.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import lombok.*;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import twentyoz.stella.misc.hibernate.Identifiable;

import java.util.List;
import java.util.UUID;
import twentyoz.viven.feature.common.model.RegModEntity;

/**
 * 강의시험문제결과
 */
@Entity
@Table(schema = "vi", name = "vi_lecture_quest_result")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class ViLectureQuestResult extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 강의시험문제결과식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "lecture_quest_result_id", nullable = false)
  private UUID lectureQuestResultId;

  /**
   * 강의식별번호
   */
  @Column(name = "lecture_id")
  private UUID lectureId;

  /**
   * 강의시험결과식별번호
   */
  @Column(name = "lecture_exam_result_id")
  private UUID lectureExamResultId;

  /**
   * 강의시험문제식별번호
   */
  @Column(name = "lecture_quest_id")
  private UUID lectureQuestId;

  /**
   * 회원식별번호
   */
  @Column(name = "mbr_id")
  private UUID mbrId;

  /**
   * 사용자 답안
   */
  @Type(type = "list-array")
  @Column(name = "mbr_answer", columnDefinition = "text[]")
  private List<String> mbrAnswer;

  /**
   * 사용자 점수
   */
  @Column(name = "mbr_score")
  private Integer mbrScore;


  /**
  * 식별번호 조회
  */
  @Override
  public UUID getId(){
    return lectureQuestResultId;
  }

  
}

