package tech.pegasys.net.txapigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TxApiGwApplication {

  public static void main(final String[] args) {
    SpringApplication.run(TxApiGwApplication.class, args);
  }
}
