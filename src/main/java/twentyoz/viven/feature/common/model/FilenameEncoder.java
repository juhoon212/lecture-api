package twentyoz.viven.feature.common.model;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

public abstract class FilenameEncoder {

  public abstract String encode(HttpServletRequest request, String filename)
      throws UnsupportedEncodingException;
}
