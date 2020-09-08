(ns clj-kondo.configs
  (:require [clojure.java.io :as io]
            [clojure.tools.cli :refer [parse-opts]]
            [cpath-clj.core :as cp]))

(def cli-options
  [["-l" "--lib LIBRARY" "Library"
    :default []
    :assoc-fn (fn [m k v]
                (update m k (fnil conj []) v))]])

(defn -main [& args]
  (let [{:keys [:lib ]} (:options (parse-opts args cli-options))]
    (doseq [l lib]
      (when-let [resource (io/resource (str l "/.clj-kondo"))]
        (doseq [[path uris] (cp/resources resource)
                :let [uri (first uris)
                      relative-path (subs path 1)
                      output-file (io/file l relative-path)]]
          (io/make-parents output-file)
          (with-open [in (io/input-stream uri)]
            (io/copy in output-file)))))))
