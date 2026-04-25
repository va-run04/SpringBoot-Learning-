# Ex9BeanVsComponent — @Bean vs @Component (When to Use Which)

## What Problem Does This Project Solve?

In all previous projects, you put `@Service` or `@Component` on your classes and Spring discovered them. That works when you **own the source code**.

But what about classes you **don't own**?

- You can't open `java.time.LocalTime` and add `@Component` to it
- You can't edit `javax.sql.DataSource`
- You can't modify any third-party library class

**`@Bean` solves this.** You write a method that creates and returns the object. Spring registers the returned object as a bean.

---

## The Scenario

- `GreetingService` — your class that generates "Good Morning/Afternoon/Evening" based on the current time
- `LocalTime` — Java library class (you don't own it) that provides the current hour
- `Password` — simulates a third-party class that needs `"SHA-256"` passed to its constructor

`GreetingService` needs `LocalTime` injected into it. But you can't put `@Component` on `LocalTime`. So you create it with `@Bean` inside a `@Configuration` class.

---

## Project Structure

```
Ex9BeanVsComponent/
├── pom.xml
└── src/main/
    ├── java/
    │   └── com/vk/
    │       ├── app/
    │       │   └── LaunchApp.java         ← runs the app
    │       ├── config/
    │       │   └── AppConfig.java         ← @Configuration with @Bean methods
    │       ├── greeting/
    │       │   └── GreetingService.java   ← @Service (your class)
    │       └── security/
    │           └── Password.java          ← simulates third-party class (no annotation)
    └── resources/
        └── applicationconfig.xml          ← just component-scan
```

---

## How to Run

1. Open the project in Eclipse
2. Right click → Maven → Update Project
3. Run `LaunchApp.java`
4. You should see (varies by time of day):

```
Good Afternoon!
Password algorithm: SHA-256
Current time: 14:35:22.123456
```

---

## How It All Connects

```
AppConfig (@Configuration)
  ├── @Bean currentTime()     → creates LocalTime.now()          → registered as bean
  └── @Bean passwordEncoder() → creates new Password("SHA-256")  → registered as bean

GreetingService (@Service)
  └── @Autowired LocalTime time  → Spring injects the currentTime bean
      └── uses time.getHour() to decide morning/afternoon/evening

LaunchApp
  └── getBean(GreetingService.class) → fully wired, ready to use
  └── getBean(Password.class)       → created with SHA-256
```

1. Spring scans `com.vk` → finds `@Service` on `GreetingService` and `@Configuration` on `AppConfig`
2. Spring sees `@Bean` methods in `AppConfig` → calls `currentTime()` and `passwordEncoder()`
3. `LocalTime` and `Password` objects are now registered as beans
4. Spring sees `@Autowired` on `GreetingService.time` field → injects the `LocalTime` bean
5. Everything is wired — you just call `getBean()` and use it

---

## @Bean vs @Component — When to Use Which

| | @Component / @Service | @Bean |
|---|---|---|
| **Put it on** | Your class declaration | A method inside `@Configuration` class |
| **Use when** | **You OWN the source code** | **You DON'T own the code** (third-party) |
| **Discovery** | Automatic via component scanning | Spring calls the method explicitly |
| **Bean name** | Class name, first letter lowercase | Method name |
| **Construction** | Spring calls default constructor | You control — any logic you want |
| **Examples** | Your services, repos, controllers | `LocalTime`, `DataSource`, `RestTemplate`, `ObjectMapper` |

---

## Why @Bean Is Needed — Two Reasons

### Reason 1: You don't own the class

```java
// You can't do this — you don't own java.time.LocalTime:
@Component   // ← IMPOSSIBLE, you can't edit this file
public class LocalTime { ... }

// So you use @Bean instead:
@Bean
public LocalTime currentTime() {
    return LocalTime.now();
}
```

### Reason 2: Custom construction logic

```java
// @Component can only call the default constructor
// But Password needs "SHA-256" passed to its constructor

@Bean
public Password passwordEncoder() {
    return new Password("SHA-256");  // you control how it's created
}
```

---

## What is @Configuration?

`@Configuration` is also a `@Component` underneath — so Spring registers it as a bean too.

But it has a special job: it tells Spring **"this class contains `@Bean` method definitions."**

Spring creates a special proxy of this class to make sure `@Bean` methods return the **same singleton instance** even if called multiple times.

---

## Compare with Previous Projects

| Project | How beans are created |
|---|---|
| SpringIoCEx4 | `<bean>` tags in XML |
| Ex7StereotypeScan | `@Service`, `@Component` on your classes |
| Ex8AutowiredQualifier | `@Service`, `@Component` + `@Qualifier` |
| **Ex9BeanVsComponent** | **`@Service` for your classes + `@Bean` for third-party classes** |

---

## What's Next?

In the next project (**The 4 Configuration Approaches**), you'll see the same app configured in 4 different ways — Pure XML, XML + Annotations, Java Config (zero XML), and Spring Boot — to understand the full evolution of Spring configuration.
