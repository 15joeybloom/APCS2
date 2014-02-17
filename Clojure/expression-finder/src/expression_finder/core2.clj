(ns expression-finder.core
  (:gen-class))
(def ops {
     + #(str "(" %1 " - " %2 ")")
     - #(str "(" %1 " + " %2 ")")
     #(- %2 %1) #(str "(" %2 " - " %1 ")");;reversed subtraction
     * #(str "(" %1 " / " %2 ")")
     #(if (= (% %1 %2) 0) (/ %1 %2) Double/NaN) #(str "(" %1 " * " %2 ")")
     #(if (= (% %2 %1) 0) (/ %2 %1) Double/NaN) #(str "(" %2 " / " %1 ")")
     });;maps each operator to a function that prints it and its arguments out in infix notation
(defn rotate [seq]
  (concat (rest seq) (take 1 seq))
)
(defn firstNonNilElse
  "Returns the first item in seq whose value
  is not nil, or returns else if all of the items
  in seq are nil" [seq else]
  (cond
    (empty? seq)
      else
    (first seq);;if the first is not nil
      (first seq)
    :else
      (firstNonNil (rest seq)))
)
(defn findExpression [target nums rotations]
  (cond
   (= (count nums) 1) ;;base case
     (if (= (first nums) target)
       (str (first nums))
        nil
     )
   (= (count nums) rotations);;if rotated thru
     (firstNonNilElse
       (map
         (fn [op]
	   (let [a (findExpression (op target (first nums)) (rest nums) 0)]
	     (if (and a (not= Double/NaN a));;if a is not nil and not NaN
	       ((ops op) a (first nums))
	       nil
	     )
	   )
         ) ops
       )
       
     )
   :else
      ;;else if there are still numbers left to be used
      (let [a (findExpression (+ target (first nums)) (rest nums) 0)]
        (if a
          (str "(" a " - " (first nums) ")")
          (let [b (findExpression (- target (first nums)) (rest nums) 0)]
            (if b
              (str "(" b " + " (first nums) ")")
              (let [c (findExpression (- (first nums) target) (rest nums) 0)]
                (if c
                  (str "(" (first nums) " - " c ")")
                  (let [d (if (= (mod (first nums) target) 0) (findExpression (/ (first nums) target) (rest nums) 0) nil)]
                    (if d
                      (str "(" (first nums) " / " d ")")
                      (let [e (if (= (mod target (first nums)) 0) (findExpression (/ target (first nums)) (rest nums) 0) nil)]
                        (if e
                          (str "(" e " * " (first nums) ")")
                          (let [f (findExpression (* target (first nums)) (rest nums) 0)]
                            (if f
                              (str "(" f " / " (first nums) ")")
                              ;;else if no expression found yet, rotate the nums and recur
                              (findExpression target (rotate nums) (inc rotations))
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
  (findExpression target nums 0)
)
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Welcome to Expression Finder")
  (println (game 0 0))
  )
