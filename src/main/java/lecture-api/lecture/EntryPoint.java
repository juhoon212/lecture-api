package twentyoz.viven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EntryPoint {

  public static void main(String[] args) {
    SpringApplication.run(EntryPoint.class, args);
  }
}
