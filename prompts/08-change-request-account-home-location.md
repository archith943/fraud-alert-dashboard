# Prompt 08 - Change Request Handling

A requirement changed after the initial implementation.

New requirement:
Location mismatch should not always compare against hardcoded US. Instead, each account should have a mock home location from an in-memory account profile service.

Example:
- A100 -> US
- A200 -> IN
- A300 -> UK
- Unknown account -> default US

Update the design with minimal code changes.

Constraints:
- Do not put business logic in the controller
- Keep the fraud rule engine testable
- Add or update unit tests
- Keep API contract unchanged

Explain:
- Which class should own account home location lookup
- Why in-memory map is acceptable for this vertical slice
- Tradeoff between in-memory map, database, cache, and external account service
- Impacted files only
