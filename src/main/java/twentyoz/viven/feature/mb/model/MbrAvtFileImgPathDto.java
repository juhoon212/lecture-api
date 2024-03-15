package twentyoz.viven.feature.mb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** 친구요청 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class MbrAvtFileImgPathDto {

  /** "프로필 배경색상" */
  private String profileBgColor;

  /** "프로필 파일 경로_1(전신)" */
  private String avtBodyFilePath;

  /** "프로필 파일 경로_2(얼굴)" */
  private String avtProfileFilePath;
}
