## Specification structure

If the precondition holds when the method is called, then the postcondition **must** hold when the method completes.

Otherwise, if the precondition does not hold when the method is called, the implementation is not bound by the postcondition => which means **free to do anything**.

![image6.1](/images/6_1.png)

![image6.2](/images/6_2.png)

## Do not allow null references

1. primitives cannot be null:

```java
int size = null;     // illegal
double depth = null; // illegal
```

2. non-primitive variables can be null:

```java
String name = null;
int[] points = null;
```

3. do not use null as parameters and return values

Cause null is troublesome and unsafe, as a general convention, null values are disallowed as patameters and return valuse unless the spec explicitly says that.

Below is error

```java
name.length()   // throws NullPointerException  
points.length   // throws NullPointerException
```

4. emptiness is not null

Below is okay if call the method.

```java
String s = "";
int[] arr = {};
```

## Testing and specifications

No matter black box test or glass box test, they **must follow the specifications => test cases must be correct**.

## Specifications for mutating methods

Also as a general convention, **the parameters is disallowed to mutate** unless the spec explicitly says.

## Exceptions

![images6.3](/images/6_3.png)

* checked exception
    * signal special results
    * checked by the compiler (`try-catch` or `throw` declaration)

```java
/**
* Compute the integer square root.
* @param x value to take square root of
* @return square root of x
* @throws NotPerfectSquareException if x is not a perfect square
*/
int integerSquareRoot(int x) throws NotPerfectSquareException
```

* unchecked exception
    * signal bugs
    * can not declare `try-catch` or `throw`(often do not declare that)
    * **error** means unexpected failures so no declaration

```java
/**
* Pops a value from this queue.
* @return next value in the queue, and removes the value from the queue
* @throws EmptyQueueException if this queue is empty
*/
int pop()
```