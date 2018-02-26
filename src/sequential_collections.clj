; Vectors
[1 2 3]

;; Indexed access
(get ["abc" false 99] 0)
(get ["abc" false 99] 1)
(get ["abc" false 99] 14)

;; count
(count [1 2 3])

;; Constructing
(vector 1 2 3)

;; Adding elements
(conj [1 2 3] 4 5 6)

;; Immutability
(def v [1 2 3])
(conj v 4 5 6)
v

; Lists

;; Constructing
(def cards '(10 :ace :jack 9))
(first cards)
(rest cards)

;; Adding elements
(conj cards :queen)

;; Stack access
(def stack '(:a :b))
(peek stack)
(doc pop)
(pop stack)
(doc next)
(next stack)
(pop [1 2 3])
(next [1 2 3])