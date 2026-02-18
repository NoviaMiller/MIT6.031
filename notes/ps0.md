# Problem Set 0: Turtle Graphics

## Part I

### Problem 0: Install and set up

Cause the version problem, I use the IntelliJ and below is how to import it:

`Fire > New > Project from Existing Sources` , then choose the ps0 which downloaded from the website and having unzipped.

Adjust the jdk version depending on your personal habit, then you can start the lab.

It is a warm-up lab, so I will only choose what I think is important.

## Part II

### Calculating headings

Below is my implementation of `calculateHeadingToPoint`, it plays some interesting tricks.

```java
public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY, int targetX, int targetY) {
        double theta = Math.toDegrees(Math.atan2(targetY - currentY, targetX - currentX));
        double angle = 90 - theta;
        double answer = (angle - currentHeading) % 360;
        return answer >= 0 ? answer : answer + 360;
    }
```

* atan2()

check detail in [Here](http://en.wikipedia.org/wiki/Atan2), its range is `(-π，π]`, it returns a radius instead of an angle. So we need to transform it with `toDegrees()`.

* %

For `int`, the `%` calculator will always return the positive number, but in `double` it will like to round to 0 and may return the negative number. That's why we need the last line to double check whether the answer is within the correct range or not.