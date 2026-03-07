## Grammars

* grammar

defines a set of strings

* terminals

the literal strings

* nonterminals

```
nonterminal ::= expression of terminals, nonterminals, and operators 
```

* productions

a grammar is described by a set of productions where each production defines a nonterminal

## Grammar operators

* repetition(highest)

`x ::= y* //zero or more y`

* concatenation

`x ::= y z //y followed by z`

* union(lowest)

`x :::= y | z //either y o r z`

## More grammar operators

the union of the main three operators above

`?`: 0 or 1 occurrence

`+`: 1 or more occurrence

`{n}`: definitely n times

`{n,m}`: [n, m]

`{,m}`: [0, m]

`{n,}`: [n, ]

`[abcd]`: a | b | c | d

`^a`: no a

## Parse trees

recursive or no

## Regular expressions

Below is good examples

```java
url ::= 'http://' ([a-z]+ '.')+ [a-z]+ (':' [0-9]+)? '/' 
markdown ::= ([^_]* | '_' [^_]* '_' )*
html ::= ( [^<>]* | '<i>' html '</i>' )*
```

`.`: matches all

`\d`: any digit

`\s`: any whitespace

`\w`: [a-zA-Z0-9]

## Using regular expressions in practice

Replace all runs of spaces in a string `s` with a single space:

```java
String singleSpacedString = s.replaceAll(" +", " ");
```

* Pattern

Pattern static compile(s:String)

Matcher matcher(s:String)

* Matcher

boolean matches()

String group(element:String)

```java
String s = "2020-03-18";
Pattern regex = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})");
Matcher m = regex.matcher(s);
if (m.matches()) {
    String year = m.group("year");
    String month = m.group("month");
    String day = m.group("day");
    // Matcher.group(name) returns the part of s that matched (?<name>...)
}
```