# NGO Donation and Volunteer Management

A small Java console application for recording donations, registering volunteers, and viewing a summary. Records are held in memory while the program is running; they are not saved after exit.

## Requirements

- JDK 8 or newer
- Apache Maven 3.6 or newer

Check the installed tools:

```powershell
java -version
mvn -version
```

## Build and run

Run these commands from the `DAILY_TASK/Day3/ngo-management` directory:

```powershell
mvn clean package
mvn exec:java
```

The first command cleans previous output, compiles the source, runs available tests, and packages the app as a JAR in `target/`. The second command runs `com.ngo.App` through Maven.

## Menu

1. Record a donor name and a positive donation amount.
2. Register a volunteer by name.
3. View donation totals and the volunteer count/list.
4. Exit the application.

## How Maven works here

Maven is a Java build and project-management tool. It reads `pom.xml` (the Project Object Model) to learn the project's coordinates, Java version, plugins, dependencies, and build settings. Maven also uses the standard source layout, so it finds application code under `src/main/java` without extra path configuration.

This project uses:

- `groupId`, `artifactId`, and `version` to identify the project (`com.ngo:ngo-management:1.0-SNAPSHOT`).
- `maven-compiler-plugin` to compile the Java 8-compatible source code.
- `exec-maven-plugin` to launch the `com.ngo.App` main class with `mvn exec:java`.
- No external libraries; the app uses classes included with the JDK.

Maven runs build lifecycle phases in order. For example, `mvn package` runs earlier phases such as `validate`, `compile`, and `test` before creating the package. `mvn clean` removes generated output, typically the `target/` directory. The local `.gitignore` excludes that generated directory from Git.

## Project layout

```text
ngo-management/
├── pom.xml
├── .gitignore
└── src/main/java/com/ngo/App.java
```