package tech.pegasys.net.txapigw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Numeric;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;

@Service
public class TransactionService {
  private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

  @Autowired private Web3j web3;
  @Autowired private TransactionSigner signer;

  public void submit(final String privateKey, final Transaction transaction) {
    process(privateKey, transaction);
  }

  public void submit(final String privateKey, final EIP1559Transaction transaction) {
    process(privateKey, transaction);
  }

  private <T extends Transaction> void process(final String privateKey, final T transaction) {
    try {
      LOG.info("submit transaction: {}", transaction.toString());
      final byte[] signedTransaction = signer.sign(privateKey, transaction);
      final EthSendTransaction response =
          web3.ethSendRawTransaction(Numeric.toHexString(signedTransaction)).send();
      LOG.info("transaction sent: {}", response.getTransactionHash());

    } catch (final Exception e) {
      LOG.error("cannot submit transaction", e);
    }
  }
}
