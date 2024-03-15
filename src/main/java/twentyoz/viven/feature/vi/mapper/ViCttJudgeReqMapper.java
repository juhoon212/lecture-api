package twentyoz.viven.feature.vi.mapper;

import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.vi.model.ViCtt;
import twentyoz.viven.feature.vi.model.ViCttJudgeHist;
import twentyoz.viven.feature.vi.model.ViCttJudgeReq;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class ViCttJudgeReqMapper {

  public ViCttJudgeReq modify(ViCttJudgeReq in, ViCttJudgeReq out) {
    if (in == null) {
      return null;
    }

    // 콘텐츠식별번호
    if (in.getCttId() != null) {
      out.setCttId(in.getCttId());
    }

    // 콘텐츠바이너리식별번호
    if (in.getCttBinId() != null) {
      out.setCttBinId(in.getCttBinId());
    }

    // 심사유형코드 - Code Group: CT_004
    if (StringUtils.isNotEmpty(in.getJudgeTypeCode())) {
      out.setJudgeTypeCode(in.getJudgeTypeCode());
    }

    // 심사요청내용
    if (StringUtils.isNotEmpty(in.getJudgeReqCont())) {
      out.setJudgeReqCont(in.getJudgeReqCont());
    }

    // 콘텐츠명
    if (StringUtils.isNotEmpty(in.getCttName())) {
      out.setCttName(in.getCttName());
    }

    // 콘텐츠전시명
    if (StringUtils.isNotEmpty(in.getCttDpName())) {
      out.setCttDpName(in.getCttDpName());
    }

    // 비고내용
    if (StringUtils.isNotEmpty(in.getRemarkCont())) {
      out.setRemarkCont(in.getRemarkCont());
    }

    // 심사상태코드 - Code Group: CT_003
    if (StringUtils.isNotEmpty(in.getJudgeStatusCode())) {
      out.setJudgeStatusCode(in.getJudgeStatusCode());
    }

    // 반려유형코드 - Code Group: CT_005
    if (StringUtils.isNotEmpty(in.getRejectTypeCode())) {
      out.setRejectTypeCode(in.getRejectTypeCode());
    }

    // 반려사유
    if (StringUtils.isNotEmpty(in.getRejectReason())) {
      out.setRejectReason(in.getRejectReason());
    }

    // 요청사용자식별번호
    if (in.getReqUserId() != null) {
      out.setReqUserId(in.getReqUserId());
    }

    // 요청일시
    if (in.getReqDt() != null) {
      out.setReqDt(in.getReqDt());
    }

    // 처리사용자식별번호
    if (in.getProcUserId() != null) {
      out.setProcUserId(in.getProcUserId());
    }

    // 처리일시
    if (in.getProcDt() != null) {
      out.setProcDt(in.getProcDt());
    }

    // 설명내용
    if (StringUtils.isNotEmpty(in.getDescCont())) {
      out.setDescCont(in.getDescCont());
    }

    // 첨부파일그룹식별번호
    if (in.getAttachFileGroupId() != null) {
      out.setAttachFileGroupId(in.getAttachFileGroupId());
    }

    // 아바타이미지그룹식별번호
    if (in.getAvtImgGroupId() != null) {
      out.setAvtImgGroupId(in.getAvtImgGroupId());
    }

    return out;
  }

  /**
   * 콘텐츠심사요청 -> 콘텐츠로 변환
   *
   * @param in 콘텐츠
   * @return
   */
  public ViCttJudgeReq toViCttJudgeReq(ViCtt in) {
    ViCttJudgeReq out = new ViCttJudgeReq();
    out.setCttId(in.getCttId());
    out.setCttTypeCode(in.getCttTypeCode());
    out.setCttName(in.getCttName());
    out.setCttDpName(in.getCttDpName());
    out.setDescCont(in.getDescCont());
    out.setAttachFileGroupId(in.getAttachFileGroupId());
    out.setAvtImgGroupId(in.getAvtImgGroupId());
    return out;
  }

  /**
   * 콘텐츠 -> 콘텐츠심사요청으로 변환
   *
   * @param in 콘텐츠
   * @return
   */
  public ViCtt toViCtt(ViCttJudgeReq in) {
    ViCtt out = new ViCtt();
    out.setCttId(in.getCttId());
    out.setCttTypeCode(in.getCttTypeCode());
    out.setCttDataTypeCode(Code.CT_007_003.getCode());
    out.setCttName(in.getCttName());
    out.setCttDpName(in.getCttDpName());
    out.setDescCont(in.getDescCont());
    out.setAvtImgGroupId(in.getAvtImgGroupId());
    out.setAttachFileGroupId(in.getAttachFileGroupId());
    out.setMbrId(in.getReqUserId());
    return out;
  }

  /**
   * 콘텐츠심사요청 -> 콘텐츠심사이력으로 변환
   *
   * @param in 콘텐츠
   * @return
   */
  public ViCttJudgeHist toViCttJudgeHist(ViCttJudgeReq in) {
    ViCttJudgeHist out = new ViCttJudgeHist();
    out.setCttJudgeReqId(in.getCttJudgeReqId());
    out.setCttTypeCode(in.getCttTypeCode());
    out.setCttName(in.getCttName());
    out.setCttDpName(in.getCttDpName());
    out.setDescCont(in.getDescCont());
    out.setJudgeTypeCode(in.getJudgeTypeCode());
    out.setJudgeReqCont(in.getJudgeReqCont());
    out.setRemarkCont(in.getRemarkCont());
    out.setJudgeStatusCode(in.getJudgeStatusCode());
    out.setRejectTypeCode(in.getRejectTypeCode());
    out.setRejectReason(in.getRejectReason());
    out.setAttachFileGroupId(in.getAttachFileGroupId());
    out.setAvtImgGroupId(in.getAvtImgGroupId());
    out.setReqUserId(in.getReqUserId());
    out.setReqDt(in.getReqDt());
    out.setProcUserId(in.getProcUserId());
    out.setProcDt(in.getProcDt());
    out.setRegId(in.getReqUserId());
    out.setRegDt(in.getProcDt());

    return out;
  }
}
