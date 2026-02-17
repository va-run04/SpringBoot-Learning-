# Ex13SpringBootAutoConfig — Spring Boot Auto-Configuration

## What is Auto-Configuration?

In all previous projects, YOU defined every bean — either in XML or with annotations. Spring only created what you told it to.

**Spring Boot auto-configuration** looks at your classpath (what dependencies are in your pom.xml) and **automatically configures beans for you**. You added `spring-boot-starter`? Spring Boot creates 50+ internal beans without you writing a single bean definition.

---

## The Scenario

Same OrderService + NotificationService. But this time:
- No XML file
- No `@Configuration` class
- No `AnnotationConfigApplicationContext`
- Just `@SpringBootApplication` and `SpringApplication.run()` — Spring Boot handles everything

---

## Project Structure

```
Ex13SpringBootAutoConfig/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/example/demo/
    │   │       ├── Ex13SpringBootAutoConfigApplication.java  ← @SpringBootApplication
    │   │       └── service/
    │   │           ├── OrderService.java                     ← @Service
    │   │           └── NotificationService.java              ← @Service + @PostConstruct
    │   └── resources/
    │       └── application.properties                        ← app config
    └── test/
        └── (auto-generated test class)
```

---

## How to Run

1. Open the project in Eclipse (imported from start.spring.io)
2. Run `Ex13SpringBootAutoConfigApplication.java`
3. You should see:

```
NotificationService is ready!
Order placed for: Spring Boot Course
[EMAIL] Sending: Your order for Spring Boot Course has been confirmed!

==============================
Total beans created: 52
==============================

=== ALL BEAN NAMES ===
  ex13SpringBootAutoConfigApplication
  orderService
  notificationService
  org.springframework.context.annotation.internalConfigurationAnnotationProcessor
  ... (48+ more internal beans)
```

**52+ beans** — and you only wrote 3 classes!

---

## @SpringBootApplication — One Annotation Replaces Three

```java
@SpringBootApplication
// = @Configuration       → this class is a config class
// + @ComponentScan       → scan this package and sub-packages
// + @EnableAutoConfiguration → auto-configure beans based on classpath
```

| What you wrote manually in previous projects | @SpringBootApplication does it for you |
|---|---|
| `@Configuration` (Ex10 — JavaConfig.java) | Built-in |
| `@ComponentScan` (Ex10 — JavaConfig.java) | Built-in — scans `com.example.demo` and all sub-packages |
| `AnnotationConfigApplicationContext(JavaConfig.class)` | Replaced by `SpringApplication.run()` |
| Nothing | `@EnableAutoConfiguration` — NEW, auto-configures 50+ beans |

---

## How Auto-Configuration Works

1. `@EnableAutoConfiguration` (inside `@SpringBootApplication`) triggers it
2. Spring Boot reads a list of auto-config classes from its internal files
3. Each auto-config class has conditions like `@ConditionalOnClass`, `@ConditionalOnMissingBean`
4. If conditions are met → beans are created. If not → skipped.

**Example:** If you add `spring-boot-starter-web` to pom.xml later:
- Spring Boot detects `DispatcherServlet.class` on classpath → condition met
- Auto-configures: DispatcherServlet, embedded Tomcat, error handling
- You write zero configuration for any of this

---

## Debug Auto-Configuration

Add to `application.properties`:

```properties
debug=true
```

Run again — you'll see:

```
============================
CONDITIONS EVALUATION REPORT
============================

Positive matches:
   (what WAS auto-configured and why)

Negative matches:
   DataSourceAutoConfiguration:
      Did not match:
         @ConditionalOnClass did not find required class 'javax.sql.DataSource'
```

`DataSourceAutoConfiguration` was skipped because you don't have a database dependency. Add `spring-boot-starter-data-jpa` later and it will auto-configure `DataSource`, `EntityManagerFactory`, and `TransactionManager` automatically.

---

## application.properties

```properties
spring.application.name=Ex13SpringBootAutoConfig
```

- Central place for all configuration
- Spring Boot reads this automatically
- Can also use `application.yml` (YAML format — same thing)
- Properties can be injected with `@Value("${spring.application.name}")`

---

## Compare: How Far We've Come

| Project | Beans defined by | XML | Context creation | Auto-created beans |
|---|---|---|---|---|
| SpringIoCEx4 | `<bean>` tags in XML | Extensive | `ClassPathXmlApplicationContext` | 0 |
| Ex7StereotypeScan | `@Service`, `@Component` | One line | `ClassPathXmlApplicationContext` | 0 |
| Ex10ThreeApproaches | `@Service`, `@Component` | None (Approach 3) | `AnnotationConfigApplicationContext` | 0 |
| **Ex13 Spring Boot** | **`@Service`** | **None** | **`SpringApplication.run()`** | **50+** |

---

## The Full Journey — All 13 Projects

| # | Project | Key Concept |
|---|---|---|
| 01 | TightCouplingEx1 | `new` keyword locks classes together |
| 02 | TightCouplingEx2 | `extends` locks classes through inheritance |
| 03 | LooseCouplingEx3 | Interface + DI = swap without changing code |
| 04 | SpringIoCEx4 | Spring IoC Container takes over object creation |
| 05 | XMLConfigEx5 | XML deep dive — primitives, refs, p/c-namespace |
| 06 | Ex6Autowiring | 4 XML autowiring modes |
| 07 | Ex7StereotypeScan | @Service, @Component, component scanning |
| 08 | Ex8AutowiredQualifier | 3 injection styles, @Qualifier vs @Primary |
| 09 | Ex9BeanVsComponent | @Bean for third-party classes |
| 10 | Ex10ThreeApproaches | Pure XML → XML+Annotations → Java Config |
| 11 | Ex11BeanLifecycle | Complete lifecycle, @PostConstruct, @PreDestroy |
| 12 | Ex12BeanScopes | Singleton vs Prototype, @Lazy |
| 13 | Ex13SpringBootAutoConfig | Auto-configuration, bean count, debug mode |

**You started with `new Alpha()` and ended with `SpringApplication.run()`.** That's the complete Spring Core → Spring Boot journey.
