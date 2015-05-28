uncaught: handle uncaught exceptions with ease
==============================================

[![Build Status](https://secure.travis-ci.org/pyr/uncaught.png)](http://travis-ci.org/pyr/uncaught)

Uncaught is a single macro namespace to provide
a convenience macro for handling uncaught exceptions.

## Installation

Add the following depenency to your `project.clj` file:

```clojure
[org.spootnik/uncaught "0.5.1"]
```

Up to date instructions and pointers to other build environement are available on [clojars](https://clojars.org/org.spootnik/uncaught)

## Documentation

`(uncaught [e & body])`: When an uncaught exception is thrown, execute `body` while binding `e` to the exception.

## Example

```clojure
(uncaught e
  (warn e "uncaught exception"))
```

## Notes

Inspired by Stuart Sierra's [Clojure Do's Series](http://stuartsierra.com/2015/05/27/clojure-uncaught-exceptions)

## License

Copyright © 2015 Pierre-Yves Ritschard <pyr@spootnik.org>.
Distributed under the ISC License, see LICENSE file.
