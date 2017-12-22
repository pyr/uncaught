(ns spootnik.uncaught
  "Namespace holding a single convenience macro: uncaught.")

(def ^:dynamic *thread*
  "The thread an exception occured on"
  nil)

(defmacro uncaught
  "When an uncaught exception is thrown, execute `body' while
   binding `e' to the exception."
  [e & body]
  `(Thread/setDefaultUncaughtExceptionHandler
    (reify Thread$UncaughtExceptionHandler
      (uncaughtException [_# thread# ~e]
        (binding [*thread* thread#]
          (do ~@body))))))

(defmacro with-uncaught
  "When an uncaught exception is thrown, execute `body' while
   binding `e' to the exception."
  [e & body]
  `(Thread/setDefaultUncaughtExceptionHandler
    (reify Thread$UncaughtExceptionHandler
      (uncaughtException [_# thread# ~e]
        (binding [*thread* thread#]
          (do ~@body))))))
