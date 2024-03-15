package twentyoz.viven.feature.vi.model;

import lombok.*;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;

import java.util.UUID;
import org.joda.time.DateTime;
import twentyoz.viven.feature.common.model.RegModEntity;

/**
 * 강의계획서
 */
@Entity
@Table(schema = "vi", name = "vi_lecture_schedule")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ViLectureSchedule extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 강의계획서식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "lecture_schedule_id", nullable = false)
  private UUID lectureScheduleId;

  /**
   * 강의식별번호
   */
  @Column(name = "lecture_id")
  private UUID lectureId;

  /**
   * 강의계획서이름
   */
  @Column(name = "lecture_schedule_name")
  private String lectureScheduleName;

  /**
   * 설명내용
   */
  @Column(name = "desc_cont")
  private String descCont;

  /**
   * 시작시간
   */
  @Column(name = "start_dt")
  private DateTime startDt;

  /**
   * 종료시간
   */
  @Column(name = "end_dt")
  private DateTime endDt;




  /**
  * 식별번호 조회
  */
  @Override
  public UUID getId(){
    return lectureScheduleId;
  }

  
}

