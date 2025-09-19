package com.codingkiddo.taxi.fareservice;

import com.codingkiddo.taxi.common.dto.*;
import org.springframework.web.bind.annotation.*;

record FareQuote(double etaSec, Estimate estimate) {}

@RestController
@RequestMapping("/fare")
class FareController {
  @PostMapping("/estimate")
  public FareQuote estimate(@RequestBody EstimateRequest req) {
    double dist = haversine(req.pickup(), req.drop());
    double minutes = Math.max(10, dist * 3.0);
    double base =
        switch (req.category() == null ? "MINI" : req.category().toUpperCase()) {
          case "SEDAN" -> 60.0;
          case "SUV" -> 80.0;
          default -> 50.0;
        };
    double perKm =
        switch (req.category() == null ? "MINI" : req.category().toUpperCase()) {
          case "SEDAN" -> 16.0;
          case "SUV" -> 20.0;
          default -> 12.0;
        };
    double perMin = 1.5;
    double surge = 1.0; // placeholder
    double total = Math.max(base + dist * perKm + minutes * perMin, base) * surge;
    return new FareQuote(
        (int) Math.round(minutes * 60),
        new Estimate(dist, minutes, base, surge, Math.round(total * 100) / 100.0));
  }

  private static double haversine(LatLng a, LatLng b) {
    double R = 6371.0;
    double dLat = Math.toRadians(b.lat() - a.lat());
    double dLon = Math.toRadians(b.lng() - a.lng());
    double s =
        Math.sin(dLat / 2) * Math.sin(dLat / 2)
            + Math.cos(Math.toRadians(a.lat()))
                * Math.cos(Math.toRadians(b.lat()))
                * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
    return 2 * R * Math.asin(Math.sqrt(s));
  }
}
