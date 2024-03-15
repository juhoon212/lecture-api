package twentyoz.viven.webapi.client.vi.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.util.UUID;

public class RefForm {


    public static class Input {
        @Data
        @Builder
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Add {

            @ApiModelProperty(value = "자료 이름", required = true)
            private String refName;

            @ApiModelProperty("첨부파일 그룹 식별번호")
            private UUID attachFileGroupId;

            @ApiModelProperty("링크")
            private String refLink;

            @ApiModelProperty(value = "자료 유형 코드 - REF_001_001 : 첨부파일, - REF_001_002 : 링크", required = true)
            private String refTypeCode;

            @ApiModelProperty("설명 내용")
            private String descCont;
        }
        @Data
        @Builder
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Modify {

            @ApiModelProperty(value = "자료실 식별 번호", required = true)
            private UUID refId;

            @ApiModelProperty("자료 이름")
            private String refName;

            @ApiModelProperty("링크")
            private String refLink;

            @ApiModelProperty("첨부파일 그룹 식별번호")
            private UUID attachFileGroupId;

            @ApiModelProperty(value = "자료 유형 코드 - REF_001_001 : 첨부파일, - REF_001_002 : 링크", required = true)
            private String refTypeCode;

            @ApiModelProperty("설명 내용")
            private String descCont;
        }

        @Data
        @Builder
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        public static class GetAll {

            @ApiModelProperty("자료 제목")
            private String refName;

            @ApiModelProperty("자료 내용")
            private String descCont;
        }
    }
    public static class Output {

        @Data
        @Builder
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Get {

            @ApiModelProperty("자료실 식별 번호")
            private UUID refId;

            @ApiModelProperty("회원 식별 번호")
            private UUID mbrId;

            @ApiModelProperty("회원 닉네임")
            private String nickName;

            @ApiModelProperty("자료 이름")
            private String refName;

            @ApiModelProperty("자료 유형 코드 - REF_001_001 : 첨부파일, - REF_001_002 : 링크")
            private String refTypeCode;

            @ApiModelProperty("링크")
            private String refLink;

            @ApiModelProperty("첨부파일 그룹 식별번호")
            private UUID attachFileGroupId;

            @ApiModelProperty("설명 내용")
            private String descCont;

            @ApiModelProperty("삭제 여부")
            private String delYn;

            @ApiModelProperty("등록자 식별번호")
            private UUID regId;

            @ApiModelProperty("등록일시")
            private DateTime regDt;

            @ApiModelProperty("수정자 식별번호")
            private UUID modId;

            @ApiModelProperty("수정일시")
            private DateTime modDt;


        }

        @Data
        @Builder
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        public static class GetAll {

            @ApiModelProperty("자료실 식별 번호")
            private UUID refId;

            @ApiModelProperty("회원 식별 번호")
            private UUID mbrId;

            @ApiModelProperty("회원 닉네임")
            private String nickName;

            @ApiModelProperty("자료 이름")
            private String refName;

            @ApiModelProperty("자료 유형 코드 - REF_001_001 : 첨부파일, - REF_001_002 : 링크")
            private String refTypeCode;

            @ApiModelProperty("링크")
            private String refLink;

            @ApiModelProperty("첨부파일 그룹 식별번호")
            private UUID attachFileGroupId;

            @ApiModelProperty("설명 내용")
            private String descCont;

            @ApiModelProperty("삭제 여부")
            private String delYn;

            @ApiModelProperty("등록자 식별번호")
            private UUID regId;

            @ApiModelProperty("등록일시")
            private DateTime regDt;

            @ApiModelProperty("수정자 식별번호")
            private UUID modId;

            @ApiModelProperty("수정일시")
            private DateTime modDt;
        }


    }
}
