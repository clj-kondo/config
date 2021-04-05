(ns clj-kondo.fulcro
  (:require [clj-kondo.hooks-api :as api]))

(defn defmutation
  [{:keys [node]}]
  (let [args          (rest (:children node))
        mutation-name (first args)
        ?docstring    (when (string? (api/sexpr (second args)))
                        (second args))
        args          (if ?docstring
                        (nnext args)
                        (next args))
        params        (first args)
        handlers      (rest args)
        handler-syms  (map (comp first :children) handlers)
        bogus-usage   (api/vector-node (vec handler-syms))
        letfn-node    (api/list-node
                       (list
                        (api/token-node 'letfn)
                        (api/vector-node (vec handlers))
                        bogus-usage))
        new-node      (api/list-node
                       (list
                        (api/token-node 'defn)
                        mutation-name
                        (when ?docstring ?docstring)
                        params
                        letfn-node))]
    (doseq [handler handlers]
      (let [hname (some-> handler :children first api/sexpr str)
            argv  (some-> handler :children second)]
        (when-not (= 1 (count (api/sexpr argv)))
          (api/reg-finding! (merge
                             (meta argv)
                             {:message (format "defmutation handler '%s' should be a fn of 1 arg" hname)
                              :type    :clj-kondo.fulcro.defmutation/handler-arity})))))
    {:node new-node}))