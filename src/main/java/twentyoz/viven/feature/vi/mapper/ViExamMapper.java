package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViExam;
import org.apache.commons.lang3.StringUtils;

@Mapper
public abstract class ViExamMapper {

  public ViExam modify(ViExam in, ViExam out) {
    if (in == null) {
      return null;
    }
    

    // 회원식별번호
    if (in.getMbrId() != null) {
      out.setMbrId(in.getMbrId());
    }

    // 시험지이름
    if (StringUtils.isNotEmpty(in.getExamName())) {
      out.setExamName(in.getExamName());
    }

    // 시험지유형코드
    if (StringUtils.isNotEmpty(in.getExamTypeCode())) {
      out.setExamTypeCode(in.getExamTypeCode());
    }

    // 시간제한
    if(in.getExamPeriod() != null) {
      out.setExamPeriod(in.getExamPeriod());
    }

    // 설명내용
    if (StringUtils.isNotEmpty(in.getDescCont())) {
      out.setDescCont(in.getDescCont());
    }

    // 키워드
    if (StringUtils.isNotEmpty(in.getKeyword())) {
      out.setKeyword(in.getKeyword());
    }

    // 삭제여부
    if (StringUtils.isNotEmpty(in.getDelYn())) {
      out.setDelYn(in.getDelYn());
    }

    // 사용여부
    if(StringUtils.isNotEmpty(in.getUseYn())) {
      out.setUseYn(in.getUseYn());
    }

    return out;
  }
}
