# Day 3 — ATM Simulator

This task introduces a Maven-based ATM simulator with PIN authentication, deposits, withdrawals, mini statements, input validation, and development/production profiles.

## Project

- `atm-simulator/` — Maven project containing the ATM simulator, build configuration, tests, and project-specific instructions.
- `atm-simulator/src/main/java/com/atm/AtmSimulator.java` — provides the ATM menu and transaction operations.
- `atm-simulator/pom.xml` — configures Java compilation, testing, packaging, and Maven profiles.

## Run

From the repository root:

```powershell
Set-Location "C:\Users\DELL\Downloads\HCL_HACKATHON_TRAINING\DAILY_TASK\Day3\atm-simulator"
$env:JAVA_HOME = "C:\Users\DELL\AppData\Local\jdks\jdk-25.0.2"
& "C:\Users\DELL\apache-maven-3.9.9\bin\mvn.cmd" clean package
& "C:\Users\DELL\apache-maven-3.9.9\bin\mvn.cmd" exec:java1
```

The default PIN is `1234`. The simulator supports deposit, withdrawal, mini-statement, and exit options.

See [atm-simulator/README.md](atm-simulator/README.md) for the complete menu, profile, and build information.

## Profiles

```powershell
& $mvn clean package -Pdev
& $mvn clean package -Pprod
```

The default profile uses `dev`. The `prod` profile changes the configured ATM mode to `prod`.