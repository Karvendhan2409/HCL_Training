# ATM Simulator

A Java console ATM simulator for deposits, withdrawals, PIN authentication, mini statements, and profile-based environment settings.

## Requirements

- JDK 8 or newer
- Apache Maven 3.6 or newer

## Build and run

Run these commands from the `DAILY_TASK/Day3/atm-simulator` directory:

```powershell
$mvn = "C:\Users\DELL\apache-maven-3.9.9\bin\mvn.cmd"
& $mvn clean package
& $mvn exec:java
```

The application supports these menu options:

1. Deposit money
2. Withdraw money
3. View mini statement
4. Exit

The PIN is `1234`. Invalid PIN attempts are limited to three.

## Profiles

- `dev` — development environment
- `prod` — production environment

Run a production build with:

```powershell
& $mvn clean package -Pprod
```


