package tech.pegasys.net.txapigw.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tech.pegasys.net.txapigw.api.TransactionAPI;
import tech.pegasys.net.txapigw.api.response.transaction.SubmitTransactionResponse;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;
import tech.pegasys.net.txapigw.service.TransactionService;

@RestController
public class TxController implements TransactionAPI {
  @Autowired private TransactionService transactionService;

  public SubmitTransactionResponse submitTransaction(
      final String privateKey, final Transaction transaction) {
    return transactionService.submit(privateKey, transaction);
  }

  public SubmitTransactionResponse submitTransaction(
      final String privateKey, final EIP1559Transaction transaction) {
    return transactionService.submit(privateKey, transaction);
  }
}
