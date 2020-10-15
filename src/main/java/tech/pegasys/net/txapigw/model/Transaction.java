package tech.pegasys.net.txapigw.model;

import java.math.BigInteger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Transaction {
  private BigInteger nonce;
  private String to;
  private BigInteger value;
  private BigInteger gasPrice;
  private BigInteger gasLimit;

  public Transaction(
      final BigInteger nonce,
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
