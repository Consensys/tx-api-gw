package tech.pegasys.net.txapigw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;
import tech.pegasys.net.txapigw.service.TransactionService;

@RestController
@RequestMapping(
    path = "/tx",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class TxController {
  @Autowired private TransactionService transactionService;

  @PostMapping(path = "/legacy/{privateKey}")
  public void submitTransaction(
      @PathVariable String privateKey, @RequestBody final Transaction transaction) {
    transactionService.submit(privateKey, transaction);
  }

  @PostMapping(path = "/eip1559/{privateKey}")
  public void submitTransaction(
      @PathVariable String privateKey, @RequestBody final EIP1559Transaction transaction) {
    transactionService.submit(privateKey, transaction);
  }
}
