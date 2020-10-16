package tech.pegasys.net.txapigw.model;

import java.math.BigInteger;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Transaction {
  @NotNull private BigInteger nonce;

  @NotBlank
  @Size(min = 42, max = 42)
  private String to;

  @NotNull private BigInteger value;
  private BigInteger gasPrice;
  @NotNull private BigInteger gasLimit;

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
