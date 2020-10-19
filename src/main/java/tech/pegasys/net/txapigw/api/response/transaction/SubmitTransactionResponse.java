package tech.pegasys.net.txapigw.api.response.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubmitTransactionResponse {
  private String transactionHash;
}
