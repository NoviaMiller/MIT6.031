## Invariants

Property of a good abstract data type is that it **preserves its own invariants**. There are many kinds of invariants including *types of variables* and *relationships between variables*.

* representation exposure:

code outside the class can modify the representation directly.

Below is an example:

```java
public class Tweet {

    private final String author;
    private final String text;
    private final Date timestamp;

    public Tweet(String author, String text, Date timestamp) {
        this.author = author;
        this.text = text;
        this.timestamp = timestamp;
    }

    /**
     * @return Twitter user who wrote the tweet
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return text of the tweet
     */
    public String getText() {
        return text;
    }

    /**
     * @return date/time when the tweet was sent
     */
    public Date getTimestamp() {
        return timestamp;
    }

}
```

![image11_1](/images/11_1.png)

* defensive copying:

```java
public Date getTimestamp() {
    return new Date(timestamp.getTime());
}
```

But there are other questions:

```java
/**
 * @return a list of 24 inspiring tweets, one per hour today
 */
public static List<Tweet> tweetEveryHourToday () {
    List<Tweet> list = new ArrayList<Tweet>(); 
    Date date = new Date();
    for (int i = 0; i < 24; i++) {
        date.setHours(i);
        list.add(new Tweet("rbmllr", "keep it up! you can do it", date));
    } 
    return list;
}
```

![image11_2](/images/11_2.png)

Then we do defensive copying in the constructor:

```java
public Tweet(String author, String text, Date timestamp) {
    this.author = author;
    this.text = text;
    this.timestamp = new Date(timestamp.getTime());
}
```

Or we just use a careful specification: 

```java
/**
 * Make a Tweet.
 * @param author    Twitter user who wrote the tweet
 * @param text      text of the tweet
 * @param timestamp date/time when the tweet was sent. Caller must never 
 *                   mutate this Date object again!
 */
public Tweet(String author, String text, Date timestamp) 
```

The best solution is: **use immutable types**

## Rep invariant & abstraction function

1. `AF: R -> A`

An *abstraction function* that maps rep values to the abstract values they represent

2. `RI: R -> boolean`

A rep invariant that maps rep values to booleans

![image11_3](/images/11_3.png)

Below is a good example:

```java
public class RatNum {

    private final int numerator;
    private final int denominator;

    // Rep invariant:
    //   denominator > 0
    //   numerator/denominator is in reduced form,
    //       i.e. gcd(|numerator|,denominator) = 1

    // Abstraction function:
    //   AF(numerator, denominator) = numerator/denominator

    /** 
     * Make a new RatNum == n.
     * @param n value
     */
    public RatNum(int n) {
        numerator = n;
        denominator = 1;
        checkRep();
    }

    /**
     * Make a new RatNum == (n / d).
     * @param n numerator
     * @param d denominator
     * @throws ArithmeticException if d == 0
     */
    public RatNum(int n, int d) {
        // reduce ratio to lowest terms
        int g = gcd(n, d);
        n = n / g;
        d = d / g;

        // make denominator positive
        if (d < 0) {
            numerator = -n;
            denominator = -d;
        } else {
            numerator = n;
            denominator = d;
        }
        checkRep();
    }
}

// Check that the rep invariant is true
// *** Warning: this does nothing unless you turn on assertion checking
//     by running Java with -enableassertions (or -ea)
private void checkRep() {
    assert denominator > 0;
    assert gcd(Math.abs(numerator), denominator) == 1;
}
```

**Check rep is responsible for implementer instead of clients.**

## No null values in the rep

## Beneficent mutation

The **abstract value** should never change but the implemetation is free to **mutate a rep value**

```java
public class RatNum {

    private int numerator;
    private int denominator;

    // Rep invariant:
    //   denominator != 0

    // Abstraction function:
    //   AF(numerator, denominator) = numerator/denominator

    //do gcd only when called
```

## Documenting the AF, RI, and safety form rep exposure

```java
// Immutable type representing a tweet.
public class Tweet {

    private final String author;
    private final String text;
    private final Date timestamp;

    // Rep invariant:
    //   author is a Twitter username (a nonempty string of letters, digits, underscores)
    //   text.length <= 280
    // Abstraction function:
    //   AF(author, text, timestamp) = a tweet posted by author, with content text, 
    //                                 at time timestamp 
    // Safety from rep exposure:
    //   All fields are private;
    //   author and text are Strings, so are guaranteed immutable;
    //   timestamp is a mutable Date, so Tweet() constructor and getTimestamp() 
    //        make defensive copies to avoid sharing the rep's Date object with clients.

    // Operations (specs and method bodies omitted to save space)
    public Tweet(String author, String text, Date timestamp) { ... }
    public String getAuthor() { ... }
    public String getText() { ... }
    public Date getTimestamp() { ... }
}
```