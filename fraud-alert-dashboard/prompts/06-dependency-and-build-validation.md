# Prompt 06 - Dependency and Build Validation

Validate dependency compatibility and local build readiness.

Check backend:
- Java version
- Spring Boot version
- Spring Kafka compatibility
- Maven compiler plugin
- Validation dependency
- Test dependencies
- Kafka client compatibility

Check frontend:
- Angular version
- TypeScript version
- RxJS version
- Node/npm compatibility
- Standalone component configuration

Check local tooling:
- Docker Compose Kafka image
- Kafka localhost:9092 availability
- Maven build command
- npm install behavior
- ng serve behavior

Return:
- Compatibility table
- Version mismatches
- Risky dependencies
- Recommended versions
- Exact commands to validate build and tests

Do not upgrade dependencies unless there is a real compatibility issue.
