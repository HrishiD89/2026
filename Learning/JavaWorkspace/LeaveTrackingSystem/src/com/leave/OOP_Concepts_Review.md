# Advanced Object-Oriented Programming: Case Study Review

This document reviews the concepts used in the "Leave Tracking System" case study, analyzes the mistakes encountered during implementation, and provides best practices for future development.

---

## 1. The Core Concepts

### A. Inheritance ("Is-A" Relationship)
**Concept:** Inheritance allows a child class to acquire the properties (fields) and behaviors (methods) of a parent class. It promotes code reuse.

**In this project:**
*   **Parent:** `LeaveRequest` (Holds common data: `requestId`, `employee`, `startDate`).
*   **Children:** `SickLeaveRequest`, `MaternityLeaveRequest`, `VacationLeaveRequest`.

**Example:**
```java
// Logic: A SickLeaveRequest IS A LeaveRequest
public class SickLeaveRequest extends LeaveRequest { ... }
```

### B. Abstract Classes & Methods (The "Blueprint")
**Concept:** An `abstract` class cannot be instantiated (you can't say `new LeaveRequest()`). It is a partial blueprint.
*   **Abstract Method:** A method defined in the parent with **no body**. Child classes **MUST** provide the code for this method.

**In this project:**
*   `LeaveRequest` knows that every leave needs to calculate days, but it doesn't know *how* (business logic differs).
*   Therefore, `public abstract int calculateLeaveDays();` forces the children to do the math.

### C. Polymorphism (One Interface, Many Forms)
**Concept:** Polymorphism allows you to treat different objects (Sick, Maternity, Vacation) as a single type (`LeaveRequest`).

**In this project:**
In `Main.java`, we created a `List<LeaveRequest>`.
```java
// We don't care if it's sick or vacation, we just want to process it.
for (LeaveRequest req : requests) {
    req.processRequest(); // Java automatically finds the correct version!
}
```

### D. Interfaces (The "Can-Do" Contract)
**Concept:** An interface defines **capabilities**. It doesn't care who you are (class hierarchy), only what you can do.

**In this project:**
*   `Approvable` defines that something can be `approve()`d or `deny()`d.
*   `LeaveRequest` signs this contract using `implements Approvable`.

---

## 2. Analysis of Your Mistakes

Here is a breakdown of the specific issues found in the initial code and why they happened.

### Mistake 1: Broken Abstract Contract (Compilation Error)
**What happened:**
You created `MaternityLeave` and `VactionLeave` extending `LeaveRequest`, but you did **not** write the `calculateLeaveDays()` method inside them.

**Why it failed:**
When you extend an abstract class, you sign a contract. The compiler error "The type MaternityLeave must implement the inherited abstract method" means: *"You promised to provide logic for calculating days, but you didn't."*

**How to fix:**
Always look for abstract methods in the parent. Use your IDE's "Add unimplemented methods" feature.

### Mistake 2: Naming Inconsistency
**What happened:**
The case study asked for `Approvable` to have a method `approve()`. You named it `approveRequest()`.

**Why it matters:**
In strict specifications (like unit tests or team projects), names must match exactly. If an external system expects `approve()`, calling `approveRequest()` will break the integration.

### Mistake 3: Typo in Class Names
**What happened:**
`VactionLeave.java` (Missing 'a' in Vacation).

**Why it matters:**
Professional code requires precision. Typos make code harder to search and maintain.

---

## 3. Deep Dive: Why use Inner Classes?

You asked specifically about the `Inner Class` usage for `StatusChange`.

### The Code:
```java
public class LeaveRequest {
    // ... fields
    private ArrayList<StatusChange> statusHistory;

    // INNER CLASS
    public class StatusChange {
        String oldStatus, newStatus;
        // ...
    }
}
```

### Why do this instead of a separate file?

1.  **Strong Logical Grouping (Encapsulation):**
    A `StatusChange` object has **no meaning** outside the context of a `LeaveRequest`. You would never have a "Status Change" floating around that isn't attached to a leave request. Defining it *inside* tells other developers: *"This class belongs here. Don't use it for anything else."*

2.  **Code Organization:**
    It keeps your project folder clean. Instead of having dozens of tiny helper files (`LeaveRequestStatusChange.java`), you bundle the helper logic inside the main class.

3.  **Access to Private Members (Power Move):**
    Non-static inner classes have access to the `private` variables of the outer class.
    *   *Example:* If `StatusChange` needed to read `this.requestId` from `LeaveRequest`, it could do so directly without needing a getter method.

---

## 4. How to Prevent These Mistakes (Checklist)

**Before you write code:**
1.  [ ] **Read the Spec:** Note down exact names (`approve`, `calculateLeaveDays`).
2.  [ ] **Check Hierarchy:** If the parent is `abstract`, ask: "What methods *must* I implement in the child?"

**While coding:**
1.  [ ] **Use @Override:** Always put `@Override` above methods you inherit. If you make a typo (e.g., `processReqest` instead of `processRequest`), the compiler will immediately yell at you because it can't find the method in the parent to override.
    ```java
    // The compiler will catch typos here!
    @Override
    public boolean processRequest() { ... }
    ```
2.  [ ] **Stub First:** When you create a child class, immediately create empty methods for the abstract requirements to stop the red error lines. Fill in the logic later.
    ```java
    public class MaternityLeave extends LeaveRequest {
        @Override
        public int calculateLeaveDays() { return 0; } // Temporary fix to make it compile
    }
    ```

**After coding:**
1.  [ ] **Test Driver:** Write a `Main` class that actually creates the objects and puts them in a List. If you can't put them in a `List<ParentType>`, your inheritance might be wrong.

