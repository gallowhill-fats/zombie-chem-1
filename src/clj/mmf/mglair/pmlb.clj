(ns mmf.mglair.pmlb)

;; Bona "Much of the action at the PMLb is built-in functionality for
;; using modalities. This includes functions for handling 
;; the data channel that connects the PMLb to the PMLc, 
;; modality buffer management, implementation
;; of the focus mechanisms."

;; In efferent modalities, PMLb command functions like 
;; mmf.mglair.pmlb/command translate their arguments into 
;; impulses that the PMLc will understand, then passes them 
;; to mmf.mglair.pmlb/execute.

;(defun turn (mod dir)
;(execute mod (format nil "~A" (cons ’tn (if (eq dir ’l) -90 90)))))

(defn text-out
  ""
  [mod-name txt]
  (execute mod-name txt))

;(defun range-sense-handler (v)
;(PMLs:perceive-distance (read-from-string (rest v))))

(defn query-term-sense-handler
  ""
  [query-term-buffer-item]
  (mmf.mglair.pmls/perceive-know-about-query-term (:item query-term-buffer-item))
