(ns clj-kondo.rum-test
  (:require [rum.core :as rum]))

;;;; defc

(rum/defc label [text] ;; text is recognized as a binding
  [:div {:class "label"} text])

(rum/defc label ;; redefined var
  [text] ;; unused binding
  [:div {:class "label"} text']) ;; unresolved binding text'

(rum/defc with-docstring
  "docstring" ;; docstring is placed correctly
  < { :will-mount (fn [x] ;; mixin is parsed correctly
                    (assoc x ::time (js/Date.))) }
  [_text])

(rum/defc defc-multiple-bodies
  "docstring" ;; docstring is placed correctly
  < { :will-mount (fn [x] ;; mixin is parsed correctly
                    (assoc x ::time (js/Date.))) }
  ([foo] (if foo
           (defc-multiple-bodies foo nil)
           (defc-multiple-bodies))) ;; invalid arity
  ([foo bar] ;; bar is unused
   foo))

;;;; defcs

(rum/defcs time-label ;; defcs is linted as defc
  < { :will-mount (fn [x] ;; mixin is parsed correctly
                    (assoc x ::time (js/Date.))) }
  [state x] ;; unused
  [:div y ;; unresolved
   ": " (str (::time state))])

(rum/defcs SomeComponent <
  {:did-mount (fn [state] state)}
  [state input another]
  input ;; binding input is used, another is unused
  (let [x "Hello"] ;; binding is unused
    nil))

(SomeComponent "hello") ;; amount of args is invalid
(SomeComponent "hello" "there") ;; amount of args is correct

(rum/defcs ComponentWithDocString
  "docstring" ;; docstring is placed correctly
  < {:did-mount (fn [state] state)}
  [state input]
  input)

(rum/defcs defcs-multiple-bodies
  "docstring" ;; docstring is placed correctly
  < {:dir-mount (fn [state] state)}
  ([state foo]) ;; foo is unused, but state isn't
  ([state foo bar]))

(defcs-multiple-bodies) ;; invalid arity
(defcs-multiple-bodies "hello") ;; valid arity
