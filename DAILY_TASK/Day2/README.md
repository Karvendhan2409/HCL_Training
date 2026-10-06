# Day 2 — Language Fundamentals and Git Fundamentals

This task covers Java data types, arrays, constants, operators, type casting, Git workflows, `.gitignore`, and SSH setup.

## Objectives

- Use primitive data types, one-dimensional arrays, and two-dimensional arrays.
- Declare `final` constants and avoid magic numbers.
- Understand operator precedence, widening and narrowing casts, overflow, and floating-point precision.
- Calculate totals, averages, minimums, maximums, and grades from sample data.
- Understand the Git flow: working directory → staging area → commit → remote.
- Configure GitHub SSH authentication and push source code without build artifacts.

## Files

- `Constants.java` — shared business constants and grading thresholds.
- `WeeklySampleData.java` — a seven-day sample array and monthly and house usage arrays.
- `MonthlyUsageAnalyzer.java` — total, average, min, max, grade, and house-total calculations.
- `MonthlyUsageAnalyzerTest.java` — executable behavior checks for the analyzer.
- `LanguageFundamentalsDemo.java` — demonstration of primitive types, arrays, casts, precedence, and precision.

## Run the Java checks

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

## Repository rules

- Java class files and generated output are ignored by `.gitignore`.
- Local SSH private keys and secrets are not committed.
- Only source code, text documentation, and repository configuration are tracked.
