package com.codingkiddo.taxi.dispatchservice;

import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispatch")
public class DispatchController {
  @PostMapping("/rank")
  public AssignResponse rank(@RequestParam String requestId, @RequestBody List<String> driverIds) {
    Random r = new Random(requestId.hashCode());
    List<Candidate> ranked =
        driverIds.stream()
            .map(id -> new Candidate(id, r.nextDouble()))
            .sorted((a, b) -> Double.compare(b.score(), a.score()))
            .toList();
    return new AssignResponse(requestId, ranked);
  }
}
