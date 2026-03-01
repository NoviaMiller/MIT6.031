## Mutability

* optimizing performance
* easy to share

```java
String s = "";
for (int i = 0; i < n; ++i) {
    s = s + i;
}
```

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; ++i) {
  sb.append(String.valueOf(i));
}
String s = sb.toString();
```

## Risks of mutation

**immutable types are safer from bugs, easier to understand, and more ready for change**

Don't do these things unless you know what you are doing:

* passing mutable values
* returning mutable values
    
    defensive copying may work but means extra work

## Aliasing is what makes mutable types risky

## Iterator

![image8.1](/images/8_1.png)

Imagine if you delete `a` while iterating what will happen? `b` will be index 0 which won't be iterated any more.

That's why don't change list when iterating.

```java
for (String subject : subjects) {
    if (subject.startsWith("6.")) {
        subjects.remove(subject);
    }
}
```

Instead, use `iter` 's `remove` method, it works

```java
Iterator iter = subjects.iterator();
while (iter.hasNext()) {
    String subject = iter.next();
    if (subject.startsWith("6.")) {
        iter.remove();
    }
}
```

**But it's not safe if there are other `Iterator`s currently active over the same list?**

## the key principle

**Immutability** : using immutable objects and unreassignable variables as much as possible.