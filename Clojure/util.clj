(defn firstNonNil
  "Returns the first item in the sequence
  that is not nil, or returns nil if all
  items are nil"
  [seq]
  (cond (empty? seq)
          nil
        (first seq)
          (first seq)
        :firstIsNil
          (-> seq rest firstNonNil)))
(defn rotate
  "Returns the first element of the argument sequence
  appended to the rest"
  [seq]
  (concat (drop 1 seq) (take 1 seq)))
(defn rotateRest
  "Returns the second element of the argument sequence
   appended to the first and rest"
  [seq]
  (if (empty? seq)
    seq
    (conj (-> (rest seq) rotate) (first seq))))
