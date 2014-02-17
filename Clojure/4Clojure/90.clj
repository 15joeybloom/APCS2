(def cartesian-product

;; (fn cartesian-product
;;   [s1 s2]
;;   (set (for [x s1
;;              y s2]
;;          [x y])))
  #(set (for [x %1
              y %2]
          [x y]))

  )

(cartesian-product #{1 3 2} #{4 5})
(= #{1 2 3} #{1 3 2})
