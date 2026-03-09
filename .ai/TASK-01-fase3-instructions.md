# INSTRUCTIONS FOR REVIEWER AGENT (Fase 3)

**Role Context:** You are the Principal Engineer & Security Auditor (as per `.ai/05-reviewer-agent.md`).

**Your Current Task:** Review the code produced by the Frontend, Java Backend, and Tester agents for the "Auto Sostitutiva" (Substitute Car) feature.

## 📄 Source of Truth
- **OpenAPI Contract**: `docs/openapi.yaml` 
- **Mockups**: `docs/mockups/helix-auto-sostitutiva.pdf`

## 🎯 Action Items

1. **Review Backend Java (`backend-java/`)**:
    - Verify that no N+1 query problems exist in `OperatorService` and `CustomerRequestService`.
    - Ensure OpenAPI generated DTOs are mapped correctly to the JPA Entities (using MapStruct).
    - Ensure the `BookingEntity` correctly handles foreign keys.
    - Validate that tests written by the Tester agent cover all boundary conditions and pass successfully (Run `mvn clean test`).

2. **Review Frontend (`frontend/`)**:
    - Check for `use client` directives. Are they used only when strictly necessary (e.g. for React state/effects)?
    - Verify that OpenAPI fetch client is strictly typed and there are NO `any` types or forced casts (`as any`).
    - Verify that the Playwright E2E tests written by the Tester agent pass successfully (Run `npx playwright test`).
    - Verify the UX resilience (Are there Loading states? Error messages?).

3. **Provide Feedback**:
    - If you find architecture flaws, security vulnerabilities, or failing tests, *reject* the review by providing a detailed list of required fixes to the respective agent (Java, Frontend, or Tester) along with code snippets.
    - If everything is perfect, secure, and all tests are green, output an official **"APPROVATO"** status so the Orchestrator can proceed to Phase 4 (DevOps).
