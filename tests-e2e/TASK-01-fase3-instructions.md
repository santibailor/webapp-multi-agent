# INSTRUCTIONS FOR TESTER AGENT (Fase 3)

**Role Context:** You are the Senior SDET & QA Automation Engineer (as per `.cursorrules/04-tester-agent.md`).

**Your Current Task:** Write automated tests to validate the "Auto Sostitutiva" (Substitute Car) feature. 

## 📄 Source of Truth
- **OpenAPI Contract**: `docs/openapi.yaml` (MUST strictly follow these endpoints and data models).
- **Mockups**: `docs/mockups/helix-auto-sostitutiva.pdf`
- **Global Phases & Tasks**: `docs/agent-tasks-fase2.md`

## 🎯 Action Items

1. **Backend Tests (Java)**
    - Navigate to the `backend-java` folder.
    - Write **Unit Tests** for `CustomerRequestService` and `OperatorService` using JUnit 5 and Mockito. Test valid inputs and edge cases (e.g., car not found during booking).
    - Write **Integration Tests** (e.g., `@SpringBootTest` + `@AutoConfigureMockMvc`) for the Controllers (`CustomerController` and `OperatorController`). Verify that the endpoints return EXACTLY the JSON structures and status codes defined in `openapi.yaml`. 
    - Verify that sorting and pagination work correctly for the `GET /api/v1/customer/requests` API.
    - Verify form validation (e.g. 400 Bad Request) on `POST /api/v1/operator/bookings`.

2. **E2E Tests (Playwright)**
    - Navigate to the `tests-e2e` folder (initialize a Playwright project if not present: `npm init playwright@latest`).
    - Write tests to simulate the Customer flow:
        - Fill out the Request form.
        - Verify validation prevents submission without Required fields.
        - Submit form and verify success message.
    - Write tests to simulate the Operator flow:
        - View Dashboard KPIs.
        - Search for requests using the search bar.
        - Book a car (Select Dates -> View Cars -> Click Book).
    - **CRITICAL**: Use data-testid or accessible locators (getByRole). Do NOT rely on brittle CSS classes.

**When Done**: Ensure `mvn clean test` (Java) and `npx playwright test` (E2E) pass. If a test fails due to a bug in the application code, DO NOT FIX the application code. Instead, generate a failure report so the Reviewer can address it.
