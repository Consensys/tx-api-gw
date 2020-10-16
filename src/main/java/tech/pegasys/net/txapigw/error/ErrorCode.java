package tech.pegasys.net.txapigw.error;

import lombok.Getter;

public enum ErrorCode {
  DEFAULT(-1),
  ETHEREUM_CLIENT_ERROR(-1000);

  @Getter private final int code;
  @Getter private final String label;

  ErrorCode(final int code) {
    this.code = code;
    this.label = this.name();
  }
}
