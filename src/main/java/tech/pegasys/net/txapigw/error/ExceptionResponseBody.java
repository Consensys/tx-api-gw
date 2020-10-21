package tech.pegasys.net.txapigw.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponseBody {
  private int errorCode;
  private String errorLabel;
  private String errorMessage;
}
