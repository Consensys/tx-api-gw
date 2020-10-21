package tech.pegasys.net.txapigw.controller.block;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tech.pegasys.net.txapigw.service.rpc.RPCClient;

@RunWith(SpringRunner.class)
@WebMvcTest(BasefeeController.class)
public class BasefeeControllerTest {
  @Autowired private MockMvc mvc;

  @MockBean private RPCClient rpcClient;

  @Before
  public void setUp() throws Exception {}

  @Test
  public void givenBasefee_whenGetLatestBasefee_thenReturnJson() throws Exception {
    given(rpcClient.getBasefee()).willReturn("0x1234");

    mvc.perform(get("/basefee").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.baseFee", is("0x1234")));
  }

  @Test
  public void givenBasefee_whenGetBasefeeAtGivenBlock_thenReturnJson() throws Exception {
    given(rpcClient.getBasefee("0x10")).willReturn("0x5678");

    mvc.perform(get("/basefee/{block}", "0x10").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.baseFee", is("0x5678")));
  }
}
