package twentyoz.viven.feature;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Code {

  private static List<Code> CODES = new ArrayList<>();

  private final String code;
  private final String codeId;
  private final String codeGroup;
  private final String codeName;
  private final String codeAddVal1;
  private final String codeAddVal2;
  private final String codeAddVal3;
  private final String sysCodeYn;
  private final Integer sortOrd;
  private final String useYn;

  public Code(
      String code,
      String codeId,
      String codeGroup,
      String codeName,
      String codeAddVal1,
      String codeAddVal2,
      String codeAddVal3,
      String sysCodeYn,
      Integer sortOrd,
      String useYn) {
    this.code = code;
    this.codeId = codeId;
    this.codeGroup = codeGroup;
    this.codeName = codeName;
    this.codeAddVal1 = codeAddVal1;
    this.codeAddVal2 = codeAddVal2;
    this.codeAddVal3 = codeAddVal3;
    this.sysCodeYn = sysCodeYn;
    this.sortOrd = sortOrd;
    this.useYn = useYn;
  }

  public static Code findByCode(String code) {
    if (code == null) {
      return null;
    }

    for (Code item : CODES) {
      if (code.equals(item.getCode())) {
        return item;
      }
    }

    return null;
  }

  public static Code findByCodeId(String codeId) {
    if (codeId == null) {
      return null;
    }

    for (Code item : CODES) {
      if (codeId.equals(item.getCodeId())) {
        return item;
      }
    }

    return null;
  }

  public static List<Code> findByCodeName(String codeName) {
    if (codeName == null) {
      return null;
    }

    List<Code> ret = new ArrayList<>();
    for (Code item : CODES) {
      if (codeName.equals(item.getCodeName())) {
        ret.add(item);
      }
    }

    return ret.size() == 0 ? null : ret;
  }

  public static List<Code> findByCodeGroup(String codeGroup) {
    List<Code> ret = new ArrayList<>();

    for (Code item : CODES) {
      if (codeGroup == null) {
        ret.add(item);
      } else {
        if (codeGroup.equals(item.getCodeGroup())) {
          ret.add(item);
        }
      }
    }

    return ret.size() == 0 ? null : ret;
  }

  // @formatter:off
  public static Code MB_001_MBR =
      new Code("MB_001_MBR", "mb-001-mbr", "MB_001", "회원", null, null, null, "Y", 1, "Y");
  public static Code MB_001_ADM =
      new Code("MB_001_ADM", "mb-001-adm", "MB_001", "관리자", null, null, null, "Y", 2, "Y");
  public static Code MB_002_001 =
      new Code("MB_002_001", "mb-002-001", "MB_002", "정상", null, null, null, "Y", 1, "Y");
  public static Code MB_002_002 =
      new Code("MB_002_002", "mb-002-002", "MB_002", "휴면", null, null, null, "Y", 2, "Y");
  public static Code MB_002_003 =
      new Code("MB_002_003", "mb-002-003", "MB_002", "정지", null, null, null, "Y", 3, "Y");
  public static Code MB_002_004 =
      new Code("MB_002_004", "mb-002-004", "MB_002", "탈퇴", null, null, null, "Y", 4, "Y");
  public static Code MB_003_001 =
      new Code("MB_003_001", "mb-003-001", "MB_003", "이용약관", null, null, null, "Y", 1, "Y");
  public static Code MB_003_002 =
      new Code("MB_003_002", "mb-003-002", "MB_003", "개인정보 수집", null, null, null, "Y", 2, "Y");
  public static Code MB_004_AND =
      new Code("MB_004_AND", "mb-004-and", "MB_004", "안드로이드", null, null, null, "Y", 1, "Y");
  public static Code MB_004_IOS =
      new Code("MB_004_IOS", "mb-004-ios", "MB_004", "아이폰", null, null, null, "Y", 2, "Y");
  public static Code MB_004_PC =
      new Code("MB_004_PC", "mb-004-pc", "MB_004", "PC", null, null, null, "Y", 1, "Y");
  public static Code MB_005_001 =
      new Code("MB_005_001", "mb-005-001", "MB_005", "정치적", null, null, null, "Y", 2, "Y");
  public static Code MB_005_002 =
      new Code("MB_005_002", "mb-005-002", "MB_005", "성적", null, null, null, "Y", 3, "Y");
  public static Code MB_005_003 =
      new Code("MB_005_003", "mb-005-003", "MB_005", "언어적", null, null, null, "Y", 4, "Y");
  public static Code MB_005_004 =
      new Code("MB_005_004", "mb-005-004", "MB_005", "기타", null, null, null, "Y", 1, "Y");
  public static Code MB_006_001 =
      new Code("MB_006_001", "mb-006-001", "MB_006", "회원", null, null, null, "Y", 2, "Y");
  public static Code MB_006_002 =
      new Code("MB_006_002", "mb-006-002", "MB_006", "콘텐츠", null, null, null, "Y", 3, "Y");
  public static Code MB_006_003 =
      new Code("MB_006_003", "mb-006-003", "MB_006", "방", null, null, null, "Y", 1, "Y");
  public static Code MB_007_001 =
      new Code("MB_007_001", "mb-007-001", "MB_007", "사용빈도 낮음", null, null, null, "Y", 2, "Y");
  public static Code MB_007_002 =
      new Code("MB_007_002", "mb-007-002", "MB_007", "서비스 이용 불편", null, null, null, "Y", 3, "Y");
  public static Code MB_007_003 =
      new Code("MB_007_003", "mb-007-003", "MB_007", "잦은 오류 발생", null, null, null, "Y", 4, "Y");
  public static Code MB_007_004 =
      new Code("MB_007_004", "mb-007-004", "MB_007", "기타", null, null, null, "Y", 1, "Y");
  public static Code CT_001_MAP =
      new Code("CT_001_MAP", "ct-001-map", "CT_001", "맵", null, null, null, "Y", 2, "Y");
  public static Code CT_001_AVT =
      new Code("CT_001_AVT", "ct-001-avt", "CT_001", "아바타", null, null, null, "Y", 3, "Y");
  public static Code CT_001_OBJ =
      new Code("CT_001_OBJ", "ct-001-obj", "CT_001", "오브젝트", null, null, null, "Y", 1, "Y");
  public static Code CT_002_001 =
      new Code("CT_002_001", "ct-002-001", "CT_002", "판매대기", null, null, null, "Y", 2, "Y");
  public static Code CT_002_002 =
      new Code("CT_002_002", "ct-002-002", "CT_002", "판매중", null, null, null, "Y", 3, "Y");
  public static Code CT_002_003 =
      new Code("CT_002_003", "ct-002-003", "CT_002", "판매중단", null, null, null, "Y", 1, "Y");
  public static Code CT_003_001 =
      new Code("CT_003_001", "ct-003-001", "CT_003", "심사요청", null, null, null, "Y", 2, "Y");
  public static Code CT_003_002 =
      new Code("CT_003_002", "ct-003-002", "CT_003", "진행중", null, null, null, "Y", 3, "Y");
  public static Code CT_003_003 =
      new Code("CT_003_003", "ct-003-003", "CT_003", "취소", null, null, null, "Y", 4, "Y");
  public static Code CT_003_004 =
      new Code("CT_003_004", "ct-003-004", "CT_003", "승인", null, null, null, "Y", 5, "Y");
  public static Code CT_003_005 =
      new Code("CT_003_005", "ct-003-005", "CT_003", "반려", null, null, null, "Y", 6, "Y");
  public static Code CT_003_006 =
      new Code("CT_003_006", "ct-003-006", "CT_003", "임시저장", null, null, null, "Y", 1, "Y");
  public static Code CT_004_INFO =
      new Code("CT_004_INFO", "ct-004-info", "CT_004", "기본정보", null, null, null, "Y", 2, "Y");
  public static Code CT_004_FILE =
      new Code("CT_004_FILE", "ct-004-file", "CT_004", "버전파일", null, null, null, "Y", 3, "Y");
  public static Code CT_004_ALL =
      new Code("CT_004_ALL", "ct-004-all", "CT_004", "기본정보+버전파일", null, null, null, "Y", 1, "Y");
  public static Code CT_005_001 =
      new Code("CT_005_001", "ct-005-001", "CT_005", "선정성", null, null, null, "Y", 2, "Y");
  public static Code CT_005_002 =
      new Code("CT_005_002", "ct-005-002", "CT_005", "폭력성", null, null, null, "Y", 3, "Y");
  public static Code CT_005_003 =
      new Code("CT_005_003", "ct-005-003", "CT_005", "오류 및 미완성", null, null, null, "Y", 4, "Y");
  public static Code CT_005_004 =
      new Code("CT_005_004", "ct-005-004", "CT_005", "홍보 및 광고", null, null, null, "Y", 1, "Y");
  public static Code CT_006_001 =
      new Code("CT_006_001", "ct-006-001", "CT_006", "승인시 바로 전시", null, null, null, "Y", 2, "Y");
  public static Code CT_006_002 =
      new Code("CT_006_002", "ct-006-002", "CT_006", "기간전시", null, null, null, "Y", 3, "Y");
  public static Code CT_006_003 =
      new Code("CT_006_003", "ct-006-003", "CT_006", "전시안함", null, null, null, "Y", 1, "Y");
  public static Code CT_007_001 =
      new Code("CT_007_001", "ct-007-001", "CT_007", "Embedded", null, null, null, "Y", 2, "Y");
  public static Code CT_007_002 =
      new Code("CT_007_002", "ct-007-002", "CT_007", "Addressable", null, null, null, "Y", 3, "Y");
  public static Code CT_007_003 =
      new Code("CT_007_003", "ct-007-003", "CT_007", "VContent", null, null, null, "Y", 4, "Y");
  public static Code CT_007_004 =
      new Code("CT_007_004", "ct-007-004", "CT_007", "Prepared", null, null, null, "Y", 1, "Y");
  public static Code CT_008_001 =
      new Code("CT_008_001", "ct-008-001", "CT_008", "대기", null, null, null, "Y", 2, "Y");
  public static Code CT_008_002 =
      new Code("CT_008_002", "ct-008-002", "CT_008", "진행중", null, null, null, "Y", 3, "Y");
  public static Code CT_008_003 =
      new Code("CT_008_003", "ct-008-003", "CT_008", "완료", null, null, null, "Y", 1, "Y");
  public static Code LOG_001_DEBUG =
      new Code("LOG_001_DEBUG", "log-001-debug", "LOG_001", "Debug", null, null, null, "Y", 2, "Y");
  public static Code LOG_001_INFO =
      new Code("LOG_001_INFO", "log-001-info", "LOG_001", "Info", null, null, null, "Y", 3, "Y");
  public static Code LOG_001_WARNING =
      new Code(
          "LOG_001_WARNING",
          "log-001-warning",
          "LOG_001",
          "Warning",
          null,
          null,
          null,
          "Y",
          4,
          "Y");
  public static Code LOG_001_ERROR =
      new Code("LOG_001_ERROR", "log-001-error", "LOG_001", "Error", null, null, null, "Y", 1, "Y");
  public static Code ROOM_001_ENTER =
      new Code("ROOM_001_ENTER", "room-001-enter", "ROOM_001", "입장", null, null, null, "Y", 1, "Y");
  public static Code ROOM_001_EXIT =
      new Code("ROOM_001_EXIT", "room-001-exit", "ROOM_001", "퇴장", null, null, null, "Y", 2, "Y");
  public static Code SV_001_001 =
      new Code("SV_001_001", "sv-001-001", "SV_001", "온라인", null, null, null, "Y", 1, "Y");
  public static Code SV_001_002 =
      new Code("SV_001_002", "sv-001-002", "SV_001", "오프라인", null, null, null, "Y", 1, "Y");
  public static Code SV_002_001 =
      new Code("SV_002_001", "sv-002-001", "SV_002", "상태변경", null, null, null, "Y", 1, "Y");
  public static Code SV_002_002 =
      new Code("SV_002_002", "sv-002-002", "SV_002", "할당", null, null, null, "Y", 2, "Y");
  public static Code SV_002_003 =
      new Code("SV_002_003", "sv-002-003", "SV_002", "토큰변경", null, null, null, "Y", 3, "Y");
  public static Code SV_002_004 =
      new Code("SV_002_004", "sv-002-004", "SV_002", "통신에러", null, null, null, "Y", 4, "Y");
  public static Code FRND_001_001 =
      new Code("FRND_001_001", "frnd-001-001", "FRND_001", "요청", null, null, null, "Y", 1, "Y");
  public static Code FRND_001_002 =
      new Code("FRND_001_002", "frnd-001-002", "FRND_001", "수락", null, null, null, "Y", 2, "Y");
  public static Code FRND_001_003 =
      new Code("FRND_001_003", "frnd-001-003", "FRND_001", "거절", null, null, null, "Y", 3, "Y");
  public static Code FRND_001_004 =
      new Code("FRND_001_004", "frnd-001-004", "FRND_001", "친구삭제", null, null, null, "Y", 4, "Y");
  public static Code AL_001_001 =
      new Code("AL_001_001", "al-001-001", "AL_001", "공지", null, null, null, "Y", 1, "Y");
  public static Code AL_001_002 =
      new Code("AL_001_002", "al-001-002", "AL_001", "심사승인", null, null, null, "Y", 2, "Y");
  public static Code AL_001_003 =
      new Code("AL_001_003", "al-001-003", "AL_001", "심사거절", null, null, null, "Y", 3, "Y");
  public static Code AL_001_004 =
      new Code("AL_001_004", "al-001-004", "AL_001", "친구요청", null, null, null, "Y", 4, "Y");
  public static Code ROOM_002_001 =
          new Code("ROOM_002_001", "room-002-001","ROOM_001", "일반", null, null, null, "Y", 1, "Y");
  public static Code ROOM_002_002 =
          new Code("ROOM_002_002", "room-002-002","ROOM_002", "강의실", null, null, null, "Y", 2, "Y");
  public static Code ROOM_002_003 =
          new Code("ROOM_002_003", "room-002-003","ROOM_003", "전시실", null, null, null, "Y", 3, "Y");
  public static Code REF_001_001 =
          new Code("REF_001_001","ref-001-001", "REF_001","첨부파일",null, null, null,"Y",1, "Y");
  public static Code REF_001_002 =
          new Code("REF_001_002","ref-001-002", "REF_001","링크",null, null, null,"Y",2, "Y");
  public static Code REF_001_003 =
          new Code("REF_001_003","ref-001-003", "REF_001","마이페이지",null, null, null,"Y",3, "Y");
  public static Code EXAM_001_001 =
          new Code("EXAM_001_001","exam-001-001", "EXAM_001","객관식 단일",null, null, null,"Y",1, "Y");
  public static Code EXAM_001_002 =
          new Code("EXAM_001_002","exam-001-002", "EXAM_001","객관식 복수",null, null, null,"Y",2, "Y");
  public static Code EXAM_001_003 =
          new Code("EXAM_001_003","exam-001-003", "EXAM_001","OX",null, null, null,"Y",3, "Y");
  public static Code EXAM_001_004 =
          new Code("EXAM_001_004","exam-001-004", "EXAM_001","단답형",null, null, null,"Y",4, "Y");
  public static Code EXAM_001_005 =
          new Code("EXAM_001_005","exam-001-005", "EXAM_001","서술형",null, null, null,"Y",5, "Y");
  public static Code EXAM_002_001 =
          new Code("EXAM_002_001","exam-002-001", "EXAM_002","골든벨",null, null, null,"Y",1, "Y");
  public static Code EXAM_002_002 =
          new Code("EXAM_002_002","exam-002-002", "EXAM_002","일반시험",null, null, null,"Y",2, "Y");
  public static Code CT_010_001 = new Code("CT_010_001","ct-010-001", "CT_010","일반","normal", null, null,"Y",1,"N");
  public static Code CT_010_002 = new Code("CT_010_002","ct-010-002", "CT_010","전시실","lecture", null, null,"Y",2,"N");
  public static Code CT_010_003 = new Code("CT_010_003","ct-010-003", "CT_010","강의실","exhibit", null, null,"Y",3,"N");
  public static Code CT_010_004 = new Code("CT_010_004","ct-010-004", "CT_010","골든벨","Goldenbell", null, null,"Y",4,"N");
  public static Code EXAM_003_001 =
          new Code("EXAM_003_001", "exam-003-001", "EXAM_003", "예정", null, null ,null , "Y", 1, "Y");
  public static Code EXAM_003_002 =
          new Code("EXAM_003_002", "exam-003-002", "EXAM_003", "진행", null, null ,null , "Y", 2, "Y");
  public static Code EXAM_003_003 =
          new Code("EXAM_003_003", "exam-003-003", "EXAM_003", "종료", null, null ,null , "Y", 3, "Y");
  public static Code EXAM_004_001 =
          new Code("EXAM_004_001", "exam-004-001", "EXAM_004", "대기", null, null ,null , "Y", 1, "Y");
  public static Code EXAM_004_002 =
          new Code("EXAM_004_002", "exam-004-002", "EXAM_004", "진행", null, null ,null , "Y", 2, "Y");
  public static Code EXAM_004_003 =
          new Code("EXAM_004_003", "exam-004-003", "EXAM_004", "완료", null, null ,null , "Y", 3, "Y");




  static {
    CODES.add(MB_001_MBR);
    CODES.add(MB_001_ADM);
    CODES.add(MB_002_001);
    CODES.add(MB_002_002);
    CODES.add(MB_002_003);
    CODES.add(MB_002_004);
    CODES.add(MB_003_001);
    CODES.add(MB_003_002);
    CODES.add(MB_004_AND);
    CODES.add(MB_004_IOS);
    CODES.add(MB_004_PC);
    CODES.add(MB_005_001);
    CODES.add(MB_005_002);
    CODES.add(MB_005_003);
    CODES.add(MB_005_004);
    CODES.add(MB_006_001);
    CODES.add(MB_006_002);
    CODES.add(MB_006_003);
    CODES.add(MB_007_001);
    CODES.add(MB_007_002);
    CODES.add(MB_007_003);
    CODES.add(MB_007_004);
    CODES.add(CT_001_MAP);
    CODES.add(CT_001_AVT);
    CODES.add(CT_001_OBJ);
    CODES.add(CT_002_001);
    CODES.add(CT_002_002);
    CODES.add(CT_002_003);
    CODES.add(CT_003_001);
    CODES.add(CT_003_002);
    CODES.add(CT_003_003);
    CODES.add(CT_003_004);
    CODES.add(CT_003_005);
    CODES.add(CT_003_006);
    CODES.add(CT_004_INFO);
    CODES.add(CT_004_FILE);
    CODES.add(CT_004_ALL);
    CODES.add(CT_005_001);
    CODES.add(CT_005_002);
    CODES.add(CT_005_003);
    CODES.add(CT_005_004);
    CODES.add(CT_006_001);
    CODES.add(CT_006_002);
    CODES.add(CT_006_003);
    CODES.add(CT_007_001);
    CODES.add(CT_007_002);
    CODES.add(CT_007_003);
    CODES.add(CT_007_004);
    CODES.add(CT_008_001);
    CODES.add(CT_008_002);
    CODES.add(CT_008_003);
    CODES.add(LOG_001_DEBUG);
    CODES.add(LOG_001_INFO);
    CODES.add(LOG_001_WARNING);
    CODES.add(LOG_001_ERROR);
    CODES.add(ROOM_001_ENTER);
    CODES.add(ROOM_001_EXIT);
    CODES.add(SV_001_001);
    CODES.add(SV_001_002);
    CODES.add(SV_002_001);
    CODES.add(SV_002_002);
    CODES.add(SV_002_003);
    CODES.add(SV_002_004);
    CODES.add(FRND_001_001);
    CODES.add(FRND_001_002);
    CODES.add(FRND_001_003);
    CODES.add(FRND_001_004);
    CODES.add(AL_001_001);
    CODES.add(AL_001_002);
    CODES.add(AL_001_003);
    CODES.add(AL_001_004);
    CODES.add(ROOM_002_001);
    CODES.add(ROOM_002_002);
    CODES.add(ROOM_002_003);
    CODES.add(REF_001_001);
    CODES.add(REF_001_002);
    CODES.add(REF_001_003);
    CODES.add(EXAM_001_001);
    CODES.add(EXAM_001_002);
    CODES.add(EXAM_001_003);
    CODES.add(EXAM_001_004);
    CODES.add(EXAM_001_005);
    CODES.add(EXAM_002_001);
    CODES.add(EXAM_002_002);
    CODES.add(CT_010_001);
    CODES.add(CT_010_002);
    CODES.add(CT_010_003);
    CODES.add(CT_010_004);
    CODES.add(EXAM_003_001);
    CODES.add(EXAM_003_002);
    CODES.add(EXAM_003_003);
    CODES.add(EXAM_004_001);
    CODES.add(EXAM_004_002);
    CODES.add(EXAM_004_003);
  }
  // @formatter:on

}
