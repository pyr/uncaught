(ns spootnik.uncaught
  "Namespace holding a single convenience macro: uncaught.")

(defmacro uncaught
  "When an uncaught exception is thrown, execute `body' while
   binding `e' to the exception."
  [e & body]
  `(Thread/setDefaultUncaughtExceptionHandler
    (reify Thread$UncaughtExceptionHandler
      (uncaughtException [_ thread ~e]
        (do ~@body)))))
