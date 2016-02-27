;; modality-data-channel

;; The implementation here differs from that in the Bona thesis. 
;; Bona uses the Allegro CL socket module which creates a permanent 
;; "socket" object which is used to create readers and writers.
;; The underlying mechanism is a ServerSocket which blocks on
;; calling an accept method and then returns a new socket on receiving 
;; a request on the ServerSocket port.
;; The socket to use in the data-channel here therefore would seem to be the
;; ServerSocket. The stream is created by (io/reader socket) and
;; (io/writer socket) which have no permanent significance because
;; the socket is newly created by the ServerSocket for each write and read.

(import '(java.net ServerSocket Socket SocketException)
        '(java.io InputStreamReader OutputStreamWriter)
        '(clojure.lang LineNumberingPushbackReader))

(defrecord2 ModalityDataChannel
  ""
  [socket nil
   channel-stream nil])

(defprotocol ConnectionStatusQuery
  "Is a modality connected/active? Modality can be identified by 
   modality object, modality name or modality data channel."
  (connected? [modality-identifier]
  (active? [modality-identifier])

(extend-protocol ConnectionStatusQuery

  Modality

  Symbol

  Modality-Data-Channel)


(defn attach-channel
  "Given a modality data channel that is not connected, associate it with a modality."(import '(java.net ServerSocket Socket SocketException)
        '(java.io InputStreamReader OutputStreamWriter)
        '(clojure.lang LineNumberingPushbackReader))
  [mod modDataChannel]
  (when-not (connected? mod)
          (assoc (:channel mod) modDataChannel)))


(defn add-data-channel
  "Add data channel to an efferent modality."
  [eff-mod msg]
  (let [tsmsg (cons (get-internal-real-time) msg)]  ; make timestamp message
  (if (conflict (:buffer eff-mod))
      (do
        (case conflict
              "blocking"  (if (and (confirm-acting emod) (acting emod))
                              (when *warnings* (cl-format true "Modality ~S is busy, discarding impulse ~S" (:name eff-mod) msg))
                              (dc-send eff-mod tsmsg)))
      (if (:acting eff-mod)
        (do 
          (kill-act-monitor emod) ; kill act monitor
          (dc-send eff-mod (:acting eff-mod))
          (assoc (:acting eff-mod) nil)) ; clear acting
        (dc-send eff-mod tsmsg) )))))


(defn dc-send 
  "Send a message to eff-mod’s data channel"
  [eff-mod msg]
  (when (confirm-acting eff-mod)
    (run-act-monitor eff-mod msg))
    (cl-format (socket (:channel eff-mod)) "~S" msg)
    (force-output (socket (:channel eff-mod)))
    msg)


(defn run-dc-reader
  "Read incoming data out of the data channel
  and add to this modality’s buffer"
  [aff-mod]
  (assoc aff-mod :dc-process 
    (. (Thread. dc-process-fn) setName (clojure.string/join ((:name aff-mod) "-channel-reader") ) ))

(defn receive
  "Read a line of textual data from the given socket"
  [socket]
  (.readLine (io/reader socket)))

(defn dc-process-fn
  ""
  [port]
  (let [ss (new ServerSocket port)] 
    (on-thread create-server  )))


(defn create-server 
  ""
  [port]
  (let [ss (new ServerSocket port)
        accept-socket (gensym)]
    (with-open [accept-socket (. ss (accept)]
      (let [msg-in (receive accept-socket)]
      (data-channel-add msg-in))))

(defn create-server 
  ""
  [port]
  (let [ss (new ServerSocket port)
        accept-socket (gensym)]
    (with-open [accept-socket (. ss (accept))]
      (let [msg-in (receive accept-socket)]
      (println msg-in)))))
(def status (atom 0))
(defn create-server []
      (let [server-sock (ServerSocket. 33333)]
        (while true
          (let [sock (.accept server-sock)
                msg-in (receive sock)]
              (reset! status msg-in)))))
  
  
(doto 
   (Thread. create-server)
   (.setDaemon true)
   (.start))
(defn create-server [port handler]
  (let [running (atom true)]
    (future
      (with-open [server-sock (ServerSocket. port)]
        (while @running
          (with-open [sock (.accept server-sock)]
            (let [msg-in (receive sock)
                  msg-out (handler msg-in)]
              (send sock msg-out))))))
    running))

(defn create-server [port]
  (let [running (atom true)]
    (future
      (with-open [server-sock (ServerSocket. port)]
        (while @running
          (with-open [sock (.accept server-sock)]
            (let [msg-in (receive sock)]
              (reset! status msg-in)))))))
    running))

(def status (atom 0))
(defn create-server []
      (let [server-sock (ServerSocket. 33333)]
        (while true
          (let [sock (.accept server-sock)
                msg-in (receive sock)]
              (reset! status (.toString msg-in))))))
  
  
(doto 
   (Thread. create-server)
   (.setDaemon true)
   (.start))
  

(defn forever []
  ;; do stuff in a loop forever
)

(.start (Thread. create-server))

(doto 
   (Thread. create-server)
   (.setDaemon true)
   (.start))




(defmethod run-dc-reader ((amod modality))
;;; the channel reader reads incoming data out of the data channel
;;; and adds it to this modality’s buffer
  (setf (dc-process amod)
        (mp:process-run-function
          (concatenate ’string (symbol-name (name amod) ) "-channel-reader")
          (lambda ()
            (let ((sock (socket::accept-connection (socket (channel amod)))))
                 (setf (socket (channel amod)) sock)
            (cl-user ::while t
              (funcall #’add-to-buffer (buffer amod)
                (string-trim ’(#\newline #\return #\null)
                (read-line sock)))))))))

            





  
