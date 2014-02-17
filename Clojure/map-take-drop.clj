(defn takeMap
  "Returns the first n entries
  in a map"
  [n map]
  (if (or (empty? map) (= n 0))
    {}
    (conj (takeMap (dec n) (rest map)) (first map))))
(defn dropMap
  "Returns a map less its first n
  entries"
  [n map]
  (if (or (empty? map) (<= n 0))
    (conj {} (reduce (fn ([] {})
                       ([a b] (conj a b)))
                     {} map))
    (dropMap (dec n) (rest map))))