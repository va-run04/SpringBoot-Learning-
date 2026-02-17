# Ex7StereotypeScan — Stereotype Annotations + Component Scanning

## What Changed from Previous Projects?

In SpringIoCEx4 and Ex6Autowiring, you wrote a `<bean>` tag in XML for **every single class**. That works for 5 classes, but not for 500.

In this project, you put **annotations directly on your Java classes** and tell Spring to **scan and discover** them automatically. The entire XML configuration shrinks to **one line**:

```xml
<context:component-scan base-package="com.vk"/>
```

No more `<bean>` tags. Spring finds everything on its own.

---

## The Scenario

Same OrderService + NotificationService. Same logic. But now:
- Classes have `@Service` and `@Component` annotations
- Spring **discovers** them instead of you listing them in XML
- `@Autowired` tells Spring to inject the dependency
- `@Primary` tells Spring which bean to pick when two match

---

## Project Structure

```
Ex7StereotypeScan/
├── pom.xml
└── src/main/
    ├── java/
    │   └── com/vk/
    │       ├── app/
    │       │   └── LaunchApp.java             ← runs the app (no annotation)
    │       ├── order/
    │       │   └── OrderService.java          ← @Component + @Autowired
    │       └── notification/
    │           ├── NotificationService.java   ← interface (no annotation)
    │           ├── EmailNotification.java     ← @Service + @Primary
    │           └── SmsNotification.java       ← @Service
    └── resources/
        └── applicationconfig.xml              ← just one line: component-scan
```

---

## How to Run

1. Open the project in Eclipse
2. Right click → Maven → Update Project
3. Run `LaunchApp.java`
4. You should see:

```
Order placed for: Spring Boot Course
[EMAIL] sending: Your order for Spring Boot Course has been confirmed!
```

Spring picked `EmailNotification` because it has `@Primary`.

---

## What Spring Does When You Run It

1. Reads `applicationconfig.xml` → sees `<context:component-scan base-package="com.vk"/>`
2. Scans `com.vk` and ALL sub-packages:
   - `com.vk.notification` → finds `@Service` on `EmailNotification` and `SmsNotification` → creates both beans
   - `com.vk.order` → finds `@Component` on `OrderService` → creates bean
   - `com.vk.app` → `LaunchApp` has no annotation → skipped
3. Sees `@Autowired` on `OrderService.notification` field
4. Two beans match `NotificationService` type → sees `@Primary` on `EmailNotification` → picks it
5. Injects `EmailNotification` into `OrderService`
6. You call `getBean()` → get the fully wired `OrderService`

---

## Annotations Used in This Project

| Annotation | Where | What it does |
|---|---|---|
| `@Service` | `EmailNotification`, `SmsNotification` | Tells Spring: "this is a bean — register it" |
| `@Component` | `OrderService` | Same as `@Service` — tells Spring to register it |
| `@Primary` | `EmailNotification` | When two beans match the same type, pick this one |
| `@Autowired` | `OrderService.notification` field | Tells Spring: "inject a matching bean here" |

---

## @Service vs @Component — What's the Difference?

They do the **same thing**. Both tell Spring: "this class is a bean, register it."

The difference is just the **name** — it tells other developers what layer the class belongs to:

| Annotation | Use for | Special behavior |
|---|---|---|
| `@Component` | Generic — any Spring bean | None |
| `@Service` | Business logic classes | None (just a label) |
| `@Repository` | Data access classes (database) | Auto DB exception translation |
| `@Controller` | Web/HTTP request handling | Enables `@RequestMapping` |
| `@RestController` | REST API endpoints | `@Controller` + `@ResponseBody` |
| `@Configuration` | Classes with `@Bean` methods | CGLIB proxy for bean creation |

They are ALL `@Component` underneath. Open the source code of `@Service` and you'll see `@Component` on it.

---

## Why @Primary is Needed Here

Both `EmailNotification` and `SmsNotification` implement `NotificationService`. When `@Autowired` asks for a `NotificationService` bean, Spring finds **two** matches.

Without `@Primary` → `NoUniqueBeanDefinitionException` (Spring can't choose)

With `@Primary` on `EmailNotification` → Spring picks email as the default.

In the next project, you'll learn `@Qualifier` — which lets you pick a **specific** bean by name instead of relying on `@Primary`.

---

## Bean Naming

Spring creates a default bean name from the class name (first letter lowercase):

| Class name | Bean name |
|---|---|
| `EmailNotification` | `emailNotification` |
| `SmsNotification` | `smsNotification` |
| `OrderService` | `orderService` |

You can customize it: `@Service("myEmail")` → bean name becomes `"myEmail"`

---

## Component Scanning — Important Rule

```xml
<context:component-scan base-package="com.vk"/>
```

Spring scans `com.vk` and **all sub-packages**:
- `com.vk.notification` → scanned ✓
- `com.vk.order` → scanned ✓
- `com.vk.app` → scanned ✓

But anything **outside** `com.vk` is **not** scanned:
- `in.vk.notification` → NOT scanned ✗
- `org.vk.order` → NOT scanned ✗

---

## Compare Across Projects — XML Shrinking

| Project | How beans are defined | XML size |
|---|---|---|
| SpringIoCEx4 | `<bean>` tag for every class + `ref` | Big |
| XMLConfigEx5 | `<bean>` tag + `<property>` / `<constructor-arg>` | Big |
| Ex6Autowiring | `<bean>` tag + `autowire` attribute | Medium |
| **Ex7StereotypeScan** | **`@Service`, `@Component` on classes** | **One line** |

---

## What's Next?

This project uses `@Autowired` on a **field** (field injection) and `@Primary` to resolve ambiguity.

In the next project (**@Autowired, @Qualifier, @Primary**), you'll explore:
- **3 injection styles**: field, constructor, setter
- **`@Qualifier`**: pick a specific bean by name (overrides `@Primary`)
- **Precedence**: `@Qualifier` > `@Primary` > byType
