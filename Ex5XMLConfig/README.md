# XMLConfigEx5 — XML Configuration Deep Dive

## What This Project Covers

In SpringIoCEx4, we used XML to define beans and wire them together. But we only used **one type of injection** at a time (setter or constructor) with **one bean reference**.

This project explores **everything you can do with XML configuration**:

- Injecting **primitive values** (int, String, double)
- Injecting **bean references** (one object into another)
- **Constructor injection** — by index and by name
- **Setter injection** — using `<property>`
- **P-namespace** — setter injection shortcut (one line)
- **C-namespace** — constructor injection shortcut (one line)
- **HAS-A relationship** — Employee HAS-A Department

---

## The Scenario

An Employee management system:

- `Employee` has: `empId` (int), `name` (String), `salary` (double), `department` (Department)
- `Department` has: `deptId` (int), `deptName` (String)
- Employee **HAS-A** Department — this is an object-type dependency

---

## Project Structure

```
XMLConfigEx5/
├── pom.xml
└── src/main/
    ├── java/
    │   └── com/vk/
    │       ├── app/
    │       │   └── LaunchApp.java          ← runs the app
    │       ├── employee/
    │       │   └── Employee.java           ← has primitives + HAS-A Department
    │       └── department/
    │           └── Department.java         ← has primitives
    └── resources/
        └── applicationconfig.xml           ← all 5 injection styles
```

---

## How to Run

1. Open the project in Eclipse
2. Right click → Maven → Update Project
3. Run `LaunchApp.java`
4. You should see:

```
Constructor (index):    Employee{empId=101, name='Rohan', salary=45000.5, department=null}
Setter + bean ref:      Employee{empId=102, name='Priya', salary=55000.0, department=Department{deptId=1, deptName='Engineering'}}
Constructor (name):     Employee{empId=103, name='Amit', salary=60000.0, department=null}
P-namespace:            Employee{empId=104, name='Sneha', salary=70000.0, department=Department{deptId=3, deptName='Sales'}}
C-namespace:            Department{deptId=4, deptName='HR'}
```

---

## 5 Injection Styles Demonstrated

### 1. Constructor Injection by Index

```xml
<bean id="emp1" class="com.vk.employee.Employee">
    <constructor-arg index="0" value="101"/>
    <constructor-arg index="1" value="Rohan"/>
    <constructor-arg index="2" value="45000.5"/>
</bean>
```

`index="0"` means first parameter, `index="1"` means second, and so on. Spring auto-converts `"101"` to `int`, `"45000.5"` to `double`.

### 2. Setter Injection + Bean Reference

```xml
<bean id="dept1" class="com.vk.department.Department">
    <property name="deptId" value="1"/>
    <property name="deptName" value="Engineering"/>
</bean>

<bean id="emp2" class="com.vk.employee.Employee">
    <property name="empId" value="102"/>
    <property name="name" value="Priya"/>
    <property name="salary" value="55000.0"/>
    <property name="department" ref="dept1"/>
</bean>
```

- `<property name="empId" value="102"/>` → Spring calls `setEmpId(102)`
- `<property name="department" ref="dept1"/>` → Spring calls `setDepartment(dept1Bean)`
- `value` = primitives/Strings
- `ref` = another bean (this is the HAS-A relationship)

### 3. Constructor Injection by Name

```xml
<bean id="emp3" class="com.vk.employee.Employee">
    <constructor-arg name="empId" value="103"/>
    <constructor-arg name="name" value="Amit"/>
    <constructor-arg name="salary" value="60000.0"/>
</bean>
```

Instead of `index`, you use the actual parameter name from the constructor.

### 4. P-namespace (Setter Shortcut)

```xml
<bean id="emp4" class="com.vk.employee.Employee"
      p:empId="104" p:name="Sneha" p:salary="70000.0"
      p:department-ref="dept3"/>
```

Same as writing 4 `<property>` tags — but in one line.

- `p:name="Sneha"` → calls `setName("Sneha")`
- `p:department-ref="dept3"` → calls `setDepartment(dept3Bean)`

### 5. C-namespace (Constructor Shortcut)

```xml
<bean id="dept4" class="com.vk.department.Department"
      c:deptId="4" c:deptName="HR"/>
```

Same as writing 2 `<constructor-arg>` tags — but in one line.

---

## value vs ref — The Key Distinction

| Attribute | What it does | Example |
|---|---|---|
| `value` | Passes a literal value (Spring auto-converts to int, double, boolean, String) | `value="Rohan"` → passes the String "Rohan" |
| `ref` | Looks up a bean by its `id` and passes that entire object | `ref="dept1"` → passes the Department bean |

---

## HAS-A Relationship

`Employee` HAS-A `Department`. This means Employee holds a Department object as a field:

```java
private Department department;
```

In XML, you wire this with `ref`:

```xml
<property name="department" ref="dept1"/>
```

Spring looks up the bean with `id="dept1"`, and passes that Department object into Employee's `setDepartment()` method.

---

## Quick Reference

| XML Syntax | What it does | Type |
|---|---|---|
| `<property name="empId" value="101"/>` | Calls `setEmpId(101)` | Setter |
| `<property name="department" ref="dept1"/>` | Calls `setDepartment(dept1Bean)` | Setter (bean ref) |
| `<constructor-arg index="0" value="101"/>` | Passes 101 to constructor position 0 | Constructor |
| `<constructor-arg name="empId" value="101"/>` | Passes 101 to param named empId | Constructor |
| `p:empId="101"` | Shortcut for `<property>` | Setter |
| `p:department-ref="dept1"` | Shortcut for `<property ref>` | Setter (bean ref) |
| `c:deptId="1"` | Shortcut for `<constructor-arg>` | Constructor |

---

## What's Next?

This project and SpringIoCEx4 are both **pure XML configuration** — every bean is manually defined in XML.

In the next project (**Autowiring in XML**), Spring will start figuring out the wiring **automatically** instead of you writing every `ref` by hand.
