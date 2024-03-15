package twentyoz.viven.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources(@PropertySource(value = "classpath:git.properties", ignoreResourceNotFound = true))
public class AppConfig {}
