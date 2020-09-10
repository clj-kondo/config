(ns example
  (:require [com.climate.claypoole :as cp]))

(cp/upfor []) ;; no bindings provided

(def pool)

(cp/upfor pool []) ;; cp/upfor with no bindings

(cp/upfor pool [x (range 10)]) ;; unused binding x

(cp/upfor pool [x (range 10)
                :let [y (inc x)]] ;; let syntax is recognized
          y)

;; From the README of claypoole:

(defn myfn [_ _])

(def ordered
  (let [pool (cp/priority-threadpool 5)]
    (cp/pfor pool [x (range 10)
                   y (range x)]
             (myfn x y))))

(def unordered
  (let [pool ;; pool binding is used
        (cp/priority-threadpool 5)]
    (cp/upfor pool  [x (range 10)
                     :let [ys (range x)] ;; :let syntax is recognized
                     y ys]
              (myfn x y))))
