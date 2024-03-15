package twentyoz.viven.feature.vi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.persistence.Column;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class ViLectureRefDto {
    /**
     * 강의자료식별번호
     */
    private UUID lectureRefId;

    /**
     * 강의식별번호
     */
    private UUID lectureId;

    /**
     * 자료식별번호
     */
    private UUID refId;
    /**
     * 자료이름
     */
    private String refName;
    /**
     * 자료유형코드
     */
    private String refTypeCode;
    /**
     * 링크
     */
    private String refLink;
    /**
     * 첨부파일그룹식별번호
     */
    private UUID attachFileGroupId;
    /**
     * 설명내용
     */
    private String descCont;

    /**
     * 삭제여부
     */
    private String delYn;

    private UUID regId;

    private DateTime regDt;

    private UUID modId;

    private DateTime modDt;

    /** 회원 닉네임* */
    private String nickName;

    public ViLectureRefDto(UUID lectureRefId,
                           UUID lectureId,
                           UUID refId,
                           String refName,
                           String refTypeCode,
                           String refLink,
                           UUID attachFileGroupId,
                           String descCont,
                           String delYn,
                           UUID regId,
                           DateTime regDt,
                           UUID modId,
                           DateTime modDt,
                           String nickName) {
        this.lectureRefId = lectureRefId;
        this.lectureId = lectureId;
        this.refId = refId;
        this.refName = refName;
        this.refTypeCode = refTypeCode;
        this.refLink = refLink;
        this.attachFileGroupId = attachFileGroupId;
        this.descCont = descCont;
        this.delYn = delYn;
        this.regId = regId;
        this.regDt = regDt;
        this.modId = modId;
        this.modDt = modDt;
        this.nickName = nickName;
    }
}
