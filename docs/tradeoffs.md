# Design Choices and Tradeoffs

## Kafka vs Direct Synchronous Fraud Evaluation

Kafka was chosen because the problem explicitly requires asynchronous event processing. Kafka decouples transaction intake from fraud evaluation and alert delivery. This makes the intake API faster and allows fraud processing to scale independently.

Tradeoff: Kafka adds infrastructure complexity, topic configuration, serialization concerns, and failure modes. For a small demo, synchronous evaluation would be simpler. For production-like event-driven systems, Kafka is the better fit.

## SSE vs WebSocket

SSE was chosen because alerts are server-to-client only. EventSource is simple, HTTP-friendly, and has built-in reconnect behavior.

Tradeoff: SSE is one-way. If the UI needed bidirectional collaboration, acknowledgements, or chat-like behavior, WebSocket would be more appropriate.

## In-Memory SSE Emitters vs Distributed Pub/Sub

In-memory emitters are simple and match the single Spring Boot project constraint.

Tradeoff: this does not work well across multiple backend instances unless sticky sessions or shared pub/sub are added. Production scaling would use Redis Pub/Sub, Kafka-to-WebSocket gateway, or a managed event delivery layer.

## JSON Events vs Avro/Protobuf

JSON was chosen for readability and interview speed.

Tradeoff: JSON lacks strong schema evolution guarantees. Production Kafka contracts often use Avro/Protobuf with Schema Registry.

## Return 503 on Kafka Failure vs In-Memory Fallback

Returning 503 is safer because the system does not falsely claim acceptance when the transaction event is lost.

Tradeoff: demo experience is less smooth if Kafka is unavailable. A real production design would use a transactional outbox to persist first and publish later.

## Angular Standalone Components vs NgModules

Standalone components were chosen because Angular 17 encourages simpler component bootstrapping without NgModule boilerplate.

Tradeoff: teams with older Angular apps may prefer NgModules for consistency during migration.
