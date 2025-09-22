package com.codingkiddo.taxi.dispatchservice;

import java.util.List;

public record AssignResponse(String requestId, List<Candidate> ranked) {}
