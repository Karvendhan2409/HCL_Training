# Day 6 - Order Processor Exceptions

This exercise demonstrates custom checked and unchecked exceptions, chained causes, multi-catch, and `finally` auditing.

## Run

From this folder in PowerShell:

```powershell
javac -d out OrderProcessorDemo.java
java -cp out OrderProcessorDemo
```

The demo processes one valid order and two rejected orders. The rejected orders print full stack traces: one for invalid text input and one for insufficient stock. Each order also prints an audit line from `finally`.

### Run `example.java`

This separate example reads two integers and divides the first by the second. From the same folder, compile and run it with:

```powershell
javac -d out example.java
java -cp out Day6.example
```

The class belongs to the `Day6` package, so run it using the fully qualified name `Day6.example`. When prompted, enter two integers, such as `10 2`; the program prints `5`. A zero divisor is handled by its `ArithmeticException` catch block.

## Copilot Chat Stack-Trace Review

Copy each rejected-order output, including its complete stack trace and every `Caused by` section, into Copilot Chat. Ask Copilot to explain where the error originated, how the cause chain works, which exception is checked or unchecked, and whether the audit runs. Record its answer below; do not omit or rewrite parts you disagree with.

### Stack trace 1: Invalid quantity

Paste this `SKU-200` stack trace into Copilot Chat:

```text
InvalidQuantityException: Quantity must be a whole number: two
	at OrderProcessor.parseQuantity(OrderProcessorDemo.java:33)
	at OrderProcessor.processOrder(OrderProcessorDemo.java:16)
	at OrderProcessorDemo.main(OrderProcessorDemo.java:6)
Caused by: java.lang.NumberFormatException: For input string: "two"
	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
	at java.base/java.lang.Integer.parseInt(Integer.java:662)
	at java.base/java.lang.Integer.parseInt(Integer.java:778)
	at OrderProcessor.parseQuantity(OrderProcessorDemo.java:31)
	... 2 more
```

Copilot's explanation:

`Integer.parseInt` cannot convert `two` and throws `NumberFormatException`. `parseQuantity` wraps that failure in `InvalidQuantityException`, preserving it as the cause. `InvalidQuantityException` is unchecked because it extends `RuntimeException`. The multi-catch handles it, and `finally` prints a failed-order audit.

Review the explanation:

- [x] Right: `InvalidQuantityException` is unchecked because it extends `RuntimeException`.
- [x] Right: its `NumberFormatException` appears as the chained cause.
- [x] Right: the audit runs even though the order was rejected.
- [x] Wrong or incomplete: saying the `NumberFormatException` is caught by the multi-catch would be incorrect; it is wrapped first, and the multi-catch catches `InvalidQuantityException`.

### Stack trace 2: Insufficient stock

Paste this `SKU-300` stack trace into Copilot Chat:

```text
InsufficientStockException: Insufficient stock for SKU-300: requested 8, available 5
	at OrderProcessor.reserveStock(OrderProcessorDemo.java:49)
	at OrderProcessor.processOrder(OrderProcessorDemo.java:17)
	at OrderProcessorDemo.main(OrderProcessorDemo.java:7)
Caused by: java.lang.IllegalStateException: Inventory rejected reservation: requested 8, available 5
	at OrderProcessor.reserveStock(OrderProcessorDemo.java:46)
	... 2 more
```

Copilot's explanation:

The reservation fails because 8 units were requested while only 5 are available. `reserveStock` throws the checked `InsufficientStockException`, retaining the lower-level `IllegalStateException` as its cause. The multi-catch handles this exception, and `finally` prints a failed-order audit.

Review the explanation:

- [x] Right: `InsufficientStockException` is checked because it extends `Exception` but not `RuntimeException`.
- [x] Right: the `IllegalStateException` appears as its chained cause.
- [x] Right: both exception types are handled by the multi-catch, and the audit still runs.
- [x] Wrong or incomplete: saying `IllegalStateException` is caught directly by the multi-catch would be incorrect; the caught exception is `InsufficientStockException`, which wraps it.

## What To Notice

- A chained cause preserves the lower-level failure; it does not make the outer exception checked or unchecked.
- The multi-catch alternatives are valid because neither exception type is a subtype of the other.
- `finally` runs for both successful and rejected orders, so every order gets an audit line.