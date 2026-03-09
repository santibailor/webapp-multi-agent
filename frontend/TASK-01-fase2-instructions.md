# INSTRUCTIONS FOR FRONTEND AGENT (Fase 2)

**Role Context:** You are the React/Next.JS Expert Front-End Coder (as per `.cursorrules/01-frontend-agent.md`).

**Your Current Task:** Implement the "Auto Sostitutiva" (Substitute Car) frontend interfaces according to the approved designs and API contract.

## 📄 Source of Truth
- **OpenAPI Contract**: `docs/openapi.yaml` (MUST strictly follow these endpoints and data models for fetching/submitting data).
- **Mockups**: `docs/mockups/helix-auto-sostitutiva.pdf`
- **Global Phases**: `docs/agent-tasks-fase2.md`

## 🎯 Action Items

1. **Project Initialization** (if not already done): Set up a Next.js (App Router or Pages Router, default App Router) project in the `frontend` directory using TypeScript, Tailwind CSS, and optionally Shadcn UI.
2. **Type Generation**: Create TypeScript interfaces/types matching the components in `docs/openapi.yaml` (e.g., `CustomerRequest`, `NewBooking`, `AvailableCar`, etc.). You can use tools like `openapi-typescript` or write them manually.
3. **API Client**: Create a service layer to interact with the backend API (`/api/v1/customer/...` and `/api/v1/operator/...`). Use `fetch` or a library like `Axios` or `React Query`.
4. **Customer Views**:
   - `GET /customer/requests`: Build the view showing the history of requests (Status, Vehicle, Dates).
   - `POST /customer/requests`: Build the multi-step or single-step form for requesting a substitute vehicle (selecting options like Gearbox, Snow Chains, Delivery Type, and Customer Info).
5. **Operator Views**:
   - **Dashboard**: `GET /operator/dashboard/stats` - Display KPI cards (Total Requests, Total Replaced, Total Expenses).
   - **Request List**: `GET /operator/requests` - A data table to view all customer requests with pagination and search.
   - **Vehicle Search & Booking**: 
     - Allow selecting a Date Range `GET /operator/vehicles/search?startDate=...&endDate=...`
     - Display the list of available cars with prices and specs.
     - Select a car and confirm booking via `POST /operator/bookings`.

**When Done**: Ensure `npm run lint` and `npm run build` pass successfully, then notify that the Frontend is ready for QA.
