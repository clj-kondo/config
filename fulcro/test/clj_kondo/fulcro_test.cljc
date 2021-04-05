(ns clj-kondo.fulcro-test
  (:require
   #?(:cljs [com.fulcrologic.fulcro.dom :as dom]
      :clj  [com.fulcrologic.fulcro.dom-server :as dom])
   [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
   [com.fulcrologic.fulcro.mutations :refer [defmutation]]))

(defsc DestructuredExample [this
                            {:keys [db/id] :as props}
                            {:keys [onClick] :as computed :or {onClick identity}}]
  {:query         [:db/id]
   :initial-state {:db/id 22}}
  (dom/div
   (str "Component: " id)))

(defmutation mutation-example1
  "Here is a doc string"
  [params]
  (action [] params) ;; handler 'action' should be a fn of 1 arg
  (remote [env] true) ;; unused binding 'env'
  )

;; (defmutation mutation-example2
;;   "Here is a doc string"
;;   [params] ;; unused binding params
;;   (action [_env] nil)
;;   (remote [_env] true))

;; (defmutation mutation-example2 ;; error redefining var mutation-example2
;;   [params]
;;   (action [_env] nil)
;;   (remote [_env] true))