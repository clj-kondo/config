(ns example
  (:require [com.climate.claypoole :as cp]))

(def pool (cp/priority-threadpool 5))

(def ordered (cp/pfor pool [x (range 10)
                            y (range 10)]
               (example/myfn x y)))
