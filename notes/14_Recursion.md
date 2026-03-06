## Structure of resursive implementations

* base case

often empty, one or two instances.

* recursive step

## Helper methods

helper methods often have different spec, remember not to expose.

## Mutual recursion

If method `A` calls method `B` and method `B` calls method `A` again, then they are mutually resursive.

```java
/**
 * @param file a file in the filesystem
 */
public static void visitNode(File file) {
  if (file.isDirectory()) {
    visitChildren(file.listFiles());
  }
}

/**
 * @param files a list of files
 */
public static void visitChildren(File[] files) {
  for (File file : files) {
    visitNode(file);
  }
}
```

## Reentrant code

Reentrant code can be safely re-entered, meaning that it **can be called again even while a call to it is underway**.

Recursion is a special case of it.

Reentrant code keeps its state entirely in **parameters and local variables** and doesn't use static variables or global variables.

## When to use recursion rather than iteration

1. The problem is naturally recursive

2. The data are naturally recursive

*functional programming*: The behavior of a method can be understood simply as a relationship between its parameters and its return value, with no side effects on any other part of the program.

*imperative programming*: with loops and variables.

