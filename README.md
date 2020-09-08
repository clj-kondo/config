# Clj-kondo.configs

This repo contains configurations and/or recommendations that can be used with
[clj-kondo](https://github.com/borkdude/clj-kondo/).

## Installation

This repo has an **experimental** main entrypoint that copies library specific config and hooks to your `.clj-kondo`. E.g.:

``` shell
clojure -Sdeps '{:deps {<coordinates>}}' -m clj-kondo.configs --lib rum --lib slingshot
```

where `<coordinates>` is `clj-kondo/configs {:git/url "https://github.com/clj-kondo/clj-kondo.configs" :sha "37dac4721754ffc1f390b1baf194a1dbbceabf28"}`

This copies Rum and Slingshot config into `.clj-kondo/configs/rum` and `.clj-kondo/configs/slingshot` respectively.

You can then add `"configs/rum"` and `"configs/slingshot"`  to your `:config-paths` in `config.edn` and Rum and Slinghot syntax will be recognized.

## License

Copyright © 2020 Michiel Borkent

Distributed under the EPL License, same as Clojure. See LICENSE.
