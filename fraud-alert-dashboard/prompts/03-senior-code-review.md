# Prompt 03 - Senior Code Review

Act as the principal engineer reviewing this pull request.

Review the code for:
- Separation of concerns
- SOLID principles
- Controller/service boundaries
- DTO/event design
- Kafka configuration correctness
- Error handling
- Logging quality
- Thread safety
- Resource cleanup
- Memory leak risks
- Frontend subscription cleanup
- API contract consistency
- Test usefulness

For every issue found, return:
- Severity: Critical, High, Medium, Low
- File and method
- Why it matters
- Suggested fix
- Whether it blocks merging

Also answer:
1. What would fail first in production?
2. What is currently over-engineered?
3. What is under-engineered?
4. Which assumptions are acceptable for a vertical slice?
5. Which assumptions must be removed before production?

Prefer targeted fixes instead of rewriting the whole project.
