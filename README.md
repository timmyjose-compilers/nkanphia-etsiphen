# nkanphia-etsiphen

An implementation of the Shunting Yard algorithm by Dijkstra. A REPL is also provided along with an evaluator that evaluates the given expressions on the fly.
This project supports the five basic arithmetic operators - addition, subtraction, multiplication, division, and exponentiation, along with the use of parentheses.

*Etymology*: "nkanphia-etsiphen" is a calque of "shunting-yard" in [Lotha](https://en.wikipedia.org/wiki/Lotha_language).

## Build

This project usese `Maven` as the build-cum-dependency manager.

To build the project:

```
$ mvn clean && mvn compile
```

To run the tests:

```
$ mvn test
```

To start the REPL:

```
$ mvn exec:java -Dexec.mainClass=com.z0ltan.compilers.shuntingyard.App
```

## Demo

```
$ mvn clean && mvn compile

$ mvn exec:java -Dexec.mainClass=com.z0ltan.compilers.shuntingyard.App

[INFO] Scanning for projects...
[INFO]
[INFO] ---------------< com.z0ltan.compilers:nkanphia-etsiphen >---------------
[INFO] Building shunting-yard 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- exec-maven-plugin:1.6.0:java (default-cli) @ nkanphia-etsiphen ---

Welcome to a demonstration of the Shunting Yard Algorithm.
Enter arithmetic expressions to be evaluated. Press Ctrl+C to quit

>> 1
1.0
>> 1 + 2
3.0
>> ((11 + 2) / 24 / 4 / 2)
0.06770833333333333
>> 13 / 12
1.0833333333333333
>> 2      ^ 10.0
1024.0
>> ((1 + 12 / 2) ^ (24 - 3 + 12 / 2 * 10))
2.837535091800107E68
>> ((((((1))))))
1.0
```

## LICENCE

See [LICENSE.md](LICENSE.md),