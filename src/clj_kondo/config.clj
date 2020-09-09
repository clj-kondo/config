(ns clj-kondo.config
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.tools.cli :refer [parse-opts]]
            [cpath-clj.core :as cp]))

(def cli-options
  [["-l" "--lib LIBRARY" "Library"
    :default []
    :assoc-fn (fn [m k v]
                (update m k (fnil conj []) v))]])

(defn delete-files-recursively
  ([f1] (delete-files-recursively f1 false))
  ([f1 silently]
   (when (.isDirectory (io/file f1))
     (doseq [f2 (.listFiles (io/file f1))]
       (delete-files-recursively f2 silently)))
   (io/delete-file f1 silently)))

(defn -main [& args]
  (let [{:keys [:lib ]} (:options (parse-opts args cli-options))]
    (doseq [l lib]
      (when-let [resource (io/resource (str l "/.clj-kondo"))]
        (let [config-dir (io/file ".clj-kondo" "configs" l)]
          (when (.exists config-dir)
            (println "Removing previous" l "config in" (.getPath config-dir))
            (delete-files-recursively config-dir))
          (println "Copying" l "config to" (.getPath config-dir))
          (doseq [[path uris] (cp/resources resource)
                  :let [uri (first uris)
                        relative-path (subs path 1)
                        output-file (io/file config-dir relative-path)]]
            (io/make-parents output-file)
            (with-open [in (io/input-stream uri)]
              (io/copy in output-file))))))
    (println "Add" (str/join
                    ", "
                    (map (fn [lib]
                           (pr-str (.getPath (io/file "configs" lib))))
                         lib))
             "to :config-paths in .clj-kondo/config.edn to activate configs.")))
