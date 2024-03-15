package twentyoz.viven.feature.sy.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.io.File;
import java.text.Normalizer;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import twentyoz.viven.feature.common.predicate.SearchPredicate;
import twentyoz.viven.feature.sy.mapper.SyAttachFileMapper;
import twentyoz.viven.feature.sy.model.QSyAttachFile;
import twentyoz.viven.feature.sy.model.SyAttachFile;
import twentyoz.viven.feature.sy.model.SyAttachFileGroup;
import twentyoz.viven.feature.sy.repository.SyAttachFileRepository;

/** 서비스 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AttachFileService {

  @Value("${app.attach.baseDir}")
  private String baseDir;

  private final SyAttachFileRepository repository;

  private final SyAttachFileMapper mapper;

  private final AttachFileGroupService attachFileGroupService;

  /**
   * 목록 조회
   *
   * @param search 검색 조건
   * @return 검색된 목록
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<SyAttachFile> getList(Predicate search) {
    return (List<SyAttachFile>)
        repository.findAll(search, Sort.by(new Order(Direction.DESC, "regDt")));
  }

  /**
   * 페이징 조회
   *
   * @param search 검색 조건
   * @param page 페이징 조건
   * @return 검색된 목록
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<SyAttachFile> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 조회
   *
   * @param id 식별번호
   * @return
   */
  @Transactional(readOnly = true)
  public SyAttachFile get(UUID id) {
    return repository
        .findOne(
            SearchPredicate.delFalse(
                new BooleanBuilder(QSyAttachFile.syAttachFile.attachFileId.eq(id))))
        .orElse(null);
  }

  /**
   * 등록
   *
   * @param entity
   * @return
   */
  public SyAttachFile add(SyAttachFile entity) {
    return repository.save(entity);
  }

  /**
   * 수정
   *
   * @param entity
   * @return
   */
  public SyAttachFile modify(UUID id, SyAttachFile entity) {
    return mapper.modify(entity, get(id));
  }

  /**
   * 삭제
   *
   * @param id 식별번호
   */
  public void remove(UUID id) {
    this.get(id).delTrue();
  }

  /**
   * 첨부파일 추가
   *
   * @param entity
   * @param file 업로드파일
   * @param rootDir 경로 (기본경로 하위에 경로 추가됨)
   * @return
   */
  @SneakyThrows
  public SyAttachFile addFile(SyAttachFile entity, MultipartFile file, String rootDir) {
    // 기본 파일 정보
    long fileSize = file.getSize();
    long oriFileCapa = file.getSize();
    String fileMimeTypeVal = file.getContentType();
    String oriFileName = Normalizer.normalize(file.getOriginalFilename(), Normalizer.Form.NFC);
    String fileExt = FilenameUtils.getExtension(oriFileName);

    // 파일명
    String fileName = UUID.randomUUID().toString();
    if (StringUtils.isNotBlank(fileExt)) {
      fileName += "." + fileExt;
    }

    // 첨부파일 경로
    String fileFullPath = String.format("%s/%s", rootDir, fileName);

    // 파일 저장
    File saved = new File(baseDir, fileFullPath);
    log.info("baseDir  :: {}", baseDir);
    log.info("fileFullPath  :: {}", fileFullPath);
    log.info("getAbsolutePath  :: {}", saved.getParentFile().getAbsolutePath());
    log.info("getParentFile  :: {}", saved.getParentFile());
    log.info("saved  :: {}", saved);
    FileUtils.forceMkdir(saved.getParentFile());
    if (saved.exists()) {
      log.info("true");
    } else {
      log.info("fail");
    }
    file.transferTo(saved);
    // 첨부파일그룹일련번호가 없는 경우 등록처리
    if (entity.getAttachFileGroupId() == null) {
      SyAttachFileGroup attachFileGroup = new SyAttachFileGroup();
      attachFileGroupService.add(attachFileGroup);
      entity.setAttachFileGroupId(attachFileGroup.getAttachFileGroupId());
    }

    // 파일 등록
    entity.setFileName(fileName);
    entity.setFileFullPath(fileFullPath);
    entity.setFileExt(fileExt);
    entity.setFileSize(fileSize);
    entity.setFileMimeTypeVal(fileMimeTypeVal);
    entity.setOriFileName(oriFileName);
    entity.setOriFileCapa(oriFileCapa);
    if (entity.getSortOrd() == null) {
      entity.setSortOrd(1);
    }
    return this.add(entity);
  }

  /**
   * 첨부파일 삭제
   *
   * @param attachFileIds 첨부파일식별번호 목록
   */
  public void removeFiles(List<UUID> attachFileIds) {
    BooleanBuilder search = new BooleanBuilder();
    search.and(QSyAttachFile.syAttachFile.attachFileId.in(attachFileIds));

    if (attachFileIds.size() > 0) {
      List<SyAttachFile> list = this.getList(search);
      if (list != null && list.size() > 0) {
        for (SyAttachFile attachFile : list) {
          attachFile.delTrue();
        }
      }
    }
  }

  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public SyAttachFile getPhysicalPath(String path) {
    SyAttachFile file = repository.findByFileFullPathAndDelYn(path, "N");
    return file;
  }

  /**
   * 첨부파일 그룹 내의 모든 파일 목록 조회
   *
   * @param attachFileGroupId 첨부파일그룹식별번호
   * @return
   */
  public List<SyAttachFile> getAttachFilesByGroupId(UUID attachFileGroupId) {
    return this.getList(
        new BooleanBuilder(QSyAttachFile.syAttachFile.attachFileGroupId.eq(attachFileGroupId)));
  }
}
