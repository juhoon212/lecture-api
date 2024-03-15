package twentyoz.viven.feature.common.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

@MappedSuperclass
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class UseYnEntity extends RegModEntity implements UseYn {

  /** 사용여부 */
  @Column(name = "use_yn", length = 1, nullable = false)
  private String useYn;

  /** 엔티티 기본 값 설정 */
  @Override
  public void defaultValue() {
    useYn = ObjectUtils.defaultIfNull(getUseYn(), "Y");
  }

  /** 사용여부를 Boolean 캐스팅 */
  public Boolean asUseYn() {
    return "Y".equals(useYn);
  }

  /** 사용여부 설정 : 사용 */
  @Override
  public void useTrue() {
    useYn = "Y";
  }

  /** 사용여부 설정 : 미사용 */
  @Override
  public void useFalse() {
    useYn = "N";
  }
}
