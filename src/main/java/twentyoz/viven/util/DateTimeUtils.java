package twentyoz.viven.util;

import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeUtils {

  /** 시간 - yyyyMMddHHmmss (yyyy-MM-dd HH:mm:ss) */
  public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

  /**
   * 당일 시작 시간
   *
   * @return 당일 00:00:00
   */
  public static DateTime getFirstTimeOfTheToday() {
    return new DateTime().toLocalDate().toDateTime(LocalTime.MIDNIGHT);
  }

  /**
   * 당일 마지막 시간
   *
   * @return 당일 23:59:59
   */
  public static DateTime getLastTimeOfTheToday() {
    return new DateTime().toLocalDate().toDateTime(new LocalTime(23, 59, 59, 999));
  }

  /**
   * 기준일자 시작 시간
   *
   * @param dateTime 기준일자
   * @return 기준일자 00:00:00
   */
  public static DateTime getFirstTimeOfTheDay(DateTime dateTime) {
    return dateTime.toLocalDate().toDateTime(LocalTime.MIDNIGHT);
  }

  /**
   * 기준일자 마지막 시간
   *
   * @param dateTime 기준일자
   * @return 기준일자 23:59:59
   */
  public static DateTime getLastTimeOfTheDay(DateTime dateTime) {
    return dateTime.toLocalDate().toDateTime(new LocalTime(23, 59, 59, 999));
  }

  /**
   * 기준일자
   *
   * @param date 기준일자
   * @return 기준일자 00:00:00
   */
  public static DateTime getFirstTimeOfTheStrDate(String date) {
    DateTimeFormatter fmt = DateTimeFormat.forPattern(yyyyMMddHHmmss);
    return fmt.parseDateTime(date + " 00:00:00");
  }

  /**
   * 기준일자 마지막 시간
   *
   * @param date 기준일자
   * @return 기준일자 23:59:59
   */
  public static DateTime getLastTimeOfTheStrDate(String date) {
    DateTimeFormatter fmt = DateTimeFormat.forPattern(yyyyMMddHHmmss);
    return fmt.parseDateTime(date + " 23:59:59");
  }

  /**
   * 현재기준 + 특정 분
   *
   * @param plus
   * @return date
   */
  public static DateTime getPlusMinuteOfCurrentTime(Integer plus) {
    DateTime dateTime = new DateTime();
    return dateTime.plusMinutes(plus);
  }

  /**
   * 특정시 + 특정 분
   *
   * @param dateTime
   * @param plus
   * @return date
   */
  public static DateTime getPlusMinuteOfDateTime(DateTime dateTime, Integer plus) {

    return dateTime.plusMinutes(plus);
  }

  /**
   * 초가 빠진 string 시간을 datetime으로 변환
   *
   * @param dateTime
   * @return date
   */
  public static DateTime getFromStringToDateTime(String dateTime) {
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    return formatter.parseDateTime(dateTime.concat(":00"));

  }

  /**
   * 밀리 세컨드를 DateTime 으로
   */
  public static DateTime getMillSecToDateTime(long millis) {

      return new DateTime(millis);
  }



  /**
   *
   * @param dateTime
   * String 시간 datetime으로 변환
   *
   */

  public static DateTime getStringToDateTime(String dateTime) {
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    return formatter.parseDateTime(dateTime);
  }



  /**
   * 요일 반환
   *
   * @return mon, tue 형식.
   */
  public static String getWeekName(DateTime dateTime) {
    if (dateTime == null) dateTime = new DateTime();
    String result = DateTimeFormat.forPattern("E").withLocale(new Locale("en")).print(dateTime);
    return result != null ? result.toLowerCase() : null;
  }
}
