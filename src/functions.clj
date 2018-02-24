; Creating Functions

(defn greet [name] (str "Hello, " name))
(greet "students")

;; Multi-arity functions
(defn messenger
  ([] (messenger "Hello world!"))
  ([msg] (println msg)))
(messenger)
(messenger "Hello class!")

;; Variadic functions
(defn hello [greeting & who]
  (println greeting who))
(hello "Hello" "world" "class")

;; Anonymous functions
((fn [message] (println message)) "Hello world!")

;; defn vs fn
(def greet (fn [name] (str "Hello, " name)))

;; Anonymous function syntax
(#(+ 6 %) 3)
(#(+ %1 %2) 3 5)
(#(println %1 %2 %&) "Hello" "Mr." "Andrew" "Shaw" "Care")

;; Gotcha
(#([%]) 1)
(#(vector %) 1)
(vector 1)

; Applying functions

;; apply
(apply + '(1 2 3 4))
(apply + 1 '(2 3 4))
(apply + 1 2 '(3 4))
(apply + 1 2 3 '(4))

(defn plot [shape coords]
  (println shape (first coords) (second coords)))
(plot "Circle" '(0 0))

(defn plot [shape coords]
  (apply println shape coords)) ; This is an odd example, it's only because coords is a sequence that apply is useful
(plot "Circle" '(0 0))

; Locals and closures

;; let
(let [x 1 y 2]
  (+ x y))

(defn messenger [msg]
  (let [a 7
        b 5
        c (clojure.string/capitalize msg)]
    (println a b c)))
(messenger "andrew")

;; Closures
(defn messenger-builder [greeting]
  (fn [who] (println greeting who)))
(def hello-er (messenger-builder "Hello"))
(hello-er "world!")

; Java interop

;; Invoking Java code
(def file (java.io.File. "./functions.clj"))
(.toString file)
(java.io.File/listRoots)
(java.io.File/pathSeparator)

;; Java methods vs functions
(.length "Hello")
(#(.length %) "Hello")

; Test your knowledge

;; Define a function `greet` that takws not arguments and prints "Hello". Replace the _ with the implementation: `(defn greet [] _)
(defn greet [] (println "Hello"))

;; Redefine `greet` using `def`, first with the `fn` special form and then with the `#()` reader macro.
(def greet (fn [] (println "Hello")))
(def greet #(println "Hello"))
(greet)

;; Define a function `greeting` which:
;;
;; * Given no arguments, returns, "Hello, World!"
;; * Given one argument x, returns, "Hello, **x**!"
;; * Given two arguments x and y, returns, "x, y!"
(defn greeting
  ([] (println "Hello, World!"))
  ([x] (println "Hello," (str x "!")))
  ([x y] (println (str x ",") (str y "!"))))
(greeting)
(greeting "Andrew")
(greeting "Hello" "Andrew")

;; Define a function `do-nothing` which takes a single argument `x` and returns it, unchanged.
(defn do-nothing [x] x)
(source identity)

;; Define a function `always-thing` which takes any number of arguments, ignores all of them, and returns the keyword `:thing`
(defn always-thing [& xs] :thing)
(always-thing)
(always-thing 1)
(always-thing 1 2)
(always-thing 1 2 3)

;; Define a function `make-thingy` which takes a single argument `x`. It should return another function, which takes any
;; number of arguments and always returns `x`.
(defn make-thingy [x] (fn [& xs] x))
(let [n (rand-int Integer/MAX_VALUE)
      f (make-thingy n)]
  (assert (= n (f)))
  (assert (= n (f :foo)))
  (assert (= n (apply f :foo (range)))))
(source constantly)

;; Define a function `triplicate` which takes another function and calls it three times, without any arguments.
(defn triplicate [f] (f) (f) (f))
(triplicate #(println "Hello"))

;; Define a function `opposite` which takes a single argument `f`. It should return another function which takes any
;; number of arguments, applies `f` on them, and then calls `not` on the result. The `not` function in Clojure does
;; logical negation.
(defn opposite [f] #(not (apply f %&)))
((opposite (fn [& xs] false)) 1 2 3)

;; Define a function `triplicate2` which takes another function and any number of arguments, then calls that function
;; three times on those arguments. Re-use the function you defined in the earlier triplicate exercise.
(defn triplicate2 [f & args] (triplicate #(f args)))
(triplicate2 println "Hello" "Andrew")

;; Using the java.lang.Math class (`Math/pow`, `Math/cos`, `Math/sin`, Math/PI`), demonstrate the following mathematical
;; facts:
;;
;; * The cosine of PI is -1
;; * For some x, sin(x)^2 + cos(x)^2 = 1
(assert (= (java.lang.Math/cos java.lang.Math/PI) -1.0))
(let
  [x (rand-int Integer/MAX_VALUE)]
  (assert (= (+
               (java.lang.Math/pow (java.lang.Math/sin x) 2)
               (java.lang.Math/pow (java.lang.Math/cos x) 2))
             1.0)))

;; Define a function that takes an HTTP URL as a string, fetches that URL from the web, and returns the content as a string.
(defn http-get
  [url]
  (let [url-instance (java.net.URL. url)] (slurp (.openStream url-instance))))
(assert (.contains (http-get "http://www.w3.org") "html"))

;; In fact, the Clojure slurp function interprets its argument as a URL first before trying it as a file name. Write a
;; simplified http-get:
(defn http-get [url] (slurp url))
(assert (.contains (http-get "http://www.w3.org") "html"))

;; Define a function one-less-arg that takes two arguments: * f, a function * x, a value and returns another function
;; which calls f on x plus any additional arguments.
(defn one-less-arg [f x]
  (fn [& args] (apply f x args)))
((one-less-arg + 3) 5 8)
(source partial)
((partial + 3) 5 8)

;; Define a function two-fns which takes two functions as arguments, f and g. It returns another function which takes
;; one argument, calls g on it, then calls f on the result, and returns that. That is, your function returns the
;; composition of f and g.
(defn two-fns [f g]
  (fn [h] (f (g h))))
((two-fns (partial + 6) (partial * 3)) 4)