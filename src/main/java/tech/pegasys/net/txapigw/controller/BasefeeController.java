package tech.pegasys.net.txapigw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tech.pegasys.net.txapigw.api.response.GetBasefeeResponse;
import tech.pegasys.net.txapigw.service.rpc.RPCClient;

@RestController
@RequestMapping(path = "/basefee", produces = MediaType.APPLICATION_JSON_VALUE)
public class BasefeeController {

  @Autowired private RPCClient rpcClient;

  @Operation(summary = "Retrieve the latest base fee")
  @GetMapping
  public @ResponseBody GetBasefeeResponse getBaseFee() {
    return new GetBasefeeResponse(rpcClient.getBasefee("latest"));
  }

  @Operation(summary = "Retrieve the base fee at given block")
  @GetMapping(path = "/{block}")
  public @ResponseBody GetBasefeeResponse getBaseFee(
      @Parameter(description = "block number or one of earliest, latest, pending") @PathVariable
          String block) {
    return new GetBasefeeResponse(rpcClient.getBasefee(block != null ? block : "latest"));
  }
}
