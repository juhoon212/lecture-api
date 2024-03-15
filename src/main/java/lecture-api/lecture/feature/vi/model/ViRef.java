package twentyoz.viven.feature.vi.model;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import twentyoz.stella.misc.hibernate.Identifiable;
import twentyoz.viven.feature.common.model.RegModEntity;

import javax.persistence.*;
import java.util.UUID;

/**
 * 자료
 */
@Entity
@Table(schema = "vi", name = "vi_ref")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViRef extends RegModEntity implements Identifiable<UUID> {

    /** 자료실 식별 번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ref_id", nullable = false)
    private UUID refId;

    /** 회원 식별 번호 */
    @Column(name = "mbr_id")
    private UUID mbrId;

    /** 자료 이름 */
    @Column(name = "ref_name")
    private String refName;

    /** 자료유형코드 */
    @Column(name = "ref_type_code")
    private String refTypeCode;

    /** 링크 */
    @Column(name = "ref_link")
    private String refLink;

    /** 첨부파일 그룹 식별번호 */
    @Column(name = "attach_file_group_id")
    private UUID attachFileGroupId;

    /** 설명 내용 */
    @Column(name = "desc_cont")
    private String descCont;

    /** 삭제 여부 */
    @Column(name = "del_yn")
    private String delYn;


    @Override
    public UUID getId() {
        return refId;
    }

    @PrePersist
    public void onPrePersist() {
        this.defaultValue();
    }

    @Override
    public void defaultValue() {
        super.defaultValue();
        delYn = ObjectUtils.defaultIfNull(delYn, "N");
    }

    /** 삭제 여부 : Y 처리 */
    public void delTrue() {
        delYn = "Y";
    }

}
