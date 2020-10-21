package tech.pegasys.net.txapigw.service.rpc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.util.function.Function;

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
    final RPCClient client = new RPCClient(baseUrl.url().toString());
    assertThatThrownBy(() -> client.call("{}"))
        .isInstanceOf(IOException.class)
        .hasMessageContaining("error calling rpc method");
    server.shutdown();
  }

  @Test
  public void givenSuccessResponse_whenGetBasefee_thenReturnsCorrectValue() throws IOException {
    final String baseFee =
        call(
            new MockResponse().setBody("{\"result\": {\"baseFee\": \"0x505a43c\"}}"),
            RPCClient::getBasefee);
    assertThat(baseFee).isEqualToIgnoringCase("0x505a43c");
  }

  @Test
  public void givenSuccessResponse_whenGetBasefeeLatest_thenReturnsCorrectValue()
      throws IOException {
    final String baseFee =
        call(
            new MockResponse().setBody("{\"result\": {\"baseFee\": \"0x505a43c\"}}"),
            rpcClient -> rpcClient.getBasefee("latest"));
    assertThat(baseFee).isEqualToIgnoringCase("0x505a43c");
  }

  @Test
  public void givenSuccessResponse_whenGetBasefeeAnyBlock_thenReturnsCorrectValue()
      throws IOException {
    final String baseFee =
        call(
            new MockResponse().setBody("{\"result\": {\"baseFee\": \"0x505a43c\"}}"),
            rpcClient -> rpcClient.getBasefee("0x12"));
    assertThat(baseFee).isEqualToIgnoringCase("0x505a43c");
  }

  @Test
  public void givenSuccessResponse_whenGetBasefeeAsLong_thenReturnsCorrectValue()
      throws IOException {
    final long baseFee =
        call(
            new MockResponse().setBody("{\"result\": {\"baseFee\": \"0x505a43c\"}}"),
            RPCClient::getBasefeeAsLong);
    assertThat(baseFee).isEqualTo(84255804);
  }

  @Test
  public void givenSuccessResponse_whenGetBasefeeAsLongLatest_thenReturnsCorrectValue()
      throws IOException {
    final long baseFee =
        call(
            new MockResponse().setBody("{\"result\": {\"baseFee\": \"0x505a43c\"}}"),
            rpcClient -> rpcClient.getBasefeeAsLong("latest"));
    assertThat(baseFee).isEqualTo(84255804);
  }

  @Test
  public void givenSuccessResponse_whenGetBasefeeAsLongAnyBlock_thenReturnsCorrectValue()
      throws IOException {
    final long baseFee =
        call(
            new MockResponse().setBody("{\"result\": {\"baseFee\": \"0x505a43c\"}}"),
            rpcClient -> rpcClient.getBasefeeAsLong("0x12"));
    assertThat(baseFee).isEqualTo(84255804);
  }

  private <T> T call(final MockResponse response, final Function<RPCClient, T> function)
      throws IOException {
    final MockWebServer server = new MockWebServer();
    server.enqueue(response);
    server.start();
    final HttpUrl baseUrl = server.url("/");
    final RPCClient client = new RPCClient(baseUrl.url().toString());
    final T returnValue = function.apply(client);
    server.shutdown();
    return returnValue;
  }
}
