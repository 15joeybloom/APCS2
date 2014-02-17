(defn f [] 1)
(f)
(defn rotate [seq]
  (concat (rest seq) (first seq))
  )
(rotate ('5 2 3))
(defn findExpression [list rotations]
  (let [target (first list)
        nums (rest list)]
    (if (or (empty nums) (= (count list) rotations));;base case
      (if (= 0 target)
        ""
        nil
      )
      ;;else if there are still numbers left to be used
      (let [a (findExpression (cons (+ (first nums) target) (rest nums) 0))]
        (if a
          (str "(" a " - " (first nums) ")")
          (let [b (findExpression (cons (- (first nums) target) (rest nums)) 0)]
            (if b
              (str "(" b " + " (first nums) ")")
              (let [c (findExpression (cons (- target (first nums)) (rest nums)) 0)]
                (if c
                  (str "(" (first nums) " / " c ")")
                  (let [d (if (= (mod (first nums) target) 0) (findExpression (cons (/ (first nums) target) (rest nums)) 0) nil)]
                    (if d
                      (str "(" d " * " (first nums) ")")
                      (let [e (if (= (mod target (first nums)) 0) (findExpression (cons (/ target (first nums)) (rest nums)) 0) nil)]
                        (if e
                          (str "(" (first nums) " / " e ")")
                          (let [f (findExpression (cons (* (first nums) target) (rest nums)) 0)]
                            (if f
                              (str "(" f " / " (first nums) ")")
                              ;;else if no expression found yet, rotate the nums and recur
                              (findExpression (cons target (rotate nums)) (inc rotations))
                            )
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
          )
        )
      )
    )
  )
)
(defn game [target & nums]
  (findExpression (cons target nums) 0)
)