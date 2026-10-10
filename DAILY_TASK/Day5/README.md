# Day 5 — Payment Hierarchy

This exercise demonstrates abstract classes, interfaces, inheritance, method overriding, and method overloading.

## Concepts

- `Payment` is an abstract base class that validates and stores the amount.
- `CardPayment`, `UpiPayment`, and `CashPayment` extend `Payment` and override `processPayment()`.
- `CardPayment` and `UpiPayment` implement the `Refundable` interface. Cash payments are not refundable in this example.
- `PaymentProcessor` overloads `pay()` with one-argument and two-argument versions.
- `Payment[]` demonstrates polymorphism: the processor calls the correct overridden method for each payment type.

## Run

From the repository root in PowerShell:

```powershell
javac -d out DAILY_TASK/Day5/PaymentDemo.java
java -cp out PaymentDemo
```
