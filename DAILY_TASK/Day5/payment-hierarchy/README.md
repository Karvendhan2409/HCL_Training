# Day 5 - Payment Hierarchy and Git Collaboration

A Java 25 Maven exercise demonstrating an abstract payment type, Card/UPI/Cash implementations, refunds, and overloaded `pay()` methods.

## Run

From this directory:

```powershell
$mvn = "C:\Users\DELL\apache-maven-3.9.9\bin\mvn.cmd"
& $mvn clean test
& $mvn exec:java
```

## Pair Git Exercise

Run the Git commands from the repository root after the Day 5 project is available on `main`. Each student should use a separate clone or worktree so both can edit the same file independently.

1. Student A creates a branch from `main`:

   ```powershell
   git switch main
   git pull --ff-only
   git switch -c day5/student-a
   ```

2. Student B independently creates a branch from the same `main` commit:

   ```powershell
   git switch main
   git pull --ff-only
   git switch -c day5/student-b
   ```

3. In both branches, edit the same line in `src/main/java/com/training/payment/app/PaymentDemo.java`:

   ```java
   System.out.println("Payment options: Card, UPI, Cash");
   ```

   Student A changes it to `Payment options: Card and UPI are ready`. Student B changes it to `Payment options: Cash and card are ready`. Commit both edits separately.

4. Student A pushes and merges `day5/student-a` into `main` through the team's normal review process.

5. Student B fetches the updated `main` and rebases their branch. Replaying the conflicting edit should stop with a content conflict:

   ```powershell
   git fetch origin
   git switch day5/student-b
   git rebase origin/main
   ```

6. Resolve the marked conflict in `PaymentDemo.java` by keeping both contributions, for example:

   ```java
   System.out.println("Payment options: Card, UPI, and Cash are ready");
   ```

   Then finish the rebase and verify the project:

   ```powershell
   git add DAILY_TASK/Day5/payment-hierarchy/src/main/java/com/training/payment/app/PaymentDemo.java
   git rebase --continue
   & "C:\Users\DELL\apache-maven-3.9.9\bin\mvn.cmd" -f DAILY_TASK/Day5/payment-hierarchy/pom.xml clean test
   ```

7. Push Student B's rebased branch and merge it into `main`. If the branch was already pushed before the rebase, update it with `git push --force-with-lease`.

Expected result: Student A's change lands first, Student B resolves the conflict created while rebasing onto `main`, and the final main branch preserves both contributions.
