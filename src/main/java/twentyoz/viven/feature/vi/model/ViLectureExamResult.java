package twentyoz.viven.feature.vi.model;

import io.swagger.models.auth.In;
import lombok.*;
import javax.persistence.*;

import org.apache.commons.lang3.ObjectUtils;
import twentyoz.stella.misc.hibernate.Identifiable;

import java.util.UUID;
import org.joda.time.DateTime;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.common.model.RegModEntity;

/**
 * 강의시험지결과
 */
@Entity
@Table(schema = "vi", name = "vi_lecture_exam_result")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViLectureExamResult extends RegModEntity implements Identifiable<UUID> {
  
  /**
   * 강의시험결과식별번호
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "lecture_exam_result_id", nullable = false)
  private UUID lectureExamResultId;

  /**
   * 강의식별번호
   *
   *
   *
   *
   */
  @Column(name = "lecture_id")
  private UUID lectureId;

  /**
   * 강의시험지식별번호
   */
  @Column(name = "lecture_exam_id")
  private UUID lectureExamId;

  /**
   * 회원식별번호
   */
  @Column(name = "mbr_id")
  private UUID mbrId;

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
   * 채점여부
   */
  @Column(name = "marking_yn")
  private String markingYn;

  /**
   * 삭제 여부
   */
  @Column(name = "del_yn")
  private String delYn;

  /**
   * 시험결과 상태 여부
   * 객체 저장되기 전 상태 대기로 세팅
   * 대기 : 강의시험결과가 테이블에 사용자의 시험데이터가 없음
   * 진행 : 강의시험결과 테이블에 사용자의 시험데이터 존재, 채점완료여부 N
   * 완료 : 강의 시험결과 테이블에 사용자의 시험데이터가 있으며, 채점 완료여부가 Y
   */


  /**
  * 식별번호 조회
  */
  @Override
  public UUID getId(){
    return lectureExamResultId;
  }

  @PrePersist
  public void onPersist() {
    this.defaultValue();
  }

  @Override
  public void defaultValue() {
    super.defaultValue();
    delYn = ObjectUtils.defaultIfNull(delYn, "N");
    markingYn = ObjectUtils.defaultIfNull(markingYn, "N");
  }




  
}

