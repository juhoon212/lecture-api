package twentyoz.viven.feature.vi.model;

import java.util.UUID;
import twentyoz.viven.feature.common.security.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class RoomDto {

  /** 방 */
  private ViRoom rm;

  /** 콘텐츠명 */
  private String cttName;

  /** 콘텐츠전시명 */
  private String cttDpName;

  private String cttDataTypeCode;

  /** 콘텐츠 파일 경로 */
  private String cttAttachFilePath;

  /** 콘텐츠 파일 식별번호 */
  private UUID cttAttachFileId;

  /** 방서버명 */
  private String roomSvName;

  /** 콘텐츠 바이너리 */
  private ViCttBin cttBin;

  /** 내 방 여부 */
  private Boolean isCreator;

  /** 닉네임 */
  private String regMbrNickname;

  private String regMbrProfilePath;

  public RoomDto(
      UUID roomId,
      String roomNo,
      String roomName,
      UUID roomSvId,
      UUID cttId,
      UUID mbrId,
      String publicYn,
      String reservYn,
      String onlineYn,
      DateTime startReservTime,
      DateTime endReservTime,
      Integer perLimit,
      Integer connPer,
      String invLink,
      String descCont,
      String keyword,
      String dpYn,
      UUID regId,
      DateTime regDt,
      UUID modId,
      DateTime modDt,
      String roomTypeCode,
      String cttName,
      String cttDpName,
      String cttDataTypeCode,
      String roomSvName,
      UUID cttBinId,
      String binVal,
      String cttPropCont,
      String nickname,
      String regMbrProfilePath,
      String cttAttachFilePath,
      UUID cttAttachFileId) {
    ViRoom viRoom = new ViRoom();
    viRoom.setRoomId(roomId);
    viRoom.setRoomNo(roomNo);
    viRoom.setRoomName(roomName);
    viRoom.setRoomSvId(roomSvId);
    viRoom.setCttId(cttId);
    viRoom.setMbrId(mbrId);
    viRoom.setPublicYn(onlineYn);
    viRoom.setPublicYn(publicYn);
    viRoom.setReservYn(reservYn);
    viRoom.setStartReservTime(startReservTime);
    viRoom.setEndReservTime(endReservTime);
    viRoom.setPerLimit(perLimit);
    viRoom.setConnPer(connPer);
    viRoom.setInvLink(invLink);
    viRoom.setDescCont(descCont);
    viRoom.setKeyword(keyword);
    viRoom.setDpYn(dpYn);
    viRoom.setRoomTypeCode(roomTypeCode);

    viRoom.setRegId(regId);
    viRoom.setRegDt(regDt);
    viRoom.setModId(modId);
    viRoom.setModDt(modDt);

    ViCttBin cttBin = new ViCttBin();

    cttBin.setCttBinId(cttBinId);
    cttBin.setCttId(cttId);
    cttBin.setBinVal(binVal);
    cttBin.setCttPropCont(cttPropCont);

    this.cttBin = cttBin;
    this.rm = viRoom;
    this.cttName = cttName;
    this.cttDpName = cttDpName;
    this.cttDataTypeCode = cttDataTypeCode;
    this.cttAttachFilePath = cttAttachFilePath;
    this.cttAttachFileId = cttAttachFileId;
    this.roomSvName = roomSvName;
    this.regMbrNickname = nickname;
    this.regMbrProfilePath = regMbrProfilePath;
    this.isCreator = mbrId.equals(UserInfo.getUserId());
  }
}
