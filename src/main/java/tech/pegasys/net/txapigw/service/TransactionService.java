package tech.pegasys.net.txapigw.service;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
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
      prepare(privateKey, transaction);
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
      prepare(privateKey, transaction);
      final byte[] signedTransaction = signer.signEIP1559(privateKey, transaction);
      final EthSendTransaction response =
          web3.ethSendRawTransaction(Numeric.toHexString(signedTransaction)).send();
      log.info("transaction sent: {}", response.getTransactionHash());
      return new SubmitTransactionResponse(response.getTransactionHash());
    } catch (final IOException e) {
      log.error("cannot submit transaction", e);
      throw new TxApiGwException(ErrorCode.ETHEREUM_CLIENT_ERROR, e);
    }
  }

  public void prepare(final String privateKey, final Transaction transaction) throws IOException {
    if (transaction.getNonce() == null || transaction.getNonce().signum() == -1) {
      setAutoNonce(privateKey, transaction);
    }
  }

  public void setAutoNonce(final String privateKey, final Transaction transaction)
      throws IOException {
    final String address = Credentials.create(privateKey).getAddress();
    log.info("retrieving nonce for address {}", address);
    final EthGetTransactionCount getTransactionCountResponse =
        web3.ethGetTransactionCount(
                address, DefaultBlockParameter.valueOf(DefaultBlockParameterName.LATEST.getValue()))
            .send();
    transaction.setNonce(getTransactionCountResponse.getTransactionCount());
    log.info("transaction nonce auto set to {} for address {}", transaction.getNonce(), address);
  }
}
