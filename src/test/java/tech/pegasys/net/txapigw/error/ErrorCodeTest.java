package tech.pegasys.net.txapigw.error;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ErrorCodeTest {

  @Test
  public void givenDefault_whenGetCode_thenReturnCorrectValue() {
    assertThat(ErrorCode.DEFAULT.getCode()).isEqualTo(-1);
  }

  @Test
  public void givenDefault_whenGetLabel_thenReturnCorrectValue() {
    assertThat(ErrorCode.DEFAULT.getLabel()).isEqualTo("DEFAULT");
  }
}
