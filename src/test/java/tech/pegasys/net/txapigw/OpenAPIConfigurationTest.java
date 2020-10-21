package tech.pegasys.net.txapigw;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class OpenAPIConfigurationTest {

  @Test
  public void givenNormalConditions_whenConstructor_thenReturnInstance() {
    assertThat(new OpenAPIConfiguration()).isNotNull();
  }
}
