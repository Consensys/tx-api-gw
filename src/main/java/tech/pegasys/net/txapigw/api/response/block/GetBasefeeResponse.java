package tech.pegasys.net.txapigw.api.response.block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetBasefeeResponse {
  private String baseFee;
}
