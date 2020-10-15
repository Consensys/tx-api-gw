package tech.pegasys.net.txapigw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;

@Service
public class TransactionService {
  private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

  @Autowired private final RPCClient client;

  public TransactionService(final RPCClient client) {
    this.client = client;
  }

  public void submit(final Transaction transaction) {
    LOG.debug("submit transaction: {}", transaction.toString());
    client.ethSendRawTransaction("");
  }

  public void submit(final EIP1559Transaction transaction) {
    LOG.debug("submit transaction: {}", transaction.toString());
    client.ethSendRawTransaction("");
  }
}
