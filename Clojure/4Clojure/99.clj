(def seq-digits

(fn seq-digits
  ([a b] (seq-digits (* a b)))
  ([n] (if (< n 10)
         [n]
         (conj (seq-digits (quot n 10)) (rem n 10)))))

  )

(quot 11 10)
(seq-digits 999 99)
