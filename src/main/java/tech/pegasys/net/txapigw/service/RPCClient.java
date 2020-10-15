package tech.pegasys.net.txapigw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RPCClient {
  private static final Logger LOG = LoggerFactory.getLogger(RPCClient.class);

  @Value("${rpc-url:http://localhost:8545}")
  private String rpcUrl;

  public void ethSendRawTransaction(final String signedTransaction) {
    LOG.info("rpc url: {}", rpcUrl);
  }
}
