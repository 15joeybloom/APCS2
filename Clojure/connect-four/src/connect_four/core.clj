(ns connect_four.core)

(defn empty-board []
  (vec (take 7 (repeat []))))

(empty-board)
