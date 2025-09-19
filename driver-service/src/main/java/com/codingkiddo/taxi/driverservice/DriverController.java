package com.codingkiddo.taxi.driverservice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {
  private final Map<String, String> states = new ConcurrentHashMap<>();

  @PostMapping("/status")
  public DriverStatus set(@RequestParam String driverId, @RequestParam String status) {
    states.put(driverId, status);
    return new DriverStatus(driverId, status);
  }

  @GetMapping("/status")
  public Map<String, String> all() {
    return states;
  }
}
