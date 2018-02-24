; Literals

;; Numeric types
42
6.022e23
42N
1.0M
22/7

;; Character types
"hello"
\e

;; Other types
nil
true
#"[0-9]+"
:alpha
:release/alpha
map
+
clojure.core/+

;; Collection types
`(1 2 3)
[1 2 3]
#{1 2 3}
{:a 1, :b 2}

; Evaluation

;; Delaying evaluation with quoting
'x
'(1 2 3)

; REPL

;; Exploring at the REPL
(+ 3 4)
(+ 10 *1)
(+ *1 *2)

(require '[clojure.repl :refer :all])
(doc +)
(doc doc)
(doc apropos)
(apropos "+")
(find-doc "trim")
(source dir)

(defn ns-docs [ns']
  (doseq [[symbol var] (ns-interns ns')]
    (newline)
    (println symbol)
    (print "  ")
    (println (:doc (meta var)))))
(ns-docs 'clojure.repl)

; Clojure basics

;; def
(def x 7)
(deref #'syntax/x)
(def y #'syntax/x)
(deref y)

;; Printing
(println "What is this:" (+ 1 2))
(prn "one\n\ttwo")

; Test your knowledge

;; Using the REPL, compute the sum of 7654 and 1234
(+ 7654 1234)

;; Rewrite the following algebraic expression as a Clojure expression: ( 7 + 3 * 4 + 5 ) / 10
(/ (+ 7 (* 3 4) 5) 10)

;; Using REPL documentation functions, find the documentation for the `rem` and `mod` functions. Compare the results of
;; the provided expressions based on the documentation
(doc rem)
(doc mod)
(rem -340 60)
(mod -340 60)
(rem (+ 60 (rem -340 60)) 60)
(- -340 (* 60 (quot -340 60)))

;; Using `find-doc`, find the function that prints the stack trace of the most recent REPL exception
(find-doc #"repl.*exception")
(not-a-function)
*e