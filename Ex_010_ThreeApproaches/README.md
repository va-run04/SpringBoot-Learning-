# Ex10ThreeApproaches — The 3 Configuration Approaches

## What This Project Shows

The same app, configured **3 different ways**. Same output, same logic — only HOW you tell Spring about your beans changes.

This is the evolution of Spring configuration:

```
Pure XML  →  XML + Annotations  →  Java Config (Zero XML)
 heavy          lighter              no XML at all
```

---

## The Scenario

Same OrderService + NotificationService. Same `placeOrder()` logic. But 3 different launch classes, each using a different configuration style.

---

## Project Structure

```
Ex10ThreeApproaches/
├── pom.xml
└── src/main/
    ├── java/
    │   └── com/vk/
    │       ├── app/
    │       │   ├── LaunchAppPureXml.java          ← Approach 1
    │       │   ├── LaunchAppXmlAnnotations.java   ← Approach 2
    │       │   └── LaunchAppJavaConfig.java        ← Approach 3
    │       ├── config/
    │       │   └── JavaConfig.java                 ← @Configuration (Approach 3)
    │       ├── order/
    │       │   └── OrderService.java               ← @Component
    │       └── notification/
    │           ├── NotificationService.java        ← interface
    │           └── EmailNotification.java          ← @Service
    └── resources/
        ├── approach1-pure-xml.xml                  ← Approach 1 config
        └── approach2-xml-annotations.xml           ← Approach 2 config
```

---

## How to Run

1. Open the project in Eclipse
2. Right click → Maven → Update Project
3. Run each launch class one by one:

| Run this class | What it demonstrates |
|---|---|
| `LaunchAppPureXml.java` | Approach 1: Pure XML |
| `LaunchAppXmlAnnotations.java` | Approach 2: XML + Annotations |
| `LaunchAppJavaConfig.java` | Approach 3: Java Config (Zero XML) |

All three produce the same output:

```
Order placed for: Spring Boot Course
[EMAIL] Sending: Your order for Spring Boot Course has been confirmed!
```

---

## The 3 Approaches Explained

### Approach 1: Pure XML (like SpringIoCEx4)

```xml
<bean id="email" class="com.vk.notification.EmailNotification"/>
<bean id="orderService" class="com.vk.order.OrderService">
    <property name="notification" ref="email"/>
</bean>
```

- Every bean manually defined in XML
- Every dependency manually wired with `ref`
- Verbose — imagine doing this for 500 classes
- No compile-time safety — typo in XML = runtime error

### Approach 2: XML + Annotations (like Ex7StereotypeScan)

```xml
<context:component-scan base-package="com.vk"/>
```

- `@Service` and `@Component` on your classes
- XML shrinks to ONE line — just component-scan
- Spring discovers and wires everything
- Still need an XML file though

### Approach 3: Java Config — Zero XML

```java
@Configuration
@ComponentScan(basePackages = {"com.vk"})
public class JavaConfig { }
```

- `@Configuration` class replaces the XML file entirely
- `@ComponentScan` replaces `<context:component-scan>`
- `AnnotationConfigApplicationContext` replaces `ClassPathXmlApplicationContext`
- **No XML files at all** — everything is in Java

---

## The Key Differences

| | Approach 1: Pure XML | Approach 2: XML + Annotations | Approach 3: Java Config |
|---|---|---|---|
| **Bean definition** | `<bean>` tags in XML | `@Service`, `@Component` on classes | `@Service`, `@Component` on classes |
| **XML needed?** | Extensive | One line (component-scan) | **None** |
| **Context creation** | `ClassPathXmlApplicationContext` | `ClassPathXmlApplicationContext` | `AnnotationConfigApplicationContext` |
| **Compile-time safe?** | No | Partial | **Yes** |
| **Used in previous projects** | SpringIoCEx4, XMLConfigEx5 | Ex7StereotypeScan, Ex8 | New in this project |

---

## What Replaced What

| XML | Replaced by (Approach 3) |
|---|---|
| `applicationconfig.xml` file | `JavaConfig.java` class with `@Configuration` |
| `<context:component-scan base-package="com.vk"/>` | `@ComponentScan(basePackages = {"com.vk"})` |
| `ClassPathXmlApplicationContext("config.xml")` | `AnnotationConfigApplicationContext(JavaConfig.class)` |

---

## Compare Across All Projects

| Project | Configuration style | XML size |
|---|---|---|
| SpringIoCEx4 | Pure XML | Big — every bean + every ref |
| XMLConfigEx5 | Pure XML | Big — properties + constructor-args |
| Ex6Autowiring | Pure XML + autowire | Medium — no ref but still `<bean>` tags |
| Ex7StereotypeScan | XML + Annotations | One line |
| Ex8AutowiredQualifier | XML + Annotations | One line |
| Ex9BeanVsComponent | XML + Annotations | One line |
| **Ex10ThreeApproaches** | **All 3 side by side** | **Pure XML → One line → Zero XML** |

---

## What's Next?

In the next project (**Bean Lifecycle**), you'll see the complete order of what happens when Spring creates a bean — from static block to constructor to `@PostConstruct` to `@PreDestroy`. You'll print each step to the console and watch the full lifecycle in action.
