package com.codingkiddo.taxi.common.dto;

public record EstimateRequest(LatLng pickup, LatLng drop, String category) {}
