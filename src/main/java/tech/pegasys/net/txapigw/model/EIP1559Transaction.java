package tech.pegasys.net.txapigw.model;

import java.math.BigInteger;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class EIP1559Transaction extends Transaction {
  private final BigInteger minerBribe;
  private final BigInteger feecap;

  public EIP1559Transaction(
      final long nonce,
      final String to,
      final BigInteger value,
      final BigInteger gasLimit,
      final BigInteger minerBribe,
      final BigInteger feecap) {
    super(nonce, to, value, null, gasLimit);
    this.minerBribe = minerBribe;
    this.feecap = feecap;
  }
}
