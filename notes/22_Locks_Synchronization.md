## Strategy 4: Synchronization

## Deadlock

A has a and blocks to wait b

B has b and blocks to wait a 

The essential feature of deadlock is a cycle of dependencies like this.

**You can also have deadlock without using any locks**

client => too much requests => server's buffer [block]

Server => too much results => client's buffer [block]

## Locking

In Java, every object has a lock implicitly associated with it, even a humble `Object`

```java
Object lock = new Object();
```

Bare `Object` like above are often used for explicit locking

---

But you can call `acquire` and `realease` on Java's intrinsic locks, instead you use the `synchronized` statement to acquire the lock for the duration of a statement block

```java
synchronized (lock) { // thread blocks here until lock is free
    // now this thread has the lock
    balance = balance + 1;
} // exiting the block releases the lock
```

Synchronized regions like this provide **mutual exclusion**: only one thread at a tiem can be in a synchronized region guarded by a given object's lock.

---

**Danger**

When Thread 1 calls `synchronized(obj) {...}`, it only does one thing:

*prevents other threads from entering their own `synchronized(expression)` , where expression refers to the same object as `obj`*

**Even Thread 1 is in its synchronized block, another thread can mutate it simply by neglecting to use `synchronized`**

## Monitor pattern

Below are two same code

```java
public int length() {
    synchronized (this) {
        return text.length();
    }
}
```

```java
public synchronized int length() {
    return text.length();
}
```

A monitor is a class whose methods re mutually exclusive so that only one thread can be inside an instance of the class at a time.

* reentrant locks

```java
synchronized (obj) {
    // ...
    synchronized (obj) { // <-- uh oh, deadlock?
        // ...
    }
    // <-- do we own the lock on obj?
}
```

Deadlock won't happen， we'll count the number of times that its owner has acquired it without releasing it.

* locking discipline

1. Every shared mutabe variable must be fuarded by some lock.

2. If an invariant involves multiple shared mutable variables, then all the variables involved must be guarded by the **same lock**.

## Atomic operations

```java
public static boolean findReplace(EditBuffer buf, String pattern, String replacement) {
    synchronized (buf) {
        int i = buf.toString().indexOf(pattern);
        if (i == -1) {
            return false;
        }
        buf.delete(i, pattern.length());
        buf.insert(i, replacement);
        return true;
    }
}
```

Why `public static synchronized boolean findReplace(EditBuffer buf, ...) {` is wrong?

It would acquire a static lock for the whole class that `findReplace` happens to be in

=> so only one thread could call `findReplace` at a time

=> a big loss in performance

=> and that's can't guard cause other code can touch the diff lock

## Deadlock rears its ugly head

```java
// thread A                   // thread B
    while (true) {                while (true) {
      harry.friend(snape);          snape.friend(harry);
      harry.defriend(snape);        snape.defriend(harry);
    }                             }
```

* solution 1: lock ordering

Ensure all code acquires the locks in that order.

```java
    public void friend(Wizard that) {
        Wizard first, second;
        if (this.name.compareTo(that.name) < 0) {
            first = this; second = that;
        } else {
            first = that; second = this;
        }
        synchronized (first) {
            synchronized (second) {
                if (friends.add(that)) {
                    that.friend(this);
                } 
            }
        }
    }
```

* solution 2: coarse-grained locking

use a single lock to guard many object instances or even a whole subsystem of a program

```java
public class Wizard {
    private final Castle castle;
    private final String name;
    private final Set<Wizard> friends;
    ...
    public void friend(Wizard that) {
        synchronized (castle) {
            if (this.friends.add(that)) {
                that.friend(this);
            }
        }
    }
}

```