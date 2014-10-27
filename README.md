# generator

[![Build Status](https://travis-ci.org/shire-digest/generator.svg?branch=master)](https://travis-ci.org/shire-digest/generator)

## Generator Protocol

### Create a generator

```clojure
=> (require '[shire-digest.generators.simple :as simple])
nil
=> (def simple-generator (simple/create generators-options))
```

### Start generating

```clojure
=> (require '[shire-digest.generators.core :refer [create]])
nil
=> (create simple-generator posts)
```

## License

Copyright © 2014 hbc

Distributed under the [SMPPL](https://github.com/xhacker/SMPPL/blob/master/SMPPL-Freeware.md) License.
