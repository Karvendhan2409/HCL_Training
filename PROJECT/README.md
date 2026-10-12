# NGO Donation and Volunteer Management System

## Project Overview

The NGO Donation and Volunteer Management System is a proposed web application for organizing an NGO's donations, volunteers, campaigns, and community events in one place. It replaces scattered spreadsheets and paper records with a central system where authorized users can manage information and follow activity.

The initial version focuses on recording donations and managing core NGO information. Online payment processing, email notifications, and advanced reporting can be added in later phases. This README describes the planned system; it does not mean these features have already been implemented.

## Goals

- Keep donation, volunteer, event, and campaign records organized.
- Let volunteers register for activities and let coordinators manage participation.
- Give users access only to the information and actions allowed by their role.
- Provide a foundation that can be expanded as the NGO's needs grow.

## User Roles

| Role | Main responsibilities |
| --- | --- |
| Admin | Manage users and system-wide records; access administrative reports. |
| Donor | Maintain a profile, record donations, and view their donation history and receipts. |
| Volunteer | Maintain skills and availability, apply to volunteer, and view event participation. |
| NGO Coordinator | Create events and campaigns, manage volunteer registrations, and track attendance. |

Role permissions will be enforced by the backend. Volunteer applications and donations that need review will have explicit statuses rather than being treated as approved automatically.

## Functional Requirements

Functional requirements describe what the system must do. These are proposed requirements; features will be implemented in phases.

### FR1. User Accounts and Access

- The system shall allow donors and volunteers to register and sign in using an email address and password.
- The system shall allow users to view and update their profiles.
- The system shall provide role-based permissions for admins, donors, volunteers, and NGO coordinators.
- The system shall allow authorized staff to approve or reject volunteer applications.

### FR2. Donation Management

- The system shall allow an authorized user to record a donation's donor, amount, date, purpose, and optional campaign.
- The system shall validate donation data and retain donation history.
- The system shall allow a donor to view their own donation history and receipt records.
- The system shall allow authorized admins to view donation records and totals.

The initial version records donations and does not need to process online payments. If online payments are added later, the system shall use a payment provider and store only its transaction reference and payment status. It shall never store card numbers or CVVs.

### FR3. Volunteer Management

- The system shall store a volunteer's skills, interests, and availability.
- The system shall allow authorized staff to approve or reject volunteer applications.
- The system shall show a volunteer their event participation history.

### FR4. Campaign and Event Management

- The system shall allow authorized coordinators to create and update campaigns with target amounts, dates, and statuses.
- The system shall allow authorized coordinators to create events with a title, description, location, date, capacity, and status.
- The system shall allow volunteers to register for events when registration rules and capacity permit.
- The system shall allow coordinators to review registrations and record attendance.
- The system shall show upcoming and completed events.

### FR5. Notifications

- The system shall be able to send registration confirmations, donation receipts, event reminders, and volunteer application decisions.
- Email notifications are planned for a later phase unless they are selected as an initial-release requirement.

### FR6. Dashboard and Reports

- The system shall display donation totals, registered volunteer counts, and upcoming and completed events to authorized users.
- The system shall support monthly donation and volunteer activity reports.
- Advanced dashboard and reporting features are planned for a later phase unless selected for the initial release.

## Non-Functional Requirements

Non-functional requirements describe how securely, reliably, and effectively the system must operate. Targets that depend on deployment size or hosting environment must be agreed before release testing.

### NFR1. Security and Privacy

- The system shall store password hashes, never plain-text passwords.
- The backend shall authenticate users and authorize every private operation according to role.
- Users shall only be able to view personal information and donation histories they are permitted to access.

### NFR2. Data Integrity

- The system shall validate required fields and reject invalid or non-positive donation amounts.
- The system shall enforce event capacity and prevent duplicate registrations where applicable.
- Related records shall use database constraints, such as foreign keys, where appropriate.

### NFR3. Reliability

- The system shall handle failed operations without reporting them as successful.
- A failed request shall not leave partially saved donation or registration data.
- Persisted donation records shall be protected by regular database backups.

### NFR4. Performance

- Under normal expected usage, pages and API requests shall respond within an agreed response-time target.
- The team shall define and test the response-time target against the intended deployment environment before release.

### NFR5. Usability

- The interface shall provide clear, consistent workflows for donors, volunteers, admins, and coordinators.
- Forms shall identify required fields and explain validation errors in understandable language.

### NFR6. Maintainability

- The backend shall separate HTTP handling, business logic, persistence, and API data-transfer types into controller, service, repository, and DTO layers.
- The project shall include automated tests for important business rules and API behavior.

### NFR7. Scalability

- The application shall use a modular design and database-backed persistence so it can support growth in donors, volunteers, campaigns, and events.
- Capacity expectations shall be defined and tested before production deployment.

### NFR8. Availability and Recovery

- The deployment plan shall define availability expectations, backup frequency, and recovery procedures.
- Database backups shall be tested to confirm that records can be restored.

## Proposed Technology Stack

- **Backend:** Java 21 and Spring Boot
- **API:** Spring Web REST endpoints using JSON
- **Database:** MySQL
- **Persistence:** Spring Data JPA with Hibernate
- **Security:** Spring Security and password hashing
- **Validation:** Spring Boot Validation
- **Build:** Maven
- **Frontend:** React, HTML, and CSS
- **Testing:** JUnit 5 and Mockito for Java tests; Postman for API exploration
- **Version control:** Git and GitHub

## How a Donation Request Flows

1. The React frontend sends a donation request to the Spring Boot API.
2. A controller validates the request shape and passes it to the service layer.
3. The service applies business rules, such as validating the amount and donor permissions.
4. A repository saves the donation through JPA/Hibernate to MySQL.
5. The API returns a response to the frontend.

Controllers handle HTTP requests, services own business rules, repositories handle database access, and models/entities represent persisted records. DTOs define the data exchanged through the API.

## Proposed Project Structure

```text
ngo-management/
├── src/
│   ├── main/
│   │   ├── java/com/ngo/
│   │   │   ├── NgoApplication.java
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   ├── dto/
│   │   │   ├── security/
│   │   │   ├── exception/
│   │   │   └── config/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Initial Data Model

| Table | Example fields |
| --- | --- |
| `users` | `id`, `name`, `email`, `password_hash`, `role` |
| `donations` | `id`, `donor_id`, `campaign_id`, `amount`, `status`, `donated_at` |
| `volunteers` | `id`, `user_id`, `skills`, `availability`, `approval_status` |
| `events` | `id`, `title`, `description`, `location`, `event_date`, `capacity`, `status` |
| `event_registrations` | `id`, `event_id`, `volunteer_id`, `registration_status`, `attendance` |
| `campaigns` | `id`, `title`, `target_amount`, `start_date`, `end_date`, `status` |

Foreign keys will connect related records, such as a donation to its donor and campaign. A separate payments table is only needed if online payment integration is introduced; it should contain transaction references and statuses, not card details.

## Draft REST API

These routes are examples for planning and may change as implementation decisions are made. Private routes must require authentication and the appropriate role.

| Method | Endpoint | Purpose |
| --- | --- | --- |
| `POST` | `/api/auth/register` | Register a donor or volunteer. |
| `POST` | `/api/auth/login` | Authenticate a user. |
| `POST` | `/api/donations` | Create a donation record. |
| `GET` | `/api/donations/my` | View the authenticated donor's donation history. |
| `GET` | `/api/donations` | Let an authorized admin view donation records. |
| `POST` | `/api/volunteers` | Create a volunteer profile. |
| `PATCH` | `/api/volunteers/{id}/approve` | Approve or reject a volunteer application. |
| `POST` | `/api/events` | Create an event. |
| `POST` | `/api/events/{id}/register` | Register the authenticated volunteer for an event. |
| `GET` | `/api/admin/dashboard` | Retrieve authorized dashboard statistics. |

## Suggested Implementation Phases

1. **Setup:** Create the Spring Boot Maven application, configure MySQL, and verify it starts.
2. **Donation records:** Implement donation entities, validation, service and repository layers, and basic create/read APIs.
3. **Core management:** Add user profiles, volunteers, campaigns, events, and event registrations.
4. **Business rules:** Add volunteer approval, event capacity checks, donation validation, and reporting queries.
5. **Security:** Add authentication, password hashing, and role-based authorization before exposing private data.
6. **Additional features:** Add email notifications, receipt delivery, dashboard reports, and optional payment-provider integration.
7. **Testing and deployment:** Add unit and API tests, test endpoints, configure backups, and deploy the application.

## Scope and Safety Notes

- Start with donation record management; do not make online payments a prerequisite for the initial version.
- Store password hashes rather than plain-text passwords.
- Enforce access rules in the backend, not only in the user interface.
- Do not store card numbers or CVVs. Use a payment provider if online payment is added.
- Define actual response-time, backup, recovery, and availability targets before release.
