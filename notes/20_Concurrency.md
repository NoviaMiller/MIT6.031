## Two models for concurrent programming

* Shared memory

* Massage passing

## Processes, threads, time-slicing

* Process

like a virtual computer

not share memory

* Thread

like a virtual processor, which is a place inside of the running program

share memory

`main()` => *main thread*

* Time slicing

## Starting a thread in java

* Thread

Thread(n:Runnable)

start()

```java
// ... in the main method:
new Thread(new HelloRunnable()).start();

// elsewhere in the code
public class HelloRunnable implements Runnable {
    public void run() {
        System.out.println("Hello from a thread!");
    }
}
```

```java
new Thread(new Runnable() {
    public void run() {
        System.out.println("Hello from a thread!");
    }
}).start();
```

* Below is an example full of bugs

```java
public class Parcae {
    public static void main(String[] args) {
        Thread nona = new Thread(new Runnable() {
            public void run() { System.out.println("spinning"); }
        });
        nona.run();
        Runnable decima = new Runnable() {
            public void run() { System.out.println("measuring"); }
        };
        decima.run();
        // ...
    }
}
```

never ever call `run()` on a thread instead of `start()`, otherwise it just run on the *main thread* instead of the new thread.

* Try to run this test

```java
@Test public void testThreadOops() {
  new Thread(() -> { throw new Error("thread oops"); }).start();
}
```

It passed and printed. You can imagine the test return fast and the thread is thrown to somewhere else.

And if we have a lot of computation before the `Error`, it even won't print cause the test passed too fast that `System.exit()` has been called.

## Shared memory example

* Interleaving

```java
private static int balance = 0;

private static void deposit() {
    balance = balance + 1;
}
private static void withdraw() {
    balance = balance - 1;
}
```

No matter `balance ++` or `balance += 1` or `balance = balance + 1`, the sentence is divided by three below instructions and they can **interleave**

![image20_1](/images/20_1.png)

If A and B do these instructions together, we call them **race condition**

* Reordering

```java
private boolean ready = false;
private int answer = 0;

// computeAnswer runs in one thread
private void computeAnswer() {
    // ... calculate for a long time ...
    answer = 42;
    ready = true;
}

// useAnswer runs in a different thread
private void useAnswer() {
    // busy-wait for computeAnswer to say it's done(not a good code)
    while (!ready) {
        Thread.yield();
    }
    if (answer == 0) throw new RuntimeException("answer wasn't ready!");
}
```

But you'll still see bugs, cause machine may do that below:

```java
private void computeAnswer() {
    // ... calculate for a long time ...

    boolean tmpr = ready;
    int tmpa = answer;

    tmpa = 42;
    tmpr = true;

    ready = tmpr;
                   // <-- what happens if useAnswer() interleaves here?
                   // ready is set, but answer isn't.
    answer = tmpa;
}
```

## Message passing example

```java
get-balance
if balance >= 1 then withdraw 1
```

Also balance problem but now is the message interleaves...

## Concurrency is hard to test and debug

* Heisenbug: hard to reproduce

* Bohrbug: easy to reproduce