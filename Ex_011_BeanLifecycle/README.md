# Ex11BeanLifecycle — Bean Lifecycle (Complete Execution Order)

## What is Bean Lifecycle?

When Spring creates a bean, it doesn't just call `new` and hand it over. There's a **specific sequence of steps** that happen — from class loading to construction to dependency injection to initialization to destruction.

This project prints every step to the console so you can watch the full lifecycle in order.

---

## The Scenario

A `ReportService` that depends on a `DatabaseService`. When Spring creates `ReportService`:
1. JVM loads the class (static block)
2. Instance block runs
3. Constructor is called
4. `DatabaseService` is injected via setter
5. `@PostConstruct` init method runs
6. Bean is ready — business methods can execute
7. On shutdown — `@PreDestroy` cleanup runs

---

## Project Structure

```
Ex11BeanLifecycle/
├── pom.xml
└── src/main/
    ├── java/
    │   └── com/vk/
    │       ├── app/
    │       │   └── LaunchApp.java         ← @Configuration + runs the app
    │       └── service/
    │           ├── ReportService.java     ← full lifecycle demo
    │           └── DatabaseService.java   ← dependency (injected into ReportService)
    └── (no XML file — using Java Config)
```

---

## How to Run

1. Open the project in Eclipse
2. Right click → Maven → Update Project
3. Run `LaunchApp.java`
4. You should see:

```
=== STARTING SPRING CONTAINER ===

1. Static block — class loaded by JVM
2. Instance block — runs before constructor
3. Constructor — Spring is creating ReportService
  [DatabaseService] Constructor called
4. Setter called — Spring injected DatabaseService
5. @PostConstruct — all dependencies ready, running init logic
   Loading config, validating state, opening connections...

=== BEAN IS READY — CALLING BUSINESS METHOD ===

6. ★ Business method — generating report
   Data: Data from database

=== SHUTTING DOWN CONTAINER ===

7. @PreDestroy — cleaning up before shutdown
   Closing connections, flushing caches, releasing resources...

=== CONTAINER CLOSED ===
```

Every step prints in sequence — watch the order carefully.

---

## The Complete Bean Lifecycle Order

```
1. Static block         → JVM loads the class (before Spring touches it)
2. Instance block       → Java instance initializer runs
3. Constructor          → Spring calls the constructor to create the bean
4. Dependency Injection → Spring injects @Autowired dependencies
5. @PostConstruct       → Your init code runs AFTER all DI is complete
   ─── BEAN IS READY ───
6. Business methods     → Your actual app logic executes
   ─── CONTAINER SHUTTING DOWN ───
7. @PreDestroy          → Your cleanup code runs BEFORE bean is destroyed
8. Bean garbage collected
```

---

## @PostConstruct

```java
@PostConstruct
public void init() { }
```

- Runs **once**, **after** all dependencies are injected
- Use for: loading config, opening connections, validating state, pre-loading data
- Why not use the constructor? Because in the constructor, dependencies might NOT be injected yet (if using setter/field injection). `@PostConstruct` runs AFTER everything is wired.

---

## @PreDestroy

```java
@PreDestroy
public void cleanup() { }
```

- Runs when the ApplicationContext is shutting down
- Use for: closing DB connections, releasing file handles, flushing caches
- **Important:** In a console app, you **MUST** call `ctx.close()` for this to fire. In a web app, the server handles shutdown automatically.

---

## New Dependency in This Project

```xml
<dependency>
    <groupId>jakarta.annotation</groupId>
    <artifactId>jakarta.annotation-api</artifactId>
    <version>3.0.0</version>
</dependency>
```

`@PostConstruct` and `@PreDestroy` are in the `jakarta.annotation` package. This dependency is needed because they're not part of `spring-context` itself.

---

## No XML File

This project uses **Java Config** (Approach 3 from Ex10):
- `@Configuration` on `LaunchApp` replaces XML
- `@ComponentScan("com.vk")` replaces `<context:component-scan>`
- `AnnotationConfigApplicationContext` replaces `ClassPathXmlApplicationContext`

---

## Other Ways to Achieve Init/Destroy (for awareness)

| Mechanism | Init | Destroy |
|---|---|---|
| **Annotations (preferred)** | `@PostConstruct` | `@PreDestroy` |
| **XML attributes** | `init-method="init"` | `destroy-method="cleanup"` |
| **Interfaces (old way)** | `InitializingBean.afterPropertiesSet()` | `DisposableBean.destroy()` |

Annotations are the modern, preferred way.

---

## What's Next?

In the next project (**Bean Scopes**), you'll learn the difference between **Singleton** and **Prototype** beans. Singleton = one instance shared everywhere. Prototype = new instance every time. You'll prove it by comparing hash codes.
