package com.codingkiddo.taxi.driverservice;

public record DriverLocationEvent(
    String driverId, double lat, double lng, Double speedKph, long ts) {}
