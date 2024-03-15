package twentyoz.viven.feature.common.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;

public class DefaultFilenameEncoder extends FilenameEncoder {

  @Override
  public String encode(HttpServletRequest request, String filename)
      throws UnsupportedEncodingException {
    return URLEncoder.encode(filename, StandardCharsets.UTF_8.name());
  }
}
