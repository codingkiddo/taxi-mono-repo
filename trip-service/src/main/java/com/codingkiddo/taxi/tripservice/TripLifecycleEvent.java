package com.codingkiddo.taxi.tripservice;

public record TripLifecycleEvent(String tripId, String event, long ts) {}
