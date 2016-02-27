(ns mmf.mglair.pmlc)

;; The PMLc implementation should specify or retrieve modality specifications 
;; along with details on how to connect to the data-channel to PMLb.
;; The specifications are used to create and connect the modalities.
;; A ModalityConnector is assigned to each modality which monitors 
;; the receipt of impulses from PMLb for efferent modalities, and 
;; of sensory data from the SAL for afferent modalities.

;; Initially, the modality specifications will be hard-coded here
;; in the form of input values ot the create-modality function.

(defprotocol PmlcModality
  (handle-data [dat]))

;; This information to create these records be loaded from a 
;; configuration file, but for the meanwhile will be hard-coded.
(defrecord2 PmlcAfferentModality
  PmlcModality
  [name ""
   socket nil
   data-handler nil])

(defrecord2 PmlcEfferentModality
  PmlcModality
  [name ""
   socket nil
   data-handler nil])

;; a map of modality names against their PMLc objects.
(def pmlc-modalities (ref {}))

; called by every efferent modality’s data channel watcher when a new impulse
arrives
; self is an object on which direct action commands on the agent are called
; handle-impulse based on a case structure on the modality name

(defn impulse-handler
  ""
  [mod-name impulse]
  (case mod-name
   :default (println impulse)))



(defn create-pmlc-modality

;;Initialize.





  
