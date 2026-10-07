# Day 3 — Maven NGO Management

This task introduces Maven through a small Java application for tracking NGO donations and volunteers.

## Project

- `ngo-management/` — Maven project containing the console app, build configuration, and project-specific instructions.
- `ngo-management/src/main/java/com/ngo/App.java` — records donations and volunteers and displays an in-memory summary.
- `ngo-management/pom.xml` — identifies the Maven project and configures Java compilation and application execution.

## Run

From the project directory:

```powershell
cd DAILY_TASK/Day3/ngo-management
mvn clean package
mvn exec:java
```

See [ngo-management/README.md](ngo-management/README.md) for requirements, menu behavior, and an explanation of Maven's project configuration and lifecycle.