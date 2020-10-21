package tech.pegasys.net.txapigw.error;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

public class TxApiGwExceptionTest {

  @Test
  public void givenException_whenToResponseBody_thenReturnCorrectValue() {
    final TxApiGwException txApiGwException =
        new TxApiGwException(
            ErrorCode.ETHEREUM_CLIENT_ERROR, new RuntimeException("test error message"));
    final ExceptionResponseBody exceptionResponseBody = txApiGwException.toResponseBody();
    assertThat(exceptionResponseBody.getErrorCode())
        .isEqualTo(ErrorCode.ETHEREUM_CLIENT_ERROR.getCode());
    assertThat(exceptionResponseBody.getErrorLabel())
        .isEqualTo(ErrorCode.ETHEREUM_CLIENT_ERROR.getLabel());
    assertThat(exceptionResponseBody.getErrorMessage()).isEqualTo("test error message");
    assertThatThrownBy(
            () -> {
              throw txApiGwException;
            })
        .hasMessage("test error message");
  }
}
