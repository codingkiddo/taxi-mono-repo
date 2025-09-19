package com.codingkiddo.taxi.driverservice;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
// --- Kafka producer for driver.location.v1 (Avro) ---
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverLocationPublisher {
  private final KafkaTemplate<String, Object> kt;

  @Value("${app.kafka.topic.driverLocation:driver.location.v1}")
  String topic;

  DriverLocationPublisher(KafkaTemplate<String, Object> kt) {
    this.kt = kt;
  }

  @PostMapping("/ping")
  public Map<String, Object> ping(
      @RequestParam String driverId,
      @RequestParam double lat,
      @RequestParam double lng,
      @RequestParam(required = false) Double speedKph) {
    var ev = new DriverLocationEvent(driverId, lat, lng, speedKph, System.currentTimeMillis());
    kt.send(topic, driverId, ev);
    return Map.of("sent", true, "topic", topic, "driverId", driverId);
  }
}
