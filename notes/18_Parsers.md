## Whitespace

```java
// the IntegerExpression grammar
@skip whitespace {//whitespaces is also the same
    expr ::= sum ;
    sum ::= primary ('+' primary)* ;
    primary ::= number | '(' sum ')' ;
}
whitespace ::= [ \t\r\n]+ ;
number ::= [0-9]+ ;
```

The actual expr are:

```java
expr ::= whitespace* sum whitespace* ;
sum ::= whitespace* primary whitespace* (whitespace* '+' whitespace* primary whitespace*)* whitespace* ;
primary ::= whitespace* number whitespace* | whitespace* '('  whitespace* sum  whitespace* ')' whitespace* ;
```

## Parserlib 

[See more clear introductions here](https://web.mit.edu/6.031/www/sp21/classes/18-parsers/)

## Handling errors

`IOException`: Your grammar  file may not exist or fail to open

`UnableToParseException`: Your grammar or string is not correct

## Left recursion and other limitations

* Left recursion:

WRONG

```java
sum ::= number | sum '+' number ;
number ::= [0-9]+ ;

//the indirect is also wrong
sum ::= number | thing number ;
thing ::= sum '+' ;
number ::= [0-9]+ ;
```

RIGHT

```java
sum ::= (number '+')* number ;
number ::= [0-9]+ ;
```

* Greediness

```java
g ::= ab threeb ;
ab ::= 'a'*'b'* ;
threeb ::= 'bbb' ;
```

The string `aaaabbb` will be wrong cause the parser is greedy to catch as most as it can when parsing the left one.