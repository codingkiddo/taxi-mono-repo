package com.codingkiddo.taxi.paymentservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TripLifecycleListener {
  private final KafkaTemplate<String, Object> kt;

  public TripLifecycleListener(KafkaTemplate<String, Object> kt) {
    this.kt = kt;
  }

  @KafkaListener(topics = "${app.kafka.topic.tripLifecycle:trip.lifecycle.v1}")
  public void onTrip(Object ev) {
    System.out.println("[payment] observed trip lifecycle: " + ev);
    kt.send("payment.events.v1", "trip", ev); // echo as payment event (demo)
  }
}
