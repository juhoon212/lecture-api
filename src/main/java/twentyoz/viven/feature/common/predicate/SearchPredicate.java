package twentyoz.viven.feature.common.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

/** 검색 Predicate */
public class SearchPredicate {

  /** WHERE USE_YN = Y 조건절 추가 */
  public static Predicate useTrue() {
    return useTrue(null);
  }

  /** WHERE USE_YN = Y 조건절 추가 */
  public static Predicate useTrue(Predicate mixin) {
    return new BooleanBuilder(mixin).and(Expressions.stringPath("useYn").eq("Y"));
  }

  /** WHERE USE_YN = N 조건절 추가 */
  public static Predicate useFalse() {
    return useFalse(null);
  }

  /** WHERE USE_YN = N 조건절 추가 */
  public static Predicate useFalse(Predicate mixin) {
    return new BooleanBuilder(mixin).and(Expressions.stringPath("useYn").eq("N"));
  }

  /** WHERE DEL_YN = Y 조건절 추가 */
  public static Predicate delTrue() {
    return delTrue(null);
  }

  /** WHERE DEL_YN = Y 조건절 추가 */
  public static Predicate delTrue(Predicate mixin) {
    return new BooleanBuilder(mixin).and(Expressions.stringPath("delYn").eq("Y"));
  }

  /** WHERE DEL_YN = N 조건절 추가 */
  public static Predicate delFalse() {
    return delFalse(null);
  }

  /** WHERE DEL_YN = N 조건절 추가 */
  public static Predicate delFalse(Predicate mixin) {
    return new BooleanBuilder(mixin).and(Expressions.stringPath("delYn").eq("N"));
  }

  /** WHERE USE_YN = Y AND DEL_YN = N 조건절 추가 */
  public static Predicate useTrueAndDelFalse(Predicate mixin) {
    return new BooleanBuilder(mixin)
        .and(Expressions.stringPath("useYn").eq("Y"))
        .and(Expressions.stringPath("delYn").eq("N"));
  }

  /** WHERE USE_YN = N AND DEL_YN = N 조건절 추가 */
  public static Predicate useFalseAndDelFalse(Predicate mixin) {
    return new BooleanBuilder(mixin)
        .and(Expressions.stringPath("useYn").eq("N"))
        .and(Expressions.stringPath("delYn").eq("N"));
  }

  /**
   * 날짜 검색
   *
   * <pre>
   * 종료 일자를 하루 더하고 1 밀리세컨을 빼서 범위 검색
   *
   * 예) right: 2010-02-05 = 2010-02-05 00:00:00.000 ~ 2010-02-05 23:59:59.999
   * </pre>
   */
  public static Predicate between(DateTimePath<DateTime> mixin, DateTime right) {
    return between(mixin, right, right);
  }

  /**
   * 날짜 검색
   *
   * <pre>
   * 종료 일자를 하루 더하고 1 밀리세컨을 빼서 범위 검색
   *
   * 예) from: 2010-02-05, to: 2010-02-12 = 2010-02-05 00:00:00.000 ~ 2010-02-12 23:59:59.999
   * </pre>
   */
  public static Predicate between(DateTimePath<DateTime> mixin, DateTime from, DateTime to) {
    return mixin.between(
        from.toLocalDate().toDateTime(LocalTime.MIDNIGHT),
        to.toLocalDate().toDateTime(new LocalTime(23, 59, 59, 999)));
  }

  /** LIKE 검색 */
  public static Predicate like(StringPath mixin, String keyword) {
    String clean = StringUtils.replace(keyword, "%", "");

    if (StringUtils.startsWith(keyword, "%") && StringUtils.endsWith(keyword, "%")) {
      return mixin.contains(clean);
    } else if (StringUtils.startsWith(keyword, "%")) {
      return mixin.endsWith(clean);
    } else if (StringUtils.endsWith(keyword, "%")) {
      return mixin.startsWith(clean);
    } else {
      return mixin.eq(keyword);
    }
  }
}
