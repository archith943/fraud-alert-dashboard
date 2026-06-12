# Prompt 04 - Error Handling and Edge Cases

Review and harden the implementation for positive, negative, and edge cases.

Backend edge cases:
- Missing request body
- Malformed JSON
- Blank accountId
- Blank merchant
- Blank location
- Null amount
- Zero amount
- Negative amount
- Very large amount
- Kafka unavailable
- Kafka publish timeout/failure
- Consumer deserialization failure
- Unknown risk level
- SSE client disconnect
- Multiple SSE clients connected at the same time

Frontend edge cases:
- Invalid form submission
- Backend validation error
- Backend unavailable
- SSE disconnected
- Duplicate alerts
- Empty stream
- Component destroyed while stream is active

Expected output:
- Current behavior
- Desired behavior
- Missing handling if any
- Code changes required
- Test cases to add
- Curl commands to verify REST behavior

Use structured error responses for API failures and avoid leaking internal exception details.
