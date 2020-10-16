package tech.pegasys.net.txapigw.api.response;

import lombok.Data;

@Data
public class GetBasefeeResponse {
  private String baseFee;

  public GetBasefeeResponse(String baseFee) {
    this.baseFee = baseFee;
  }
}
