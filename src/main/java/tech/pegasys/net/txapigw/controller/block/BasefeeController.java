package tech.pegasys.net.txapigw.controller.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tech.pegasys.net.txapigw.api.BasefeeAPI;
import tech.pegasys.net.txapigw.api.response.block.GetBasefeeResponse;
import tech.pegasys.net.txapigw.service.rpc.RPCClient;

@RestController
public class BasefeeController implements BasefeeAPI {

  @Autowired private RPCClient rpcClient;

  public @ResponseBody GetBasefeeResponse getBaseFee() {
    return new GetBasefeeResponse(rpcClient.getBasefee());
  }

  public @ResponseBody GetBasefeeResponse getBaseFee(final String block) {
    return new GetBasefeeResponse(rpcClient.getBasefee(block));
  }
}
