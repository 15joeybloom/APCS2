(ns ninety-seven)

(def pascal-row

  (memoize
   (fn pascal-row
    [n]
    (if (<= n 1) [1]
      (let [decn (dec n)
            lastrow (pascal-row decn)]
        (loop [i 1
               returnMe (conj! (transient []) 1)]
          (if (= i decn)
            (persistent! (conj! returnMe 1))
            (recur (inc i) (conj! returnMe (+' (lastrow i) (lastrow (dec i))))))))))
   )

  )

(print (map pascal-row (range 1 100)))
