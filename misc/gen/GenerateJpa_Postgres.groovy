/*############################################################################################################
 JPA File Generator (IntelliJ)

 설명 : IntelliJ 툴을 통해 JPA File 을 자동생성 가능한 Generator
 ############################################################################################################

 # 주의사항
파일 덮어쓰기 확인 필요

 # 파일 자동 생성 방법
 1. IntelliJ의 데이터베이스에서 원하는 테이블을 선택 후 오른쪽 마우스를 클릭하여 Scripted Extensions 선택
 2. Scripted Extensions 내에서 Generator 를 선택
 3. Project Root 선택 후 확인 (src 상위)

 # 파일 생성 규칙 설정
 1. Generator 기본 설정 : 프로젝트에 맞게 기본 정보를 설정
 2. 파일 생성 타입 설정 : 생성하길 원하는 파일 종류를 설정 (기본 all 설정 시 전체 생성)

 # 파일 명명 규칙
 각 파일은 아래 명명 규칙에 의해 생성됨

 - Entity : table 명을 PascalCase 로 표기
 - Mapper : {Entity}Mapper
 - Repostory : {Entity}Repository
 - Service : {Entity.replace('schema')}Service (ex. SyAttachFile -> AttachFileService)
 - Api : {Entity.replace('schema')}Api (ex. SyAttachFile -> AttachFileService)
 - Form : {Entity.replace('schema')}Form (ex. SyAttachFile -> AttachFileForm)
 - FormMapper : {Entity.replace('schema')}FormMapper (ex. SyAttachFile -> AttachFileFormMapper)
 - Predicate : {Entity.replace('schema')}FormPredicate (ex. SyAttachFile -> AttachFileFormPredicate)

 # Primary Key
 1. Primary Key 가 단일키 or 복합키 여부에 따라 알맞게 생성되며 복합키인 경우에는 Entity, Repository 파일만 생성됨

 # 삭제여부
 1. 삭제여부가 있는 테이블은 Entity 에 삭제여부 처리에 대한 기본 메소드 생성됨

 */


import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil
import org.slf4j.LoggerFactory

import java.nio.file.Paths

/**############################################################################################################
 1. Generator 기본 설정
 ############################################################################################################ */

/**
 * 패키지 / 참조파일 경로
 */
baseDir = "/src/main/java/kr/twoz/viven"          // 파일이 생성되는 기본 패키지
refBaseDir = "/misc/gen/ref"                    // Generator 참조 파일 경로 (변경하지 않고 사용할 것)

/**
 * 기본 정보
 */
info = [
        basePackage: "kr.twoz.viven",             // 기본 패키지
        featureDir : baseDir + "/feature",      // feature 패키지 경로
        webapiDir  : baseDir + "/webapi",       // webapi 패키지 경로
        dir        : ""                         // 선택한 경로 (자동으로 설정되기 때문에 변경하지 않고 사용할 것)
]

/**############################################################################################################
 2. 파일 생성 타입 설정

 setting.type : 파일생성 타입 (all: 전체, f: feature, w: webapi)
 setting.overwrite : 파일 덮어쓰기 기능 (true 인 경우 파일 덮어쓰기하여 생성됨, false 인 경우 파일이 없는 경우에만 생성)
 setting.feature : feature 하위 파일생성여부
 setting.webapi : webapi 하위 파일생성여부 (* 테이블의 PK가 단일키인 경우에만 생성됨)
 ############################################################################################################ */

setting = [
        type     : "all",
        overwrite: true,
        feature  : [
                entity    : true,
                mapper    : true,
                repository: true,
                service   : true
        ],
        webapi   : [
                api       : true,
                form      : true,
                formMapper: true,
                predicate : true
        ],
]

/**############################################################################################################
 Generator methods
 ############################################################################################################ */

/**
 * DB Column Data Type 에 따른 자료형 설정
 */
typeMapping = [
        (~/(?i)bigint|numeric/): "Long",
        (~/(?i)int/)           : "Integer",
        (~/(?i)date|timestamp/): "DateTime",
        (~/(?i)time/)          : "LocalTime",
        (~/(?i)uuid/)          : "UUID",
        (~/(?i)/)              : "String"
]

tableInfo = [
        table            : "",     // 테이블 (제공되는 기본 테이블 객체)
        fields           : [],     // 전체 컬럼 목록
        cols             : [],     // 컬럼 목록 (등록/수정 정보 제외)
        normalCols       : [],     // 컬럼 목록 (PK/등록/수정 정보 제외)
        baseCols         : [],     // 컬럼 목록 (PK 제외)
        primaryKeys      : [],     // Priamry Key 배열
        primaryKeyName   : "",     // Priamry Key 명 (단일키인 경우 설정됨)
        primaryKeyType   : "",     // Priamry Key 데이터유형 (단일키인 경우 설정됨)
        isMultiPrimaryKey: false,  // Priamry Key 복합키 여부
        existReg         : false,  // 등록자식별번호/등록일시 컬럼 존재 여부
        existMod         : false,  // 수정자식별번호/수정일시 컬럼 존재 여부
        existUseYn       : false,  // 사용여부 컬럼 존재 여부
        existDelYn       : false,  // 삭제여부 컬럼 존재 여부
        existDateTime    : false,  // DateTime 컬럼 존재 여부
        existLocalTime   : false,  // LocalTime 컬럼 존재 여부
        existLocalDate   : false,  // LocalDate 컬럼 존재 여부
        existString      : false,  // PK, 등록/수정정보 제외하고 String 유형의 데이터가 존재하는지 여부
]

/**
 * 초기화
 * @param table
 * @param dir
 */
def init(table, dir) {
    def arrTableName = table.getName().tokenize('_')
    def baseClassName = ""
    for (t in arrTableName) {
        if (arrTableName[0] != t) {
            baseClassName = baseClassName + javaName(t, true)
        }
    }

    // 기본정보 설정
    info.schema = arrTableName[0]
    info.entityClassName = javaName(table.getName(), true)
    info.baseClassName = baseClassName
    info.dir = dir.toString()
    info.refDir = info.dir + "/" + refBaseDir
    info.tableName = table.getName()
    tableInfo.table = table

    // 테이블 기준으로 테이블/컬럼 정보 생성
    initTableInfo()

    // 파일 생성
    generateFiles(dir)
}

/**
 * 파일 생성
 */
def generateFiles(dir) {
    def genDir = "" // 파일이 생성될 경로
    def fileName = "" // 생성될 파일명

    // feature 파일 생성
    if (setting.type == 'all' || setting.type == 'f') {
        genDir = dir.toString() + info.featureDir + "/" + info.schema

        // Entity File 생성
        if (setting.feature.entity) generateFile(genDir, "model", "entity", info.entityClassName + ".java")

        // 복합키가 아닌 경우에만 Mapper, Service 파일 생성
        if (!tableInfo.isMultiPrimaryKey) {
            // PK, 등록/수정정보 외 컬럼이 존재하는 경우에만 mapper 생성
            if (tableInfo.fields.size > 0) {
                // Mapper File 생성
                if (setting.feature.mapper) generateFile(genDir, "mapper", "mapper", info.entityClassName + "Mapper.java")
            }
            // Service File 생성
            if (setting.feature.service) generateFile(genDir, "service", "service", info.baseClassName + "Service.java")
        }
        // Repository File 생성
        if (setting.feature.repository) generateFile(genDir, "repository", "repository", info.entityClassName + "Repository.java")
    }

    // webapi 파일 생성
    if (setting.type == 'all' || setting.type == 'w') {
        genDir = dir.toString() + info.webapiDir + "/" + info.schema

        // 복합키가 아니고 PK, 등록/수정 정보를 제외한 컬럼이 있는 경우에만 파일 생성
        if (!tableInfo.isMultiPrimaryKey && tableInfo.normalCols.size > 0) {
            // API File 생성
            if (setting.webapi.api) generateFile(genDir, "api", "api", info.baseClassName + "Api.java")

            // Form File 생성
            if (setting.webapi.form) generateFile(genDir, "form", "form", info.baseClassName + "Form.java")

            // FormMapper File 생성
            if (setting.webapi.formMapper) generateFile(genDir, "mapper", "formMapper", info.baseClassName + "FormMapper.java")

            // Predicate File 생성
            if (setting.webapi.predicate) generateFile(genDir, "predicate", "formPredicate", info.baseClassName + "FormPredicate.java")
        }
    }
}

/**
 * 파일 생성
 * @param dir 경로
 * @param subDir 하위 경로
 * @param fileType 파일 유형
 * @param fileName 파일명
 * @return
 */
def generateFile(dir, subDir, fileType, fileName) {
    def fileDir = dir + '/' + subDir

    // 디렉토리 생성
    makeDir(dir)
    makeDir(fileDir)

    def genFile = new File(fileDir, fileName);
    // 덮어쓰기 가능을 true 로 설정한 경우 바로 생성
    if (setting.overwrite) {
        genFile.withPrintWriter('UTF-8') {
            out -> generate(out, fileType)
        }

        // 덮어쓰기 가능을 false 로 설정한 경우 파일이 없는 경우에만 생성
    } else {
        if (!genFile.exists()) {
            genFile.withPrintWriter('UTF-8') {
                out -> generate(out, fileType)
            }
        }
    }
}

/**
 * 파일내용 변환
 * @param out
 * @param dir 참조파일 경로
 * @param refFileName 참조파일명
 */
def generate(out, fileType) {
    def tableName = tableInfo.table.getComment()
    def schemaUpper = Case.UPPER.apply(info.schema)
    def entityClassName = info.entityClassName
    def baseClassName = info.baseClassName
    def refFileName = getRefFileName(fileType) // 참조파일명
    def tableComment = tableName.replaceAll(info.schema + '_', '').replaceAll(schemaUpper + "_", "")

    def list = getRefContentList("", refFileName + ".java")
    for (item in list) {
        item = getFileContents(fileType, item)
        item = item.replaceAll("_schema_", info.schema)
        item = item.replaceAll("_basePackage_", info.basePackage)
        item = item.replaceAll("_tableComment_", tableComment)
        item = item.replaceAll("_BaseEntity_", entityClassName)
        item = item.replaceAll("_BaseEntityLower_", javaName(entityClassName, false))
        item = item.replaceAll("_BaseMapper_", entityClassName + "Mapper")
        item = item.replaceAll("_BaseRepository_", entityClassName + "Repository")
        item = item.replaceAll("_BaseService_", baseClassName + "Service")
        item = item.replaceAll("_BaseApi_", baseClassName + "Api")
        item = item.replaceAll("_BaseForm_", baseClassName + "Form")
        item = item.replaceAll("_BaseFormMapper_", baseClassName + "FormMapper")
        item = item.replaceAll("_BaseFormPredicate_", baseClassName + "FormPredicate")
        item = item.replaceAll("_baseClassName_", baseClassName)
        item = item.replaceAll("_Identifiable_", tableInfo.isMultiPrimaryKey == true ? entityClassName + ".PrimaryKey" : tableInfo.primaryKeyType)
        item = item.replaceAll("_pkId_", tableInfo.primaryKeyName)
        item = item.replaceAll("_pkDataType_", tableInfo.primaryKeyType)
        out.println(item)
    }
}

/**
 * 디렉토리 생성
 * @param path
 * @return
 */
def makeDir(path) {
    File file = new File(path)
    if (!file.exists()) {
        file.mkdir()
    }
}

/**
 * 파일내용 불러오기
 * @param fileType
 * @param item
 * @return
 */
def getFileContents(fileType, content) {
    def result = ""

    switch (fileType) {
        case 'entity':
            result = createEntity(content)
            break
        case 'mapper':
            result = createMapper(content)
            break
        case 'repository':
            result = createRepository(content)
            break
        case 'service':
            result = createService(content)
            break
        case 'api':
            result = createApi(content)
            break
        case 'form':
            result = createForm(content)
            break
        case 'formMapper':
            result = createFormMapper(content)
            break
        case 'formPredicate':
            result = createFormPredicate(content)
            break
    }

    return result
}

/**
 * 참조파일명 조회
 * @param fileType
 * @return
 */
def getRefFileName(fileType) {
    return "Base" + javaName(fileType, true)
}

/**
 * 테이블 정보 조회
 * @param table
 * @return
 */
def initTableInfo() {
    clearTableInfo()

    DasUtil.getColumns(tableInfo.table).reduce([]) { fields, col ->
        def name = javaName(col.getName(), false)
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value

        // 컬럼목록 생성 (전체)
        tableInfo.fields.add([
                name   : name,
                colName: col.getName(),
                type   : typeStr,
                comment: col.getComment()
        ])

        // 컬럼목록 생성 (Primary Key)
        if (DasUtil.isPrimary(col)) {
            tableInfo.primaryKeys.add([
                    name   : name,
                    colName: col.getName(),
                    type   : typeStr,
                    comment: col.getComment()
            ])
        } else {
            // 컬럼목록 생성 (PK, 등록/수정 정보 제외)
            if (name != "regId" && name != "regDt" && name != "modId" && name != "modDt") {
                tableInfo.normalCols.add([
                        name   : name,
                        colName: col.getName(),
                        type   : typeStr,
                        comment: col.getComment()
                ])

                // PK, 등록/수정정보 제외하고 String 유형의 데이터가 존재하는지 여부
                if (typeStr == "String") tableInfo.existString = true
            }

            // 컬럼목록 생성 (PK 제외)
            tableInfo.baseCols.add([
                    name   : name,
                    colName: col.getName(),
                    type   : typeStr,
                    comment: col.getComment()
            ])
        }

        // 컬럼목록 생성 (등록/수정 정보는 제외)
        if (name != "regId" && name != "regDt" && name != "modId" && name != "modDt") {
            tableInfo.cols.add([
                    name   : name,
                    colName: col.getName(),
                    type   : typeStr,
                    comment: col.getComment()
            ])
        }

        if (typeStr == "UUID") tableInfo.existUUID = true
        if (typeStr == "DateTime" && name != "regDt" && name != "modDt") tableInfo.existDateTime = true
        if (typeStr == "LocalTime") tableInfo.existLocalTime = true
        if (typeStr == "LocalDate") tableInfo.existLocalDate = true
        if (name == "regDt") tableInfo.existReg = true
        if (name == "modDt") tableInfo.existMod = true
        if (name == "useYn") tableInfo.existUseYn = true
        if (name == "delYn") tableInfo.existDelYn = true

        // 복합키인 경우 복합키 여부를 true 설정
        if (tableInfo.primaryKeys.size > 1) {
            tableInfo.isMultiPrimaryKey = true

            // 복합키가 아닌 경우 PK 데이터 타입 설정
        } else {
            tableInfo.primaryKeyName = tableInfo.primaryKeys[0].name
            tableInfo.primaryKeyType = tableInfo.primaryKeys[0].type
        }
    }
}

/**
 * 테이블 정보 clear
 * @return
 */
def clearTableInfo() {
    tableInfo.fields = []
    tableInfo.cols = []
    tableInfo.normalCols = []
    tableInfo.baseCols = []
    tableInfo.primaryKeys = []
    tableInfo.primaryKeyName = ""
    tableInfo.primaryKeyType = ""
    tableInfo.isMultiPrimaryKey = false
    tableInfo.existReg = false
    tableInfo.existMod = false
    tableInfo.existUseYn = false
    tableInfo.existDelYn = false
    tableInfo.existDateTime = false
    tableInfo.existLocalTime = false
    tableInfo.existLocalDate = false
    tableInfo.existString = false
}

/**
 * Column 변수 Convert 처리
 * @param content
 * @param col
 * @return
 */
def convertColumn(content, col) {
    content = content.replaceAll("_dataType_", col.type);
    content = content.replaceAll("_colName_", col.colName);
    content = content.replaceAll("_colNameUpper_", javaName(col.colName, true));
    content = content.replaceAll("_name_", col.name);
    content = content.replaceAll("_comment_", col.comment);
    return content
}

/**
 * 문자열을 CamelCase 표기로 변환
 * @param str 변환할 문자열
 * @param capitalize 첫번째 문자열 대문자 표기 여부
 * @return
 */
def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

/**
 * 파일 선택
 */
FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each {
        init(it, dir)
    }
}

/**
 * import class Line 생성 - 컬럼 데이터유형에 따라 import 구문 생성 (공통)
 * @param cols 컬럼 목록
 * @return
 */
def createImportClasses(cols) {
    def classes = ""
    def importLineUUID = "import java.util.UUID;"
    def importLineDateTime = "import org.joda.time.DateTime;"
    def importLineLocalTime = "import org.joda.time.LocalTime;"
    def importLineLocalDate = "import org.joda.time.LocalDate;"

    def existUUID = existDateTime = existLocalTime = existLocalDate = false
    for (item in cols) {
        if (item.type == 'UUID') {
            existUUID = true
        } else if (item.type == 'DateTime') {
            existDateTime = true
        } else if (item.type == 'LocalTime') {
            existLocalTime = true
        } else if (item.type == 'LocalDate') {
            existLocalDate = true
        }
    }

    if (existUUID) classes = classes == "" ? importLineUUID : classes + "\n" + importLineUUID
    if (existDateTime) classes = classes == "" ? importLineDateTime : classes + "\n" + importLineDateTime
    if (existLocalTime) classes == "" ? importLineLocalTime : classes + "\n" + importLineLocalTime
    if (existLocalDate) classes == "" ? importLineLocalDate : classes + "\n" + importLineLocalDate

    return classes
}

/** ############################################################################################################
 Generate Entity 관련 methods
 ############################################################################################################ */

/**
 * Entity File Convert
 * @param content
 * @return
 */
def createEntity(content) {
    def columns = createEntityColumns()
    def methods = createEntityMethods()
    def innerClasses = createEntityInnerClasses()
    def importClasses = createEntityImportClasses() // import class line 조회
    def extendsClass = tableInfo.existMod == true ? "RegModEntity" : "RegEntity"
    extendsClass = tableInfo.existUseYn == true ? "UseYnEntity" : extendsClass

    content = content.replaceAll("_tableName_", tableInfo.table.getName())
    content = content.replaceAll("_columns_", columns)
    content = content.replaceAll("_methods_", methods)
    content = content.replaceAll("_innerClasses_", innerClasses)

    // 테이블에 등록/수정 정보가 있는 경우
    if (tableInfo.existReg || tableInfo.existMod) {
        content = content.replaceAll("_ExtendsEntity_", "extends " + extendsClass)
        content = content.replaceAll("_equalsAndHashCodeCallSuper_", "true")
        // 없는 경우
    } else {
        content = content.replaceAll("_ExtendsEntity_", "")
        content = content.replaceAll("_equalsAndHashCodeCallSuper_", "false")
    }

    content = content.replaceAll("_importClasses_", importClasses)

    return content
}

/**
 * Entity Import class 구문 생성
 * @return
 */
def createEntityImportClasses() {
    def classes = createImportClasses(tableInfo.cols)
    def importSerializable = "import java.io.Serializable;"
    def extendsClass = tableInfo.existMod == true ? "RegModEntity" : "RegEntity"
    extendsClass = tableInfo.existUseYn == true ? "UseYnEntity" : extendsClass

    if (tableInfo.isMultiPrimaryKey) classes = classes == "" ? importSerializable : classes + "\n" + importSerializable
    if (tableInfo.existDelYn) classes = classes + "\n" + "import org.apache.commons.lang3.ObjectUtils;"
    if (tableInfo.existReg || tableInfo.existMod) classes = classes + "\n" + "import " + info.basePackage +".feature.common.model." + extendsClass + ";"
    return classes
}

/**
 * Entity Columns 생성
 *
 * @return
 */
def createEntityColumns() {
    def result = ""
    // Primary Key
    def conPk = getRefContentList("entity", "ColumnPrimaryKey.java")
    result = convertColumns(tableInfo.primaryKeys, conPk)

    // Columns
    def conCol = getRefContentList("entity", "Column.java")
    result += convertColumns(tableInfo.normalCols, conCol)
    return result
}

/**
 * 참조 파일 내용 리스트로 조회
 * @param dir
 * @param name
 * @return
 */
def getRefContentList(dir, name) {
    return new File(info.refDir + "/" + dir, name).collect { it }
}

/**
 * 참조 파일 컨텐츠 텍스트 가져오기
 * @param dir
 * @param name
 * @return
 */
def getRefContentText(dir, name) {
    return new File(info.refDir + "/" + dir, name).text
}

/**
 * Convert Columns 
 * @param cols 컬럼 목록
 * @param content 기본 파일내용
 * @return
 */
def convertColumns(cols, content) {
    def result = ""
    for (col in cols) {
        def column = ""
        for (item in content) {
            result += "\n" + convertColumn(item, col)
        }
        result += "\n" + column
    }
    return result
}

/**
 * Entity method 생성
 * @return
 */
def createEntityMethods() {
    def result = ""

    /*
     * Method - getId()
     */
    def retId = getRefContentText("entity", "MethodGetId.java")
    def dataType = ""
    def returnId = ""

    // 복합키인 경우
    if (tableInfo.isMultiPrimaryKey) {
        def ids = ""
        for (key in tableInfo.primaryKeys) {
            ids += ids == "" ? key.name : ", " + key.name
        }
        dataType = "PrimaryKey"
        returnId = "new PrimaryKey(" + ids + ")"

        // 일반키인 경우
    } else {
        dataType = tableInfo.primaryKeyType
        returnId = tableInfo.primaryKeyName
    }

    result = retId.replaceAll("_dataType_", dataType)
    result = result.replaceAll("_returnId_", returnId)

    /*
     * Method - delTrue(), delFalse(), useTrue(), useFalse()
     */
    if (tableInfo.existDelYn || tableInfo.existUseYn) {
        def methodDefaultValue = getRefContentText("/entity", "MethodDefaultValue.java")
        def methodUseYn = tableInfo.existUseYn == true ? "useYn = ObjectUtils.defaultIfNull(useYn, \"Y\");" : ""
        def methodDelYn = tableInfo.existDelYn == true ? "delYn = ObjectUtils.defaultIfNull(delYn, \"N\");" : ""
        def _methodContents_ = methodUseYn
        _methodContents_ = _methodContents_ != "" ? methodUseYn + "\n    " + methodDelYn : methodDelYn        
        methodDefaultValue = methodDefaultValue.replaceAll("_methodContents_", _methodContents_)

        result += "\n\n" + getRefContentText("/entity", "MethosOnPrePersist.java")
        result += "\n\n" + methodDefaultValue
        if (tableInfo.existUseYn) result += "\n\n" + getRefContentText("/entity", "MethodUseYn.java")
        if (tableInfo.existDelYn) result += "\n\n" + getRefContentText("/entity", "MethodDelYn.java")
    }

    return result
}

/**
 * Entity Inner Class 생성
 * @param
 * @return
 */
def createEntityInnerClasses() {
    def result = ""

    // Primary Key 가 복합키인 경우 PrimaryKey Class 생성
    if (tableInfo.isMultiPrimaryKey) {
        def classPrimaryKey = getRefContentText("/entity", "ClassPrimaryKey.java")
        for (key in tableInfo.primaryKeys) {
            result += result == "" ? "private " + key.type + " " + key.name + ";" : "\n    " + "private " + key.type + " " + key.name + ";"
        }
        result = "\n" + classPrimaryKey.replaceAll("_primaryKeys_", result)
    }

    if (tableInfo.existReg || tableInfo.existMod) {

    }

    return result
}


/** ############################################################################################################
 Generate Mapper 관련 methods
 ############################################################################################################ */

/**
 * Mapper 생성
 * @param content
 * @return
 */
def createMapper(content) {
    def modifyLogic = createMapperLogic()
    def importClasses = createMapperImportClasses()

    content = content.replaceAll("_importClasses_", importClasses)
    content = content.replaceAll("_modifyLogic_", modifyLogic)
    return content
}

/**
 * Mapper Import class 구문 생성
 * @return
 */
def createMapperImportClasses() {
    def classes = ""
    def importLineStringUtils = "import org.apache.commons.lang3.StringUtils;"

    if (tableInfo.existString) {
        classes = importLineStringUtils
    }
    return classes
}

/**
 * Mapper Columns 생성
 * @return
 */
def createMapperLogic() {
    def result = ""
    for (col in tableInfo.normalCols) {
        def logic = ""
        def content = getRefContentList("/mapper", "ModifyColumn.java")

        if ("String".equals(col.type)) {
            content = getRefContentList("/mapper", "ModifyStringColumn.java")
        }

        for (item in content) {
            logic += "\n" + convertColumn(item, col)
        }
        result += "\n" + logic
    }
    return result
}

/** ############################################################################################################
 Generate Repository 관련 methods
 ############################################################################################################ */

/**
 * Repository 생성
 * @param content
 * @return
 */
def createRepository(content) {
    def importClasses = ""

    // PK가 단일키인 경우에만 import 문 추가
    if (!tableInfo.isMultiPrimaryKey) {
        importClasses = createImportClasses(tableInfo.primaryKeys)
    }

    content = content.replaceAll("_importClasses_", importClasses)
    return content
}

/** ############################################################################################################
 Generate Service
 ############################################################################################################ */

/**
 * Service 생성
 * @param content
 * @return
 */
def createService(content) {
    def importClasses = createServiceImportClasses()
    def removeMethods = createRemoveMethods()

    content = content.replaceAll("_importClasses_", importClasses)
    content = content.replaceAll("_removeMethods_", removeMethods)
    return content
}

/**
 * Service Import class 구문 생성
 * @return
 */
def createServiceImportClasses() {
    def classes = ""

    // PK가 단일키인 경우에만 import 문 추가
    if (!tableInfo.isMultiPrimaryKey) {
        classes = createImportClasses(tableInfo.primaryKeys)
    }

    return classes
}

/**
 * Service 삭제처리 method 구문 생성
 * @return
 */
def createRemoveMethods() {
    def result = ""
    def content = getRefContentText("/service", "MethodRemove.java")
    result = convertColumn(content, tableInfo.primaryKeys[0])

    // 삭제여부가 있는 경우
    if (tableInfo.existDelYn) {
        result = result.replaceAll("_removeLogic_", "this.get(id).delTrue();")
    } else {
        result = result.replaceAll("_removeLogic_", "repository.delete(id);")
    }
    return result
}


/** ############################################################################################################
 Generate API
 ############################################################################################################ */

/**
 * API 생성
 * @param content
 * @return
 */
def createApi(content) {
    def importClasses = createApiImportClasses()
    def apiUrl = "/" + info.schema + "/" + info.tableName.replaceAll(info.schema + "_", "")
    apiUrl = apiUrl.replaceAll("_", "-")

    content = content.replaceAll("_importClasses_", importClasses)
    content = content.replaceAll("_ApiUrl_", apiUrl)
    return content
}

/**
 * API Import class 구문 생성
 * @return
 */
def createApiImportClasses() {
    def classes = ""

    // PK가 단일키인 경우에만 import 문 추가
    if (!tableInfo.isMultiPrimaryKey) {
        classes = createImportClasses(tableInfo.primaryKeys)
    }

    return classes
}

/**
 ############################################################################################################
 Generate Form
 ############################################################################################################
 */

/**
 * Form 생성
 * @param content
 * @return
 */
def createForm(content) {
    def importClasses = createFormImportClasses()
    def getColumns = createApiModelProperty('get') // 조회 - 전체 컬럼 목록
    def addColumns = createApiModelProperty('add') // 등록 - 컬럼 목록 (PK/등록/수정 정보 제외)
    def modifyColumns = createApiModelProperty('modify') // 수정 - 컬럼 목록 (등록/수정 정보 제외)

    content = content.replaceAll("_importClasses_", importClasses)
    content = content.replaceAll("_inputAdd_", addColumns)
    content = content.replaceAll("_inputModify_", modifyColumns)
    content = content.replaceAll("_outputGetAll_", getColumns)
    content = content.replaceAll("_outputGet_", getColumns)
    return content
}

/**
 * API Model Property 생성
 * @param type 생성유형
 *
 * get : 조회용 property 생성 (전체 컬럼에 대해 생성)
 * add : 등록용 property 생성 (PK, 등록/수정 정보 제외하고 생성)
 * modify : 수정용 property 생성 (등록/수정 정보 제외하고 생성)
 * @return
 */
def createApiModelProperty(type) {
    def result = ""

    // 수정인 경우 PK 항목 생성
    if (type != 'add') {
        def formPk = ""
        if (type == 'modify') {
            formPk = getRefContentList("/form", "PropertyPrimaryKey.java")
        } else if (type == 'get') {
            formPk = getRefContentList("/form", "Property.java")
        }
        result = convertColumns(tableInfo.primaryKeys, formPk)
    }

    // 기본 항목 생성
    def form = getRefContentList("/form", "Property.java").collect { it }

    // 조회인 경우 등록/수정 포함하여 항목 생성
    if (type == 'get') {
        result += convertColumns(tableInfo.baseCols, form)
        // 조회가 아닌 경우 등록/수정 제외하고 항목 생성
    } else {
        result += convertColumns(tableInfo.normalCols, form)
    }

    return result
}

/**
 * Form Import class 구문 생성
 * @return
 */
def createFormImportClasses() {
    return createImportClasses(tableInfo.fields)
}

/**
 ############################################################################################################
 Generate FormMapper
 ############################################################################################################
 */

/**
 * FormMapper 생성
 * @param content
 * @return
 */
def createFormMapper(content) {
    def importClasses = createFormMapperImportClasses()
    content = content.replaceAll("_importClasses_", importClasses)
    return content
}

/**
 * FormMapper Import class 구문 생성
 * @return
 */
def createFormMapperImportClasses() {
    def classes = ""
    // Primary Key 가 UUID 인 경우
    if ("UUID".equals(tableInfo.primaryKeys[0].type)) {
        classes = "import java.util.UUID;"
    } else {
        classes = "import java.lang.String;"
    }
    return classes
}

/**
 ############################################################################################################
 Generate FormPredicate
 ############################################################################################################
 */

/**
 * FormPredicate 생성
 * @param content
 * @return
 */
def createFormPredicate(content) {
    def importClasses = createFormPredicateImportClasses()

    content = content.replaceAll("_importClasses_", importClasses)
    return content
}

/**
 * FormMapper Import class 구문 생성
 * @return
 */
def createFormPredicateImportClasses() {
    def classes = ""
    // Primary Key 가 UUID 인 경우
    if ("UUID".equals(tableInfo.primaryKeys[0].type)) {
        classes = "import java.util.UUID;"
    } else {
        classes = "import java.lang.String;"
    }
    return classes
}