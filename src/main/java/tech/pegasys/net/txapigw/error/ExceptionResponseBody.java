package tech.pegasys.net.txapigw.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseBody {
  private int errorCode;
  private String errorLabel;
  private String errorMessage;
}
