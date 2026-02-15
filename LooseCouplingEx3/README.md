# LooseCouplingEx3 — Loose Coupling using Interface + Dependency Injection

## What is Loose Coupling?

When a class depends on an **interface** instead of a specific class, and the dependency is **passed from outside** instead of created inside — the classes are loosely coupled. You can swap, replace, or add new implementations without ever opening the class that uses them.

---

## The Scenario

Same online store, same `OrderService`, same notification problem from TightCouplingEx1 and TightCouplingEx2.

But this time:
- `OrderService` depends on a `NotificationService` **interface** — not `EmailNotification` directly
- The notification type is **passed from outside** (through constructor or setter)
- `OrderService` has **no idea** whether it's sending email, SMS, or WhatsApp

---

## How It Solves the Problems from TightCouplingEx1 & TightCouplingEx2

| Problem (from Ex1 & Ex2) | How Loose Coupling Fixes It |
|---|---|
| Client wants SMS instead of email | Just pass `new SmsNotification()` from `Main.java`. Done. |
| Client wants WhatsApp notifications | Just pass `new WhatsAppNotification()`. Done. |
| `send()` method signature changes in one class | Other notification classes are not affected |
| Locked through `new` keyword (Ex1) | No `new` keyword inside `OrderService` — dependency comes from outside |
| Locked through `extends` (Ex2) | No `extends` — `OrderService` HOLDS a notification, doesn't BECOME one |

**`OrderService.java` was NEVER opened, NEVER changed.** That's the power of loose coupling.

---

## Two Things Working Together

Loose coupling needs **two things** — not just one:

| Concept | What It Solves | Without It |
|---|---|---|
| **Interface** | Makes the field type flexible — accepts any class that has `send()` | Field is locked to one specific class |
| **Dependency Injection** | Passes the dependency from outside instead of creating inside | Class still creates its own dependency with `new` |

You need **both**. Interface alone is not enough. DI alone is not enough.

---

## Project Structure

```
LooseCouplingEx3/
└── src/
    ├── main/
    │   └── Main.java                    ← creates dependencies and passes them in
    └── com/loose/
        ├── NotificationService.java     ← interface (the contract)
        ├── EmailNotification.java       ← implementation 1
        ├── SmsNotification.java         ← implementation 2
        ├── WhatsAppNotification.java    ← implementation 3
        └── OrderService.java            ← depends on interface, not specific class
```

---

## How to Run

1. Open the project in Eclipse
2. Run `Main.java`
3. You should see:

```
Order placed for: Spring Boot Course
[EMAIL] Sending: Your order for Spring Boot Course has been confirmed!

Order placed for: Java Course
[SMS] Sending: Your order for Java Course has been confirmed!

Order placed for: React Course
[WHATSAPP] Sending: Your order for React Course has been confirmed!
```

Same `OrderService` — three different notification types. No code change inside `OrderService`.

---

## What's Happening in Main.java

```java
// Constructor injection — pass Email at creation time
OrderService service = new OrderService(new EmailNotification());
service.placeOrder("Spring Boot Course");

// Setter injection — swap to SMS without touching OrderService
service.setNotification(new SmsNotification());
service.placeOrder("Java Course");

// Swap to WhatsApp — again, OrderService code didn't change
service.setNotification(new WhatsAppNotification());
service.placeOrder("React Course");
```

`Main.java` is the one creating dependencies and passing them in. This is **dependency injection by hand** — you (in `main()`) are manually deciding what to pass.

---

## The Remaining Problem

It works — but someone still has to write `new EmailNotification()` somewhere. Right now `main()` is doing this job manually.

In a real app with hundreds of classes, you can't wire everything by hand in `main()`.

**→ Spring IoC Container will take over this job. You just define what exists — Spring creates and wires everything automatically.**

---

## Quick Comparison Across All 3 Projects

| Project | How OrderService gets its dependency | Can you swap? | Have to change OrderService? |
|---|---|---|---|
| **TightCouplingEx1** (`new` keyword) | Creates `EmailNotification` inside itself | No | Yes — every time |
| **TightCouplingEx2** (`extends`) | Inherits from `EmailNotification` | No | Yes — every time |
| **LooseCouplingEx3** (interface + DI) | Receives `NotificationService` from outside | Yes — email, SMS, WhatsApp, anything | **Never** |
