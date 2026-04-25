# Ex12BeanScopes — Bean Scopes (Singleton vs Prototype)

## What is Bean Scope?

Scope controls **how many instances** of a bean Spring creates and **how long they live**.

- **Singleton** (default) — ONE instance, shared everywhere
- **Prototype** — NEW instance every time you ask for it

---

## The Scenario

| Class | Scope | Why |
|---|---|---|
| `NotificationService` | Singleton | Stateless — one instance can serve everyone |
| `OrderRequest` | Prototype | Stateful — each order has its own ID, can't share |
| `HeavyReportService` | Singleton + @Lazy | Expensive to create, might not always be needed |

---

## Project Structure

```
Ex12BeanScopes/
├── pom.xml
└── src/main/
    ├── java/
    │   └── com/vk/
    │       ├── app/
    │       │   └── LaunchApp.java             ← runs all 3 demos
    │       └── service/
    │           ├── NotificationService.java   ← singleton (default)
    │           ├── OrderRequest.java          ← prototype (new every time)
    │           └── HeavyReportService.java    ← @Lazy (created on first use)
    └── (no XML file — using Java Config)
```

---

## How to Run

1. Open the project in Eclipse
2. Right click → Maven → Update Project
3. Run `LaunchApp.java`
4. You should see:

```
=== STARTING CONTAINER ===

[Singleton] NotificationService constructor called

=== SINGLETON TEST ===
n1 == n2? true
n1 hash: 12345678
n2 hash: 12345678
Singleton: ONE instance, shared everywhere

=== PROTOTYPE TEST ===
[Prototype] OrderRequest constructor called — new instance!
[Prototype] OrderRequest constructor called — new instance!
o1 == o2? false
o1 hash: 87654321
o2 hash: 11223344
o1 orderId: ORD-001
o2 orderId: ORD-002
Prototype: NEW instance every time

=== @LAZY TEST ===
Requesting HeavyReportService for the first time...
[Lazy] HeavyReportService constructor called — this is expensive!
[Lazy] Generating heavy report...
```

(Hash numbers will be different on your machine)

---

## What to Observe

### Singleton (NotificationService)
- Constructor called **once** — at container startup
- `n1 == n2` is `true` — both point to the **same object**
- Both have the **same hashcode**
- Every `getBean()` returns the same instance

### Prototype (OrderRequest)
- Constructor called **twice** — once for each `getBean()` call
- `o1 == o2` is `false` — they are **different objects**
- Different hashcodes
- `o1` has orderId `ORD-001`, `o2` has `ORD-002` — they don't share state

### @Lazy (HeavyReportService)
- Constructor was **NOT** called at container startup
- Constructor called only when you first request it with `getBean()`
- Use for expensive beans that might not always be needed

---

## Singleton vs Prototype

| | Singleton (default) | Prototype |
|---|---|---|
| **Instances** | ONE per Spring container | NEW one every `getBean()` call |
| **Created when** | Container startup (eager) | When requested |
| **Same object?** | `getBean() == getBean()` → `true` | `getBean() == getBean()` → `false` |
| **Spring manages lifecycle?** | Yes — including `@PreDestroy` | No — `@PreDestroy` does NOT work |
| **Use for** | Stateless services, repos, utilities | Stateful objects, user-specific data |

---

## @Lazy

```java
@Service
@Lazy
public class HeavyReportService { }
```

- Singleton beans are **eager** by default — created at container startup even if nobody uses them
- `@Lazy` says: "don't create me at startup, create me only when someone asks for me"
- Useful for beans that are expensive to create and might not always be needed

---

## Important: Prototype + @PreDestroy

`@PreDestroy` does **NOT** work for prototype beans.

Spring creates the prototype bean and hands it to you — but does NOT track it after that. When the container shuts down, it only cleans up **singleton** beans.

---

## Web-Aware Scopes (for awareness)

These exist in web applications only — you'll use them when you build Spring MVC apps:

| Scope | One instance per... |
|---|---|
| `request` | HTTP request |
| `session` | HTTP session |
| `application` | ServletContext |

---

## What's Next?

In the next project (**Spring Boot Auto-Configuration**), you'll create your first Spring Boot project. You'll see how `@SpringBootApplication` replaces `@Configuration` + `@ComponentScan` + `@EnableAutoConfiguration`, and how Spring Boot auto-configures 50+ beans without you writing a single bean definition.
