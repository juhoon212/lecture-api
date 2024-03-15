package twentyoz.viven.feature.mb.model;

import java.util.UUID;
import javax.persistence.*;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.common.model.UseYnEntity;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;

/** 회원 */
@Entity
@Table(schema = "mb", name = "mb_mbr")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MbMbr extends UseYnEntity implements Identifiable<UUID> {

  public static final String PREFIX_MBR_NO = "MBR";

  /** 회원식별번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "mbr_id", nullable = false)
  private UUID mbrId;

  /** Tmpl: MBR_{mbr_id first 8 char} */
  @Column(name = "mbr_no")
  private String mbrNo;

  /** Code Group: MB_001 */
  @Column(name = "mbr_type_code")
  private String mbrTypeCode;

  /** Code Group: MB_002 */
  @Column(name = "mbr_status_code")
  private String mbrStatusCode;

  /** 로그인아이디 */
  @Column(name = "login_id")
  private String loginId;

  /** 비밀번호 */
  @Column(name = "pw")
  private String pw;

  /** 회원명 */
  @Column(name = "mbr_name")
  private String mbrName;

  /** 닉네임 */
  @Column(name = "nickname")
  private String nickname;

  /** 이메일 */
  @Column(name = "email")
  private String email;

  /** 휴대폰번호 */
  @Column(name = "mphone_no")
  private String mphoneNo;

  /** 가입일시 */
  @Column(name = "join_dt")
  private DateTime joinDt;

  /** 최근로그인일시 */
  @Column(name = "last_login_dt")
  private DateTime lastLoginDt;

  /** 사용여부 */
  @Column(name = "use_yn")
  private String useYn;

  /** 삭제여부 */
  @Column(name = "del_yn")
  private String delYn;

  /** 생년월일 */
  @Column(name = "birthday")
  private String birthday;

  /** 온라인 여부 */
  @Column(name = "online_yn")
  private String onlineYn;

  /** 콘텐츠식별번호 */
  @Column(name = "ctt_id")
  private UUID cttId;

  /** 프로필 파일 경로_1(전신) */
  @Column(name = "avt_body_file_path")
  private String avtBodyFilePath;

  /** 프로필 파일 경로_2(얼굴) */
  @Column(name = "avt_profile_file_path")
  private String avtProfileFilePath;

  /** 프로필 배경색상 */
  @Column(name = "profile_bg_color")
  private String profileBgColor;

  /** 임시비밀번호 여부 */
  @Column(name = "temp_pw_yn")
  private String tempPwYn;

  /** 비밀번호 유효일시 */
  @Column(name = "pw_valid_dt")
  private DateTime pwValidDt;

  /** 식별번호 조회 */
  @Override
  public UUID getId() {
    return mbrId;
  }

  @PrePersist
  public void onPrePersist() {
    this.defaultValue();
  }

  @Override
  public void defaultValue() {
    super.defaultValue();
    useYn = ObjectUtils.defaultIfNull(useYn, "Y"); // 사용여부
    delYn = ObjectUtils.defaultIfNull(delYn, "N"); // 삭제여부
    mbrTypeCode =
        ObjectUtils.defaultIfNull(
            mbrTypeCode, Code.MB_001_MBR.getCode()); // 회원유형코드 (MB_001_MBR : 회원)
    mbrStatusCode =
        ObjectUtils.defaultIfNull(
            mbrStatusCode, Code.MB_002_001.getCode()); // 회원상태코드 (MB_002_001 : 정상)
  }

  /** 사용여부 : Y 처리 */
  public void useTrue() {
    useYn = "Y";
  }

  /** 사용여부 : N 처리 */
  public void useFalse() {
    useYn = "N";
  }

  /** 삭제여부 : Y 처리 */
  public void delTrue() {
    delYn = "Y";
  }

  /** 삭제여부 : N 처리 */
  public void delFalse() {
    delYn = "N";
  }

  /**
   * 식별번호 설정
   *
   * @param id
   */
  public void setId(UUID id) {
    this.mbrId = id;
  }
}
