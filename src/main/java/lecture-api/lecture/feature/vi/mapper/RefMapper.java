package twentyoz.viven.feature.vi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViRef;

@Mapper
public abstract class RefMapper {

    public ViRef modify(ViRef in, ViRef out) {

        if(in == null) {
            return null;
        }

        if(StringUtils.isNotEmpty(in.getRefName())) {
            out.setMbrId(in.getMbrId());
        }

        if(StringUtils.isNotEmpty(in.getRefName())) {
            out.setRefName(in.getRefName());
        }

        if(StringUtils.isNotEmpty(in.getRefTypeCode())) {
            out.setRefTypeCode(in.getRefTypeCode());
        }

        if(in.getAttachFileGroupId() != null) {
            out.setAttachFileGroupId(in.getAttachFileGroupId());
        }

        if(StringUtils.isNotEmpty(in.getRefLink())) {
            out.setRefLink(in.getRefLink());
        }

        if(StringUtils.isNotEmpty(in.getDescCont())) {
            out.setDescCont(in.getDescCont());
        }

        if(StringUtils.isNotEmpty(in.getDelYn())) {
            out.setDelYn(in.getDelYn());
        }

        return out;
    }
}
