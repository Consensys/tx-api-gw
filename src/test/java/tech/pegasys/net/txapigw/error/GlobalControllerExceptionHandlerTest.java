package tech.pegasys.net.txapigw.error;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.pegasys.net.txapigw.controller.block.BasefeeController;
import tech.pegasys.net.txapigw.service.rpc.RPCClient;

@RunWith(SpringRunner.class)
@WebMvcTest(BasefeeController.class)
public class GlobalControllerExceptionHandlerTest {
  private MockMvc mockMvc;
  @Autowired private BasefeeController controller;
  @MockBean private RPCClient rpcClient;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc =
        MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(new GlobalControllerExceptionHandler())
            .build();
  }

  @Test
  public void testGlobalExceptionHandlerError() throws Exception {

    Mockito.when(controller.getBaseFee())
        .thenThrow(new TxApiGwException(ErrorCode.DEFAULT, new RuntimeException("error message")));
    mockMvc
        .perform(get("/basefee"))
        .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
        .andExpect(jsonPath("$.errorCode", is(-1)))
        .andExpect(jsonPath("$.errorLabel", is("DEFAULT")))
        .andExpect(jsonPath("$.errorMessage", is("error message")));
  }

  @Test
  public void testGlobalExceptionHandlerErrorAnotherMethod() throws Exception {

    Mockito.when(controller.getBaseFee("0x10"))
        .thenThrow(new TxApiGwException(ErrorCode.DEFAULT, new RuntimeException("error message")));
    mockMvc
        .perform(get("/basefee/{block}", "0x10"))
        .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
        .andExpect(jsonPath("$.errorCode", is(-1)))
        .andExpect(jsonPath("$.errorLabel", is("DEFAULT")))
        .andExpect(jsonPath("$.errorMessage", is("error message")));
  }
}
