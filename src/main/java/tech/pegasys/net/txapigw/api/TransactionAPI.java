package tech.pegasys.net.txapigw.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.pegasys.net.txapigw.api.response.transaction.SubmitTransactionResponse;
import tech.pegasys.net.txapigw.model.EIP1559Transaction;
import tech.pegasys.net.txapigw.model.Transaction;

@RequestMapping(
    path = "/tx",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transaction API", description = "Transaction Web API documentation")
public interface TransactionAPI {

  @Operation(summary = "Submit a legacy Ethereum transaction")
  @PostMapping(path = "/legacy/{privateKey}")
  SubmitTransactionResponse submitTransaction(
      @Parameter(description = "private key to use to sign the transaction") @PathVariable
          String privateKey,
      @RequestBody final Transaction transaction);

  @Operation(summary = "Submit an EIP-1559 Ethereum transaction")
  @PostMapping(path = "/eip1559/{privateKey}")
  SubmitTransactionResponse submitTransaction(
      @Parameter(description = "private key to use to sign the transaction") @PathVariable
          String privateKey,
      @RequestBody final EIP1559Transaction transaction);
}
