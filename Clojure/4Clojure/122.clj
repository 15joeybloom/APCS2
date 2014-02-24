(ns one-twenty-two)

(def read-binary

  (fn read-binary
    [s]
    (apply + (map *
                  (map #(if (= % \0) 0 1) s)
                  (map #(rationalize (. Math pow 2 %)) (range (dec (count s)) -1 -1)))))

  )

;; (. Math pow 3 4)
;; (map #(rationalize (. Math pow 2 %)) (range (count "10101010101")))
(read-binary "1000")
(map * '(0) '(1N))
(map * '(1 0 0) '(1N 2N 4N))
