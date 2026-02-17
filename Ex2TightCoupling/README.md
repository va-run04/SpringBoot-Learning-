#Tight Coupling Ex2 (using `extends`)

## What is Tight Coupling through Inheritance?

When one class **extends** another class, it permanently becomes a type of that class. The two classes are locked together through the parent-child relationship. You can't undo `extends` at runtime — it's decided when you write the code and stays that way forever.

> **Note:** Tight coupling through the `new` keyword is covered in previous example i.e tightcouplingex1

---

## The Scenario

Same online store, same `OrderService`, same `EmailNotification`.

But this time, instead of creating `EmailNotification` with `new`, `OrderService` **extends** it:

```java
public class OrderService extends EmailNotification {
```

This means `OrderService` **IS-A** `EmailNotification`. It inherited the `send()` method directly.

---

## IS-A vs HAS-A — The Key Idea

When you write `extends`, you're saying **"this class IS A type of that class"**:

- `class Dog extends Animal` → "A dog **is a** animal" ✓ Makes sense
- `class OrderService extends EmailNotification` → "An order service **is a** email notification" ✗ Makes no sense!

An order service **places orders**. It's not a notification. It just **needs to use** one.

---

## Project Structure

```
01-TightCoupling-Inheritance/
└── src/
    ├── main/
    │   └── Main.java              ← runs the app
    └── com/tight/
        ├── EmailNotification.java ← sends email notifications
        └── OrderService.java      ← extends EmailNotification (tightly coupled)
```

---

## How to Run

1. Open the project in Eclipse
2. Run `Main.java`
3. You should see:

```
Order placed for: Spring Boot Course
[EMAIL] Sending: Your order for Spring Boot Course has been confirmed!
```

---

## The Problem

| Client Request | What Happens |
|---|---|
| "I want SMS instead of email" | Can't swap. `OrderService` is BOUND to `EmailNotification` through `extends`. You'd have to rewrite `OrderService` to extend `SmsNotification` instead. |
| "I want both email AND SMS" | Impossible. Java only allows ONE parent class (single inheritance). |
| `EmailNotification` adds a new method | `OrderService` inherits it automatically — even if it doesn't need it. |
| You want `OrderService` to extend another class | Can't. It already extends `EmailNotification`. Slot is taken. |

---

## The Root Cause

```java
public class OrderService extends EmailNotification {
```

This one line is the problem. `OrderService` **became** `EmailNotification`. It inherited everything — locked permanently.

---

## Two Ways Tight Coupling Happens — Summary

| Type | The Lock | Example |
|---|---|---|
| `new` keyword (Example 01a) | Class creates its own dependency inside itself | `EmailNotification n = new EmailNotification();` |
| `extends` (Example 01b) | Class permanently inherits from one specific class | `class OrderService extends EmailNotification` |

Both make it impossible to swap or change the dependency without rewriting the class.

---

## Composition over Inheritance

There are two ways a class can use another class:

- **Inheritance** (`extends`) → "I **AM** that class" → locked forever
- **Composition** (field) → "I **HAVE** that class" → can swap anytime

```
Don't BECOME the thing. Just HOLD the thing.
That way you can always swap it later.
```

---

## What's Next?

In **Loose Coupling Project**, we use **composition + interface**. `OrderService` will HOLD a `NotificationService` reference — not BECOME one. Then we can pass email, SMS, or WhatsApp from outside without ever touching `OrderService`.
