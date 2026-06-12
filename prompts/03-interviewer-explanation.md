During the interview, explain:

I used AI to scaffold the first version, but I am not treating it as final. I am checking the system boundaries: REST contract, Kafka serialization, async consumer behavior, SSE lifecycle, validation, and failure handling. My main priority is a coherent vertical slice. If a requirement changes, I will isolate the change in the right component instead of rewriting the whole system.

If asked why Kafka: it decouples intake from fraud evaluation and lets each side scale independently.

If asked why SSE: the browser only needs server-to-client updates, so SSE is simpler than WebSocket.

If asked about production gaps: I would add a transactional outbox, Schema Registry, authentication, observability dashboards, DLQ/retry topics, and distributed SSE delivery for multiple backend instances.
