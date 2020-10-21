package tech.pegasys.net.txapigw.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(DefaultController.class)
public class DefaultControllerTest {
  @Autowired private MockMvc mvc;

  @Test
  public void givenRootUrl_whenHttpGet_thenRedirectToSwagger() throws Exception {
    mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/swagger-ui.html"));
  }
}
