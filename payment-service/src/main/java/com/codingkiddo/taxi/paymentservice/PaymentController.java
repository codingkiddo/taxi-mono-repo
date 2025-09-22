package com.codingkiddo.taxi.paymentservice;

import java.util.Map;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment")
public class PaymentController {
  @PostMapping("/{tripId}/init")
  public InitResp init(
      @PathVariable String tripId, @RequestParam(defaultValue = "UPI") String method) {
    return new InitResp(tripId, "AUTHORIZED", "prov-" + tripId);
  }

  @PostMapping("/webhook")
  public Map<String, String> webhook(@RequestBody Map<String, String> body) {
    return body;
  }
}
