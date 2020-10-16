package tech.pegasys.net.txapigw.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseBody {
  private int errorCode;
  private String errorLabel;
  private String errorMessage;
}
