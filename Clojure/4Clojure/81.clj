(ns eighty-one)
(def my-intersection

(fn
  [s1 s2]
  (clojure.set/select #(and (contains? s1 %) (contains? s2 %)) (clojure.set/union s1 s2)))

  )

(clojure.set/union #{1 2 3} #{2 3 4})
(my-intersection #{0 1 2 3} #{2 3 4 5})
