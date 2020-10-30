package tech.pegasys.net.txapigw.service;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;

@Service
public class TransactionSigner {

  public byte[] sign(final String privateKey, final Transaction transaction) {
    final RawTransaction etherTransaction =
        RawTransaction.createEtherTransaction(
            transaction.getNonce(),
            transaction.getGasPrice(),
            transaction.getGasLimit(),
            transaction.getTo(),
            transaction.getValue());
    return TransactionEncoder.signMessage(etherTransaction, Credentials.create(privateKey));
  }

  public byte[] signEIP1559(final String privateKey, final EIP1559Transaction transaction) {
    final RawTransaction etherTransaction =
        RawTransaction.createEtherTransaction(
            transaction.getNonce(),
            transaction.getGasLimit(),
            transaction.getTo(),
            transaction.getValue(),
            transaction.getMinerBribe(),
            transaction.getFeecap());
    return TransactionEncoder.signMessage(etherTransaction, Credentials.create(privateKey));
  }
}
