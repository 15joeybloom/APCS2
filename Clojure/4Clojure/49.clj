(def f

(fn my-split
  [i xx]
  (conj (empty xx) (vec (take i xx)) (vec (drop i xx))))

  )

(f 3 [1 2 3 4 5 6])
