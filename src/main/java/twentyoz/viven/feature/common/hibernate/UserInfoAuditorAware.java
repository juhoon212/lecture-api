package twentyoz.viven.feature.common.hibernate;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.AuditorAware;
import twentyoz.viven.security.JwtPayload;
import twentyoz.viven.security.JwtPayloadHolder;

/**
 * Spring Data JPA 제공하는 AuditorAware
 *
 * <pre>
 * '@CreatedBy', '@LastModifiedBy' 를 엔티티의 변수에 추가하면
 * 현재 로그인 정보를 찾아 변수에 주입한다.
 * </pre>
 */
public class UserInfoAuditorAware implements AuditorAware<UUID> {

  @Override
  public Optional<UUID> getCurrentAuditor() {
    JwtPayload payload = JwtPayloadHolder.get();

    return payload == null ? Optional.empty() : Optional.of(payload.asMbrId());
  }
}
