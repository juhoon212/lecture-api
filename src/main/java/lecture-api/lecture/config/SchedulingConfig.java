package twentyoz.viven.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@ConditionalOnProperty(value = "app.scheduled.enabled", havingValue = "true", matchIfMissing = false)
@Configuration
@EnableScheduling
public class SchedulingConfig {}
