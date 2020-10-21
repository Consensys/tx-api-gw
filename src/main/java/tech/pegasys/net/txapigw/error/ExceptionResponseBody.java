package tech.pegasys.net.txapigw.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponseBody {
  private int errorCode;
  private String errorLabel;
  private String errorMessage;
}
