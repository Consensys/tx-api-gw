package tech.pegasys.net.txapigw.error;

public enum ErrorCode {
  DEFAULT(-1),
  ETHEREUM_CLIENT_ERROR(-1000);

  private final int code;
  private final String label;

  ErrorCode(final int code) {
    this.code = code;
    this.label = this.name();
  }

  public int getCode() {
    return code;
  }

  public String getLabel() {
    return label;
  }
}
