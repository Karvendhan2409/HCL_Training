# Day 4 - Bank Account Debugging Lab

This project demonstrates a `BankAccount` model, service, and application package layout. The application uses only the JDK and can be compiled and run without Maven.

## Run Without Maven

From PowerShell:

```powershell
Set-Location "C:\Users\DELL\Downloads\HCL_HACKATHON_TRAINING\DAILY_TASK\Day4\bank-account-demo"
$env:JAVA_HOME = "C:\Users\DELL\AppData\Local\jdks\jdk-25.0.2"
$classes = Join-Path $env:TEMP "bank-account-demo-classes"
New-Item -ItemType Directory -Force -Path $classes | Out-Null
$sources = Get-ChildItem "src\main\java" -Recurse -Filter "*.java" | ForEach-Object { $_.FullName }
& "$env:JAVA_HOME\bin\javac.exe" -d $classes $sources
if ($LASTEXITCODE -ne 0) { throw "Compilation failed." }
& "$env:JAVA_HOME\bin\java.exe" -cp $classes com.atm.app.BankAccountApp
```

Expected output:

```text
Account ACCT-1000 created with balance $1000.0
Final balance: $1100.0
```

## Tests

The JUnit tests use Maven. To run them, use `mvn clean test` from this project directory. Maven is not required to compile or run the application.

## Debugging Exercise

The current `withdraw()` implementation correctly subtracts the withdrawal amount. To recreate the debugging exercise, temporarily change `balance -= amount` to `balance += amount` in `BankAccount.withdraw()`.

1. Run the application in the VS Code Java debugger with a conditional breakpoint in `BankAccount.withdraw()` where `amount > 0 && balance > 0`.
2. Add a watch on `balance` and step through the method.
3. Observe the balance increasing instead of decreasing.
4. Restore `balance -= amount` and run the application again using the commands above.

The conditional breakpoint and watch can be used with the VS Code Java Debugger or JDB.
