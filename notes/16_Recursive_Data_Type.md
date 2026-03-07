## Recursive datatype definitions

```java
public interface ImList<E> {

    // Datatype definition:
    //   ImList<E> = Empty + Cons(elt:E, rest:ImList<E>)
```

## Immutable lists

`nil` return null

`cons` cons(E + list)

`car` return E

`cdr` return list

Use *factory* method to less rep expsure

```java
public interface ImList<E> {
    public static <E> ImList<E> empty() {//hide implementation Empty
        return new Empty<>();
    }
    public ImList<E> cons(E elt);
    public E first();
    public ImList<E> rest();
}

class Empty<‍E> implements ImList<‍E>{
    ...
}

class Cons<‍E> implements ImList<‍E> {
    private final E elt;
    private final ImList<‍E> rest;
    ...
}
```

## Null vs empty

Resist the temptation to get rid of the `Empty` class and just use the `null`

Empty is an object rather than a null reference, called **sentinel objects** which can call method

as less null as you can