# INSTRUCTIONS FOR JAVA BACKEND AGENT (Fase 2)

**Role Context:** You are the Spring Boot Vibe Coder (as per `.cursorrules/02-java-backend-agent.md`).

**Your Current Task:** Implement the "Auto Sostitutiva" (Substitute Car) backend features.

## 📄 Source of Truth
- **OpenAPI Contract**: `docs/openapi.yaml` (MUST strictly follow these endpoints and data models).
- **Global Phases & Tasks**: `docs/agent-tasks-fase2.md`

## 🎯 Action Items

1. **Project Initialization**: Initialize a Spring Boot 3.4+ project in `backend-java` (if it does not exist) utilizing Maven, Java 21, Spring Web, Spring Data JPA, H2/PostgreSQL, Flyway, Lombok, and SpringDoc OpenAPI.
2. **DTO Generation**: Read `docs/openapi.yaml` and generate precisely the DTOs listed in the `components/schemas` (e.g., `CustomerRequest`, `NewCustomerRequest`, `OperatorStats`, `AvailableCar`, `NewBooking`). Use Lombok to eliminate boilerplate (`@Data`/`@Value`). You can use the OpenAPI Generator Maven Plugin as requested by your vibe rules, or just code them manually if you prefer.
3. **Persistenza & Flyway**:
   - Create the JPA Entities mapping to the models (`Car`, `CustomerRequest`, `Booking`).
   - Create Flyway migration files (`src/main/resources/db/migration/V1__schema.sql`) for creating the required tables (use portable SQL syntax with `GENERATED ALWAYS AS IDENTITY`).
   - Create Repositories.
4. **Service Layer**: Implement the business logic:
    - Customer requests CRUD.
    - Operator dashboards (calculating stats).
    - Available car searching algorithm based on dates.
    - Booking logic.
5. **Controllers**: Implement exactly these paths:
    - `/api/v1/customer/requests` (GET, POST)
    - `/api/v1/operator/dashboard/stats` (GET)
    - `/api/v1/operator/requests` (GET)
    - `/api/v1/operator/vehicles/search` (GET)
    - `/api/v1/operator/bookings` (POST)

**When Done**: Ensure `mvn clean verify` passes (or at least `mvn clean compile`) and notify that the Java Backend is ready for QA.
