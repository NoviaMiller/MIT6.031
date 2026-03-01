## Introduction

Any code within the curly vraes of `A` 's class body can touch `A` 's private fields and methods even to more than one `A` object. 

```java
public void loanTo(Wallet that) {
    that.amount += this.amount;
    amount = 0;
}
```

## Classifying types and operations

* creator: t* -> T
* producer: T+, t* -> T
* observer: T+, t* -> t
* mutator: T+, t* -> void | t | T

A creator implemented as a static method is often called a **factory method**