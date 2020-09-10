(ns example
  (:require [clojure.test :refer [is]]
            [mockery.core :as mockery :refer [with-mock with-mocks]]))

(with-mock _
  {:target ::test-fn
   :return (fn [& _] 100500)}
  (example/test-fn 10))

(with-mock _
  {:target :test-fn ;; :test-fn must be fully qualified
   :return (fn [& _] 100500)}
  (example/test-fn 10))

(with-mock _
  {:return (fn [& _] 100500)} ;; no target specified
  (example/test-fn 10))

(with-mocks
  [foo {:target ::test-fn}
   bar {:target :example/test-fn-2}]
  (example/test-fn 1)
  (example/test-fn-2 1 2)
  (is (= @foo
         {:called? true
          :call-count 1
          :call-args '(1)
          :call-args-list '[(1)]
          :target ::test-fn}))
  (is (= @bar
         {:called? true
          :call-count 1
          :call-args '(1 2)
          :call-args-list '[(1 2)]
          :target ::test-fn-2})))

;; what is this example supposed to do?
(with-mocks
  [foo {:target ::test-fn} ;; unresolved symbol foo?
   bar {}] ;; unresolved symbol bar?
  (example/test-fn 1)
  (example/test-fn-2 1 2)
  (is (= @foo
         {:called? true
          :call-count 1
          :call-args '(1)
          :call-args-list '[(1)]
          :target ::test-fn}))
  (is (= @bar
         {:called? true
          :call-count 1
          :call-args '(1 2)
          :call-args-list '[(1 2)]
          :target ::test-fn-2})))
