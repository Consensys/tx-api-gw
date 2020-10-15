package tech.pegasys.net.txapigw.model;

import java.math.BigInteger;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Transaction {
  private final long nonce;
  private final String to;
  private final BigInteger value;
  private final BigInteger gasPrice;
  private final BigInteger gasLimit;

  public Transaction(
      final long nonce,
      final String to,
      final BigInteger value,
      final BigInteger gasPrice,
      final BigInteger gasLimit) {
    this.nonce = nonce;
    this.to = to;
    this.value = value;
    this.gasPrice = gasPrice;
    this.gasLimit = gasLimit;
  }
}
