# Real-Time Fraud Alert Dashboard

Production-oriented vertical slice for the interview problem: REST transaction intake, Kafka-based asynchronous fraud evaluation, and Server-Sent Events streaming to an Angular 17 dashboard.

## Tech Stack

- Backend: Java 17, Spring Boot 3.3.6, Spring Web, Spring Validation, Spring Kafka, Actuator
- Messaging: Apache Kafka on `localhost:9092`
- Frontend: Angular 17 standalone components, Reactive Forms, EventSource SSE
- Testing: JUnit 5, Spring Boot Test, MockMvc, Embedded Kafka

## Compatibility Notes

Spring Boot 3.x requires Java 17+. Spring Kafka is managed by the Spring Boot dependency BOM, which prevents manual version drift. Angular 17 uses Node/TypeScript/RxJS compatibility defined by the official Angular version matrix.

Recommended local versions:

```bash
java -version      # 17+
mvn -version
node -v            # 18.13+ or 20.x compatible with Angular 17
npm -v
```

## Architecture

```text
Angular UI --POST /api/transactions--> Spring TransactionController
                                           |
                                           v
                                  Kafka transaction.received
                                           |
                                           v
                                  FraudDetectionConsumer
                                           |
                                           v
                                  Kafka fraud.evaluated
                                           |
                                           v
                                  AlertConsumer + AlertBroadcaster
                                           |
                                           v
Angular UI <-- GET /api/alerts/stream SSE -- AlertController
```

## Backend Setup

### 1. Start Kafka

Option A: local Kafka already installed on `localhost:9092`.

Option B: Docker example:

```bash
docker run -d --name kafka -p 9092:9092 apache/kafka:latest
```

### 2. Run backend

```bash
cd backend
mvn clean test
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

Health check:

```bash
curl http://localhost:8080/actuator/health
```

## Frontend Setup

```bash
cd frontend
npm install
npm start
```

Frontend runs on:

```text
http://localhost:4200
```

## API Schema

See [`docs/api-schema.yaml`](docs/api-schema.yaml).

## API Examples

### Subscribe to SSE stream

```bash
curl -N http://localhost:8080/api/alerts/stream
```

### LOW_RISK transaction

```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"accountId":"A123","amount":100,"merchant":"Walmart","location":"US"}'
```

### MEDIUM_RISK transaction

```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"accountId":"A123","amount":500,"merchant":"Amazon","location":"IN"}'
```

### HIGH_RISK transaction

```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"accountId":"A123","amount":15000,"merchant":"Tesla","location":"US"}'
```

### Negative validation case

```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"accountId":"","amount":-1,"merchant":"","location":""}'
```

Expected: `400 Bad Request` with field-level errors.

## Kafka Topics

| Topic | Producer | Consumer | Purpose |
|---|---|---|---|
| `transaction.received` | TransactionService | FraudDetectionConsumer | Raw accepted transaction event |
| `fraud.evaluated` | FraudDetectionConsumer | AlertConsumer | Evaluated risk alert event |

## Fraud Rules

Priority order matters:

1. `amount > 10000` => `HIGH_RISK`
2. `location != US` => `MEDIUM_RISK`
3. otherwise => `LOW_RISK`

High risk intentionally takes precedence over location mismatch.

## Error Handling

- Request validation: `400 Bad Request`
- Kafka producer failure during transaction intake: `503 Service Unavailable`
- SSE disconnects: stale emitters are removed from memory
- Kafka deserialization: `ErrorHandlingDeserializer` prevents poison messages from crashing the consumer container

## Kafka Unavailable Fallback Approach

Current implementation returns `503` from `POST /api/transactions` if the transaction cannot be published. This is safer than returning `202` and losing the event.

Production alternatives:

1. Transactional outbox table, then relay to Kafka.
2. Durable local queue with retry.
3. In-memory queue only for demo mode, not production.

## Tests

```bash
cd backend
mvn test
```

Test coverage includes:

- Fraud rule unit tests
- Controller validation tests
- SSE broadcaster unit test
- Embedded Kafka integration-style fraud flow test

## Interview Notes

The repo includes a `prompts/` folder. Use it during screen share to prove how you directed AI, reviewed output, and corrected gaps.
