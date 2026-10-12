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

## Planned Features

### Accounts and Profiles

- Register donor and volunteer accounts and sign in with email and password.
- Maintain user profiles and volunteer skills, interests, and availability.
- Support role-based permissions for admins, donors, volunteers, and coordinators.
- Allow authorized staff to approve or reject volunteer applications.

### Donations and Campaigns

- Record donation amount, donor, date, purpose, and optional campaign.
- Validate donation data and retain a donation history.
- Show donation totals and provide a donation receipt record.
- Associate campaigns with target amounts, dates, and a status.

The first version records donations; it does not need to collect payment card details. If online payments are added later, use a payment provider in test mode and store only the provider's transaction reference and payment status. Never store card numbers or CVVs.

### Events and Volunteer Participation

- Create events with a title, description, location, date, capacity, and status.
- Let volunteers register for events, subject to capacity and registration rules.
- Let coordinators review registrations and record attendance.
- Show upcoming and completed events and volunteer participation history.

### Notifications and Reports

- Plan for email confirmations, donation receipts, event reminders, and approval decisions.
- Provide dashboards for donation totals, volunteer counts, and event activity.
- Generate monthly donation and volunteer activity reports.

Email and advanced reporting are later-phase features unless required for the initial release.

## Quality Requirements

| Area | Requirement |
| --- | --- |
| Security and privacy | Hash passwords; enforce authorization on private operations; limit access to personal details and donation histories. |
| Data integrity | Validate amounts and required fields, prevent duplicate records where appropriate, and enforce event capacity. |
| Reliability | Handle failed operations safely and protect saved donation records from loss. |
| Performance | Keep pages and API responses within an agreed response-time target under normal usage. |
| Scalability | Keep the design able to support growth in donors, volunteers, and events. |
| Usability | Provide clear, straightforward workflows for users with different levels of technical experience. |
| Maintainability | Separate API handling, business rules, persistence, and data-transfer types into distinct layers. |
| Availability and recovery | Plan reliable deployment, regular database backups, and a recovery process. |

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
