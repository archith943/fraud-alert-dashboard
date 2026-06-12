Build a production-quality vertical slice for a Real-Time Fraud Alert Dashboard.

Backend:
- Java 17, Spring Boot 3.x, Maven
- POST /api/transactions accepts accountId, amount, merchant, location
- Validate inputs
- Generate UUID
- Publish TransactionReceivedEvent to Kafka topic transaction.received
- Return 202 Accepted
- Fraud consumer listens to transaction.received
- Rules: amount > 10000 HIGH_RISK; location != US MEDIUM_RISK; otherwise LOW_RISK
- High risk takes precedence
- Publish FraudEvaluatedEvent to fraud.evaluated
- Alert service consumes fraud.evaluated
- GET /api/alerts/stream sends alerts through SSE
- Add CORS for Angular localhost:4200
- Add global exception handler and Kafka unavailable handling
- Use readable package structure: config, controller, dto, event, exception, model, service

Frontend:
- Angular 17 standalone components
- Reactive form for transaction submission
- EventSource subscription to SSE stream
- Live table with red/amber/green risk badges
- HIGH_RISK alerts appear at top and briefly highlight

Quality:
- Unit tests for fraud rules, controller validation, SSE broadcaster
- Integration test using Embedded Kafka
- README with setup, curl commands, dependencies, tradeoffs
- OpenAPI schema
