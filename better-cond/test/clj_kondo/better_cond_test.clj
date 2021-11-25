(ns example.core
   (:require [better-cond.core :as b]))

(defn fn-which-may-return-falsey
  [x]
  [x])

(defn fn-which-may-return-nil
  [x]
  (first x))

(def a 2)

(b/cond
  (odd? a) 1

  :let [a (quot a 2)]
  ; a has been rebound to the result of (quot a 2) for the remainder
  ; of this cond.

  :when-let [x (fn-which-may-return-falsey a),
	     y (fn-which-may-return-falsey (* 2 a))]
  ; this when-let binds x and y for the remainder of the cond and
  ; bails early with nil unless x and y are both truthy

  :when-some [b (fn-which-may-return-nil x),
	      c (fn-which-may-return-nil y)]
  ; this when-some binds b and c for the reaminder of the cond and

  ; bails early with nil unless b and c are both not nil

  :when (seq x)
  ; the above when bails early with nil unless (seq x) is truthy
  ; it could have been written as: (not (seq x)) nil

  :when-let [[u v] [nil nil]]

  :do (println [x y u v])
  ; A great way to put a side-effecting statement, like a println
  ; into the middle of a cond

  (odd? (+ b c)) 2

  3)
