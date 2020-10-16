package tech.pegasys.net.txapigw.model;

import java.math.BigInteger;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

@ToString(callSuper = true)
public class EIP1559Transaction extends Transaction {
  @Schema(example = "1000", required = true, description = "the miner bribe, in WEI")
  @NotNull
  private BigInteger minerBribe;

  @Schema(
      example = "1000000000",
      description = "the maximum of ETH the user is willing to pay for the transaction, in WEI")
  @NotNull
  private BigInteger feecap;

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

  public BigInteger getMinerBribe() {
    return minerBribe;
  }

  public void setMinerBribe(BigInteger minerBribe) {
    this.minerBribe = minerBribe;
  }

  public BigInteger getFeecap() {
    return feecap;
  }

  public void setFeecap(BigInteger feecap) {
    this.feecap = feecap;
  }
}
