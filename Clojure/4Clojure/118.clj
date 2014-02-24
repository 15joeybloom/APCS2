(def my-map

  (fn my-map
    [f s]
    (cons (f (first s))
      (if-let [ss (seq (rest s))]
        (lazy-seq (my-map f ss))
         nil)))

  )

(my-map inc '(1 2 3))
(my-map inc '()) ; => NullPointerException
