## Comments where needed

Specify the provenance or source of a piece of code that was copied or adapted from elsewhere

```java
// read a web page into a string
// see http://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code
String mitHomepage = new Scanner(new URL("http://www.mit.edu").openStream(), "UTF-8").useDelimiter("\\A").next();
```

## Fail fast

Failing fast means that code should reveal its bugs as early as possible. 

* not fail faster (just can check)

```java
public static int dayOfYear(int month, int dayOfMonth, int year) {
    if (month < 1 || month > 12) {
        return -1;
    }
    ...
}
```

* fail faster and dynamic error

```java
public static int dayOfYear(int month, int dayOfMonth, int year) {
    if (month < 1 || month > 12) {
        throw new IllegalArgumentException();
    }
    ...
}
```

* fail faster and static error

```java
public enum Month { JANUARY, FEBRUARY, MARCH, ..., DECEMBER };
public static int dayOfYear(Month month, int dayOfMonth, int year) {
    ...
}
```

## Avoid magic numbers

Below is a good example

```java
final double oneRevolution = 360.0;
final int numSides = 5;
final int sideLength = 36;
for (int i = 0; i < numSides; ++i) {
    turtle.forward(sideLength);
    turtle.turn(oneRevolution / numSides);
}
```

## One purpose for each variable

Variables are not a scarce resource in programming. Make use of them.

**Method parameters**, in particular, should generally be left unmodified. `final` in parameters means in the inner method the parameters are unreassignable, it could not be `final` when passing in.

```java
public static int dayOfYear(final int month, final int dayOfMonth, final int year)
```

## Avoid special-case code

### *Actively resist the temptation to handle special cases separately.*