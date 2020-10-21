package tech.pegasys.net.txapigw.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class DefaultController {

  @RequestMapping(path = "/", method = RequestMethod.GET)
  public void redirect(HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui.html");
  }
}
