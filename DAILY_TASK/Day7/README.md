# Day 7 - Library Waitlist

This demo uses Java collections for a small library waitlist.

## Run

From the project root in PowerShell:

```powershell
javac -d out DAILY_TASK/Day7/LibraryWaitlistDemo.java
java -cp out Day7.LibraryWaitlistDemo
```

## Concepts

| Concept | Purpose |
| --- | --- |
| `LinkedHashSet` | Stores unique book titles and removes duplicates. |
| `ArrayList` | Stores the library book catalogue. |
| `ArrayDeque` | Manages the waiting list in FIFO (First In, First Out) order. |
| `PriorityQueue` | Gives faculty requests priority over student requests. |
| `Iterator.remove()` | Safely removes overdue books while iterating through a list. |
| `ArrayList` vs `LinkedList` | Compares the time taken for middle insertions and indexed reads. |
| `System.nanoTime()` | Measures execution time in nanoseconds. |

It demonstrates a de-duplicated book catalogue, a first-in-first-out waitlist, faculty-first requests, removal of overdue loans with an iterator, and a basic `ArrayList` versus `LinkedList` timing comparison.