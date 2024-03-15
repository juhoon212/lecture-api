package twentyoz.viven.feature.vi.model;

import lombok.*;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;

import java.util.UUID;
import twentyoz.viven.feature.common.model.RegModEntity;

/**
 * 강의
 */
@Entity
@Table(schema = "vi", name = "vi_lecture")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViLecture extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 강의식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "lecture_id", nullable = false)
  private UUID lectureId;

  /**
   * 방식별번호
   */
  @Column(name = "room_id")
  private UUID roomId;

  /**
   * 강의이름
   */
  @Column(name = "lecture_name")
  private String lectureName;

  /**
   * 강의 계획서 첨부파일 그룹 식별번호
   */

  @Column(name = "lecture_plan_file_group_id")
  private UUID lecturePlanFileGroupId;

  /**
   * 원래 룸 컨텐츠 식별번호
   * @return
   */
  @Column(name = "mapCttId")
  private UUID mapCttId;


  /**
  * 식별번호 조회
  */
  @Override
  public UUID getId(){
    return lectureId;
  }

  
}

