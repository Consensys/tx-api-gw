package tech.pegasys.net.txapigw.api.response.block;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetBasefeeResponse {
  private String baseFee;
}
