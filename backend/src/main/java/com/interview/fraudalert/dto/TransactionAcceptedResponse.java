package com.interview.fraudalert.dto;
import java.time.Instant;
import java.util.UUID;
public record TransactionAcceptedResponse(UUID transactionId, String status, Instant acceptedAt) {}
