package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViLectureRef;
import org.apache.commons.lang3.StringUtils;

@Mapper
public abstract class ViLectureRefMapper {

  public ViLectureRef modify(ViLectureRef in, ViLectureRef out) {
    if (in == null) {
      return null;
    }
    

    // 강의식별번호
    if (in.getLectureId() != null) {
      out.setLectureId(in.getLectureId());
    }

    // 자료식별번호
    if (in.getRefId() != null) {
      out.setRefId(in.getRefId());
    }

    // 자료이름
    if (StringUtils.isNotEmpty(in.getRefName())) {
      out.setRefName(in.getRefName());
    }

    // 자료유형코드
    if (StringUtils.isNotEmpty(in.getRefTypeCode())) {
      out.setRefTypeCode(in.getRefTypeCode());
    }


    // 링크
    if (StringUtils.isNotEmpty(in.getRefLink())) {
      out.setRefLink(in.getRefLink());
      out.setAttachFileGroupId(null);
    }

    // 첨부파일그룹식별번호
    if (in.getAttachFileGroupId() != null) {
      out.setAttachFileGroupId(in.getAttachFileGroupId());
      out.setRefLink(null);
    }

    // 설명내용
    if (StringUtils.isNotEmpty(in.getDescCont())) {
      out.setDescCont(in.getDescCont());
    }

    // 삭제여부
    if (StringUtils.isNotEmpty(in.getDelYn())) {
      out.setDelYn(in.getDelYn());
    }

    return out;
  }
}
