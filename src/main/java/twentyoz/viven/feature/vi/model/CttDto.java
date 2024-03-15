package twentyoz.viven.feature.vi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CttDto {

  private static final ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
    mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
    mapper.enable(MapperFeature.PROPAGATE_TRANSIENT_MARKER);
    mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
  }

  /** 콘텐츠 */
  private ViCtt ctt;

  /** 콘텐츠 버전 */
  private ViCttBin cttBin;

  /** 콘텐츠버전 > 전시중인 버전값 */
  private UUID cttBinId;

  /** 바이너리값 */
  private String activeBinVal;

  /** 콘텐츠속성내용 */
  private Map<String, Object> activeCttProp;

  /** 대표이미지 첨부파일 경로 */
  private String mainFilePath;

  /** 전신 첨부파일 경로 */
  private String avtBodyFilePath;

  /** 프로필 첨부파일 경로 */
  private String avtProfileFilePath;

  /** 닉네임 */
  private String nickname;

  @SneakyThrows
  public CttDto(
      UUID cttId,
      String cttNo,
      String cttTypeCode,
      String cttName,
      String cttDpName,
      String descCont,
      String sellStatusCode,
      String cttDataTypeCode,
      String cttMapType,
      String dpYn,
      DateTime dpStartDt,
      DateTime dpEndDt,
      UUID attachFileGroupId,
      UUID avtImgGroupId,
      String useYn,
      String delYn,
      UUID regId,
      DateTime regDt,
      UUID modId,
      DateTime modDt,
      UUID cttBinId,
      String activeBinVal,
      String activeCttPropCont,
      String mainFilePath,
      String avtBodyFilePath,
      String avtProfileFilePath,
      String nickname) {
    ViCtt viCtt = new ViCtt();
    viCtt.setCttId(cttId);
    viCtt.setCttNo(cttNo);
    viCtt.setCttTypeCode(cttTypeCode);
    viCtt.setCttMapType(cttMapType);
    viCtt.setCttName(cttName);
    viCtt.setCttDpName(cttDpName);
    viCtt.setDescCont(descCont);
    viCtt.setSellStatusCode(sellStatusCode);
    viCtt.setCttDataTypeCode(cttDataTypeCode);
    viCtt.setDpYn(dpYn);
    viCtt.setDpStartDt(dpStartDt);
    viCtt.setDpEndDt(dpEndDt);
    viCtt.setAttachFileGroupId(attachFileGroupId);
    viCtt.setAvtImgGroupId(avtImgGroupId);
    viCtt.setUseYn(useYn);
    viCtt.setDelYn(delYn);
    viCtt.setRegId(regId);
    viCtt.setRegDt(regDt);
    viCtt.setModId(modId);
    viCtt.setModDt(modDt);
    this.ctt = viCtt;

    this.cttBinId = cttBinId;
    this.activeBinVal = activeBinVal;
    if (StringUtils.isNotBlank(activeCttPropCont)) {
      this.activeCttProp = mapper.readValue(activeCttPropCont, new TypeReference<Map<String, Object>>() {
      });
    }

    this.mainFilePath = mainFilePath;
    this.avtBodyFilePath = avtBodyFilePath;
    this.avtProfileFilePath = avtProfileFilePath;

    this.nickname = nickname;
  }
}
