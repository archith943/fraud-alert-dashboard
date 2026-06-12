# Prompt 07 - Security and Production Readiness Review

Review the application from a security, reliability, and operability perspective.

Security checks:
- Request validation
- CORS configuration
- No sensitive data in logs
- No stack traces exposed to clients
- DTOs do not expose internal implementation details

Reliability checks:
- Kafka failure handling
- Consumer error handling
- Retry/DLQ considerations
- SSE emitter cleanup
- Thread-safe emitter collection
- Back-pressure considerations

Operability checks:
- Structured logging
- Health checks
- Metrics worth adding
- Trace/correlation ID opportunities
- Clear README runbook

Return:
- Must-fix items for this slice
- Acceptable simplifications for this slice
- Production improvements if traffic increases 100x
- Tradeoffs behind the recommended choices
