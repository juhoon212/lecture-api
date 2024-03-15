package twentyoz.viven.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig implements Jackson2ObjectMapperBuilderCustomizer {

  @Override
  public void customize(Jackson2ObjectMapperBuilder builder) {
    builder
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .dateFormat(new StdDateFormat())
        .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .featuresToDisable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
        .featuresToEnable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
        .featuresToEnable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
        .modulesToInstall(new JavaTimeModule())
        .build();
  }
}
