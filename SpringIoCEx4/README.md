# SpringIoCEx4 — Spring IoC Container

## What is IoC (Inversion of Control)?

In the previous projects, **you** created objects and wired dependencies manually in `main()`:

```java
OrderService service = new OrderService(new EmailNotification());
```

**You** were the delivery person — deciding what to create and what to pass.

With Spring IoC, **the control is inverted**. You no longer create objects. **Spring** reads your configuration (XML file), creates all objects, and wires them together. You just ask for the ready-made object.

```java
OrderService service = container.getBean(OrderService.class);
```

> You give the recipe (XML). Spring cooks the meal (creates + wires objects).

---

## What is a Bean?

- When **you** create an object with `new` → it's just a regular Java object
- When **Spring** creates and manages it → it's called a **Bean**

Spring controls the bean's entire life — creation, injection, and destruction.

---

## The Scenario

Same OrderService + NotificationService from LooseCouplingEx3. But now Spring is doing the wiring instead of `main()`.

---

## Project Structure

```
SpringIoCEx4/
├── pom.xml
└── src/main/
    ├── java/
    │   └── com/vk/
    │       ├── main/
    │       │   ├── LaunchApplication.java       ← ApplicationContext (use this)
    │       │   └── LaunchAppBeanFactory.java     ← BeanFactory (for awareness)
    │       ├── order/
    │       │   └── OrderService.java             ← depends on NotificationService
    │       └── notification/
    │           ├── NotificationService.java      ← interface
    │           ├── EmailNotification.java        ← implementation 1
    │           └── SmsNotification.java          ← implementation 2
    └── resources/
        └── applicationconfig.xml                 ← Spring reads this to create beans
```

---

## How to Run

1. Open the project in Eclipse
2. Right click → Maven → Update Project
3. Run `LaunchApplication.java`
4. You should see:

```
Spring created EmailNotification
Spring created SmsNotification
Spring created OrderService with constructor injection
Order placed for: Spring Boot Course
[SMS] Sending: Your order for Spring Boot Course has been confirmed!
```

---

## What Spring Does Behind the Scenes

When you run `new ClassPathXmlApplicationContext("applicationconfig.xml")`, Spring:

1. Reads `applicationconfig.xml`
2. Creates `EmailNotification` bean (because `<bean id="email">` exists)
3. Creates `SmsNotification` bean (because `<bean id="sms">` exists)
4. Creates `OrderService` bean and passes `sms` bean into its constructor (because `<constructor-arg ref="sms">`)
5. Returns the fully wired `OrderService` when you call `getBean()`

You never wrote `new OrderService()` or `new SmsNotification()`. Spring did it all.

---

## Setter vs Constructor Injection in XML

This project supports **both**. Switch between them in `applicationconfig.xml`:

**Constructor Injection** (currently active):
```xml
<bean id="orderService" class="com.vk.order.OrderService">
    <constructor-arg name="notification" ref="sms"/>
</bean>
```

**Setter Injection** (comment constructor, uncomment this):
```xml
<bean id="orderService" class="com.vk.order.OrderService">
    <property name="notification" ref="email"/>
</bean>
```

- `<constructor-arg>` → Spring passes dependency through the constructor
- `<property>` → Spring calls the setter method (`setNotification()`)

---

## Swap Notification Without Touching Java Code

Want email instead of SMS? Just change `ref` in XML:

```xml
<constructor-arg name="notification" ref="email"/>
```

Want SMS? Change it back:

```xml
<constructor-arg name="notification" ref="sms"/>
```

**OrderService.java was never opened. Never changed. Only XML changed.**

---

## ApplicationContext vs BeanFactory

This project has two launch classes to show the difference:

| | `LaunchApplication.java` | `LaunchAppBeanFactory.java` |
|---|---|---|
| **Container** | ApplicationContext | BeanFactory |
| **Bean loading** | Eager — ALL beans created at startup | Lazy — beans created only when you call `getBean()` |
| **Use in real projects** | 99% of the time | Rare — resource-constrained environments |

Run both and compare the console output — you'll see the order of object creation is different.

---

## Compare Across All Projects

| Project | Who creates objects? | Who wires dependencies? | How to swap? |
|---|---|---|---|
| **TightCouplingEx1** | You (`new` inside class) | Nobody — hardcoded | Rewrite the class |
| **TightCouplingEx2** | You (`extends`) | Nobody — inherited | Rewrite the class |
| **LooseCouplingEx3** | You (`new` in main()) | You (pass in constructor/setter) | Change `main()` |
| **SpringIoCEx4** | **Spring** (reads XML) | **Spring** (reads XML) | **Change XML only** |
