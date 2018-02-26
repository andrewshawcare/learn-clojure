; Statements vs. Expressions

; Flow Control Expressions

;; if
(str "2 is " (if (even? 2) "even" "odd"))
(if (true? false) "impossible!")

;; Truth
(if true :truthy :falsey)
(if (Object.) :truthy :falsey)
(if [] :truthy :falsey)
(if 0 :truthy :falsey)
(if false :truthy :falsey)
(if nil :truthy :falsey)

;; if and do
(if (even? 5)
  (do (println "even") true)
  (do (println "odd") false))

;; when
(def x -1)
(when (neg? x)
  (throw (RuntimeException. (str "x must be positive: " x))))

;; cond
(let [x 5]
  (cond
    (< x 2) "x is less than 2"
    (< x 10) "x is less than 10"))

;; cond and else
(let [x 11]
  (cond
    (< x 2) "x is less than 2"
    (< x 10) "x is less than 10")
    :else "x is greater than or equal to 10")

;; case
(defn foo [x]
  (case x
    5 "x is 5"
    10 "x is 10"))
(foo 10)
(foo 11)

;; case with else-expression
(defn foo [x]
  (case x
    5 "x is 5"
    10 "x is 10"
    "x isn't 5 or 10"))
(foo 11)

; Iteration for Side Effects

;; dotimes
(dotimes [i 3]
  (println i))

;; doseq
(doseq [n (range 3)]
  (println n))

;; doseq with multiple bindings
(doseq [letter [:a :b]
        number (range 3)]
  (prn [letter number]))

; Clojure's for
(for [letter [:a :b]
      number (range 3)]
  [letter number])

; Recursion

;; Recursion and Iteration

;; loop and recur
(loop [i 0]
  (if (< i 10)
    (recur (inc i))
    i))

;; defn and recur
(defn increase [i]
  (if (< i 10)
    (recur (inc i))
    i))
(increase 0)

;; recur for recursion

; Exceptions

;; Exception handling
(try
  (/ 2 1)
  (catch ArithmeticException _ "divide by 0")
  (finally (println "cleanup")))
(try
  (/ 2 0)
  (catch ArithmeticException _ "divide by 0")
  (finally (println "cleanup")))

;; Throwing exceptions
(try
  (throw (Exception. "something went wrong"))
  (catch Exception e (.getMessage e)))

;; Exceptions with Clojure data
(try
  (throw (ex-info "There was a problem" {:detail 42}))
  (catch Exception e
    (prn (:detail (ex-data e)))))

;; with-open
(let [f (clojure.java.io/writer "/tmp/new")]
  (try
    (.write f "some text")
    (finally
      (.close f))))

(with-open [f (clojure.java.io/writer "/tmp/new")]
  (.write f "some text"))