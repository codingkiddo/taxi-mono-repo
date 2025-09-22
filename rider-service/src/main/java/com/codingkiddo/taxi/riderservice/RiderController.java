package com.codingkiddo.taxi.riderservice;

import com.codingkiddo.taxi.common.dto.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/rider")
public class RiderController {
  private final RestClient rest = RestClient.builder().baseUrl("http://fare-service:8085").build();

  @PostMapping("/estimate")
  public Object estimate(@RequestBody EstimateRequest req) {
    return rest.post().uri("/fare/estimate").body(req).retrieve().body(Object.class);
  }

  @GetMapping("/healthz")
  public String health() {
    return "ok";
  }
}
