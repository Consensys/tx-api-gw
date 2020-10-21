package tech.pegasys.net.txapigw.service.rpc;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;

public class RPCClientTest {

  @Test
  public void givenErrorResponse_whenCall_thenExceptionIsThrown() throws IOException {
    final MockWebServer server = new MockWebServer();
    server.enqueue(new MockResponse().setResponseCode(400));
    server.start();
    final HttpUrl baseUrl = server.url("/");
    System.out.println(baseUrl.url().toString());
    final RPCClient client = new RPCClient(baseUrl.url().toString());
    assertThatThrownBy(() -> client.call("{}"))
        .isInstanceOf(IOException.class)
        .hasMessageContaining("error calling rpc method");
    server.shutdown();
  }
}
