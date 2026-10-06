# HCL Hackathon Training

This repository contains Java language fundamentals and Git workflow exercises for the HCL Hackathon training.

## Day 2 — Language Fundamentals and Git Fundamentals

### Learning goals

- Use primitive types, arrays, variables, constants, operators, and casts.
- Understand operator precedence and the difference between widening and narrowing casts.
- Recognize integer overflow and floating-point precision.
- Understand the Git workflow: working directory → staging area → commit → remote.
- Configure GitHub SSH authentication and push changes to a new repository.
- Keep generated build artifacts and local secrets out of Git.

### Project structure

- `DAILY_TASK/Day2/Constants.java` — business-rule constants.
- `DAILY_TASK/Day2/WeeklySampleData.java` — a week and monthly sample data in arrays.
- `DAILY_TASK/Day2/MonthlyUsageAnalyzer.java` — monthly usage calculations.
- `DAILY_TASK/Day2/MonthlyUsageAnalyzerTest.java` — executable behavior checks.
- `DAILY_TASK/Day2/LanguageFundamentalsDemo.java` — casts, operators, precision, and arrays.

### Run the Day 2 Java checks

```powershell
javac -d out DAILY_TASK/Day2/*.java
java -cp out Day2.MonthlyUsageAnalyzerTest
java -cp out Day2.LanguageFundamentalsDemo
```

## Git workflow

```powershell
git init
git add .
git commit -m "Add Day 2 Java fundamentals and Git workflow examples"
git push -u origin main
```

The repository ignores Java build output, IDE metadata, operating-system files, and local SSH keys.
