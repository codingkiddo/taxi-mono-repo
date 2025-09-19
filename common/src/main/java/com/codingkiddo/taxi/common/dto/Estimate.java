package com.codingkiddo.taxi.common.dto;

public record Estimate(
    double distanceKm, double durationMin, double baseFare, double surge, double total) {}
