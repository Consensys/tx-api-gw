package tech.pegasys.net.txapigw.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class XDefaultController {

  /*@Override
  public String getErrorPath() {
    return "/error";
  }*/

  /*@RequestMapping("/error")
  public void handleErrorWithRedirect(HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui.html");
  }*/

  @RequestMapping(value = "/")
  public void redirect(HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui.html");
  }
}
