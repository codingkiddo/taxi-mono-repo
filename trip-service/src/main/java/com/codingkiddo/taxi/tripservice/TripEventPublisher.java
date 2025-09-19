package com.codingkiddo.taxi.tripservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class TripEventPublisher {
  private final KafkaTemplate<String, Object> kt;

  @Value("${app.kafka.topic.tripLifecycle:trip.lifecycle.v1}")
  String topic;

  TripEventPublisher(KafkaTemplate<String, Object> kt) {
    this.kt = kt;
  }

  @PostMapping("/{id}/start")
  public Trip startAndEvent(
      @PathVariable String id, @RequestParam String riderId, @RequestParam String driverId) {
    Trip t = new Trip(id, riderId, driverId, "ENROUTE");
    kt.send(topic, id, new TripLifecycleEvent(id, "STARTED", System.currentTimeMillis()));
    return t;
  }

  @PostMapping("/{id}/finish")
  public Trip finishAndEvent(@PathVariable String id) {
    kt.send(topic, id, new TripLifecycleEvent(id, "COMPLETED", System.currentTimeMillis()));
    return new Trip(id, "?", "?", "COMPLETED");
  }
}
