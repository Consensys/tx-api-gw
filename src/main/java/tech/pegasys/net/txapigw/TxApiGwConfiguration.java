package tech.pegasys.net.txapigw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class TxApiGwConfiguration {
  @Value("${rpc-url:http://localhost:8545}")
  private String rpcUrl;

  @Bean
  public Web3j web3j() {
    return Web3j.build(new HttpService(rpcUrl));
  }
}
