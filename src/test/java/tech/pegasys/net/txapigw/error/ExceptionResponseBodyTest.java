package tech.pegasys.net.txapigw.error;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ExceptionResponseBodyTest {

  @Test
  public void whenCallGetters_thenReturnCorrectValues() {
    final ExceptionResponseBody exceptionResponseBody =
        new ExceptionResponseBody(ErrorCode.ETHEREUM_CLIENT_ERROR.getCode(), "label", "message");
    assertThat(exceptionResponseBody.getErrorCode())
        .isEqualTo(ErrorCode.ETHEREUM_CLIENT_ERROR.getCode());
    assertThat(exceptionResponseBody.getErrorLabel()).isEqualTo("label");
    assertThat(exceptionResponseBody.getErrorMessage()).isEqualTo("message");
  }
}
