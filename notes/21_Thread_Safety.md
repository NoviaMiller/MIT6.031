## What threadsafe means

* behaves correctly

satisfy its spec and preserve its rep invariant

* regardless of how threads are executed

* without additional coordination

like "you can't call get() while set() is in progress", these additional coordination is disallowed

## Strategy 1: confinement **(variables)**

confine the thread that makes it can't read or write references directly.

1. "good" local variables

always thread confined, stored in each thread's own stack

**Careful: if it's an object reference, check what it points to**

2. avoid global variables

like singleton design pattern

`private static Class simulator = ...`

3. Confinement and anonymous classes

```java
public class PinballSimulator {
    
    private int highScore;
    // ...
    
    public void simulate() {
        int numberOfLives = 3;
        List<Ball> ballsInPlay = new ArrayList<>();
        
        new Thread(new Runnable() {
            public void run() {
[1]              ballsInPlay.add(new Ball());
[2]              numberOfLives += 1; //ERROR
[3]              System.out.println(numberOfLives);
[4]              highScore += 1000;
            }
        }).start();
    }
}
```

* line 3

Java allows read local variables

* line 2

But not allowed to be reassigned => you can allege the **local variables** itself is threadsafe, which is *immutable*

* line 1

But be careful if it is an object and points to something mutable... the instruction is allowed and dangerous.

* line 4

How about **field**, you are free to access them.

## Strategy 2: immutability **(type)**

A type is *threadsafe immutable* if it has:

1. no mutator methods

2. all fileds declared `private` and `final`

3. no rep exposure

4. no mutation whatsoever of mutable objects in the rep - not even beneficent mutation

## Strategy 3: using threadsafe data types

like `StringBuilder` => `StringBuffer`

`private static Map<Integer,Boolean> cache = Collections.synchronizedMap(new HashMap<>());`

* don't circumvent the wrapper

* Iterators are still not threadsafe

* atomic operations are't enough to prevent races

`if ( ! list.isEmpty()) { String s = list.get(0); ... }`

* a threadsafe data type != safe for use by multiple threads != no race conditon

## Serializability

Thread A calls `A()` while thread B calls `B()`

If the result is okay by `A-followed-by-B` or `B-followed-by-A`, then we say it is *consistent with serializability*, otherwise we say it is *violates serializability*