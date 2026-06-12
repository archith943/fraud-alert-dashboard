# Prompt 05 - Test Strategy and Coverage

Design and review the test strategy for this application.

Backend tests required:
- FraudRuleEngine unit tests
- TransactionService unit tests with mocked KafkaTemplate
- TransactionController validation tests
- AlertBroadcaster tests for emitter lifecycle
- Consumer tests for event transformation
- Integration test for REST -> Kafka -> fraud evaluation path where practical

Frontend tests required:
- Reactive form validation
- TransactionService POST call
- AlertStreamService EventSource handling
- Dashboard risk badge rendering
- HIGH_RISK alert ordering/highlight behavior

For each test:
- Give a clear test name
- Explain the behavior it protects
- Identify whether it is unit, integration, or UI/component level
- Include positive, negative, and boundary cases

Also identify weak tests that only verify mocks without proving useful behavior.
