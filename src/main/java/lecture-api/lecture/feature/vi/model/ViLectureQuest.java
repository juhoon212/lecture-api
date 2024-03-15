package twentyoz.viven.feature.vi.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import lombok.*;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import twentyoz.stella.misc.hibernate.Identifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import twentyoz.viven.feature.common.model.RegModEntity;

/**
 * 강의시험문제
 */
@Entity
@Table(schema = "vi", name = "vi_lecture_quest")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class ViLectureQuest extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 강의시험문제식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "lecture_quest_id", nullable = false)
  private UUID lectureQuestId;

  /**
   * 강의식별번호
   */
  @Column(name = "lecture_id")
  private UUID lectureId;

  /**
   * 강의시험지식별번호
   */
  @Column(name = "lecture_exam_id")
  private UUID lectureExamId;

  /**
   * 시험지문제식별번호
   */
  @Column(name = "exam_item_id")
  private UUID examItemId;

  /**
   * 시험문제식별번호
   */
  @Column(name = "exam_quest_id")
  private UUID examQuestId;

  /**
   * 시험문제 이름
   */
  @Column(name = "quest_name")
  private String questName;

  /**
   * 시험문제 유형 코드
   */
  @Column(name = "quest_type_code")
  private String questTypeCode;

  /**
   * 시험문제 내용
   */
  @Column(name = "quest_cont")
  private String questCont;

  /**
   * 시험문제 첨부파일 식별번호
   */
  @Column(name = "quest_file_group_id")
  private UUID questFileGroupId;

  /**
   * 객관식문항설정정보
   */
  @Column(name = "choice_cont")
  private String choiceCont;

  /**
   * 배점
   */

  @Column(name = "score")
  private Integer score;

  /**
   * 정답
   */
  @Type(type = "list-array")
  @Column(name = "answer", columnDefinition = "text[]")
  private List<String> answer;

  /**
   * 골든벨가능여부
   */
  @Column(name = "goldenbell_yn")
  private String goldenbellYn;

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
    return lectureQuestId;
  }

  
}

