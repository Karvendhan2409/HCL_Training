# Day 3 — ATM Simulator

This task introduces a Maven-based ATM simulator with PIN authentication, deposits, withdrawals, mini statements, input validation, and development/production profiles.

## Project

- `atm-simulator/` — Maven project containing the ATM simulator, build configuration, tests, and project-specific instructions.
- `atm-simulator/src/main/java/com/atm/AtmSimulator.java` — provides the ATM menu and transaction operations.
- `atm-simulator/pom.xml` — configures Java compilation, testing, packaging, and Maven profiles.

## Run

From the repository root:

cd .\DAILY_TASK\Day3\atm-simulator
mvn clean package
mvn exec:java

The default PIN is `1234`. The simulator supports deposit, withdrawal, mini-statement, and exit options.

See [atm-simulator/README.md](atm-simulator/README.md) for the complete menu, profile, and build information.

## Profiles

```powershell
& $mvn clean package -Pdev
& $mvn clean package -Pprod
```

The default profile uses `dev`. The `prod` profile changes the configured ATM mode to `prod`.