;;; Copyright (c) Brian P. Clark. All rights reserved.
;;; The use and distribution terms for this software are covered by the
;;; Apache License 2,0 (http://opensource.org/licenses/Apache-2.0)
;;; which can be found in the file al-v2.html at the root of this distribution.
;;; By using this software in any fashion, you are agreeing to be bound by
;;; the terms of this license.
;;; You must not remove this notice, or any other, from this software.
;;;
;;; Based on original Lisp code by J.P. Bona taken from dissertation:
;;; MGLAIR: A Multimodal Cognitive Agent Architecture, (2013),
;;; Department of Computer Science and Engineering,
;;; State University of New York at Buffalo.

(ns mmf.mglair.modality)

(defrecord Modality
  [;; name, a unique identifier
   name
   ;; predicate/frame associated with the modality
   predicate
   ;; agent has conscious access to modality (true or nil)
   access  
   ;; data channel process 
   dc-process
   ;; percept/act buffer
   buffer
   ;; focus level
   focus
   ;; focus modifiable? (true or nil)
   focus-modifiable
   ;; sleep-inc: used internally to manipulate focus
   ;; not intended to be modified by the user.
   sleep-inc
   ;; delays the running of modality if nil
   runmod
   ;; register stores KL term for last act/percept
   ;; modified by perform, and by perceptual functions
   register
   ;; Human-readable description of modality
   description
   ;; data channel
   channel]
)

(defrecord AfferentModality 
  [;; name, a unique identifier
   name
   ;; predicate/frame associated with the modality
   predicate
   ;; applied to the contents of the perceptual buffer
   sense-handler
   ;; reference to perceptual process
   pproc
   ;; agent has conscious access to modality (true or nil)
   access  
   ;; data channel process 
   dc-process
   ;; percept buffer
   afferent-buffer
   ;; focus level
   focus
   ;; focus modifiable? (true or nil)
   focus-modifiable
   ;; sleep-inc: used internally to manipulate focus
   ;; not intended to be modified by the user.
   sleep-inc
   ;; delays the running of modality if nil
   runmod
   ;; register stores KL term for last act/percept
   ;; modified by perform, and by perceptual functions
   register
   ;; Human-readable description of modality
   description
   ;; data channel
   channel]
)

(defrecord EfferentModality 
  [;; name, a unique identifier
   name
   ;; predicate/frame associated with the modality
   predicate
   ;; applied to the contents of the perceptual buffer
   sense-handler
   ;; reference to reference to the process that reads 
   ;; impulses from action buffer and adds them to its data channel
   bproc
   ;; agent has conscious access to modality (true or nil)
   access  
   ;; data channel process 
   dc-process
   ;; act buffer
   efferent-buffer
   ;; focus level
   focus
   ;; focus modifiable? (true or nil)
   focus-modifiable
   ;; sleep-inc: used internally to manipulate focus
   ;; not intended to be modified by the user.
   sleep-inc
   ;; delays the running of modality if nil
   runmod
   ;; register stores KL term for last act/percept
   ;; modified by perform, and by perceptual functions
   register
   ;; Human-readable description of modality
   description
   ;; data channel
   channel]
)








