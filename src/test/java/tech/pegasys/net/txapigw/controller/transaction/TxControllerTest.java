package tech.pegasys.net.txapigw.controller.transaction;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tech.pegasys.net.txapigw.api.response.transaction.SubmitTransactionResponse;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;
import tech.pegasys.net.txapigw.service.TransactionService;

@RunWith(SpringRunner.class)
@WebMvcTest(TxController.class)
public class TxControllerTest {
  private static final String PRIVATE_KEY =
      "0xdaa230fc01f963d8694d9206504657bc58063dc7df9449b00c0026628fbdc50d";
  private static final String TX_HASH =
      "0x7f2b8753ea654cebe9f17d95c4975becca197f32c857e35baa7da58729f17f79";
  @Autowired private MockMvc mvc;

  @MockBean private TransactionService transactionService;

  @Before
  public void setUp() throws Exception {}

  @Test
  public void givenValidTx_whenSubmitTransaction_thenReturnCorrectHash() throws Exception {
    given(transactionService.submit(anyString(), any(Transaction.class)))
        .willReturn(new SubmitTransactionResponse(TX_HASH));
    mvc.perform(
            post("/tx/legacy/{privateKey}", PRIVATE_KEY)
                .content(
                    asJsonString(
                        Transaction.builder()
                            .nonce(BigInteger.ONE)
                            .to("0xf17f52151EbEF6C7334FAD080c5704D77216b732")
                            .value(BigInteger.valueOf(1000000))
                            .gasPrice(BigInteger.valueOf(1000))
                            .gasLimit(BigInteger.valueOf(21000))
                            .build()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.transactionHash", is(TX_HASH)));
  }

  @Test
  public void givenInvalidTx_whenSubmitTransaction_thenReturnCorrectHash() throws Exception {
    given(transactionService.submit(anyString(), any(Transaction.class)))
        .willReturn(new SubmitTransactionResponse(TX_HASH));
    mvc.perform(
            post("/tx/legacy/{privateKey}", PRIVATE_KEY)
                .content("{\"unknownField\": 1}")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.gasLimit", is("must not be null")));
  }

  @Test
  public void givenValidEIP1559Tx_whenSubmitTransaction_thenReturnCorrectHash() throws Exception {
    given(transactionService.submit(anyString(), any(EIP1559Transaction.class)))
        .willReturn(new SubmitTransactionResponse(TX_HASH));
    mvc.perform(
            post("/tx/eip1559/{privateKey}", PRIVATE_KEY)
                .content(
                    asJsonString(
                        EIP1559Transaction.eip1559Builder()
                            .nonce(BigInteger.ONE)
                            .to("0xf17f52151EbEF6C7334FAD080c5704D77216b732")
                            .value(BigInteger.valueOf(1000000))
                            .gasLimit(BigInteger.valueOf(21000))
                            .minerBribe(BigInteger.valueOf(1000000))
                            .feecap(BigInteger.valueOf(1000000000))
                            .build()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.transactionHash", is(TX_HASH)));
  }

  @Test
  public void givenInvalidEIP1559Tx_whenSubmitTransaction_thenReturnCorrectHash() throws Exception {
    given(transactionService.submit(anyString(), any(Transaction.class)))
        .willReturn(new SubmitTransactionResponse(TX_HASH));
    mvc.perform(
            post("/tx/eip1559/{privateKey}", PRIVATE_KEY)
                .content("{\"unknownField\": 1}")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.minerBribe", is("must not be null")));
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
