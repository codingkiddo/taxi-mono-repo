package com.codingkiddo.taxi.tripservice;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class TripController {
  private final Map<String, Trip> trips = new HashMap<>();

  @PostMapping("/{id}/start")
  public Trip start(
      @PathVariable String id, @RequestParam String riderId, @RequestParam String driverId) {
    Trip t = new Trip(id, riderId, driverId, "ENROUTE");
    trips.put(id, t);
    return t;
  }

  @PostMapping("/{id}/finish")
  public Trip finish(@PathVariable String id) {
    Trip t = trips.getOrDefault(id, new Trip(id, "?", "?", "UNKNOWN"));
    Trip done = new Trip(t.id(), t.riderId(), t.driverId(), "COMPLETED");
    trips.put(id, done);
    return done;
  }

  @GetMapping("/{id}")
  public Trip get(@PathVariable String id) {
    return trips.get(id);
  }
}
