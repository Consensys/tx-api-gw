package tech.pegasys.net.txapigw.model;

import java.math.BigInteger;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class EIP1559Transaction extends Transaction {
  @NotNull private BigInteger minerBribe;
  @NotNull private BigInteger feecap;

  public EIP1559Transaction(
      final BigInteger nonce,
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
