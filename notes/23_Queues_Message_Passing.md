## Two models for concurrency

* shared memory

*inplicitly* through mutation of shared data

* message passing

More advantages, *explicitly* passing messages through the communication channel

## Message passing between threads

* blocking method

if a method is a blocking method, then a call to that method can block, waiting until some event occurs before it returns to the caller.

* ordinary `Queue`

`add(e)`

`remove()`

* => `BlockingQueue` => `ArrayBlockingQueue` | `LinkedBlockingQueue`

`put(e)`

`take()`

Tips: you'd better choose an **immutable type**

## Message passing example

see code of 23 in relevant directory

```java
public void run() {
    // handle requests until we are interrupted
    while ( ! Thread.interrupted()) {
        try {
            // block until a request arrives
            int n = in.take();
            FridgeResult result = handleDrinkRequest(n);
            out.put(result);
        } catch (InterruptedException ie) {
            // stop
            break;
        }
    }
}
```

call `interrupt()` to the thread =>

If: blocking
    throw `InterruptedException`
else:
    Thread.interrupted() == true