# Prompt 01 - Initial Architecture and Vertical Slice

Act as a senior full-stack engineer helping me implement a real-time fraud alert dashboard as a coherent vertical slice.

Goal:
Build a small but production-minded system where a transaction is submitted through REST, evaluated asynchronously through Kafka, and streamed live to an Angular dashboard through Server-Sent Events.

Technical constraints:
- Backend: Spring Boot 3.x, Java 17, Maven
- Messaging: Kafka on localhost:9092
- Frontend: Angular 17+ standalone components
- State: in-memory only
- Stream: EventSource/SSE, not WebSocket

Backend requirements:
- POST /api/transactions
- Request body: accountId, amount, merchant, location
- Validate required fields and amount > 0
- Generate UUID transactionId
- Publish TransactionReceivedEvent to topic transaction.received
- Return HTTP 202 Accepted with transactionId and status
- Consumer listens to transaction.received
- Fraud rules:
  - amount > 10000 => HIGH_RISK
  - location != account home location, mock default US => MEDIUM_RISK
  - otherwise => LOW_RISK
- Publish FraudEvaluatedEvent to topic fraud.evaluated
- Alert service consumes fraud.evaluated and broadcasts to connected SSE clients
- GET /api/alerts/stream exposes the SSE stream

Frontend requirements:
- Reactive form for accountId, amount, merchant, location
- Submit transaction to backend
- Subscribe to SSE using EventSource
- Live-updating table of alerts
- Risk badge colors: HIGH_RISK red, MEDIUM_RISK amber, LOW_RISK green
- New HIGH_RISK alerts should appear at the top with a brief visual highlight

Deliverables:
- Clean backend package structure
- DTOs, events, controllers, services, Kafka config, exception handling
- Angular services and standalone dashboard component
- API schema/OpenAPI document
- README with setup/run/test instructions
- Flow documentation
- Tradeoff documentation
- Unit and integration tests

Important:
Do not just generate code. Explain the design boundaries, why each component exists, and where I should inspect the implementation for correctness.
