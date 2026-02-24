## Deterministic & underdetermined & nondeterministic

* deterministic

input satisfying the precondition, then only one valid output

* underdetermined

input satisfying the precondition, allow many valid outputs

* nondetermined

same input, different output. Often happens when concurrenting or random

## Declarative vs. operational spec

* declarative

recommended more, only declaration

* operational

tell you how to implement => you'd better use comments within the body of the method

## Stronger vs. weaker spec

![image7.1](/images/7_1.png)

the relationship can be stronger or weaker or same or *incomparable*

When we have to change the specifications, we should make it stronger as possible

## The specification should use **abstract** types where possible