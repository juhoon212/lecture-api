package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ViRoomConnHistDto {

  /** 방 식별번호 */
  private UUID roomId;

  /** 방 번호 */
  private String roomNo;

  /** 방 이르 */
  private String roomName;

  /** 설명내용 (방) */
  private String descCont;

  /** 키워드 */
  private String keyword;

  /** 닉네임 */
  private String nickname;

  /** 공개여부 */
  private String publicYn;

  /** 인원제한 */
  private Integer perLimit;

  /** 접속인원 */
  private Integer connPer;

  /** 예약여부 */
  private String reservYn;

  /** 시작예약시간 */
  private DateTime startReservTime;

  /** 종료예약시간 */
  private DateTime endReservTime;

  /** * 방 유형 코드 * */
  private String roomTypeCode;

  /** 콘텐츠전시명 */
  private String cttDpName;

  /** 맵이미지경로 */
  private String fileFullPath;

  /** 첨부파일식별번호 */
  private UUID attachFileId;


}
