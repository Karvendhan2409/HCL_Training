# Day 4 — Bank Account Debugging Lab

This project demonstrates a BankAccount model, service, and application package layout. It also contains a planted-withdrawal debugging exercise.

## Run

```powershell
& "C:\Users\DELL\apache-maven-3.9.9\bin\mvn.cmd" clean test
& "C:\Users\DELL\apache-maven-3.9.9\bin\mvn.cmd" exec:java -Dexec.mainClass=com.atm.app.BankAccountApp
```

## Debugging exercise

1. The model initially contains the deliberate bug `balance += amount` in `withdraw()`.
2. Run the application with a conditional breakpoint at `BankAccount.withdraw()` where `amount > 0 && balance > 0`.
3. Add a watch on `balance`.
4. Step through the method and observe the balance increasing instead of decreasing.
5. Use hot code replace to change `balance += amount` to `balance -= amount`.
6. Run the full tests again.

The conditional breakpoint and watch can be performed in VS Code Java Debugger or with JDB.
