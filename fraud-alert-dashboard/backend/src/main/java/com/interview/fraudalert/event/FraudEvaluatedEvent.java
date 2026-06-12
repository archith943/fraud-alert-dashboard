package com.interview.fraudalert.event;
import com.interview.fraudalert.model.RiskLevel;
import java.math.BigDecimal; import java.time.Instant; import java.util.UUID;
public record FraudEvaluatedEvent(UUID transactionId, String accountId, BigDecimal amount, String merchant, String location, RiskLevel riskLevel, String reason, Instant evaluatedAt) {}
