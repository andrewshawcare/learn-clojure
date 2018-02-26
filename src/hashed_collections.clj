; Sets
(def players #{"Alice", "Bob", "Kelly"})

;; Adding to a set
(conj players "Fred")

;; Removing from a set
(disj players "Bob" "Sal")

;; Checking containment
(contains? players "Kelly")

;; Sorted sets
(conj (sorted-set) "Bravo" "Charlie" "Sigma" "Alpha")

;; into
(def new-players ["Tim" "Sue" "Greg"])
(into players new-players)

; Maps

;; Creating a literal map
(def scores {"Fred" 1400
             "Bob" 1240
             "Angela" 1024})
(def scores {"Fred" 1400, "Bob" 1240, "Angela" 1024})


;; Adding new key-value pairs
(assoc scores "Sally" 0)
(assoc scores "Bob" 0)

;; Removing key-value pairs
(dissoc scores "Bob")

;; Looking up by key
(get scores "Angela")
(def directions {:north 0
                 :east 1
                 :sourth 2
                 :west 3})
(directions :north)
(def bad-lookup-map nil)
(bad-lookup-map :foo)

;; Looking up with a default
(get scores "Sam" 0)
(directions :northwest -1)
(bad-lookup-map :foo 0) ; Still throws a NullPointerException

;; Checking contains
(contains? scores "Fred")
(find scores "Fred")

;; Keys or values
(keys scores)
(vals scores)

;; Building a map
(def players #{"Alice" "Bob" "Kelly"})
(zipmap players (repeat 0))
(into {} (map #(vector % 0) players))
(reduce #(assoc %1 %2 0) {} players)

;; Combining maps
(def new-scores {"Angela" 300 "Jeff" 900})
(merge scores new-scores)
(def new-scores {"Fred" 550 "Angela" 300 "Sam" 1000})
(merge-with + scores new-scores)

;; Sorted maps
(def sm (sorted-map
          "Bravo" 204
          "Alfa" 35
          "Sigma" 99
          "Charlie" 100))
(keys sm)
(vals sm)

; Representing application domain information
(def person
  {:first-name "Kelly"
   :last-name "Keen"
   :age 32
   :occupation "Programmer"})

;; Field accessors
(get person :occupation)
(person :occupation)
(:occupation person)
(:favorite-color person "beige")

;; Updating fields
(assoc person :occupation "Baker")

;; Removing a field
(dissoc person :age)

;; Nested entities
(def company
  {:name "WidgetCo"
   :address {:street "123 Main Street"
             :city "Springfield"
             :state "IL"}})
(get-in company [:address :city])
(assoc-in company [:address :street] "303 Broadway")

;; Records
(defrecord Person [first-name last-name age occupation])
(def kelly (->Person "Kelly" "Keen" 32 "Programmer"))
(def kelly (map->Person {:first-name "Kelly"
                         :last-name "Keen"
                         :age 32
                         :occupation "Programmer"}))
(:occupation kelly)