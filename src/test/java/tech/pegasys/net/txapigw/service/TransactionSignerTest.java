package tech.pegasys.net.txapigw.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.Test;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;

public class TransactionSignerTest {
  private static final String PRIVATE_KEY =
      "0xdaa230fc01f963d8694d9206504657bc58063dc7df9449b00c0026628fbdc50d";
  private final TransactionSigner signer = new TransactionSigner();

  @Test
  public void givenFrontierTransaction_whenSign_thenReturnCorrectByteArray() {
    assertThat(
            signer.sign(
                PRIVATE_KEY,
                Transaction.builder()
                    .nonce(BigInteger.ONE)
                    .to("0xf17f52151EbEF6C7334FAD080c5704D77216b732")
                    .value(BigInteger.valueOf(1000000))
                    .gasPrice(BigInteger.valueOf(1000))
                    .gasLimit(BigInteger.valueOf(21000))
                    .build()))
        .asHexString()
        .isEqualToIgnoringCase(
            "f864018203e882520894f17f52151ebef6c7334fad080c5704d77216b732830f4240801ba02c0b8888fc9c8183e612515fb97b7fa22557290b5057f6be9c92f5ac8122fc90a066ea2b9b53c1924cae563109bc2fa28c0f32d30ce44fc2debfcaab7dd33dfb88");
  }

  @Test
  public void givenEIP1559Transaction_whenSign_thenReturnCorrectByteArray() {
    assertThat(
            signer.sign(
                PRIVATE_KEY,
                EIP1559Transaction.eip1559Builder()
                    .nonce(BigInteger.ONE)
                    .to("0xf17f52151EbEF6C7334FAD080c5704D77216b732")
                    .value(BigInteger.valueOf(1000000))
                    .gasLimit(BigInteger.valueOf(21000))
                    .minerBribe(BigInteger.valueOf(1000000))
                    .feecap(BigInteger.valueOf(1000000000))
                    .build()))
        .asHexString()
        .isEqualToIgnoringCase(
            "F86B018082520894F17F52151EBEF6C7334FAD080C5704D77216B732830F424080830F4240843B9ACA001BA0B40369E208EA015944DB3BC8052957C7F51F14409646179444D60C2331588598A0195AD74E3CF85369FD996E234D1161D07D1E6114686C19C52A79A816112CF3B8");
  }
}
