package tech.pegasys.net.txapigw;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.SpringApplication;

@RunWith(PowerMockRunner.class)
public class TxApiGwApplicationMainTest {

  @Test
  @PrepareForTest(SpringApplication.class)
  public void main() {
    mockStatic(SpringApplication.class);
    TxApiGwApplication.main(new String[] {});
    verifyStatic(SpringApplication.class);
    SpringApplication.run(TxApiGwApplication.class);
  }
}
