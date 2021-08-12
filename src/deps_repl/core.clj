(ns deps-repl.core
  (:require
   [clojure.core.async :as a :refer [chan go go-loop <! >! <!! >!!  take! put! offer! poll! alt! alts! close! onto-chan!
                                     pub sub unsub mult tap untap mix admix unmix pipe
                                     timeout to-chan  sliding-buffer dropping-buffer
                                     pipeline pipeline-async]]
   [clojure.string]
   [clojure.spec.alpha :as s]
   [clojure.java.io :as io]))

(defn process
  [opts]
  (let [default-opts {:main-ns 'user
                      :repl? true
                      :host "0.0.0.0"
                      :port 7788}
        {:keys [main-ns host port repl?]} (merge default-opts opts)]

    (eval (macroexpand `(require '[~main-ns])))

    (when repl?
      (apply clojure.main/main ["-e" (str (macroexpand `(do (in-ns '~main-ns) nil)))
                                "-r"]))

    (<!! (chan))))