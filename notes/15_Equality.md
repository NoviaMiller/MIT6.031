## Equivalence relation

* reflexive

* symmetric 

* transitive

## Implementing equals()

* wrong way

```java
public class Duration {
    ...   
    // Problematic definition of equals()
    public boolean equals(Duration that) {
        return this.getLength() == that.getLength();        
    }
}
```

Thinking about this example

```java
Duration d1 = new Duration (1, 2);
Duration d2 = new Duration (1, 2);
Object o2 = d2;
d1.equals(d2) → true
d1.equals(o2) → false
```

```java
// implicit method inherited from Object:
    public boolean equals(Object that) {
        return this == that;
    }
```

**Java compiler selects between overloaded operations using the *static type of the parameter expressions***

* better way to implement

```java
@Override 
public boolean equals(Object that) {
    return that instanceof Duration && this.sameValue((Duration)that);
}

private boolean sameValue(Duration that) {
    return this.getLength() == that.getLength();
}
```

The `instanceof` operator is dynamic checking and **disallowed anywhere except for implementing `equals`**

## The Object contract

1. `equals` must define an equivalence relation

2. consistent when repeated calls

3. null -> `false`

4. `hashCode` should be same

Tips: When `that` is `null`, `that instanceof Duration` returns false

## Equality of immutable types

* Behavioral equality is the same as observational equality

* must override `equals()`

* must override `hashCode()`

## Equality of mutable types

* not same

* generally should not be overriden

* not either

Below is a good example:

```java
List<String> list = new ArrayList<>();
list.add("a");

Set<List<String>> set = new HashSet<List<String>>();
set.add(list);

set.contains(list) → true
```

![image15_1](/images/15_1.png)

Then mutate it:

```java
list.add("goodbye");
set.contains(list) → false!

//we can find list when iterate but it shows false!
for (List<String> l : set) { 
    set.contains(l) → false! 
}
```

![image15_2](/images/15_2.png)

**You should never ever use mutable types as set elements unless must**

## Integer and int tricky

```java
Integer x = new Integer(3);
Integer y = new Integer(3);
x.equals(y) → true
x == y -> false
```

So...

```java
Map<String, Integer> a = new HashMap<>(), b = new HashMap<>();
String c = "c";
a.put(c, 130); // put ints into the maps
b.put(c, 130);
a.get(c) == b.get(c) → ??
```

The answer is `false` though the `equals()` will return `true`