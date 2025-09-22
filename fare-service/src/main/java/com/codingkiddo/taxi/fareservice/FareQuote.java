package com.codingkiddo.taxi.fareservice;

import com.codingkiddo.taxi.common.dto.Estimate;

public record FareQuote(double etaSec, Estimate estimate) {}