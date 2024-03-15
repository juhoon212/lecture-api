package twentyoz.viven.feature.vi.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class ViRefDto {

    /** 자료 식별 번호 */
    private UUID refId;

    /** 회원 식별 번호 */
    private UUID mbrId;

    /** 자료 이름 */
    private String refName;

    /** 자료유형코드 */
    private String refTypeCode;

    /** 링크 */
    private String refLink;

    /** 첨부파일 그룹 식별번호 */
    private UUID attachFileGroupId;

    /** 설명 내용 */
    private String descCont;

    /** 삭제 여부 */
    private String delYn;

    /** 회원 닉네임 */
    private String mbrNickName;

    private UUID regId;

    private DateTime regDt;

    private UUID modId;

    private DateTime modDt;

    public ViRefDto(
            UUID refId,
            UUID mbrId,
            String refName,
            String refTypeCode,
            String refLink,
            UUID attachFileGroupId,
            String descCont,
            String delYn,
            String nickName,
            UUID regId,
            DateTime regDt,
            UUID modId,
            DateTime modDt
            )
    {
        this.refId = refId;
        this.mbrId = mbrId;
        this.refName = refName;
        this.refTypeCode = refTypeCode;
        this.refLink = refLink;
        this.attachFileGroupId = attachFileGroupId;
        this.descCont = descCont;
        this.delYn = delYn;
        this.mbrNickName = nickName;
        this.regId = regId;
        this.regDt = regDt;
        this.modId = modId;
        this.modDt = modDt;
    }
}
