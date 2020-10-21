package tech.pegasys.net.txapigw.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Numeric;
import tech.pegasys.net.txapigw.api.response.transaction.SubmitTransactionResponse;
import tech.pegasys.net.txapigw.error.TxApiGwException;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {
  private static final String PRIVATE_KEY =
      "0xdaa230fc01f963d8694d9206504657bc58063dc7df9449b00c0026628fbdc50d";
  private static final String SIGNED_TX =
      "0xf864018203e882520894f17f52151ebef6c7334fad080c5704d77216b732830f4240801ba02c0b8888fc9c8183e612515fb97b7fa22557290b5057f6be9c92f5ac8122fc90a066ea2b9b53c1924cae563109bc2fa28c0f32d30ce44fc2debfcaab7dd33dfb88";
  private static final String TX_HASH =
      "0x7f2b8753ea654cebe9f17d95c4975becca197f32c857e35baa7da58729f17f79";

  @TestConfiguration
  static class TransactionServiceTestContextConfiguration {

    @Bean
    public TransactionService transactionService() {
      return new TransactionService();
    }
  }

  @Autowired private TransactionService service;
  @MockBean private Web3j web3;
  @MockBean private TransactionSigner signer;

  @Test
  public void givenValidTx_whenSubmit_thenReturnTxHash() throws IOException {
    given(signer.sign(eq(PRIVATE_KEY), any(Transaction.class)))
        .willReturn(Numeric.hexStringToByteArray(SIGNED_TX));
    final EthSendTransaction responseMock = mock(EthSendTransaction.class);
    when(responseMock.getTransactionHash()).thenReturn(TX_HASH);
    final Request web3jRequestMock = mock(Request.class);
    when(web3jRequestMock.send()).thenReturn(responseMock);
    given(web3.ethSendRawTransaction(anyString())).willReturn(web3jRequestMock);
    assertThat(
            service.submit(
                PRIVATE_KEY,
                Transaction.builder()
                    .nonce(BigInteger.ONE)
                    .to("0xf17f52151EbEF6C7334FAD080c5704D77216b732")
                    .value(BigInteger.valueOf(1000000))
                    .gasPrice(BigInteger.valueOf(1000))
                    .gasLimit(BigInteger.valueOf(21000))
                    .build()))
        .isEqualToComparingFieldByField(new SubmitTransactionResponse(TX_HASH));
  }

  @Test
  public void givenIOException_whenSubmit_thenThrowTxApiGwException() {
    given(signer.sign(eq(PRIVATE_KEY), any(Transaction.class)))
        .willReturn(Numeric.hexStringToByteArray(SIGNED_TX));
    given(web3.ethSendRawTransaction(anyString()))
        .willAnswer(
            invocation -> {
              throw new IOException("cannot connect to Ethereum client");
            });
    assertThatThrownBy(() -> service.submit(PRIVATE_KEY, Transaction.builder().build()))
        .isInstanceOf(TxApiGwException.class)
        .hasMessage("cannot connect to Ethereum client");
  }

  @Test
  public void givenValidEIP1559Tx_whenSubmit_thenReturnTxHash() throws IOException {
    given(signer.sign(eq(PRIVATE_KEY), any(EIP1559Transaction.class)))
        .willReturn(Numeric.hexStringToByteArray(SIGNED_TX));
    final EthSendTransaction responseMock = mock(EthSendTransaction.class);
    when(responseMock.getTransactionHash()).thenReturn(TX_HASH);
    final Request web3jRequestMock = mock(Request.class);
    when(web3jRequestMock.send()).thenReturn(responseMock);
    given(web3.ethSendRawTransaction(anyString())).willReturn(web3jRequestMock);
    assertThat(
            service.submit(
                PRIVATE_KEY,
                EIP1559Transaction.eip1559Builder()
                    .nonce(BigInteger.ONE)
                    .to("0xf17f52151EbEF6C7334FAD080c5704D77216b732")
                    .value(BigInteger.valueOf(1000000))
                    .gasLimit(BigInteger.valueOf(21000))
                    .minerBribe(BigInteger.valueOf(1000000))
                    .feecap(BigInteger.valueOf(1000000000))
                    .build()))
        .isEqualToComparingFieldByField(new SubmitTransactionResponse(TX_HASH));
  }

  @Test
  public void givenIOException_whenSubmitEIP1559Tx_thenThrowTxApiGwException() {
    given(signer.sign(eq(PRIVATE_KEY), any(EIP1559Transaction.class)))
        .willReturn(Numeric.hexStringToByteArray(SIGNED_TX));
    given(web3.ethSendRawTransaction(anyString()))
        .willAnswer(
            invocation -> {
              throw new IOException("cannot connect to Ethereum client");
            });
    assertThatThrownBy(
            () -> service.submit(PRIVATE_KEY, EIP1559Transaction.eip1559Builder().build()))
        .isInstanceOf(TxApiGwException.class)
        .hasMessage("cannot connect to Ethereum client");
  }
}
