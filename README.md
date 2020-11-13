# nkanphia-etsiphen

The algorithm is a slightly-modified version of that originally proposed by Dijkstra which also takes care of [unary operators and single-argument functions](https://www.andr.mu/logs/the-shunting-yard-algorithm/).

There is also a REPL along with an evaluator that evaluates the given expressions on the fly.

Etymology: "nkanphia-etsiphen" is a calque of "shunting-yard" in [Lotha](https://en.wikipedia.org/wiki/Lotha_language).

## Build

This project usese `Maven` as the build-cum-dependency manager.

To build the project:

```
$ mvn clean && mvn compile && mvn package
```

To run the tests:

```
$ mvn test
```

To start the REPL:

```
$ mvn exec:java -Dexec.mainClass=com.z0ltan.compilers.App
```

## Demo

```
$ mvn exec:java -Dexec.mainClass=com.z0ltan.compilers.App



```

## LICENCE

See [LICENSE.md](LICENSE.md),