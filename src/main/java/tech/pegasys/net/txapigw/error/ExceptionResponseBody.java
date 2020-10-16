package tech.pegasys.net.txapigw.error;

public class ExceptionResponseBody {
  private int errorCode;
  private String errorLabel;
  private String errorMessage;

  public ExceptionResponseBody(int errorCode, String errorLabel, String errorMessage) {
    this.errorCode = errorCode;
    this.errorLabel = errorLabel;
    this.errorMessage = errorMessage;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorLabel() {
    return errorLabel;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
