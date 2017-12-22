uncaught: handle uncaught exceptions with ease
==============================================

[![Build Status](https://secure.travis-ci.org/pyr/uncaught.png)](http://travis-ci.org/pyr/uncaught)

Uncaught is a single macro namespace to provide
a convenience macro for handling uncaught exceptions.

## Installation

Add the following depenency to your `project.clj` file:

```clojure
[spootnik/uncaught "0.5.5"]
```

Up to date instructions and pointers to other build environement are
available on [clojars](https://clojars.org/spootnik/uncaught)

## Documentation

`(with-uncaught [e & body])`: When an uncaught exception is thrown,
execute `body` while binding `e` to the exception.  The `*thread*`
dynamic variable will also be bound to the thread on which the
exception occured.

## Example

```clojure
(with-uncaught e
  (warn e "uncaught exception"))
```

## Notes

An `uncaught` symbol is available for backward compatibility purposes.
This is inspired by Stuart Sierra's [Clojure Do's Series](http://stuartsierra.com/2015/05/27/clojure-uncaught-exceptions)

## License

Copyright © 2015 Pierre-Yves Ritschard <pyr@spootnik.org>.
Distributed under the ISC License, see LICENSE file.
