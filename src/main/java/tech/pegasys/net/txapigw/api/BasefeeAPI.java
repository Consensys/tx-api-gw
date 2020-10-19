package tech.pegasys.net.txapigw.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.pegasys.net.txapigw.api.response.block.GetBasefeeResponse;

@RequestMapping(path = "/basefee", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Basefee API", description = "Basefee Web API documentation")
public interface BasefeeAPI {

  @Operation(summary = "Retrieve the latest base fee")
  @GetMapping
  @ResponseBody
  GetBasefeeResponse getBaseFee();

  @Operation(summary = "Retrieve the base fee at given block")
  @GetMapping(path = "/{block}")
  @ResponseBody
  GetBasefeeResponse getBaseFee(
      @Parameter(description = "block number or one of earliest, latest, pending") @PathVariable
          String block);
}
