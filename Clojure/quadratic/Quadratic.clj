(import 'java.lang.System)
(def a (. Integer parseInt (read-line)))
(def b (. Integer parseInt (read-line)))
(def c (. Integer parseInt (read-line)))

(def discriminant (- (* b b) ( * 4 a c )))
;(cond
;  (< discriminant 0)
;	(println "No solution")
;  (= discriminant 0)
;	(println (/ (+ (-' b) (Math.sqrt discriminant)) (* 2 a)))
;  :else
  	(println (str (/ (+ (-' b) (Math.sqrt discriminant)) (* 2 a)) "\n"
    			  (/ (- (-' b) (Math.sqrt discriminant)) (* 2 a))));)