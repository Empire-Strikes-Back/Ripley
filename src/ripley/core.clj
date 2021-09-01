(ns ripley.core
  (:require 
    [clojure.core.async :as a :refer [<! >! <!! >!! chan put! take! go alt! alts! do-alts close! timeout pipe mult tap untap 
                                      pub sub unsub mix admix unmix dropping-buffer sliding-buffer pipeline pipeline-async to-chan! thread]]
    [clojure.string]
    [clojure.java.io :as io))


(defn process
  [opts]
  (let [default-opts {:main-ns nil}
        {:keys [main-ns]} (merge default-opts opts)]

  (requiring-resolve 'main-ns)
  
  (apply clojure.main/main ["--eval" (format "(in-ns '%s)" main-ns)
                            "--repl"])
  )
)