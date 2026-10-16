# Day 8 - Employee Directory

This demo uses Java collections and ordering rules to report on a small employee directory.

## Run

From the project root in PowerShell:

```powershell
javac -d out DAILY_TASK/Day8/EmployeeDirectoryDemo.java
java -cp out Day8.EmployeeDirectoryDemo
```

## Concepts

| Concept | Purpose |
| --- | --- |
| `HashMap` | Looks up an employee by ID. |
| `Map.merge()` | Counts employees per department. |
| `TreeMap` | Prints departments in sorted order. |
| `Comparable<Employee>` | Sorts employees by ID. |
| Comparator chain | Sorts salary descending, then department, name, and ID. |
| `equals()` and `hashCode()` | Demonstrates the broken hash contract and its correction. |
