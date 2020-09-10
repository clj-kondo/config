(ns hooks.claypoole.upfor
  (:require [clj-kondo.hooks-api :as api]))

(defn upfor [{:keys [:node]}]
  (let [[pool binding-vec & body] (rest (:children node))
        bindings (:children binding-vec)]
    (try
      (when-not binding-vec
        (throw (ex-info "No bindings provided" (meta node))))
      (when-not (vector? (api/sexpr binding-vec))
        (throw (ex-info (str (api/sexpr (first (:children node)))
                             " requires vector for its bindings")
                        (meta binding-vec))))
      (when (zero? (count bindings))
        (api/reg-finding! (assoc (meta (first (:children node)))
                                 :message (str (api/sexpr (first (:children node)))
                                               " with no bindings")
                                 :type :claypoole)))
      (when-not (and binding-vec
                     (zero? (mod (count (api/sexpr binding-vec)) 2)))
        (throw (ex-info "Binding vector requires an even number of forms"
                        (meta binding-vec))))
      {:node
       (api/list-node
        (list
         (api/token-node 'for) binding-vec
         (api/list-node (list*
                         (api/token-node 'do) ;; body is wrapped in do because
                                              ;; for can only have one
                                              ;; expression in body
                         pool ;; pool is used
                         body))))}
      (catch Exception e
        (api/reg-finding! (assoc (ex-data e) :message (ex-message e) :type :hook))))))
