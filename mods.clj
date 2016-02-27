(ns mmf.mglair.mod)

(def MODALITIES (ref {}))

(def warnings (atom true))

;(load "mglair/modality/modality-classes") ; defclass, methods for modality classes
;(load "mglair/modality/modality-data-channel") ; defclass, methods for data channels
;(load "mglair/modality/modality-focus") ; functions for modality focus
;(load "mglair/modality/modality-buffer") ; classes, methods for modality buffers
;(load "mglair/modality/modality-timing.cl") ; functions for modality buffer timing







(defun list-modalities
  "print a list of all modalities"
  [mod]
)

(defun run-all-modalities
  "Run all defined modalities."
  [])

(defn get-modality 
  "given a symbol naming a modality, return the modality object"
  [name])

(defn run-modality
  "start the internal processes for an afferent modality"
  [amod modality])

(defn run-modality ((emod efferent-modality))
  "start the internal processes for an efferent modality"
  [emod modality] )

(defn set-sense-process 
  "kill the sense handler currently monitoring amod and run fn in a new process"
  [afferent-modality fn])

(defn get-modalities-by-type 
  "Return a list of modalities whose type is mtype"
  [mtype])

(defun act-modality
  "given the symbol that names a sneps act node,
  return the modality with which it’s associated"
  [aname])

(defun primact-modality
  "given the symbol that names a primitive action,
  get the modality with which it’s associated"
 [pa-name])

(defmethod run-sense-handler 
  "run an afferent modality’s sense handler"
 [amod afferent-modality])

(defn execute ; &rest args)
  "execute impulse in the efferent modality with name mname"
 [(mname symbol) impulse])

(defmethod execute
  ""
  [mod efferent-modality])












