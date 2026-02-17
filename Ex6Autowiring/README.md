# Ex6Autowiring — Autowiring in XML (4 Modes)

## What is Autowiring?

In SpringIoCEx4, you manually told Spring which bean to inject by writing `ref="email"` or `ref="sms"`. You specified every connection yourself.

**Autowiring** means you tell Spring: "figure out the wiring yourself." Spring looks at bean names, types, or constructor parameters and matches them **automatically**.

---

## The Scenario

Same OrderService + NotificationService from SpringIoCEx4. Same classes, same interface. Only the XML configuration changes — instead of `ref`, we use `autowire`.

---

## Project Structure

```
Ex6Autowiring/
├── pom.xml
└── src/main/
    ├── java/
    │   └── com/vk/
    │       ├── app/
    │       │   └── LaunchApp.java             ← runs the app
    │       ├── order/
    │       │   └── OrderService.java          ← has setter + constructor
    │       └── notification/
    │           ├── NotificationService.java   ← interface
    │           ├── EmailNotification.java     ← implementation 1
    │           └── SmsNotification.java       ← implementation 2
    └── resources/
        └── applicationconfig.xml              ← try all 4 modes here
```

---

## How to Run

1. Open the project in Eclipse
2. Right click → Maven → Update Project
3. Run `LaunchApp.java`
4. Currently **Mode 3 (byType)** is active. You should see:

```
Order placed for: Spring Boot Course
[Email] Sending: Your order for Spring Boot Course has been confirmed!
```

Spring found `EmailNotification` bean, saw it's of type `NotificationService`, and injected it into `OrderService` automatically. You never wrote `ref="email"`.

---

## The 4 Autowiring Modes

### Mode 1: `autowire="no"` (default)

```xml
<bean id="email" class="com.vk.notification.EmailNotification"/>
<bean id="orderService" class="com.vk.order.OrderService" autowire="no">
    <property name="notification" ref="email"/>
</bean>
```

You manually specify everything with `ref`. This is what SpringIoCEx4 did. No surprises, most explicit.

### Mode 2: `autowire="byName"`

```xml
<bean id="notification" class="com.vk.notification.EmailNotification"/>
<bean id="orderService" class="com.vk.order.OrderService" autowire="byName"/>
```

Spring matches **bean id** to **setter method name**:
- Setter is `setNotification()` → property name is `notification`
- Spring looks for a bean with `id="notification"` → found it → injects it

**Gotcha:** If you rename bean id to `"email"` instead of `"notification"` → Spring won't find a match. **No error, just null.** Silent failure!

### Mode 3: `autowire="byType"` (currently active in your code)

```xml
<bean id="email" class="com.vk.notification.EmailNotification"/>
<bean id="orderService" class="com.vk.order.OrderService" autowire="byType"/>
```

Spring matches by **TYPE** of setter parameter:
- Setter parameter type is `NotificationService`
- Spring finds a bean of that type (`EmailNotification` implements `NotificationService`) → injects it
- Bean id doesn't matter here — only the type matters

**Gotcha:** If TWO beans of same type exist → `NoUniqueBeanDefinitionException`

### Mode 4: `autowire="constructor"`

```xml
<bean id="email" class="com.vk.notification.EmailNotification"/>
<bean id="orderService" class="com.vk.order.OrderService" autowire="constructor"/>
```

Same as `byType` but uses the **constructor** instead of the setter. Matches constructor parameter types to available beans.

---

## Try Each Mode

Switch between modes by commenting/uncommenting sections in `applicationconfig.xml`:

| Try this | What happens |
|---|---|
| Activate Mode 2, change bean id from `"notification"` to `"email"` | `WARNING: No notification service injected!` — silent null |
| Activate Mode 3, add a second bean: `<bean id="sms" class="com.vk.notification.SmsNotification"/>` | `NoUniqueBeanDefinitionException` — Spring can't choose |
| Add `primary="true"` on email bean after the above error | Works again — Spring picks the primary bean |
| Add `autowire-candidate="false"` on sms bean instead | Also works — sms is excluded from autowiring |

---

## Fixing Ambiguity (2 Beans of Same Type)

When `byType` or `constructor` finds two beans that match, Spring crashes. Two fixes:

| Fix | What it does | XML |
|---|---|---|
| `primary="true"` | Makes one bean the default choice | `<bean id="email" class="..." primary="true"/>` |
| `autowire-candidate="false"` | Excludes a bean from autowiring | `<bean id="sms" class="..." autowire-candidate="false"/>` |

---

## The 4 Modes — Summary

| Mode | How Spring matches | Uses | Risk |
|---|---|---|---|
| `no` (default) | You specify everything with `ref` | Setter or constructor | None — safest |
| `byName` | Bean id must match setter name | Setter | Rename id → silent null |
| `byType` | Bean TYPE matches setter param type | Setter | 2 beans same type → exception |
| `constructor` | Bean TYPE matches constructor param type | Constructor | Same as byType |

---

## Compare with SpringIoCEx4

| | SpringIoCEx4 | Ex6Autowiring |
|---|---|---|
| How Spring knows what to inject | You wrote `ref="email"` explicitly | Spring figures it out from name/type |
| XML verbosity | More verbose (every `ref` written out) | Less verbose (just `autowire="byType"`) |
| Risk | None — you control everything | Possible silent nulls or ambiguity errors |

---

## What's Next?

Autowiring in XML is still XML-based — you're still writing `<bean>` tags for every class.

In the next project (**Stereotype Annotations + Component Scanning**), you'll put `@Service` and `@Component` directly on your Java classes. Spring will **discover** them automatically. The XML shrinks to just **one line**.
