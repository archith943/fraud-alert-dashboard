# Flow Documentation

## Positive Flow

1. User submits transaction from Angular reactive form.
2. Angular calls `POST /api/transactions`.
3. Backend validates request.
4. Backend generates UUID transaction ID.
5. Backend publishes `TransactionReceivedEvent` to `transaction.received`.
6. API returns `202 Accepted`.
7. Fraud consumer receives event and evaluates rules.
8. Fraud consumer publishes `FraudEvaluatedEvent` to `fraud.evaluated`.
9. Alert consumer receives event.
10. Alert broadcaster pushes event to all active SSE clients.
11. Angular table updates live.
12. HIGH_RISK alerts are inserted at the top and highlighted.

## Validation Failure Flow

1. Request contains missing account ID, missing merchant, missing location, or amount <= 0.
2. Spring validation rejects request.
3. Global exception handler returns `400 Bad Request` with field errors.
4. No Kafka event is published.

## Kafka Failure Flow

1. Request is valid.
2. Backend attempts to publish to Kafka.
3. Kafka publish fails.
4. API returns `503 Service Unavailable`.
5. This avoids pretending the transaction was accepted when the event was not durably published.

## SSE Disconnect Flow

1. Browser opens EventSource connection.
2. Backend stores `SseEmitter` in memory.
3. Browser closes tab or network disconnects.
4. `onCompletion`, `onTimeout`, or `onError` removes stale emitter.

## Mid-Interview Requirement Change Examples

- Add new rule: merchant in blacklist => HIGH_RISK.
  - Best change: add a rule to `FraudRuleEngine` and unit test it.
- Change home location per account.
  - Best change: introduce `AccountProfileService` interface instead of hardcoded `US`.
- Replace SSE with WebSocket.
  - Best change: preserve event pipeline, replace only delivery adapter.
