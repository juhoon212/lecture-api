package twentyoz.viven.security;

import com.auth0.jwt.interfaces.Claim;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JwtPayload {

  private static final String KEY_MBR_ID = "mbrId";

  private static final String KEY_LOGIN_ID = "loginId";

  private static final String KEY_MBR_NAME = "mbrName";

  private static final String KEY_NICKNAME = "nickname";

  private static final String KEY_CREATED_AT = "createdAt";

  private String mbrId;

  private String loginId;

  private String mbrName;

  private String nickname;

  private long createdAt;

  public JwtPayload(Map<String, Claim> m) {
    this.mbrId = m.get(KEY_MBR_ID).asString();
    this.loginId = m.get(KEY_LOGIN_ID).asString();
    this.mbrName = m.get(KEY_MBR_NAME).asString();
    this.nickname = m.get(KEY_NICKNAME).asString();
    this.createdAt = m.get(KEY_CREATED_AT).asLong();
  }

  public UUID asMbrId() {
    return mbrId == null ? null : UUID.fromString(mbrId);
  }

  public Map<String, Object> asMap() {
    Map<String, Object> m = new LinkedHashMap<>();
    m.put(KEY_MBR_ID, mbrId);
    m.put(KEY_LOGIN_ID, loginId);
    m.put(KEY_MBR_NAME, mbrName);
    m.put(KEY_NICKNAME, nickname);
    m.put(KEY_CREATED_AT, createdAt);

    return m;
  }
}
