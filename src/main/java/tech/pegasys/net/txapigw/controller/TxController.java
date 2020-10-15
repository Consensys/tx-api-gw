package tech.pegasys.net.txapigw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;

@RestController
@RequestMapping(
    path = "/tx",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class TxController {
  private static final Logger LOG = LoggerFactory.getLogger(TxController.class);

  @PostMapping(path = "/legacy")
  public void submitTransaction(@RequestBody final Transaction transaction) {
    LOG.info("received legacy transaction = {}", transaction.toString());
  }

  @PostMapping(path = "/eip1559")
  public void submitTransaction(@RequestBody final EIP1559Transaction transaction) {
    LOG.info("received eip1559 transaction = {}", transaction.toString());
  }
}
