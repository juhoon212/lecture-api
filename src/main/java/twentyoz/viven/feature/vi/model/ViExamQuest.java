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
 * 시험문제
 */
@Entity
@Table(schema = "vi", name = "vi_exam_quest")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class ViExamQuest extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 시험문제식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "exam_quest_id", nullable = false)
  private UUID examQuestId;

  /**
   * 회원식별번호
   */
  @Column(name = "mbr_id")
  private UUID mbrId;

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
   * 시험문제 첨부파일 식별번호
   */

  @Column(name = "quest_file_group_id")
  private UUID questFileGroupId;

  /**
   * 키워드
   */
  @Column(name = "keyword")
  private String keyword;


  /**
  * 식별번호 조회
  */
  @Override
  public UUID getId(){
    return examQuestId;
  }





  
}

