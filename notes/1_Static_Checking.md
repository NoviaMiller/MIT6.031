## Types

A **type** is a set of values, always with **operations** that can be performed on those values.

In Java, we have types:

* primitive types (lowercase conventionally)
* objective types (start with a capital letter)

And operations:

* an operator like `+`
* a method of an object like `a.add(b)`
* a function like `Math.sin(theta)`

## Static Typing

Java is a **statically-typed** language: the type of all variables are known at compile time (before running).

Python is a **dynamically-typed** language: this kind of checking is deferred until runtime.

**Static typing** is a particular kind of **static checking**, which means checking for bugs at compile time.

## Static checking, dynamic checking, no checking

There are two interesting examples:

```java
int a = 8;
int b = 0;
int c = 8 / 0;
```

**Dynamic checking:** illegal argument values

```java
double a = 8;
double b = 0;
double c = 8 / 0;
```

**No checking:** special value POSITIVE_INFINITY

## Arrays and Collectins

Arrays like below will cause **buffer overflow**:

```java
int[] a = new int[10]
``` 

So we often use below to replace:

```java
List<Integer> list = new ArrayList<>();
```

**Tips:** the `<>` only accept **object types**, every primitive type has its corresponing object type.


