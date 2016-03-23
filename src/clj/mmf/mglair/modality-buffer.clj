;(require '[clojure.core.async :as async :refer [<! >! <!! >!! timeout chan alt! alts!! go put! take! take]])

(require 'clojure.core.async :as async :refer :all)

(import '[java.time Instant])

(defprotocol ModalityBuffer
  "Protocol for AfferentBuffer and EfferentBuffer."
  (make-buffer [this])
)


(defrecord2 AfferentBuffer
  [queue (ref (chan))
   exp-int -1
   conflict 'squeeze
   capacity 5]
   ModalityBuffer
   (make-buffer 
     [this] 
     (println "Making buffer...")))

(defrecord2 EfferentBuffer
  [queue (ref (chan))
   exp-int -1
   conflict 'blocking
   capacity 5]
   ModalityBuffer
   (make-buffer 
     [this] 
     (println "Making buffer...")))








(defn print-all-buffer-stats
"Prints the buffer status for all modalities"
  [])
