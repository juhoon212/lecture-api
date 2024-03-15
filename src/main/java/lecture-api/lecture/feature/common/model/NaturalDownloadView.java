package twentyoz.viven.feature.common.model;

import java.io.File;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NaturalDownloadView extends DownloadView {

  public NaturalDownloadView(String path) {
    super(path);
  }

  public NaturalDownloadView(String path, String filename) {
    super(path, filename);
  }

  public NaturalDownloadView(String path, String filename, String contentType) {
    super(path, filename, contentType);
  }

  public NaturalDownloadView(File file) {
    super(file);
  }

  public NaturalDownloadView(File file, String filename) {
    super(file, filename);
  }

  public NaturalDownloadView(File file, String filename, String contentType) {
    super(file, filename, contentType);
  }

  /**
   * 다운로드 헤더 준비
   *
   * @param request
   * @param response
   * @param filename
   * @throws UnsupportedEncodingException
   */
  protected void prepareAttachmentFilename(
      HttpServletRequest request, HttpServletResponse response, String filename)
      throws UnsupportedEncodingException {
    String encodeFilename = this.getFilenameEncoder().encode(request, filename);
    response.setHeader("Content-Disposition", String.format("filename=\"%s\"", encodeFilename));
  }
}
