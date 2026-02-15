# 01a - Tight Coupling (using `new` keyword)

## What is Tight Coupling?

When one class **directly creates** another class inside itself using the `new` keyword, the two classes become locked together. You can't swap, replace, or change the dependency without opening the class and rewriting it.

> **Note:** Tight coupling can also happen through **inheritance (`extends`)**. That's covered in another example.

---

## The Scenario

An online store has an `OrderService` that sends a notification after every order.

Right now, `OrderService` creates `EmailNotification` directly inside itself:

```java
EmailNotification notification = new EmailNotification();
```

It works — but it's locked to email **forever**.

---

## Project Structure

```
01-TightCoupling-NewKeyword/
└── src/
    ├── main/
    │   └── Main.java              ← runs the app
    └── com/tight/
        ├── EmailNotification.java ← sends email notifications
        └── OrderService.java      ← places orders (tightly coupled)
```

---

## How to Run

1. Open the project in Eclipse
2. Run `Main.java`
3. You should see:

```
Order placed for: Spring Boot Course
[EMAIL] sending: Your order for Spring Boot Coursehas been confirmed!
```

---

## The Problem

| Client Request | What You Have To Do |
|---|---|
| "I want SMS instead of email" | Open `OrderService.java`, delete `EmailNotification`, write `SmsNotification` |
| "I want WhatsApp notifications" | Open `OrderService.java` again, change the code again |
| `send()` method now needs a subject line | `OrderService` breaks, you have to fix it |

Every new requirement forces you to **go inside `OrderService` and rewrite it.**

---

## The Root Cause

```java
EmailNotification notification = new EmailNotification();
```

This one line is the problem. `OrderService` **chose its own dependency**. It's permanently locked to `EmailNotification`.

`OrderService` should only care about **placing orders** — not about HOW notifications are sent.

---

## What's Next?

In **Loose Coupling project**, we fix this by using an **interface**. `OrderService` will depend on a `NotificationService` contract — not a specific class. Then we can swap email, SMS, WhatsApp without ever opening `OrderService.java`.
