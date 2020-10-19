package tech.pegasys.net.txapigw.service.rpc;

import java.io.IOException;
import java.util.Objects;

import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public class RPCClient {
  private static final MediaType JSON = MediaType.parse("application/json");

  private final String rpcEndpoint;
  private final OkHttpClient client;

  public RPCClient(final String rpcEndpoint) {
    this.rpcEndpoint = rpcEndpoint;
    this.client = new OkHttpClient.Builder().build();
  }

  public String call(final String jsonRequest) throws IOException {
    final Response response =
        client
            .newCall(
                new Request.Builder()
                    .url(rpcEndpoint)
                    .post(RequestBody.create(jsonRequest, JSON))
                    .build())
            .execute();
    if (response.isSuccessful()) {
      return Objects.requireNonNull(response.body()).string();
    } else {
      throw new IOException("error calling rpc method");
    }
  }

  public Long getBasefeeAsLong() {
    return getBasefeeAsLong("latest");
  }

  public Long getBasefeeAsLong(final String block) {
    return Long.parseLong(getBasefee(block).substring(2), 16);
  }

  public String getBasefee() {
    return getBasefee("latest");
  }

  public String getBasefee(final String block) {
    try {
      return JsonPath.parse(call(RpcMethodTemplate.ETH_GET_BLOCK_BY_NUMBER.format(block)))
          .read("$.result.baseFee");
    } catch (final Exception e) {
      log.error("error retrieving base fee", e);
      return null;
    }
  }
}
