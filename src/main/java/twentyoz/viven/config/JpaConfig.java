package twentyoz.viven.config;

import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import twentyoz.viven.feature.common.hibernate.UserInfoAuditorAware;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

  @Bean
  public AuditorAware<UUID> auditorAware() {
    return new UserInfoAuditorAware();
  }
}
