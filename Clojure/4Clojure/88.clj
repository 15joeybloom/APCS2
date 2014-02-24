(fn symmetric-difference
  [s1 s2]
  (set (filter #(not= (contains? s1 %) (contains? s2 %)) (clojure.set/union s1 s2))))
