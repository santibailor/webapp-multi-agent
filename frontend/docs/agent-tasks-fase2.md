# Interfaccia delle Istruzioni (Fase 2 - Sviluppo Parallelo)

Questa directory di istruzioni fornisce ai singoli agenti lo strato formale per sviluppare la feature "Auto Sostitutiva". Il contratto SSOT è approvato ed è disponibile nel file `docs/openapi.yaml`.

---

## 💻 Agente Frontend (TS/Next.js)

**Obiettivo:** Costruire le interfacce utente del modulo "Auto Sostitutiva" (lato Cliente e lato Operatore) rispettando i mockup `docs/mockups/helix-auto-sostitutiva.pdf` e consumando l'OpenAPI generato.

**Task assegnati:**
1. **Generazione Tipi**: Estrai la specifica TypeScript/Zod dal file `docs/openapi.yaml` per le entità `CustomerRequest`, `OperatorRequest`, `AvailableCar`, etc.
2. **API Services**: Implementa le chiamate verso le API rispettando esattamente l'OpenAPI (usando mock server temporanei o puntando all'origin configurata).
3. **Pagine Cliente**:
    - Creare la vista del form per richiedere l'auto sostitutiva (recuperando tipo consegna, optional, dati cliente). Endpoint: `POST /customer/requests`.
    - Creare la vista elenco/storico delle richieste del cliente. Endpoint: `GET /customer/requests`.
4. **Pagine Operatore**:
    - Creare la dashboard con le statistiche sommarie. Endpoint: `GET /operator/dashboard/stats`.
    - Creare l'elenco generale delle richieste auto sostitutive con ricerca/paginazione. Endpoint: `GET /operator/requests`.
    - Creare il flusso di prenotazione. Ricerca auto `GET /operator/vehicles/search` e di prenotazione finale `POST /operator/bookings`.

---

## ☕ Agente Backend Java (Spring Boot)

**Obiettivo:** Realizzare lo strato di backend core, partendo dall'approccio API-First stabilito dall'Orchestratore, implementando logica business e i data model associati.

**Task assegnati:**
1. **Contrarti e DTO**: Assicurati che i DTO Java esposti o generati riflettano alla perfezione `docs/openapi.yaml`. Non alterare il contratto senza consultare l'Orchestratore.
2. **Controllers**: Implementa i controller definiti nel documento YAML (`CustomerRequestsController`, `OperatorRequestsController`, `OperatorVehiclesController`, `OperatorDashboardController`).
3. **Service Layer**: 
    - Implementare i servizi di business: calcolo costo auto disponibili, mantenimento dello storico richieste, generazione statistiche globali operatore.
4. **Persistenza (Flyway e Entities)**:
    - Creare le entità JPA (`Car`, `CustomerRequest`, `Booking`).
    - Aggiorna i file di migrazione Flyway esistenti (se si applica) per accomodare lo schema del database necessario, tenendo traccia dell'uso corretto di `GENERATED ALWAYS AS IDENTITY` o altri constraint già in uso nel progetto.

---

## 🐍 Agente Backend Python (FastAPI/AI)

**Obiettivo:** Implementare l'eventuale parte Python in caso di elaborazioni asincrone/AI.

**Task assegnati:**
Attualmente, i mockup e i requisiti dell'auto sostitutiva non prevedono feature analitiche o di Intelligenza Artificiale complesse (il flusso è puramente CRUD e logica prenotazione gestita dal layer Java).
- *Stato attuale*: **Sei in STAND-BY per questa feature**. 
- Se l'utente o l'Architettura dovesse richiedere (in un secondo momento) l'estrazione OCR dei documenti cliente o script AI per l'ottimizzazione dell'assegnazione vetture, verrai attivato con un nuovo contratto API specifico. Non devi scrivere codice per questa fase.
