(ns sixty-three)


(def my-group-by

  (fn my-group-by
    [f ss]
    (loop [m {}
           to-group (seq ss)]
      (if (empty? to-group)
        m
        (let [f-s (f (first to-group))
              v (get m f-s)]
          (recur (assoc m f-s (conj (if v v []) (first to-group)))
                 (rest to-group))))))

  )
(-> (my-group-by #(> % 5) [1 3 6 8]) (= {false [1 3] true [6 8]}))

(time (my-group-by identity (range 400)))
(time (group-by identity (range 400)))

