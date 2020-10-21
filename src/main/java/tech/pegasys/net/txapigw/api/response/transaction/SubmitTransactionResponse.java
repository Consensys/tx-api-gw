package tech.pegasys.net.txapigw.api.response.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SubmitTransactionResponse {
  private String transactionHash;
}
