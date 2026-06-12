Review the generated implementation as a senior engineer.

Check for:
- Kafka topic names are consistent
- Request validation is applied and tested
- Kafka producer failures are not silently swallowed
- SSE emitters are removed on disconnect/error/timeout
- HIGH_RISK rule takes precedence over location rule
- Angular unsubscribes/closes EventSource on destroy
- CORS supports localhost:4200
- Tests cover positive, negative, and integration paths

Then provide only the code changes needed to fix issues.
