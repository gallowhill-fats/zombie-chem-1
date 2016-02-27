(defprotocol Modality 
  "abstract methods for an MGLAIR modality"
  (create-buffer [this] "create buffer")
  (set-buffer-capacity [this] "set buffer capacity")
  (set-mod-focus [this] "set modality focus")
  (create-mod-data-channel [this] "create modality data channel")
  (set-acting-status [this] "set efferent modality acting status to true or false")
  (is-acting [this] "is this efferent modality currently acting?")
  (create-sense-handler [this] "create sense handler for afferent-modality") 
  (delay-mod-running [this] "delay the running of modality if runmod is nil")
  (run-act-monitor [this] "start process that monitors acting status for efferent modality")
  (kill-act-monitor [this] "kill process that monitors acting status for efferent modality")
  (clear-register [this] "clear register for afferent or efferent modality")
  (print-modality [this] "print the modality properties"))


;see if we can get away with the protocol and the two modality records
;(defrecord2 Modality
;  "Superclass of AfferentModality and EfferentModality."
;  [])

(defrecord2 AfferentModality 
  [;; name, a unique identifier
   name ""
   ;; predicate/frame associated with the modality
   predicate nil
   ;; applied to the contents of the perceptual buffer
   sense-handler nil
   ;; reference to perceptual process
   pproc nil
   ;; agent has conscious access to modality (true or nil)
   access true
   ;; data channel process 
   dc-process nil
   ;; percept buffer
   afferent-buffer nil
   ;; focus level
   focus 5
   ;; focus modifiable? (true or nil)
   focus-modifiable true
   ;; sleep-inc: used internally to manipulate focus
   ;; not intended to be modified by the user.
   sleep-inc 1000
   ;; delays the running of modality if nil
   runmod true
   ;; register stores KL term for last act/percept
   ;; modified by perform, and by perceptual functions
   register nil
   ;; Human-readable description of modality
   description "Modality in which information flows to the knowledge layer
     as the result of some perception event."
   ;; data channel
   channel nil]
   Modality
)


(defrecord2 EfferentModality 
  [;; name, a unique identifier
   name ""
   ;; predicate/frame associated with the modality
   predicate nil
   ;; applied to the contents of the perceptual buffer
   sense-handler nil
   ;; reference to reference to the process that reads 
   ;; impulses from action buffer and adds them to its data channel
   bproc nil
   ;; agent has conscious access to modality (true or nil)
   access true
   ;; data channel process 
   dc-process nil
   ;; act buffer
   efferent-buffer nil
   ;; focus level
   focus 5
   ;; focus modifiable? (true or nil)
   focus-modifiable true
   ;; sleep-inc: used internally to manipulate focus
   ;; not intended to be modified by the user.
   sleep-inc 1000
   ;; delays the running of modality if nil
   runmod true
   ;; register stores KL term for last act/percept
   ;; modified by perform, and by perceptual functions
   register nil
   ;; Is the modality currently in use at the PMLc?
   acting false
   ;;
   act-monitor nil
   ;; 
   confirm-acting false
   ;; Human-readable description of modality
   description "Modality in which a perform function from the knowledge layer
     results in an external action."
   ;; data channel
   channel nil]
   Modality
)



;; original code has two methods, one taking a symbol representing a modality
;; and the other taking a modality
(defn set-sense-handler
  "Takes a modality mod and a function fn. Checks fn is a function, and, if so,
  sets this to the sense-handler slot of the modality. Otherwise, error message
  output."
  [mod fn]
  (if (fn? fn)
    (assoc mod :sense-handler fn) 
    (when warnings
      (cl-format "set-sense-handler called with a symbol that does not refer to a function: -S" fn))))







