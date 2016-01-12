(ns spootnik.uncaught-test
  (:import java.util.concurrent.ThreadPoolExecutor
           java.util.concurrent.ArrayBlockingQueue
           java.util.concurrent.TimeUnit)
  (:require [clojure.test      :refer :all]
            [spootnik.uncaught :refer :all]))

(deftest caught-test
  (let [queue  (ArrayBlockingQueue. 4)
        tpe    (ThreadPoolExecutor. 4 4 1 TimeUnit/MINUTES queue)
        p      (promise)
        errors (atom nil)]
    (testing "Catching uncaught exceptions"
      (uncaught e (swap! errors conj e))
      (.execute tpe (Thread. (fn [] (- nil 0))))
      (.execute tpe (Thread. (fn [] (- nil 0))))
      (.execute tpe (Thread. (fn [] (- nil 0))))
      (.execute tpe (Thread. (fn [] (deliver p nil))))
      (Thread/sleep 200)
      @p
      (is (= 3 (count @errors))))))

(deftest with-uncaught-test
  (let [queue  (ArrayBlockingQueue. 4)
        tpe    (ThreadPoolExecutor. 4 4 1 TimeUnit/MINUTES queue)
        p      (promise)
        errors (atom nil)]
    (testing "Catching uncaught exceptions"
      (with-uncaught e (swap! errors conj e))
      (.execute tpe (Thread. (fn [] (- nil 0))))
      (.execute tpe (Thread. (fn [] (- nil 0))))
      (.execute tpe (Thread. (fn [] (- nil 0))))
      (.execute tpe (Thread. (fn [] (deliver p nil))))
      (Thread/sleep 200)
      @p
      (is (= 3 (count @errors))))))

(deftest unset-handler-test
  (let [queue  (ArrayBlockingQueue. 4)
        tpe    (ThreadPoolExecutor. 4 4 1 TimeUnit/MINUTES queue)
        p      (promise)
        errors (atom nil)]
    (testing "Replace uncaught handler"
      (with-uncaught e (swap! errors conj e))
      (.execute tpe (Thread. (fn [] (- nil 0))))
      (Thread/sleep 200)
      (with-uncaught e)
      (.execute tpe (Thread. (fn [] (- nil 0))))
      (.execute tpe (Thread. (fn [] (- nil 0))))
      (.execute tpe (Thread. (fn [] (deliver p nil))))
      (Thread/sleep 200)
      @p
      (is (= 1 (count @errors))))))
