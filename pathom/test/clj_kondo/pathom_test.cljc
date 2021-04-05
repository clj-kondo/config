(ns clj-kondo.pathom-test
  (:require [com.wsscode.pathom.connect :as pc]
            [com.example.db :as my-database]))

;; unused binding env
(pc/defresolver person-resolver [{:keys [database] :as env} {:keys [person/id]}]
  {::pc/input #{:person/id}
   ::pc/output [:person/first-name :person/age]}
  (let [person (my-database/get-person database id)]
    {:person/age        (:age person)
     :person/first-name (:first-name person)}))

;; unused binding env
(pc/defmutation send-message [env {:keys [message/text]}]
  {::pc/sym    'send-message
   ::pc/params [:message/text]
   ::pc/output [:message/id :message/text]}
  {:message/id   123
   :message/text text})