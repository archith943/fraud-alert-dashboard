package com.interview.fraudalert.event;
import java.math.BigDecimal; import java.time.Instant; import java.util.UUID;
public record TransactionReceivedEvent(UUID transactionId, String accountId, BigDecimal amount, String merchant, String location, Instant receivedAt) {}
