package tech.pegasys.net.txapigw.service;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Numeric;
import tech.pegasys.net.txapigw.api.response.transaction.SubmitTransactionResponse;
import tech.pegasys.net.txapigw.error.ErrorCode;
import tech.pegasys.net.txapigw.error.TxApiGwException;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;

@Service
@Slf4j
public class TransactionService {

  @Autowired private Web3j web3;
  @Autowired private TransactionSigner signer;

  public SubmitTransactionResponse submit(final String privateKey, final Transaction transaction) {
    try {
      log.info("submit transaction: {}", transaction.toString());
      final byte[] signedTransaction = signer.sign(privateKey, transaction);
      final EthSendTransaction response =
          web3.ethSendRawTransaction(Numeric.toHexString(signedTransaction)).send();
      log.info("transaction sent: {}", response.getTransactionHash());
      return new SubmitTransactionResponse(response.getTransactionHash());
    } catch (final IOException e) {
      log.error("cannot submit transaction", e);
      throw new TxApiGwException(ErrorCode.ETHEREUM_CLIENT_ERROR, e);
    }
  }

  public SubmitTransactionResponse submit(
      final String privateKey, final EIP1559Transaction transaction) {
    try {
      log.info("submit transaction: {}", transaction.toString());
      final byte[] signedTransaction = signer.sign(privateKey, transaction);
      final EthSendTransaction response =
          web3.ethSendRawTransaction(Numeric.toHexString(signedTransaction)).send();
      log.info("transaction sent: {}", response.getTransactionHash());
      return new SubmitTransactionResponse(response.getTransactionHash());
    } catch (final IOException e) {
      log.error("cannot submit transaction", e);
      throw new TxApiGwException(ErrorCode.ETHEREUM_CLIENT_ERROR, e);
    }
  }
}
