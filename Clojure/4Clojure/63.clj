 (ns sixty-three)


(def my-group-by

  (fn my-group-by
    [f ss]
;;     (if (empty? ss)
;;       {}
;;       (let [m (my-group-by f (rest ss))]
;;         (if (contains? m (f (first ss)))
;;           (-> m
;;               (get (f (first ss)))
;;               (conj (first ss)))
;;           (assoc m (f (first ss)) [(first ss)])))))
    (loop [m {}
           to-group ss]
      (if (empty? ss)
        m
        (let [f-s (f (first to-group))
              v (get m f-s)]
          (recur (assoc m f-s (conj (if v v []) (first to-group)))
                 (rest to-group))))))

  )
(my-group-by #(> % 5) [1 3 6 8])

