# Clj-kondo config

This repo contains configurations and/or recommendations that can be used with
[clj-kondo](https://github.com/borkdude/clj-kondo/). Check the `resources` folder for available configs.

## Installation

This repo has an **experimental** main entrypoint that copies library specific config and hooks to your `.clj-kondo`. E.g.:

``` shell
clojure -A:clj-kondo/config --lib rum --lib slingshot
```

provided that you have an alias like this in your `deps.edn`:

``` 
:clj-kondo/config
  {:extra-deps {clj-kondo/config {:git/url "https://github.com/clj-kondo/config" :sha "ddf2ba45c7e78133b2808657ce601051d364bce5"}}
   :main-opts ["-m" "clj-kondo.config"]}
``` 

This copies Rum and Slingshot config into `.clj-kondo/configs/rum` and `.clj-kondo/configs/slingshot` respectively:

``` shell
$ clojure -A:clj-kondo/config --lib rum --lib slingshot
Removing previous rum config in .clj-kondo/configs/rum
Copying rum config to .clj-kondo/configs/rum
Copying slingshot config to .clj-kondo/configs/slingshot
Add "configs/rum", "configs/slingshot" to :config-paths in .clj-kondo/config.edn to activate configs.
```

You can then add `"configs/rum"` and `"configs/slingshot"`  to your `:config-paths` in `config.edn` and Rum and Slinghot syntax will be recognized.

## License

Copyright © 2020 Michiel Borkent

Distributed under the EPL License, same as Clojure. See LICENSE.
