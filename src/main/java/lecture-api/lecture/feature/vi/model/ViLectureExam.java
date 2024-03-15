package twentyoz.viven.feature.vi.model;

import lombok.*;
import javax.persistence.*;

import org.apache.commons.lang3.ObjectUtils;
import twentyoz.stella.misc.hibernate.Identifiable;

import java.util.UUID;
import org.joda.time.DateTime;
import twentyoz.viven.feature.common.model.RegModEntity;

/**
 * 강의시험지
 */
@Entity
@Table(schema = "vi", name = "vi_lecture_exam")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViLectureExam extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 강의시험지식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "lecture_exam_id", nullable = false)
  private UUID lectureExamId;

  /**
   * 강의식별번호
   */
  @Column(name = "lecture_id")
  private UUID lectureId;

  /**
   * 시험지식별번호
   */
  @Column(name = "exam_id")
  private UUID examId;

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
   * 시험시작시간
   */
  @Column(name = "exam_start_dt")
  private DateTime examStartDt;

  /**
   * 시험종료시간
   */
  @Column(name = "exam_end_dt")
  private DateTime examEndDt;

  /**
   * 시험소요시간(분)
   */
  @Column(name = "exam_period")
  private Integer examPeriod;

  /**
   * 골든벨 콘텐츠 식별번호
   */
  @Column(name = "goldenbell_ctt_id")
  private UUID goldenbellCttId;

  /**
   * 채점 여부
   */
  @Column(name = "marking_yn")
  private String markingYn;

  /**
   * 시험 상태
   */
  @Transient
  private String examStatus;

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  @PostLoad
  @PostPersist
  public void changeExamStatus() {

    // 현재 시각
    long now = System.currentTimeMillis();

    // 시작시간 (초)
    long startDt = examStartDt.getMillis();
    // 끝시간 (초)
    long endDt = examEndDt.getMillis();

    if (startDt > now) {
      examStatus = Code.EXAM_003_001.getCode(); // 예정
    } else if (startDt < now && endDt > now) {
      examStatus = Code.EXAM_003_002.getCode(); // 진행
    } else if (endDt < now) {
      examStatus = Code.EXAM_003_003.getCode(); // 종료
    }
  }

  @Override
  public void defaultValue() {
    super.defaultValue();
    markingYn = ObjectUtils.defaultIfNull(markingYn, "N");
  }

  /**
  * 식별번호 조회
  */
  @Override
  public UUID getId(){
    return lectureExamId;
  }

  
}

