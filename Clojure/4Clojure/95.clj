(def tree?

  (fn tree?
    [[data left right :as s]
     ]
    (or (nil? s)
        (and (coll? s)
             (= (count s) 3)
             (tree? left)
             (tree? right))))

  )

(let [[data left right :as s] nil]
;;   (list data left right shouldbeempty)
  s)
