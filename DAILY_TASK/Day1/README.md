# Day 1 — Java Platform Information

This task uses Java to display information about the current runtime environment.

## Objectives

- Use `Runtime` to access JVM resource information.
- Read Java runtime and operating-system properties.
- Print processor count, maximum heap, and free heap values.

## Files

- `PlatformInfo.java` — Java application that prints platform information.
- `PlatformInfo.class` — compiled output and is intentionally excluded from Git.

## Run the program

```powershell
javac DAILY_TASK/Day1/PlatformInfo.java
java -cp DAILY_TASK/Day1 PlatformInfo
```

## What it prints

- Java version
- Operating-system name
- Available processors
- Maximum heap size
- Free heap size

The program uses `System.getProperty()` for system information and `Runtime.getRuntime()` for JVM resource information.
