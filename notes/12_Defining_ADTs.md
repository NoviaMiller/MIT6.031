## Interfaces

* can't be instantiated with `new` but a class provides a rep for it.

* no constructor

## subclass

* the subclass should have stronger (or same) precondition and postcondition.

Which means if a place can use `A`, then must its subclass `B` can be.

* rep exposure risk

## dynamic dispatch  

It uses the implementation appropriate to the dynamic type of the object not the static type of the reference that points to the object.

![image12_1](/images/12_1.png)

![image12_2](/images/12_2.png)

Below is a good example

![image12_3](/images/12_3.png)

1. `list = obj; //WRONG`

subtype -> supertype is disalloewd, while supertype -> subtype is okay

2. `list = (List<String>) obj; //TRUE`

typecast expression is okay, but if fail, you will see `ClassCastException`

## Generic types

## Enumerations

Below is a good example:

```java
public enum Month {
    // the values of the enumeration, written as calls to the private constructor below
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(31),
    SEPTEMBER(30),
    OCTOBER(31),
    NOVEMBER(30),
    DECEMBER(31);

    // rep
    private final int daysInMonth;

    // enums also have an automatic, invisible rep field:
    //   private final int ordinal;
    // which takes on values 0, 1, ... for each value in the enumeration.

    // rep invariant:
    //   daysInMonth is the number of days in this month in a non-leap year
    // abstraction function:
    //   AF(ordinal,daysInMonth) = the (ordinal+1)th month of the Gregorian calendar
    // safety from rep exposure:
    //   all fields are private, final, and have immutable types

    // Make a Month value. Not visible to clients, only used to initialize the
    // constants above.
    private Month(int daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

    /**
     * @param isLeapYear true iff the year under consideration is a leap year
     * @return number of days in this month in a normal year (if !isLeapYear) 
     *                                           or leap year (if isLeapYear)
     */
    public int getDaysInMonth(boolean isLeapYear) {
        if (this == FEBRUARY && isLeapYear) {
            return daysInMonth+1;
        } else {
            return daysInMonth;
        }
    }

    /**
     * @return first month of the semester after this month
     */
    public Month startOfNextSemester() {
        switch (this) {
            case JANUARY:
                return FEBRUARY;
            case FEBRUARY:   // cases with no break or return
            case MARCH:      // fall through to the next case
            case APRIL:
            case MAY:
                return JUNE;
            case JUNE:
            case JULY:
            case AUGUST:
                return SEPTEMBER;
            case SEPTEMBER:
            case OCTOBER:
            case NOVEMBER:
            case DECEMBER:
                return JANUARY;
            default:
                throw new RuntimeException("can't get here");
        }
    }
}
```

## Summary

![image12_4](/images/12_4.png)