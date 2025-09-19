package com.codingkiddo.taxi.dispatchservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LocationListener {
  // Using generic Object (Avro record deserialized as Map/GenericRecord); demo logs to console
  @KafkaListener(topics = "${app.kafka.topic.driverLocation:driver.location.v1}")
  public void onLocation(Object value) {
    System.out.println("[dispatch] driver.location received: " + value);
  }
}
