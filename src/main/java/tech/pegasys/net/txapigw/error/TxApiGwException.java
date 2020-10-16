package tech.pegasys.net.txapigw.error;

public class TxApiGwException extends RuntimeException {
  final ErrorCode errorCode;

  public TxApiGwException(final ErrorCode errorCode, final Throwable cause) {
    super(cause.getMessage());
    this.errorCode = errorCode;
  }

  public ExceptionResponseBody toResponseBody() {
    return new ExceptionResponseBody(errorCode.getCode(), errorCode.getLabel(), getMessage());
  }
}
