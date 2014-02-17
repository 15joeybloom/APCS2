(import 'java.lang.Math)
;(def a (. Integer parseInt (read-line)))
;(def b (. Integer parseInt (read-line)))
;(def c (. Integer parseInt (read-line)))

(def a 14)
(def b 12)
(def c 10)

(def discriminant (- (* b b) ( * 4 a c ))
(cond
  (< discriminant 0)
	(println "No solution")
  (= discriminant 0)
	(println (/ (+ (-' b) (. Math sqrt discriminant)) (* 2 a)))
  :else
  	(println (str (/ (+ (-' b) (. Math sqrt discriminant)) (* 2 a)) "\n"
    			  (/ (- (-' b) (. Math sqrt discriminant)) (* 2 a))))))
