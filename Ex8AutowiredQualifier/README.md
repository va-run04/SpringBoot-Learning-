# Ex8AutowiredQualifier — @Autowired, @Qualifier, @Primary (3 Injection Styles)

## What This Project Covers

In Ex7StereotypeScan, you used `@Autowired` on a **field** and `@Primary` to pick the default bean. That's just one injection style.

This project shows all **3 ways to use `@Autowired`** and introduces `@Qualifier` — which lets you pick a **specific bean by name**, overriding `@Primary`.

---

## The Scenario

Same notification system. But now there are **3 notification types** (Email, SMS, WhatsApp) and **3 OrderService classes** — one for each injection style. Each gets a different notification to show how `@Primary` and `@Qualifier` work.

---

## Project Structure

```
Ex8AutowiredQualifier/
├── pom.xml
└── src/main/
    ├── java/
    │   └── com/vk/
    │       ├── app/
    │       │   └── LaunchApp.java                 ← runs all 3 demos
    │       ├── order/
    │       │   ├── OrderServiceField.java         ← field injection
    │       │   ├── OrderServiceConstructor.java   ← constructor injection
    │       │   └── OrderServiceSetter.java        ← setter injection
    │       └── notification/
    │           ├── NotificationService.java       ← interface
    │           ├── EmailNotification.java         ← @Service + @Primary
    │           ├── SmsNotification.java           ← @Service
    │           └── WhatsAppNotification.java      ← @Service
    └── resources/
        └── applicationconfig.xml                  ← just component-scan
```

---

## How to Run

1. Open the project in Eclipse
2. Right click → Maven → Update Project
3. Run `LaunchApp.java`
4. You should see:

```
=== FIELD INJECTION ===
[Field Injection]
Order placed for: Spring Boot Course
[EMAIL] Sending: Your order for Spring Boot Course has been confirmed!

=== CONSTRUCTOR INJECTION ===
[Constructor Injection + @Qualifier]
Order placed for: Java Course
[SMS] Sending: Your order for Java Course has been confirmed!

=== SETTER INJECTION ===
[Setter Injection + @Qualifier]
Order placed for: React Course
[WHATSAPP] Sending: Your order for React Course has been confirmed!
```

Same interface, 3 different notification types — depending on injection style and `@Qualifier`.

---

## 3 Injection Styles Explained

### 1. Field Injection (NOT recommended)

```java
@Autowired
private NotificationService notification;
```

- `@Autowired` directly on the field
- Shortest code — but NOT recommended
- Can't make field `final`, hides dependencies, hard to test
- Gets `@Primary` bean (`EmailNotification`) because no `@Qualifier` specified

### 2. Constructor Injection (RECOMMENDED)

```java
private final NotificationService notification;

public OrderServiceConstructor(@Qualifier("smsNotification") NotificationService notification) {
    this.notification = notification;
}
```

- Dependency passed through the constructor
- Field can be `final` → immutable
- Dependencies are explicit — you can see them in the constructor
- `@Autowired` is **optional** when there's only one constructor (Spring 4.3+)
- `@Qualifier("smsNotification")` overrides `@Primary` → picks SMS

### 3. Setter Injection (for optional dependencies)

```java
@Autowired
@Qualifier("whatsAppNotification")
public void setNotification(NotificationService notification) {
    this.notification = notification;
}
```

- Spring calls the setter method after creating the object
- Use when dependency is optional or needs to change at runtime
- `@Qualifier("whatsAppNotification")` overrides `@Primary` → picks WhatsApp

---

## @Primary vs @Qualifier

| Annotation | What it does | Where you put it |
|---|---|---|
| `@Primary` | Default choice when multiple beans match the same type | On the class (`EmailNotification`) |
| `@Qualifier("beanName")` | Picks a specific bean by its name — overrides `@Primary` | Next to `@Autowired` (on field, constructor param, or setter) |

---

## Precedence — What Wins?

| Priority | Mechanism | What happens |
|---|---|---|
| 1st (highest) | `@Qualifier` | Always wins — picks exact bean by name |
| 2nd | `@Primary` | Wins when no `@Qualifier` is present |
| 3rd (lowest) | byType | Fails if multiple beans match the same type |

This is why:
- `OrderServiceField` → no `@Qualifier` → `@Primary` wins → **Email**
- `OrderServiceConstructor` → `@Qualifier("smsNotification")` → overrides `@Primary` → **SMS**
- `OrderServiceSetter` → `@Qualifier("whatsAppNotification")` → overrides `@Primary` → **WhatsApp**

---

## 3 Styles — Comparison

| Style | Where @Autowired goes | Supports final? | Recommended? | When to use |
|---|---|---|---|---|
| Field | On the field | No | No | Avoid — only for quick prototyping |
| Constructor | On the constructor | Yes | **Yes — best practice** | Mandatory dependencies |
| Setter | On the setter method | No | Sometimes | Optional dependencies |

---

## Bean Names Used by @Qualifier

Spring creates bean names from class names (first letter lowercase):

| Class | Bean name (use in @Qualifier) |
|---|---|
| `EmailNotification` | `"emailNotification"` |
| `SmsNotification` | `"smsNotification"` |
| `WhatsAppNotification` | `"whatsAppNotification"` |

---

## Compare with Ex7StereotypeScan

| | Ex7StereotypeScan | Ex8AutowiredQualifier |
|---|---|---|
| Injection styles shown | Field only | Field + Constructor + Setter |
| Ambiguity resolution | `@Primary` only | `@Primary` + `@Qualifier` |
| Notification types | 2 (Email, SMS) | 3 (Email, SMS, WhatsApp) |
| Key learning | Component scanning replaces XML | Which injection style to use and when |

---

## What's Next?

In the next project (**@Bean vs @Component**), you'll learn what to do when you **don't own** the source code of a class. You can't put `@Component` on `java.time.LocalTime` or `javax.sql.DataSource` — so you use `@Bean` methods inside a `@Configuration` class instead.
