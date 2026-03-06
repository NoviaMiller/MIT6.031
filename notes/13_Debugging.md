## Find the bug using the scientific method

## 1. Study the data

In a Java stack trace, the latest call is on top and the oldest call is on the bottom.

But the calls at the top or bottom of the stack may be library code that you didn't write. Your own code is often somewhere in the middle.

```java
java.lang.NullPointerException
  at java.util.Objects.requireNonNull(Objects.java:221)
  at java.util.AbstractSet.removeAll(AbstractSet.java:169)
  at turtle.TurtleSoup.drawPersonalArt(TurtleSoup.java:29)
  at turtle.TurtleSoupTest.testPersonalArt(TurtleSoupTest.java:39)
  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
...
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206) 
```

![image13_1](/images/13_1.png)

some clues like `java.util` `java.lang` `org.eclipse` `jdk.` shows that they are not your codes. Your code is only in the 3rd and 4th line.

## 2. Hypothesize

The module or their connections? We can use *binary search*.

1. slicing

Find the relevant variables and statements.

2. mutability can do a good job

3. delta debugging

Compare successful runs with failing runs to try to localize the bug.

## 3. Experiment

* print statement

* logging

* assertion

* breakpoint

## 4. Repeat

Keep an audit trail 