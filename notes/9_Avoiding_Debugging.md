## First defense: make bugs impossible

**static checking, dynamyic checking, immutability**

```java
char[] text4 = new char[] { text0, 'e', 'i', 'o', 'u' };
final char[] text5 = text4;
```

Below is available:

```java
text4 = text5;
text4[0] = 'x';
text5[0] = 'z';
```

Below is not available:

```java
text5 = text4;
```

## Second defense: localize bugs

fail fast and test more

## Assertions

* JUnit `assert...()`

Which should be used in JUnit tests, and encouraged to use.

* `assert` statements

Which only run with `-ea`.

When testing, we encourage it.

**When released, it won't work.**

Below is an example

```java
assert x >= 0 : "x is " + x;
```

## What to assert

**Method argument requirements**

**Method return value requirements**

```java
public double sqrt(double x) {
    assert x >= 0;
    double r;
    ... // compute result r
    assert Math.abs(r*r - x) < .0001;
    return r;
}
```

**Assertion failures therefore indicate bugs**. 

**External failures** are not bugs and tehre is no change you can make to your program in advance that will prevent them from happening.

External failures should be handled using exceptions instead like `FileNotFoundException` or `NoRouteToHostException`.

If you have to use assertion, don't use `assert` statement which can be turned off but exceptions:

```java
switch (vowel) {
  case 'a':
  case 'e':
  case 'i':
  case 'o':
  case 'u': return "A";
  default: throw new AssertionError("must be a vowel, but was: " + vowel);
}
```

## Incremental development

* Unit testing

* Regression testing

## Modularity & encapsulation

Minimize the scope of variables

* Always declare a loop variable in the for-loop initializer:

```java
for (int i = 0; i < 100; ++i) 
```

* Declare a variable only when you first need it, and in the innermost curly-brace block that you can.

* Avoid global variables

```java
static final int PROPLE_PER_BATHROOM = 5;
```

`final` means unreassignable

`static` means it is a class variable instead of an instance variable.